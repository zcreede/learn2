package com.emr.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnowIdUtils1 {
   protected Logger log = LoggerFactory.getLogger(SnowIdUtils1.class);

   public static long uniqueLong() {
      return SnowIdUtils1.SnowFlake.SNOW_FLAKE.nextId();
   }

   public static String uniqueLongHex() {
      return String.format("%016x", uniqueLong());
   }

   public static void main(String[] args) throws InterruptedException {
      for(int i = 0; i < 100; ++i) {
         long snowID = uniqueLong();
         System.out.println(snowID);
      }

   }

   private static class SnowFlake {
      private static final SnowFlake SNOW_FLAKE = new SnowFlake();
      private final long START_TIMESTAMP = 1557489395327L;
      private final long SEQUENCE_BIT = 12L;
      private final long MACHINE_BIT = 10L;
      private final long TIMESTAMP_LEFT = 22L;
      private final long MAX_SEQUENCE = 4095L;
      private final long MAX_MACHINE_ID = 1023L;
      private long machineIdPart;
      private long sequence = 0L;
      private long lastStamp = -1L;

      private SnowFlake() {
         long localIp = 4321L;
         this.machineIdPart = (localIp & 1023L) << 12;
      }

      public synchronized long nextId() {
         long currentStamp = this.timeGen();
         if (currentStamp < this.lastStamp) {
            throw new RuntimeException(String.format("时钟已经回拨.  Refusing to generate id for %d milliseconds", this.lastStamp - currentStamp));
         } else {
            if (currentStamp == this.lastStamp) {
               this.sequence = this.sequence + 1L & 4095L;
               if (this.sequence == 0L) {
                  currentStamp = this.getNextMill();
               }
            } else {
               this.sequence = 0L;
            }

            this.lastStamp = currentStamp;
            return currentStamp - 1557489395327L << 22 | this.machineIdPart | this.sequence;
         }
      }

      private long getNextMill() {
         long mill;
         for(mill = this.timeGen(); mill <= this.lastStamp; mill = this.timeGen()) {
         }

         return mill;
      }

      protected long timeGen() {
         return System.currentTimeMillis();
      }
   }
}
