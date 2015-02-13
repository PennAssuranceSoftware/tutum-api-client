package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.pennassurancesoftware.tutum.type.NodeState;
import com.pennassurancesoftware.tutum.util.EnumerationUtils;
import com.pennassurancesoftware.tutum.util.QueryParamBuilder.QueryParamName;

public class NodeFilter implements Serializable {
   private static final long serialVersionUID = 2980424641965505394L;

   @QueryParamName("node_cluster")
   private String nodeCluster;
   @QueryParamName("node_type")
   private String nodeType;
   private String region;
   private String state;

   public String getNodeCluster() {
      return nodeCluster;
   }

   public String getNodeType() {
      return nodeType;
   }

   public String getRegion() {
      return region;
   }

   public NodeState getState() {
      return EnumerationUtils.lookup( NodeState.class, state );
   }

   public NodeFilter setNodeCluster( String nodeCluster ) {
      this.nodeCluster = nodeCluster;
      return this;
   }

   public NodeFilter setNodeType( String nodeType ) {
      this.nodeType = nodeType;
      return this;
   }

   public NodeFilter setRegion( String region ) {
      this.region = region;
      return this;
   }

   public NodeFilter setState( String state ) {
      this.state = state;
      return this;
   }

   public NodeFilter setState( NodeState state ) {
      setState( state.value() );
      return this;
   }

   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }
}
