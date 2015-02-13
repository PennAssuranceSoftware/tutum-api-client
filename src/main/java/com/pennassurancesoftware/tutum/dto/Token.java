package com.pennassurancesoftware.tutum.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Token {
   private String token;

   public String getToken() {
      return token;
   }

   public void setToken( String token ) {
      this.token = token;
   }

   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }
}
