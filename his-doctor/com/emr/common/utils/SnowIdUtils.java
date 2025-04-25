package com.emr.common.utils;

import java.io.InputStream;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.scanner.Constant;

public class SnowIdUtils {
   public static long uniqueLong() {
      long id = SnowIdUtils.SnowflakeIdWorker.SNOW_FLAKE.nextId();
      return id;
   }

   public static String uniqueLongHex() {
      long uniqueLong = uniqueLong();
      System.out.println(uniqueLong);
      System.out.println(String.format("%016x", uniqueLong));
      return String.format("%016x", uniqueLong);
   }

   public static void main(String[] args) throws InterruptedException {
      for(int i = 0; i < 100; ++i) {
         long snowID = uniqueLong();
         System.out.println(snowID);
      }

   }

   private static class SnowflakeIdWorker {
      private static final SnowflakeIdWorker SNOW_FLAKE = new SnowflakeIdWorker();
      private final long twepoch = 1420041600000L;
      private final long workerIdBits = 5L;
      private final long datacenterIdBits = 5L;
      private final long maxWorkerId = 31L;
      private final long maxDatacenterId = 31L;
      private final long sequenceBits = 12L;
      private final long workerIdShift = 12L;
      private final long datacenterIdShift = 17L;
      private final long timestampLeftShift = 22L;
      private final long sequenceMask = 4095L;
      private long workerId;
      private long datacenterId;
      private long sequence = 0L;
      private long lastTimestamp = -1L;

      private void getWorkerIdDatacenterId() {
         Yaml yaml = new Yaml();
         InputStream in = Constant.class.getClassLoader().getResourceAsStream("application.yml");
         Map map = (Map)yaml.load(in);
         Map<String, Object> emr = (Map)map.get("emr");
         String workerIdStr = emr.get("workerId").toString();
         String datacenterIdStr = emr.get("datacenterId").toString();
         this.workerId = Long.valueOf(workerIdStr);
         this.datacenterId = Long.valueOf(datacenterIdStr);
      }

      public SnowflakeIdWorker() {
         this.getWorkerIdDatacenterId();
         if (this.workerId <= 31L && this.workerId >= 0L) {
            if (this.datacenterId > 31L || this.datacenterId < 0L) {
               throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", 31L));
            }
         } else {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", 31L));
         }
      }

      public synchronized long nextId() {
         long timestamp = this.timeGen();
         if (timestamp < this.lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", this.lastTimestamp - timestamp));
         } else {
            if (this.lastTimestamp == timestamp) {
               this.sequence = this.sequence + 1L & 4095L;
               if (this.sequence == 0L) {
                  timestamp = this.tilNextMillis(this.lastTimestamp);
               }
            } else {
               this.sequence = 0L;
            }

            this.lastTimestamp = timestamp;
            return timestamp - 1420041600000L << 22 | this.datacenterId << 17 | this.workerId << 12 | this.sequence;
         }
      }

      protected long tilNextMillis(long lastTimestamp) {
         long timestamp;
         for(timestamp = this.timeGen(); timestamp <= lastTimestamp; timestamp = this.timeGen()) {
         }

         return timestamp;
      }

      protected long timeGen() {
         return System.currentTimeMillis();
      }
   }
}
