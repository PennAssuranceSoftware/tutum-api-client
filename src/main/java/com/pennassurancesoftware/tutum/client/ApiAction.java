/*
 * The MIT License
 * 
 * Copyright (c) 2010-2014 Jeevanandam M. (myjeeva.com)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.pennassurancesoftware.tutum.client;

import com.pennassurancesoftware.tutum.dto.Action;
import com.pennassurancesoftware.tutum.dto.Actions;
import com.pennassurancesoftware.tutum.dto.Container;
import com.pennassurancesoftware.tutum.dto.Containers;
import com.pennassurancesoftware.tutum.dto.Logs;
import com.pennassurancesoftware.tutum.dto.Node;
import com.pennassurancesoftware.tutum.dto.NodeCluster;
import com.pennassurancesoftware.tutum.dto.NodeClusters;
import com.pennassurancesoftware.tutum.dto.NodeType;
import com.pennassurancesoftware.tutum.dto.NodeTypes;
import com.pennassurancesoftware.tutum.dto.Nodes;
import com.pennassurancesoftware.tutum.dto.Provider;
import com.pennassurancesoftware.tutum.dto.Providers;
import com.pennassurancesoftware.tutum.dto.Region;
import com.pennassurancesoftware.tutum.dto.Regions;
import com.pennassurancesoftware.tutum.dto.Service;
import com.pennassurancesoftware.tutum.dto.Services;
import com.pennassurancesoftware.tutum.dto.Token;
import com.pennassurancesoftware.tutum.dto.VolumeGroup;
import com.pennassurancesoftware.tutum.dto.VolumeGroups;

/**
 * Enumeration of Tutum RESTful resource information.
 */
public enum ApiAction {

   ACTIONS("/action", RequestMethod.GET, Actions.class),
   GET_ACTION("/action/%s", RequestMethod.GET, Action.class),
   PROVIDERS("/provider", RequestMethod.GET, Providers.class),
   GET_PROVIDER("/provider/%s", RequestMethod.GET, Provider.class),
   REGIONS("/region", RequestMethod.GET, Regions.class),
   GET_REGION("/region/%s/%s", RequestMethod.GET, Region.class),
   NODETYPES("/nodetype", RequestMethod.GET, NodeTypes.class),
   GET_NODETYPE("/nodetype/%s/%s", RequestMethod.GET, NodeType.class),
   NODECLUSTERS("/nodecluster", RequestMethod.GET, NodeClusters.class),
   GET_NODECLUSTER("/nodecluster/%s", RequestMethod.GET, NodeCluster.class),
   CREATE_NODECLUSTER("/nodecluster/", RequestMethod.POST, NodeCluster.class),
   DEPLOY_NODECLUSTER("/nodecluster/%s/deploy/", RequestMethod.POST, NodeCluster.class),
   UPDATE_NODECLUSTER("/nodecluster/%s/", RequestMethod.PATCH, NodeCluster.class),
   UPGRADE_DOCKER_NODECLUSTER("/nodecluster/%s/docker-upgrade/", RequestMethod.POST, NodeCluster.class),
   TERMINATE_NODECLUSTER("/nodecluster/%s/", RequestMethod.DELETE, NodeCluster.class),
   NODES("/node", RequestMethod.GET, Nodes.class),
   GET_NODE("/node/%s", RequestMethod.GET, Node.class),
   DEPLOY_NODE("/node/%s/deploy/", RequestMethod.POST, Node.class),
   UPDATE_NODE("/node/%s/", RequestMethod.PATCH, Node.class),
   UPGRADE_DOCKER_NODE("/node/%s/docker-upgrade/", RequestMethod.POST, Node.class),
   TERMINATE_NODE("/node/%s/", RequestMethod.DELETE, Node.class),
   CREATE_TOKEN("/token/", RequestMethod.POST, Token.class),
   SERVICES("/service", RequestMethod.GET, Services.class),
   CREATE_SERVICE("/service/", RequestMethod.POST, Service.class),
   GET_SERVICE("/service/%s", RequestMethod.GET, Service.class),
   GET_SERVICE_LOGS("/service/%s/logs/", RequestMethod.GET, Logs.class),
   UPDATE_SERVICE("/service/%s/", RequestMethod.PATCH, Service.class),
   START_SERVICE("/service/%s/start/", RequestMethod.POST, Service.class),
   STOP_SERVICE("/service/%s/stop/", RequestMethod.POST, Service.class),
   REDEPLOY_SERVICE("/service/%s/redeploy/", RequestMethod.POST, Service.class),
   TERMINATE_SERVICE("/service/%s/", RequestMethod.DELETE, Service.class),
   CONTAINERS("/container", RequestMethod.GET, Containers.class),
   GET_CONTAINER("/container/%s", RequestMethod.GET, Container.class),
   GET_CONTAINER_LOGS("/container/%s/logs/", RequestMethod.GET, Logs.class),
   START_CONTAINER("/container/%s/start/", RequestMethod.POST, Container.class),
   STOP_CONTAINER("/container/%s/stop/", RequestMethod.POST, Container.class),
   TERMINATE_CONTAINER("/container/%s/", RequestMethod.DELETE, Container.class),
   VOLUMEGROUPS("/volumegroup", RequestMethod.GET, VolumeGroups.class),
   GET_VOLUMEGROUP("/volumegroup/%s", RequestMethod.GET, VolumeGroup.class),

   ;

   private String path;

   private RequestMethod method;

   private Class<?> clazz;

   ApiAction( String path ) {
      this( path, RequestMethod.GET );
   }

   ApiAction( String path, RequestMethod method ) {
      this( path, method, null );
   }

   ApiAction( String path, RequestMethod method, Class<?> clazz ) {
      this.path = path;
      this.method = method;
      this.clazz = clazz;
   }

   /**
    * @return the path
    */
   public String getPath() {
      return path;
   }

   /**
    * @return the method
    */
   public RequestMethod getMethod() {
      return method;
   }

   /**
    * @return the clazz
    */
   public Class<?> getClazz() {
      return clazz;
   }
}
