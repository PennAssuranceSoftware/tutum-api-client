package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.SerializedName;
import com.pennassurancesoftware.tutum.type.VolumeGroupState;
import com.pennassurancesoftware.tutum.util.EnumerationUtils;

public class VolumeGroup implements Serializable {
   private static final long serialVersionUID = -2868109505383694127L;

   private String name;
   @SerializedName("resource_uri")
   private String resourceUri;
   private List<String> services = new ArrayList<String>();
   private String state;
   private String uuid;
   private List<String> volumes = new ArrayList<String>();

   public String getName() {
      return name;
   }

   public String getResourceUri() {
      return resourceUri;
   }

   public List<String> getServices() {
      return services;
   }

   public VolumeGroupState getState() {
      return EnumerationUtils.lookup( VolumeGroupState.class, state );
   }

   public String getUuid() {
      return uuid;
   }

   public List<String> getVolumes() {
      return volumes;
   }

   public void setName( String name ) {
      this.name = name;
   }

   public void setResourceUri( String resourceUri ) {
      this.resourceUri = resourceUri;
   }

   public void setServices( List<String> services ) {
      this.services = services;
   }

   public void setState( String state ) {
      this.state = state;
   }

   public void setState( VolumeGroupState state ) {
      setState( state.value() );
   }

   public void setUuid( String uuid ) {
      this.uuid = uuid;
   }

   public void setVolumes( List<String> volumes ) {
      this.volumes = volumes;
   }

   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }
}
