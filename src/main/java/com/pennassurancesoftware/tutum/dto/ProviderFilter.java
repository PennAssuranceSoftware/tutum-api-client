package com.pennassurancesoftware.tutum.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ProviderFilter {
   private String name;

   public String getName() {
      return name;
   }

   public ProviderFilter setName( String name ) {
      this.name = name;
      return this;
   }

   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }
}
