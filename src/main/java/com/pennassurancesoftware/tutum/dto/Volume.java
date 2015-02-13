package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.SerializedName;
import com.pennassurancesoftware.tutum.type.VolumeState;
import com.pennassurancesoftware.tutum.util.EnumerationUtils;

public class Volume implements Serializable {
   private static final long serialVersionUID = -2868109505383694127L;

   private List<String> containers = new ArrayList<String>();
   private String node;
   private String resourceUri;
   private String state;
   private String uuid;
   @SerializedName("volume_group")
   private String volumeGroup;

   public List<String> getContainers() {
      return containers;
   }

   public String getNode() {
      return node;
   }

   public String getResourceUri() {
      return resourceUri;
   }

   public VolumeState getState() {
      return EnumerationUtils.lookup( VolumeState.class, state );
   }

   public String getUuid() {
      return uuid;
   }

   public String getVolumeGroup() {
      return volumeGroup;
   }

   public void setContainers( List<String> containers ) {
      this.containers = containers;
   }

   public void setNode( String node ) {
      this.node = node;
   }

   public void setResourceUri( String resourceUri ) {
      this.resourceUri = resourceUri;
   }

   public void setState( String state ) {
      this.state = state;
   }

   public void setState( VolumeState state ) {
      setState( state.value() );
   }

   public void setUuid( String uuid ) {
      this.uuid = uuid;
   }

   public void setVolumeGroup( String volumeGroup ) {
      this.volumeGroup = volumeGroup;
   }

   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }
}
