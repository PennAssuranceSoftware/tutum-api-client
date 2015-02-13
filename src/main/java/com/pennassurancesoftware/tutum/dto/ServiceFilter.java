package com.pennassurancesoftware.tutum.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.SerializedName;
import com.pennassurancesoftware.tutum.type.ServiceState;
import com.pennassurancesoftware.tutum.util.EnumerationUtils;

public class ServiceFilter {
   private String name;
   private String state;
   @SerializedName("unique_name")
   private String uniqueName;

   public String getName() {
      return name;
   }

   public ServiceState getState() {
      return EnumerationUtils.lookup( ServiceState.class, state );
   }

   public String getUniqueName() {
      return uniqueName;
   }

   public ServiceFilter setName( String name ) {
      this.name = name;
      return this;
   }

   public ServiceFilter setState( String state ) {
      this.state = state;
      return this;
   }

   public ServiceFilter setState( ServiceState state ) {
      setState( state.value() );
      return this;
   }

   public ServiceFilter setUniqueName( String uniqueName ) {
      this.uniqueName = uniqueName;
      return this;
   }

   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }
}
