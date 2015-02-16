package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pennassurancesoftware.tutum.type.AutoDestoryType;
import com.pennassurancesoftware.tutum.type.AutoRestartType;
import com.pennassurancesoftware.tutum.type.DeploymentStrategyType;
import com.pennassurancesoftware.tutum.type.ServiceState;
import com.pennassurancesoftware.tutum.util.EnumerationUtils;

public class Service implements Serializable {
   public static class Port extends AbstractPort implements Serializable {
      private static final long serialVersionUID = -6442666801663425212L;
   }

   public static class RelatedService implements Serializable {
      private static final long serialVersionUID = 5570440107236960416L;

      @SerializedName("from_service")
      private String fromService;
      private String name;
      @SerializedName("to_service")
      private String toService;

      public String getFromService() {
         return fromService;
      }

      public String getName() {
         return name;
      }

      public String getToService() {
         return toService;
      }

      public void setFromService( String fromService ) {
         this.fromService = fromService;
      }

      public void setName( String name ) {
         this.name = name;
      }

      public void setToService( String toService ) {
         this.toService = toService;
      }
   }

   private static final long serialVersionUID = -5254444978757549427L;

   @Expose
   private String autodestroy;
   @Expose
   private String autorestart;
   @Expose
   private List<Binding> bindings = new ArrayList<Binding>();
   @Expose
   @SerializedName("container_envvars")
   private List<EnvironmentVariable> containerEnvvars = new ArrayList<EnvironmentVariable>();
   @Expose
   @SerializedName("container_ports")
   private List<Port> containerPorts = new ArrayList<Port>();
   private List<String> containers = new ArrayList<String>();
   @SerializedName("cpu_shares")
   private String cpuShares;
   @SerializedName("current_num_containers")
   private Integer currentNumContainers;
   @SerializedName("deployed_datetime")
   private Date deployedDatetime;
   @Expose
   @SerializedName("deployment_strategy")
   private String deploymentStrategy;
   @SerializedName("destroyed_datetime")
   private Date destroyedDatetime;
   @Expose
   private String entrypoint;
   @Expose
   private String image;
   @SerializedName("image_name")
   private String imageName;
   @SerializedName("image_tag")
   private String imageTag;
   @SerializedName("linked_from_service")
   private List<String> linkedFromServices = new ArrayList<String>();
   @Expose
   @SerializedName("linked_to_service")
   private List<String> linkedToService = new ArrayList<String>();
   @SerializedName("link_variables")
   private Map<String, String> linkVariables = new HashMap<String, String>();
   private Integer memory;
   @Expose
   private String name;
   @Expose
   private Boolean privileged;
   @SerializedName("public_dns")
   private String publicDns;
   @SerializedName("resource_uri")
   private String resourceUri;
   @Expose
   private List<String> roles = new ArrayList<String>();
   @Expose
   @SerializedName("run_command")
   private String runCommand;
   @SerializedName("running_num_containers")
   private Integer runningNumContainers;
   @Expose
   @SerializedName("sequential_deployment")
   private Boolean sequentialDeployment;
   @SerializedName("started_datetime")
   private Date startedDatetime;
   private String state;
   @SerializedName("stopped_datetime")
   private Date stoppedDatetime;
   @SerializedName("stopped_num_containers")
   private Integer stoppedNumContainers;
   @Expose
   private List<Tag> tags = new ArrayList<Tag>();
   @Expose
   @SerializedName("target_num_containers")
   private Integer targetNumContainers;
   @SerializedName("unique_name")
   private String uniqueName;
   private String uuid;

   public boolean canBeStarted() {
      return getState().canStartFrom();
   }

   public AutoDestoryType getAutodestroy() {
      return EnumerationUtils.lookup( AutoDestoryType.class, autodestroy );
   }

   public AutoRestartType getAutorestart() {
      return EnumerationUtils.lookup( AutoRestartType.class, autorestart );
   }

   public List<Binding> getBindings() {
      return bindings;
   }

   public List<EnvironmentVariable> getContainerEnvvars() {
      return containerEnvvars;
   }

   public List<Port> getContainerPorts() {
      return containerPorts;
   }

   public List<String> getContainers() {
      return containers;
   }

   public String getCpuShares() {
      return cpuShares;
   }

   public Integer getCurrentNumContainers() {
      return currentNumContainers;
   }

   public Date getDeployedDatetime() {
      return deployedDatetime;
   }

   public DeploymentStrategyType getDeploymentStrategy() {
      return EnumerationUtils.lookup( DeploymentStrategyType.class, deploymentStrategy );
   }

   public Date getDestroyedDatetime() {
      return destroyedDatetime;
   }

   public String getEntrypoint() {
      return entrypoint;
   }

   public String getImage() {
      return image;
   }

   public String getImageName() {
      return imageName;
   }

   public String getImageTag() {
      return imageTag;
   }

   public List<String> getLinkedFromServices() {
      return linkedFromServices;
   }

   public List<String> getLinkedToService() {
      return linkedToService;
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

   public Integer getRunningNumContainers() {
      return runningNumContainers;
   }

   public Boolean getSequentialDeployment() {
      return sequentialDeployment;
   }

   public Date getStartedDatetime() {
      return startedDatetime;
   }

   public ServiceState getState() {
      return EnumerationUtils.lookup( ServiceState.class, state );
   }

   public Date getStoppedDatetime() {
      return stoppedDatetime;
   }

   public Integer getStoppedNumContainers() {
      return stoppedNumContainers;
   }

   public List<Tag> getTags() {
      return tags;
   }

   public Integer getTargetNumContainers() {
      return targetNumContainers;
   }

   public String getUniqueName() {
      return uniqueName;
   }

   public String getUuid() {
      return uuid;
   }

   public boolean isPendingOperation() {
      return getState().isPendingOperation();
   }

   public boolean isTerminated() {
      return ServiceState.Terminated.equals( getState() );
   }

   public void setAutodestroy( AutoDestoryType autodestroy ) {
      setAutodestroy( autodestroy.value() );
   }

   public void setAutodestroy( String autodestroy ) {
      this.autodestroy = autodestroy;
   }

   public void setAutorestart( AutoRestartType autorestart ) {
      setAutorestart( autorestart.value() );
   }

   public void setAutorestart( String autorestart ) {
      this.autorestart = autorestart;
   }

   public void setBindings( List<Binding> bindings ) {
      this.bindings = bindings;
   }

   public void setContainerEnvvars( List<EnvironmentVariable> containerEnvvars ) {
      this.containerEnvvars = containerEnvvars;
   }

   public void setContainerPorts( List<Port> containerPorts ) {
      this.containerPorts = containerPorts;
   }

   public void setContainers( List<String> containers ) {
      this.containers = containers;
   }

   public void setCpuShares( String cpuShares ) {
      this.cpuShares = cpuShares;
   }

   public void setCurrentNumContainers( Integer currentNumContainers ) {
      this.currentNumContainers = currentNumContainers;
   }

   public void setDeployedDatetime( Date deployedDatetime ) {
      this.deployedDatetime = deployedDatetime;
   }

   public void setDeploymentStrategy( DeploymentStrategyType deploymentStrategy ) {
      setDeploymentStrategy( deploymentStrategy.value() );
   }

   public void setDeploymentStrategy( String deploymentStrategy ) {
      this.deploymentStrategy = deploymentStrategy;
   }

   public void setDestroyedDatetime( Date destroyedDatetime ) {
      this.destroyedDatetime = destroyedDatetime;
   }

   public void setEntrypoint( String entrypoint ) {
      this.entrypoint = entrypoint;
   }

   public void setImage( String image ) {
      this.image = image;
   }

   public void setImageName( String imageName ) {
      this.imageName = imageName;
   }

   public void setImageTag( String imageTag ) {
      this.imageTag = imageTag;
   }

   public void setLinkedFromServices( List<String> linkedFromServices ) {
      this.linkedFromServices = linkedFromServices;
   }

   public void setLinkedToService( List<String> linkedToService ) {
      this.linkedToService = linkedToService;
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

   public void setRunningNumContainers( Integer runningNumContainers ) {
      this.runningNumContainers = runningNumContainers;
   }

   public void setSequentialDeployment( Boolean sequentialDeployment ) {
      this.sequentialDeployment = sequentialDeployment;
   }

   public void setStartedDatetime( Date startedDatetime ) {
      this.startedDatetime = startedDatetime;
   }

   public void setState( ServiceState state ) {
      setState( state.value() );
   }

   public void setState( String state ) {
      this.state = state;
   }

   public void setStoppedDatetime( Date stoppedDatetime ) {
      this.stoppedDatetime = stoppedDatetime;
   }

   public void setStoppedNumContainers( Integer stoppedNumContainers ) {
      this.stoppedNumContainers = stoppedNumContainers;
   }

   public void setTags( List<Tag> tags ) {
      this.tags = tags;
   }

   public void setTargetNumContainers( Integer targetNumContainers ) {
      this.targetNumContainers = targetNumContainers;
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

   public boolean isStarted() {
      return ServiceState.Running.equals( getState() );
   }
}
