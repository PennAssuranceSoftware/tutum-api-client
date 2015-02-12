package com.pennassurancesoftware.tutum.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class RegionFilter {
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
