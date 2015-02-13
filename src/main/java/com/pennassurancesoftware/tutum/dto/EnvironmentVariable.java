package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class EnvironmentVariable implements Serializable {
   private static final long serialVersionUID = -5652443158194015056L;

   private String key;
   private String value;

   public String getKey() {
      return key;
   }

   public String getValue() {
      return value;
   }

   public void setKey( String key ) {
      this.key = key;
   }

   public void setValue( String value ) {
      this.value = value;
   }

   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }
}
