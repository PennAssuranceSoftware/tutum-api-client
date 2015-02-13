package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ProviderFilter implements Serializable {
   private static final long serialVersionUID = 5193158412457859370L;

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
