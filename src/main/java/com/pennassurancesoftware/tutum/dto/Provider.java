package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.SerializedName;

public class Provider implements Serializable {
   private static final long serialVersionUID = -2868109505383694127L;

   private Boolean available;
   private String label;
   private String name;
   private List<String> regions = new ArrayList<String>();
   @SerializedName("resource_uri")
   private String resourceUri;

   public Boolean getAvailable() {
      return available;
   }

   public String getLabel() {
      return label;
   }

   public String getName() {
      return name;
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

   public void setLabel( String label ) {
      this.label = label;
   }

   public void setName( String name ) {
      this.name = name;
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
