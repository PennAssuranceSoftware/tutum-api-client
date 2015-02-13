package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.SerializedName;

public abstract class AbstractPort implements Serializable {
   private static final long serialVersionUID = -5730326704039379919L;

   @SerializedName("inner_port")
   private Integer innerPort;
   @SerializedName("outer_port")
   private Integer outerPort;
   @SerializedName("port_name")
   private String portName;
   private String protocol;
   private Boolean published;

   public Integer getInnerPort() {
      return innerPort;
   }

   public Integer getOuterPort() {
      return outerPort;
   }

   public String getPortName() {
      return portName;
   }

   public String getProtocol() {
      return protocol;
   }

   public Boolean getPublished() {
      return published;
   }

   public void setInnerPort( Integer innerPort ) {
      this.innerPort = innerPort;
   }

   public void setOuterPort( Integer outerPort ) {
      this.outerPort = outerPort;
   }

   public void setPortName( String portName ) {
      this.portName = portName;
   }

   public void setProtocol( String protocol ) {
      this.protocol = protocol;
   }

   public void setPublished( Boolean published ) {
      this.published = published;
   }

   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }
}
