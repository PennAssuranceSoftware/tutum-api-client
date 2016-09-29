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
import com.pennassurancesoftware.tutum.dto.Tag;
import com.pennassurancesoftware.tutum.dto.Tags;
import com.pennassurancesoftware.tutum.dto.Token;
import com.pennassurancesoftware.tutum.dto.Volume;
import com.pennassurancesoftware.tutum.dto.VolumeGroup;
import com.pennassurancesoftware.tutum.dto.VolumeGroups;
import com.pennassurancesoftware.tutum.dto.Volumes;
import com.pennassurancesoftware.tutum.dto.WebhookHandler;
import com.pennassurancesoftware.tutum.dto.WebhookHandlers;

/**
 * Enumeration of Tutum RESTful resource information.
 */
public enum ApiAction {

   ACTIONS("audit", "/action", RequestMethod.GET, Actions.class),
   GET_ACTION("audit", "/action/%s", RequestMethod.GET, Action.class),
   PROVIDERS("infra", "/provider", RequestMethod.GET, Providers.class),
   GET_PROVIDER("infra", "/provider/%s", RequestMethod.GET, Provider.class),
   REGIONS("infra", "/region", RequestMethod.GET, Regions.class),
   GET_REGION("infra", "/region/%s/%s", RequestMethod.GET, Region.class),
   NODETYPES("infra", "/nodetype", RequestMethod.GET, NodeTypes.class),
   GET_NODETYPE("infra", "/nodetype/%s/%s", RequestMethod.GET, NodeType.class),
   NODECLUSTERS("infra", "/nodecluster", RequestMethod.GET, NodeClusters.class),
   GET_NODECLUSTER("infra", "/nodecluster/%s", RequestMethod.GET, NodeCluster.class),
   CREATE_NODECLUSTER("infra", "/nodecluster/", RequestMethod.POST, NodeCluster.class),
   DEPLOY_NODECLUSTER("infra", "/nodecluster/%s/deploy/", RequestMethod.POST, NodeCluster.class),
   UPDATE_NODECLUSTER("infra", "/nodecluster/%s/", RequestMethod.PATCH, NodeCluster.class),
   UPGRADE_DOCKER_NODECLUSTER("infra", "/nodecluster/%s/docker-upgrade/", RequestMethod.POST, NodeCluster.class),
   TERMINATE_NODECLUSTER("infra", "/nodecluster/%s/", RequestMethod.DELETE, NodeCluster.class),
   NODES("infra", "/node", RequestMethod.GET, Nodes.class),
   GET_NODE("infra", "/node/%s", RequestMethod.GET, Node.class),
   DEPLOY_NODE("infra", "/node/%s/deploy/", RequestMethod.POST, Node.class),
   UPDATE_NODE("infra", "/node/%s/", RequestMethod.PATCH, Node.class),
   UPGRADE_DOCKER_NODE("infra", "/node/%s/docker-upgrade/", RequestMethod.POST, Node.class),
   TERMINATE_NODE("infra", "/node/%s/", RequestMethod.DELETE, Node.class),
   CREATE_TOKEN("unknown", "/token/", RequestMethod.POST, Token.class),
   SERVICES("app", "/service", RequestMethod.GET, Services.class),
   CREATE_SERVICE("app", "/service/", RequestMethod.POST, Service.class),
   GET_SERVICE("app", "/service/%s", RequestMethod.GET, Service.class),
   GET_SERVICE_LOGS("app", "/service/%s/logs/", RequestMethod.GET, Logs.class),
   UPDATE_SERVICE("app", "/service/%s/", RequestMethod.PATCH, Service.class),
   SCALE_SERVICE("app", "/service/%s/scale/", RequestMethod.POST, Service.class),
   START_SERVICE("app", "/service/%s/start/", RequestMethod.POST, Service.class),
   STOP_SERVICE("app", "/service/%s/stop/", RequestMethod.POST, Service.class),
   REDEPLOY_SERVICE("app", "/service/%s/redeploy/", RequestMethod.POST, Service.class),
   TERMINATE_SERVICE("app", "/service/%s/", RequestMethod.DELETE, Service.class),
   CONTAINERS("app", "/container", RequestMethod.GET, Containers.class),
   GET_CONTAINER("app", "/container/%s", RequestMethod.GET, Container.class),
   GET_CONTAINER_LOGS("app", "/container/%s/logs/", RequestMethod.GET, Logs.class),
   START_CONTAINER("app", "/container/%s/start/", RequestMethod.POST, Container.class),
   STOP_CONTAINER("app", "/container/%s/stop/", RequestMethod.POST, Container.class),
   TERMINATE_CONTAINER("app", "/container/%s/", RequestMethod.DELETE, Container.class),
   VOLUMEGROUPS("unknown", "/volumegroup", RequestMethod.GET, VolumeGroups.class),
   GET_VOLUMEGROUP("unknown", "/volumegroup/%s", RequestMethod.GET, VolumeGroup.class),
   VOLUMES("unknown", "/volume", RequestMethod.GET, Volumes.class),
   GET_VOLUME("unknown", "/volume/%s", RequestMethod.GET, Volume.class),
   TAGS("unknown", "/%s/%s/tags/", RequestMethod.GET, Tags.class),
   TAG_RESOURCE("unknown", "/%s/%s/tags/", RequestMethod.POST, Tag[].class),
   DELETE_TAG("unknown", "/%s/%s/tags/%s/", RequestMethod.DELETE, Tag.class),
   WEBHOOK_HANDLERS("unknown", "/service/%s/webhook/handler/", RequestMethod.GET, WebhookHandlers.class),
   CREATE_WEBHOOK_HANDLER("unknown", "/service/%s/webhook/handler/", RequestMethod.POST, WebhookHandler[].class),
   GET_WEBHOOK_HANDLER("unknown", "/service/%s/webhook/handler/%s/", RequestMethod.GET, WebhookHandler.class),
   DELETE_WEBHOOK_HANDLER("unknown", "/service/%s/webhook/handler/%s/", RequestMethod.DELETE, WebhookHandler.class),
   CALL_WEBHOOK_HANDLER("unknown", "/service/%s/webhook/handler/%s/call/", RequestMethod.POST, WebhookHandler.class),

   ;

   private String path;
   private String category;

   private RequestMethod method;

   private Class<?> clazz;

   ApiAction( String category, String path ) {
      this( category, path, RequestMethod.GET );
   }

   ApiAction( String category, String path, RequestMethod method ) {
      this( category, path, method, null );
   }

   ApiAction( String category, String path, RequestMethod method, Class<?> clazz ) {
      this.path = path;
      this.method = method;
      this.clazz = clazz;
      this.category = category;
   }

   public String category() {
      return category;
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
