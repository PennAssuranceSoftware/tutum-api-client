package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.SerializedName;

public class Region implements Serializable {
   private static final long serialVersionUID = 7474296056490119429L;

   @SerializedName("resource_uri")
   private String resourceUri;
   private String name;
   private String label;
   @SerializedName("node_types")
   private List<String> nodeTypes = new ArrayList<String>();
   @SerializedName("availability_zones")
   private List<String> availabilityZones = new ArrayList<String>();
   private String provider;
   private Boolean available;

   public String getResourceUri() {
      return resourceUri;
   }

   public void setResourceUri( String resourceUri ) {
      this.resourceUri = resourceUri;
   }

   public String getName() {
      return name;
   }

   public void setName( String name ) {
      this.name = name;
   }

   public String getLabel() {
      return label;
   }

   public void setLabel( String label ) {
      this.label = label;
   }

   public List<String> getNodeTypes() {
      return nodeTypes;
   }

   public void setNodeTypes( List<String> nodeTypes ) {
      this.nodeTypes = nodeTypes;
   }

   public List<String> getAvailabilityZones() {
      return availabilityZones;
   }

   public void setAvailabilityZones( List<String> availabilityZones ) {
      this.availabilityZones = availabilityZones;
   }

   public String getProvider() {
      return provider;
   }

   public void setProvider( String provider ) {
      this.provider = provider;
   }

   public Boolean getAvailable() {
      return available;
   }

   public void setAvailable( Boolean available ) {
      this.available = available;
   }

   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }
}
