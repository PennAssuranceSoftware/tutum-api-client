package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.pennassurancesoftware.tutum.type.ContainerState;
import com.pennassurancesoftware.tutum.util.EnumerationUtils;
import com.pennassurancesoftware.tutum.util.QueryParamBuilder.QueryParamName;

public class ContainerFilter implements Serializable {
   private static final long serialVersionUID = -4075031290562702032L;

   private String name;
   private String state;
   @QueryParamName("unique_name")
   private String uniqueName;

   public String getName() {
      return name;
   }

   public ContainerState getState() {
      return EnumerationUtils.lookup( ContainerState.class, state );
   }

   public String getUniqueName() {
      return uniqueName;
   }

   public ContainerFilter setName( String name ) {
      this.name = name;
      return this;
   }

   public ContainerFilter setState( ContainerState state ) {
      setState( state.value() );
      return this;
   }

   public ContainerFilter setState( String state ) {
      this.state = state;
      return this;
   }

   public ContainerFilter setUniqueName( String uniqueName ) {
      this.uniqueName = uniqueName;
      return this;
   }

   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }
}
