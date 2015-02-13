package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.SerializedName;
import com.pennassurancesoftware.tutum.type.ContainerState;
import com.pennassurancesoftware.tutum.util.EnumerationUtils;

public class Container implements Serializable {
   public static class ContainerLink implements Serializable {
      private static final long serialVersionUID = 3028777456429298469L;
      private Map<String, String> endpoints = new HashMap<String, String>();
      @SerializedName("from_container")
      private String fromContainer;
      private String name;
      @SerializedName("to_container")
      private String toContainer;

      public Map<String, String> getEndpoints() {
         return endpoints;
      }

      public String getFromContainer() {
         return fromContainer;
      }

      public String getName() {
         return name;
      }

      public String getToContainer() {
         return toContainer;
      }

      public void setEndpoints( Map<String, String> endpoints ) {
         this.endpoints = endpoints;
      }

      public void setFromContainer( String fromContainer ) {
         this.fromContainer = fromContainer;
      }

      public void setName( String name ) {
         this.name = name;
      }

      public void setToContainer( String toContainer ) {
         this.toContainer = toContainer;
      }

      @Override
      public String toString() {
         return ReflectionToStringBuilder.toString( this );
      }
   }

   public static class Port extends AbstractPort implements Serializable {
      private static final long serialVersionUID = -6442666801663425212L;
      @SerializedName("endpoint_uri")
      private String endpointUri;
      @SerializedName("uri_protocol")
      private String uriProtocol;

      public String getEndpointUri() {
         return endpointUri;
      }

      public String getUriProtocol() {
         return uriProtocol;
      }

      public void setEndpointUri( String endpointUri ) {
         this.endpointUri = endpointUri;
      }

      public void setUriProtocol( String uriProtocol ) {
         this.uriProtocol = uriProtocol;
      }
   }

   private static final long serialVersionUID = 957514540362481689L;

   private String autodestroy;
   private List<Binding> bindings = new ArrayList<Binding>();
   @SerializedName("container_envvars")
   private List<Object> containerEnvvars = new ArrayList<Object>();
   @SerializedName("container_ports")
   private List<Port> containerPorts = new ArrayList<Port>();
   @SerializedName("cpu_shares")
   private Integer cpuShares;
   @SerializedName("deployed_datetime")
   private Date deployedDatetime;
   @SerializedName("destroyed_datetime")
   private Date destroyedDatetime;
   private String entrypoint;
   @SerializedName("exit_code")
   private Integer exitCode;
   @SerializedName("exit_code_msg")
   private String exitCodeMsg;
   @SerializedName("image_name")
   private String imageName;
   @SerializedName("image_tag")
   private String imageTag;
   @SerializedName("linked_to_container")
   private List<ContainerLink> linkedToContainer = new ArrayList<ContainerLink>();
   @SerializedName("link_variables")
   private Map<String, String> linkVariables = new HashMap<String, String>();
   private Integer memory;
   private String name;
   private String node;
   private Boolean privileged;
   @SerializedName("public_dns")
   private String publicDns;
   @SerializedName("resource_uri")
   private String resourceUri;
   private List<String> roles = new ArrayList<String>();
   @SerializedName("run_command")
   private String runCommand;
   private String service;
   @SerializedName("started_datetime")
   private Date startedDatetime;
   private String state;
   @SerializedName("stopped_datetime")
   private Date stoppedDatetime;
   @SerializedName("unique_name")
   private String uniqueName;
   private String uuid;

   public String getAutodestroy() {
      return autodestroy;
   }

   public List<Binding> getBindings() {
      return bindings;
   }

   public List<Object> getContainerEnvvars() {
      return containerEnvvars;
   }

   public List<Port> getContainerPorts() {
      return containerPorts;
   }

   public Integer getCpuShares() {
      return cpuShares;
   }

   public Date getDeployedDatetime() {
      return deployedDatetime;
   }

   public Date getDestroyedDatetime() {
      return destroyedDatetime;
   }

   public String getEntrypoint() {
      return entrypoint;
   }

   public Integer getExitCode() {
      return exitCode;
   }

   public String getExitCodeMsg() {
      return exitCodeMsg;
   }

   public String getImageName() {
      return imageName;
   }

   public String getImageTag() {
      return imageTag;
   }

   public List<ContainerLink> getLinkedToContainer() {
      return linkedToContainer;
   }

   public Map<String, String> getLinkVariables() {
      return linkVariables;
   }

   public Integer getMemory() {
      return memory;
   }

   public String getName() {
      return name;
   }

   public String getNode() {
      return node;
   }

   public Boolean getPrivileged() {
      return privileged;
   }

   public String getPublicDns() {
      return publicDns;
   }

   public String getResourceUri() {
      return resourceUri;
   }

   public List<String> getRoles() {
      return roles;
   }

   public String getRunCommand() {
      return runCommand;
   }

   public String getService() {
      return service;
   }

   public Date getStartedDatetime() {
      return startedDatetime;
   }

   public ContainerState getState() {
      return EnumerationUtils.lookup( ContainerState.class, state );
   }

   public Date getStoppedDatetime() {
      return stoppedDatetime;
   }

   public String getUniqueName() {
      return uniqueName;
   }

   public String getUuid() {
      return uuid;
   }

   public void setAutodestroy( String autodestroy ) {
      this.autodestroy = autodestroy;
   }

   public void setBindings( List<Binding> bindings ) {
      this.bindings = bindings;
   }

   public void setContainerEnvvars( List<Object> containerEnvvars ) {
      this.containerEnvvars = containerEnvvars;
   }

   public void setContainerPorts( List<Port> containerPorts ) {
      this.containerPorts = containerPorts;
   }

   public void setCpuShares( Integer cpuShares ) {
      this.cpuShares = cpuShares;
   }

   public void setDeployedDatetime( Date deployedDatetime ) {
      this.deployedDatetime = deployedDatetime;
   }

   public void setDestroyedDatetime( Date destroyedDatetime ) {
      this.destroyedDatetime = destroyedDatetime;
   }

   public void setEntrypoint( String entrypoint ) {
      this.entrypoint = entrypoint;
   }

   public void setExitCode( Integer exitCode ) {
      this.exitCode = exitCode;
   }

   public void setExitCodeMsg( String exitCodeMsg ) {
      this.exitCodeMsg = exitCodeMsg;
   }

   public void setImageName( String imageName ) {
      this.imageName = imageName;
   }

   public void setImageTag( String imageTag ) {
      this.imageTag = imageTag;
   }

   public void setLinkedToContainer( List<ContainerLink> linkedToContainer ) {
      this.linkedToContainer = linkedToContainer;
   }

   public void setLinkVariables( Map<String, String> linkVariables ) {
      this.linkVariables = linkVariables;
   }

   public void setMemory( Integer memory ) {
      this.memory = memory;
   }

   public void setName( String name ) {
      this.name = name;
   }

   public void setNode( String node ) {
      this.node = node;
   }

   public void setPrivileged( Boolean privileged ) {
      this.privileged = privileged;
   }

   public void setPublicDns( String publicDns ) {
      this.publicDns = publicDns;
   }

   public void setResourceUri( String resourceUri ) {
      this.resourceUri = resourceUri;
   }

   public void setRoles( List<String> roles ) {
      this.roles = roles;
   }

   public void setRunCommand( String runCommand ) {
      this.runCommand = runCommand;
   }

   public void setService( String service ) {
      this.service = service;
   }

   public void setStartedDatetime( Date startedDatetime ) {
      this.startedDatetime = startedDatetime;
   }

   public void setState( String state ) {
      this.state = state;
   }

   public void setState( ContainerState state ) {
      setState( state.value() );
   }

   public void setStoppedDatetime( Date stoppedDatetime ) {
      this.stoppedDatetime = stoppedDatetime;
   }

   public void setUniqueName( String uniqueName ) {
      this.uniqueName = uniqueName;
   }

   public void setUuid( String uuid ) {
      this.uuid = uuid;
   }

   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }
}
