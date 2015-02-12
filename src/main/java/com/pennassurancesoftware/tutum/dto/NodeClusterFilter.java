package com.pennassurancesoftware.tutum.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.pennassurancesoftware.tutum.type.NodeClusterState;
import com.pennassurancesoftware.tutum.util.EnumerationUtils;
import com.pennassurancesoftware.tutum.util.QueryParamBuilder.QueryParamName;

public class NodeClusterFilter {
   private String name;
   @QueryParamName("node_type")
   private String nodeType;
   private String region;
   private String state;

   public String getName() {
      return name;
   }

   public String getNodeType() {
      return nodeType;
   }

   public String getRegion() {
      return region;
   }

   public NodeClusterState getState() {
      return EnumerationUtils.lookup( NodeClusterState.class, state );
   }

   public NodeClusterFilter setName( String name ) {
      this.name = name;
      return this;
   }

   public NodeClusterFilter setNodeType( String nodeType ) {
      this.nodeType = nodeType;
      return this;
   }

   public NodeClusterFilter setRegion( String region ) {
      this.region = region;
      return this;
   }

   public NodeClusterFilter setState( NodeClusterState state ) {
      setState( state.value() );
      return this;
   }

   public NodeClusterFilter setState( String state ) {
      this.state = state;
      return this;
   }

   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }
}
