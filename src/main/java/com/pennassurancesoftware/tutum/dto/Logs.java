package com.pennassurancesoftware.tutum.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Logs {
   private String logs;

   public String getLogs() {
      return logs;
   }

   public void setLogs( String logs ) {
      this.logs = logs;
   }

   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }
}
