package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pennassurancesoftware.tutum.type.ProtocolType;
import com.pennassurancesoftware.tutum.util.EnumerationUtils;

public abstract class AbstractPort implements Serializable {
   private static final long serialVersionUID = -5730326704039379919L;

   @Expose
   @SerializedName("inner_port")
   private Integer innerPort;
   @Expose
   @SerializedName("outer_port")
   private Integer outerPort;
   @Expose
   @SerializedName("port_name")
   private String portName;
   @Expose
   private String protocol;
   @Expose
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

   public ProtocolType getProtocol() {
      return EnumerationUtils.lookup( ProtocolType.class, protocol );
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

   public void setProtocol( ProtocolType protocol ) {
      setProtocol( protocol.value() );
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
