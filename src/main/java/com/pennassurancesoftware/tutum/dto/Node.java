package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.SerializedName;

public class Node implements Serializable {
   private static final long serialVersionUID = -3952227522277743092L;

   @SerializedName("deployed_datetime")
   private Date deployedDatetime;
   @SerializedName("destroyed_datetime")
   private Date destroyedDatetime;
   @SerializedName("docker_execdriver")
   private String dockerCxecdriver;
   @SerializedName("docker_graphdriver")
   private String dockerGraphdriver;
   @SerializedName("docker_version")
   private String dockerVersion;
   @SerializedName("external_fqdn")
   private String externalFqdn;
   @SerializedName("last_seen")
   private Date lastSeen;
   @SerializedName("node_cluster")
   private String nodeCluster;
   @SerializedName("node_type")
   private String nodeType;
   @SerializedName("public_ip")
   private String publicIp;
   private String region;
   @SerializedName("resource_uri")
   private String resourceUri;
   private String state;
   private List<Tag> tags = new ArrayList<Tag>();
   private String uuid;

   public Date getDeployedDatetime() {
      return deployedDatetime;
   }

   public Date getDestroyedDatetime() {
      return destroyedDatetime;
   }

   public String getDockerCxecdriver() {
      return dockerCxecdriver;
   }

   public String getDockerGraphdriver() {
      return dockerGraphdriver;
   }

   public String getDockerVersion() {
      return dockerVersion;
   }

   public String getExternalFqdn() {
      return externalFqdn;
   }

   public Date getLastSeen() {
      return lastSeen;
   }

   public String getNodeCluster() {
      return nodeCluster;
   }

   public String getNodeType() {
      return nodeType;
   }

   public String getPublicIp() {
      return publicIp;
   }

   public String getRegion() {
      return region;
   }

   public String getResourceUri() {
      return resourceUri;
   }

   public String getState() {
      return state;
   }

   public List<Tag> getTags() {
      return tags;
   }

   public String getUuid() {
      return uuid;
   }

   public void setDeployedDatetime( Date deployedDatetime ) {
      this.deployedDatetime = deployedDatetime;
   }

   public void setDestroyedDatetime( Date destroyedDatetime ) {
      this.destroyedDatetime = destroyedDatetime;
   }

   public void setDockerCxecdriver( String dockerCxecdriver ) {
      this.dockerCxecdriver = dockerCxecdriver;
   }

   public void setDockerGraphdriver( String dockerGraphdriver ) {
      this.dockerGraphdriver = dockerGraphdriver;
   }

   public void setDockerVersion( String dockerVersion ) {
      this.dockerVersion = dockerVersion;
   }

   public void setExternalFqdn( String externalFqdn ) {
      this.externalFqdn = externalFqdn;
   }

   public void setLastSeen( Date lastSeen ) {
      this.lastSeen = lastSeen;
   }

   public void setNodeCluster( String nodeCluster ) {
      this.nodeCluster = nodeCluster;
   }

   public void setNodeType( String nodeType ) {
      this.nodeType = nodeType;
   }

   public void setPublicIp( String publicIp ) {
      this.publicIp = publicIp;
   }

   public void setRegion( String region ) {
      this.region = region;
   }

   public void setResourceUri( String resourceUri ) {
      this.resourceUri = resourceUri;
   }

   public void setState( String state ) {
      this.state = state;
   }

   public void setTags( List<Tag> tags ) {
      this.tags = tags;
   }

   public void setUuid( String uuid ) {
      this.uuid = uuid;
   }
   
   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }

}
