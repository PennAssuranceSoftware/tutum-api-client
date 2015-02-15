package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pennassurancesoftware.tutum.type.NodeClusterState;
import com.pennassurancesoftware.tutum.util.EnumerationUtils;

public class NodeCluster implements Serializable {
   private static final long serialVersionUID = -2202527200127364851L;

   @SerializedName("current_num_nodes")
   private Integer currentNumNodes;
   @SerializedName("deployed_datetime")
   private Date deployedDatetime;
   @SerializedName("destroyed_datetime")
   private Date destroyedDatetime;
   private Integer disk;
   @Expose
   private String name;
   @Expose
   @SerializedName("node_type")
   private String nodeType;
   @Expose
   private String provider;
   @Expose
   private String region;
   @SerializedName("resource_uri")
   private String resourceUri;
   private String state;
   @Expose
   private List<Tag> tags = new ArrayList<Tag>();
   @Expose
   @SerializedName("target_num_nodes")
   private Integer targetNumNodes;
   private String uuid;

   public boolean isPendingOperation() {
      return getState().isPendingOperation();
   }

   public Integer getCurrentNumNodes() {
      return currentNumNodes;
   }

   public Date getDeployedDatetime() {
      return deployedDatetime;
   }

   public Date getDestroyedDatetime() {
      return destroyedDatetime;
   }

   public Integer getDisk() {
      return disk;
   }

   public String getName() {
      return name;
   }

   public String getNodeType() {
      return nodeType;
   }

   public String getProvider() {
      return provider;
   }

   public String getRegion() {
      return region;
   }

   public String getResourceUri() {
      return resourceUri;
   }

   public NodeClusterState getState() {
      return EnumerationUtils.lookup( NodeClusterState.class, state );
   }

   public List<Tag> getTags() {
      return tags;
   }

   public Integer getTargetNumNodes() {
      return targetNumNodes;
   }

   public String getUuid() {
      return uuid;
   }

   public boolean isDeployed() {
      return NodeClusterState.Deployed.equals( getState() );
   }
   
   public boolean isTerminated() {
      return NodeClusterState.Terminated.equals( getState() );
   }

   public void setCurrentNumNodes( Integer currentNumNodes ) {
      this.currentNumNodes = currentNumNodes;
   }

   public void setDeployedDatetime( Date deployedDatetime ) {
      this.deployedDatetime = deployedDatetime;
   }

   public void setDestroyedDatetime( Date destroyedDatetime ) {
      this.destroyedDatetime = destroyedDatetime;
   }

   public void setDisk( Integer disk ) {
      this.disk = disk;
   }

   public void setName( String name ) {
      this.name = name;
   }

   public void setNodeType( String nodeType ) {
      this.nodeType = nodeType;
   }

   public void setProvider( String provider ) {
      this.provider = provider;
   }

   public void setRegion( String region ) {
      this.region = region;
   }

   public void setResourceUri( String resourceUri ) {
      this.resourceUri = resourceUri;
   }

   public void setState( NodeClusterState state ) {
      setState( state.value() );
   }

   public void setState( String state ) {
      this.state = state;
   }

   public void setTags( List<Tag> tags ) {
      this.tags = tags;
   }

   public void setTargetNumNodes( Integer targetNumNodes ) {
      this.targetNumNodes = targetNumNodes;
   }

   public void setUuid( String uuid ) {
      this.uuid = uuid;
   }

   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }
}
