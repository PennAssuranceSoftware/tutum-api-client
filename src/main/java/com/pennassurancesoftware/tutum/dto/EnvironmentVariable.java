package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.Expose;

public class EnvironmentVariable implements Serializable {
   private static final long serialVersionUID = -5652443158194015056L;

   @Expose
   private String key;
   @Expose
   private String value;

   public EnvironmentVariable() {}

   public EnvironmentVariable( String key, String value ) {
      setKey( key );
      setValue( value );
   }

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
