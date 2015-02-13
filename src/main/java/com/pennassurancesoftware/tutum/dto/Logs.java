package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Logs implements Serializable {
   private static final long serialVersionUID = 4077842712498224334L;
   
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
