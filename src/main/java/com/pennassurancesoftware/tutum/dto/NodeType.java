package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.SerializedName;

public class NodeType implements Serializable {
   private static final long serialVersionUID = -2508271388447138329L;

   private Boolean available;
   private Integer cpu;
   private Integer disk;
   private String label;
   private Integer memory;
   private String name;
   private String provider;
   private List<String> regions = new ArrayList<String>();
   @SerializedName("resource_uri")
   private String resourceUri;

   public Boolean getAvailable() {
      return available;
   }

   public Integer getCpu() {
      return cpu;
   }

   public Integer getDisk() {
      return disk;
   }

   public String getLabel() {
      return label;
   }

   public Integer getMemory() {
      return memory;
   }

   public String getName() {
      return name;
   }

   public String getProvider() {
      return provider;
   }

   public List<String> getRegions() {
      return regions;
   }

   public String getResourceUri() {
      return resourceUri;
   }

   public void setAvailable( Boolean available ) {
      this.available = available;
   }

   public void setCpu( Integer cpu ) {
      this.cpu = cpu;
   }

   public void setDisk( Integer disk ) {
      this.disk = disk;
   }

   public void setLabel( String label ) {
      this.label = label;
   }

   public void setMemory( Integer memory ) {
      this.memory = memory;
   }

   public void setName( String name ) {
      this.name = name;
   }

   public void setProvider( String provider ) {
      this.provider = provider;
   }

   public void setRegions( List<String> regions ) {
      this.regions = regions;
   }

   public void setResourceUri( String resourceUri ) {
      this.resourceUri = resourceUri;
   }

   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }
}
