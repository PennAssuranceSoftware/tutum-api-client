package com.pennassurancesoftware.tutum.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tag {
   @Expose
   private String name;
   @Expose
   @SerializedName("resource_uri")
   private String resourceUri;

   public Tag() {}

   public Tag( String name ) {
      this( name, null );
   }

   public Tag( String name, String resourceUri ) {
      setName( name );
      setResourceUri( resourceUri );
   }

   public String getName() {
      return name;
   }

   public void setName( String name ) {
      this.name = name;
   }

   public String getResourceUri() {
      return resourceUri;
   }

   public void setResourceUri( String resourceUri ) {
      this.resourceUri = resourceUri;
   }
   
   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }

}
