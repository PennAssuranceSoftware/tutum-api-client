package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Token implements Serializable {
   private static final long serialVersionUID = -6414404801091343158L;

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
