package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.SerializedName;

public class Binding implements Serializable {
   private static final long serialVersionUID = -692343063940047817L;

   @SerializedName("container_path")
   private String containerPath;
   @SerializedName("host_path")
   private String hostPath;
   private Boolean rewritable;
   @SerializedName("volumes_from")
   private String volumesFrom;

   public String getContainerPath() {
      return containerPath;
   }

   public String getHostPath() {
      return hostPath;
   }

   public Boolean getRewritable() {
      return rewritable;
   }

   public String getVolumesFrom() {
      return volumesFrom;
   }

   public void setContainerPath( String containerPath ) {
      this.containerPath = containerPath;
   }

   public void setHostPath( String hostPath ) {
      this.hostPath = hostPath;
   }

   public void setRewritable( Boolean rewritable ) {
      this.rewritable = rewritable;
   }

   public void setVolumesFrom( String volumesFrom ) {
      this.volumesFrom = volumesFrom;
   }

   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }
}
