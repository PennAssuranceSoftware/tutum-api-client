package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class RegionFilter implements Serializable {
   private static final long serialVersionUID = 6282535116012940959L;

   private String name;
   private String provider;

   public String getProvider() {
      return provider;
   }

   public RegionFilter setProvider( String provider ) {
      this.provider = provider;
      return this;
   }

   public String getName() {
      return name;
   }

   public RegionFilter setName( String name ) {
      this.name = name;
      return this;
   }

   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }
}
