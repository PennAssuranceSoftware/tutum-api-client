package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.Expose;

public class WebhookHandler implements Serializable {
   private static final long serialVersionUID = -2868109505383694127L;

   @Expose
   private String name;
   private String resourceUri;
   private String url;

   public WebhookHandler() {}

   public WebhookHandler( String name ) {
      setName( name );
   }

   public String getName() {
      return name;
   }

   public String getResourceUri() {
      return resourceUri;
   }

   public String getUrl() {
      return url;
   }

   public void setName( String name ) {
      this.name = name;
   }

   public void setResourceUri( String resourceUri ) {
      this.resourceUri = resourceUri;
   }

   public void setUrl( String url ) {
      this.url = url;
   }

   public String getServiceUuid() {
      String result = null;
      if( url != null ) {
         boolean pickupNext = false;
         final List<String> items = Arrays.asList( url.split( "/" ) );
         for( String item : items ) {
            if( pickupNext ) {
               result = item;
               break;
            }
            else {
               pickupNext = "service".equals( item );
            }
         }
      }
      return result;
   }

   public String getWebhookUuid() {
      String result = null;
      if( url != null ) {
         boolean pickupNext = false;
         final List<String> items = Arrays.asList( url.split( "/" ) );
         for( String item : items ) {
            if( pickupNext ) {
               result = item;
               break;
            }
            else {
               pickupNext = "handler".equals( item );
            }
         }
      }
      return result;
   }

   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }
}
