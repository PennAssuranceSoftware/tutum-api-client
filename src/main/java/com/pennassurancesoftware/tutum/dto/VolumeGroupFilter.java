package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class VolumeGroupFilter implements Serializable {
   private static final long serialVersionUID = -8720021041588480155L;

   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }
}
