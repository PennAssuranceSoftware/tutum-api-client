package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
   @SerializedName("target_num_nodes")
   private Integer targetNumNodes;
   private String uuid;

   public String getProvider() {
      return provider;
   }

   public void setProvider( String provider ) {
      this.provider = provider;
   }

   public Integer getCurrentNumNodes() {
      return currentNumNodes;
   }

   public void setCurrentNumNodes( Integer currentNumNodes ) {
      this.currentNumNodes = currentNumNodes;
   }

   public Date getDeployedDatetime() {
      return deployedDatetime;
   }

   public void setDeployedDatetime( Date deployedDatetime ) {
      this.deployedDatetime = deployedDatetime;
   }

   public Date getDestroyedDatetime() {
      return destroyedDatetime;
   }

   public void setDestroyedDatetime( Date destroyedDatetime ) {
      this.destroyedDatetime = destroyedDatetime;
   }

   public Integer getDisk() {
      return disk;
   }

   public void setDisk( Integer disk ) {
      this.disk = disk;
   }

   public String getName() {
      return name;
   }

   public void setName( String name ) {
      this.name = name;
   }

   public String getNodeType() {
      return nodeType;
   }

   public void setNodeType( String nodeType ) {
      this.nodeType = nodeType;
   }

   public String getRegion() {
      return region;
   }

   public void setRegion( String region ) {
      this.region = region;
   }

   public String getResourceUri() {
      return resourceUri;
   }

   public void setResourceUri( String resourceUri ) {
      this.resourceUri = resourceUri;
   }

   public String getState() {
      return state;
   }

   public void setState( String state ) {
      this.state = state;
   }

   public Integer getTargetNumNodes() {
      return targetNumNodes;
   }

   public void setTargetNumNodes( Integer targetNumNodes ) {
      this.targetNumNodes = targetNumNodes;
   }

   public String getUuid() {
      return uuid;
   }

   public void setUuid( String uuid ) {
      this.uuid = uuid;
   }

   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }
}
