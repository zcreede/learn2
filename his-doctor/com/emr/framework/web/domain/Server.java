package com.emr.framework.web.domain;

import com.emr.common.utils.Arith;
import com.emr.common.utils.ip.IpUtils;
import com.emr.framework.web.domain.server.Cpu;
import com.emr.framework.web.domain.server.Jvm;
import com.emr.framework.web.domain.server.Mem;
import com.emr.framework.web.domain.server.Sys;
import com.emr.framework.web.domain.server.SysFile;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.CentralProcessor.TickType;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;

public class Server {
   private static final int OSHI_WAIT_SECOND = 1000;
   private Cpu cpu = new Cpu();
   private Mem mem = new Mem();
   private Jvm jvm = new Jvm();
   private Sys sys = new Sys();
   private List sysFiles = new LinkedList();

   public Cpu getCpu() {
      return this.cpu;
   }

   public void setCpu(Cpu cpu) {
      this.cpu = cpu;
   }

   public Mem getMem() {
      return this.mem;
   }

   public void setMem(Mem mem) {
      this.mem = mem;
   }

   public Jvm getJvm() {
      return this.jvm;
   }

   public void setJvm(Jvm jvm) {
      this.jvm = jvm;
   }

   public Sys getSys() {
      return this.sys;
   }

   public void setSys(Sys sys) {
      this.sys = sys;
   }

   public List getSysFiles() {
      return this.sysFiles;
   }

   public void setSysFiles(List sysFiles) {
      this.sysFiles = sysFiles;
   }

   public void copyTo() throws Exception {
      SystemInfo si = new SystemInfo();
      HardwareAbstractionLayer hal = si.getHardware();
      this.setCpuInfo(hal.getProcessor());
      this.setMemInfo(hal.getMemory());
      this.setSysInfo();
      this.setJvmInfo();
      this.setSysFiles(si.getOperatingSystem());
   }

   private void setCpuInfo(CentralProcessor processor) {
      long[] prevTicks = processor.getSystemCpuLoadTicks();
      Util.sleep(1000L);
      long[] ticks = processor.getSystemCpuLoadTicks();
      long nice = ticks[TickType.NICE.getIndex()] - prevTicks[TickType.NICE.getIndex()];
      long irq = ticks[TickType.IRQ.getIndex()] - prevTicks[TickType.IRQ.getIndex()];
      long softirq = ticks[TickType.SOFTIRQ.getIndex()] - prevTicks[TickType.SOFTIRQ.getIndex()];
      long steal = ticks[TickType.STEAL.getIndex()] - prevTicks[TickType.STEAL.getIndex()];
      long cSys = ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()];
      long user = ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()];
      long iowait = ticks[TickType.IOWAIT.getIndex()] - prevTicks[TickType.IOWAIT.getIndex()];
      long idle = ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];
      long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
      this.cpu.setCpuNum(processor.getLogicalProcessorCount());
      this.cpu.setTotal((double)totalCpu);
      this.cpu.setSys((double)cSys);
      this.cpu.setUsed((double)user);
      this.cpu.setWait((double)iowait);
      this.cpu.setFree((double)idle);
   }

   private void setMemInfo(GlobalMemory memory) {
      this.mem.setTotal(memory.getTotal());
      this.mem.setUsed(memory.getTotal() - memory.getAvailable());
      this.mem.setFree(memory.getAvailable());
   }

   private void setSysInfo() {
      Properties props = System.getProperties();
      this.sys.setComputerName(IpUtils.getHostName());
      this.sys.setComputerIp(IpUtils.getHostIp());
      this.sys.setOsName(props.getProperty("os.name"));
      this.sys.setOsArch(props.getProperty("os.arch"));
      this.sys.setUserDir(props.getProperty("user.dir"));
   }

   private void setJvmInfo() throws UnknownHostException {
      Properties props = System.getProperties();
      this.jvm.setTotal((double)Runtime.getRuntime().totalMemory());
      this.jvm.setMax((double)Runtime.getRuntime().maxMemory());
      this.jvm.setFree((double)Runtime.getRuntime().freeMemory());
      this.jvm.setVersion(props.getProperty("java.version"));
      this.jvm.setHome(props.getProperty("java.home"));
   }

   private void setSysFiles(OperatingSystem os) {
      FileSystem fileSystem = os.getFileSystem();

      for(OSFileStore fs : fileSystem.getFileStores()) {
         long free = fs.getUsableSpace();
         long total = fs.getTotalSpace();
         long used = total - free;
         SysFile sysFile = new SysFile();
         sysFile.setDirName(fs.getMount());
         sysFile.setSysTypeName(fs.getType());
         sysFile.setTypeName(fs.getName());
         sysFile.setTotal(this.convertFileSize(total));
         sysFile.setFree(this.convertFileSize(free));
         sysFile.setUsed(this.convertFileSize(used));
         sysFile.setUsage(Arith.mul(Arith.div((double)used, (double)total, 4), (double)100.0F));
         this.sysFiles.add(sysFile);
      }

   }

   public String convertFileSize(long size) {
      long kb = 1024L;
      long mb = kb * 1024L;
      long gb = mb * 1024L;
      if (size >= gb) {
         return String.format("%.1f GB", (float)size / (float)gb);
      } else if (size >= mb) {
         float f = (float)size / (float)mb;
         return String.format(f > 100.0F ? "%.0f MB" : "%.1f MB", f);
      } else if (size >= kb) {
         float f = (float)size / (float)kb;
         return String.format(f > 100.0F ? "%.0f KB" : "%.1f KB", f);
      } else {
         return String.format("%d B", size);
      }
   }
}
