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
package com.pennassurancesoftware.tutum;

import java.util.List;

import com.pennassurancesoftware.tutum.dto.Action;
import com.pennassurancesoftware.tutum.dto.ActionFilter;
import com.pennassurancesoftware.tutum.dto.Actions;
import com.pennassurancesoftware.tutum.dto.Container;
import com.pennassurancesoftware.tutum.dto.ContainerFilter;
import com.pennassurancesoftware.tutum.dto.Containers;
import com.pennassurancesoftware.tutum.dto.Meta;
import com.pennassurancesoftware.tutum.dto.Node;
import com.pennassurancesoftware.tutum.dto.NodeCluster;
import com.pennassurancesoftware.tutum.dto.NodeClusterFilter;
import com.pennassurancesoftware.tutum.dto.NodeClusters;
import com.pennassurancesoftware.tutum.dto.NodeFilter;
import com.pennassurancesoftware.tutum.dto.NodeType;
import com.pennassurancesoftware.tutum.dto.NodeTypeFilter;
import com.pennassurancesoftware.tutum.dto.NodeTypes;
import com.pennassurancesoftware.tutum.dto.Nodes;
import com.pennassurancesoftware.tutum.dto.Provider;
import com.pennassurancesoftware.tutum.dto.ProviderFilter;
import com.pennassurancesoftware.tutum.dto.Providers;
import com.pennassurancesoftware.tutum.dto.Region;
import com.pennassurancesoftware.tutum.dto.RegionFilter;
import com.pennassurancesoftware.tutum.dto.Regions;
import com.pennassurancesoftware.tutum.dto.Service;
import com.pennassurancesoftware.tutum.dto.ServiceFilter;
import com.pennassurancesoftware.tutum.dto.Services;
import com.pennassurancesoftware.tutum.dto.Tag;
import com.pennassurancesoftware.tutum.dto.TagFilter;
import com.pennassurancesoftware.tutum.dto.Tags;
import com.pennassurancesoftware.tutum.dto.Token;
import com.pennassurancesoftware.tutum.dto.Volume;
import com.pennassurancesoftware.tutum.dto.VolumeFilter;
import com.pennassurancesoftware.tutum.dto.VolumeGroup;
import com.pennassurancesoftware.tutum.dto.VolumeGroupFilter;
import com.pennassurancesoftware.tutum.dto.VolumeGroups;
import com.pennassurancesoftware.tutum.dto.Volumes;
import com.pennassurancesoftware.tutum.dto.WebhookHandler;
import com.pennassurancesoftware.tutum.exception.RequestUnsuccessfulException;
import com.pennassurancesoftware.tutum.exception.TutumException;
import com.pennassurancesoftware.tutum.type.TagResourceType;

/**
 * <p>
 * <strong>Tutum API client in Java</strong>
 * </p>
 *
 * <p>
 * A simple and meaningful java methods for <a href="https://dashboard.tutum.co"
 * title="Tutum's API">Tutum's API</a>. All of the RESTful that you find in
 * Tutum API's Version 1 is available via simple java methods.
 * </p>
 *
 * <p>
 * <strong>Sample Code:</strong><br/>
 *
 * <pre>
 * // Create a Tutum client
 * Tutum apiClient = new TutumClient(authToken);
 * <code>or</code>
 * Tutum apiClient = new TutumClient("v2", authToken);
 *
 * <strong>Let's invoke the appropriate method as per need</strong>
 *
 * // Fetching all the available droplets from control panel
 * Droplets droplets = apiClient.getAvailableDroplets(pageNo);
 *
 * // Fetching all the available kernels for droplet
 * Kernels kernels = apiClient.getAvailableKernels(dropletId, pageNo);
 *
 * // Create a new droplet
 * Droplet newDroplet = new Droplet();
 * newDroplet.setName("api-client-test-host");
 * newDroplet.setSize(new Size("512mb")); // setting size by slug value
 * newDroplet.setRegion(new Region("sgp1")); // setting region by slug value; sgp1 => Singapore 1 Data center
 * newDroplet.setImage(new Image(1601)); // setting by Image Id 1601 => centos-5-8-x64 also available in image slug value
 * newDroplet.setEnableBackup(Boolean.TRUE);
 * newDroplet.setEnableIpv6(Boolean.TRUE);
 * newDroplet.setEnablePrivateNetworking(Boolean.TRUE);
 * Droplet droplet = apiClient.createDroplet(newDroplet);
 *
 * // Fetch droplet information
 * Droplet droplet = apiClient.getDropletInfo(dropletId);
 *
 * // Fetch Available Plans/Sizes supported by Tutum
 * Sizes sizes = apiClient.getAvailableSizes(pageNo);
 *
 * // Fetch Available Regions supported by Tutum
 * Sizes sizes = apiClient.getAvailableRegions(pageNo);
 *
 * and so on... simple to use and effective!
 * </pre>
 *
 * </p>
 */
public interface Tutum {
   /** Interface that is used to provide object to caller when iterating through objects that require multiple calls */
   public static interface TutumObjectCallback<T> {
      /**
       * Handle a single object pulled back from a call to the Tutum API
       * @param meta Meta data returned from the called used to get the object
       * @param object Tutum object to handle in the callback
       * @return Flag that indicates the iteration should continue or not
       */
      boolean handle( Meta meta, T object );
   }

   /**
    * <p>
    * Method allows you to create a new Node Cluster. See the required parameters section below for an
    * explanation of the variables that are needed to create a new Node Cluster.
    * </p>
    * <p>
    * Create a instance of {@link NodeCluster} class and populated the object appropriately.
    * Minimum required values are -
    * </p>
    *
    * <pre>
    * {
    *   "name": "mycluster",
    *   "region": "/api/v1/region/digitalocean/lon1/",
    *   "node_type": "/api/v1/nodetype/digitalocean/1gb/"
    * }
    * </pre>
    *
    * @param cluster Object that will used to create the new cluster
    * @return {@link NodeCluster}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   NodeCluster createNodeCluster( NodeCluster cluster ) throws TutumException, RequestUnsuccessfulException;

   /**
    * <p>
    * Method allows you to create a new Service. See the required parameters section below for an
    * explanation of the variables that are needed to create a new Service.
    * </p>
    * <p>
    * Create a instance of {@link Service} class and populated the object appropriately.
    * Minimum required values are -
    * </p>
    *
    * <pre>
    * {
    *   "image": "tutum/hello-world",
    *   "name": "my-new-app",
    *   "target_num_containers": 2
    * }
    * </pre>
    *
    * @param service Object that will used to create the new service
    * @return {@link Service}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   Service createService( Service service ) throws TutumException, RequestUnsuccessfulException;

   /**
    * A token allows authentication for agents running in Nodes.  Creates a new token.
    *
    * @return {@link Token}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   String createToken() throws TutumException, RequestUnsuccessfulException;

   /**
    * Deploys and provisions a recently created node in the specified region and cloud provider.
    *
    * @param uuid the id of the node
    * @return {@link Node}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   Node deployNode( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Deploys and provisions a recently created node cluster in the specified region and cloud provider.
    *
    * @param uuid the id of the node cluster
    * @return {@link NodeCluster}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   NodeCluster deployNodeCluster( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Method returns complete information for given action ID
    *
    * @param uuid the id of the action
    * @return {@link Action}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   Action getAction( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all actions in chronological order. Returns a list of Action objects.
    *
    * @return {@link Actions}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   List<Action> getActions() throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all actions in chronological order. Returns a list of Action objects.
    *
    * @param filter Filters that can be applied to the actions that are returned
    * @return {@link Actions}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   List<Action> getActions( ActionFilter filter ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all actions in chronological order. Returns a list of Action objects.
    *
    * @param filter Filters that can be applied to the actions that are returned
    * @param pageNo for pagination
    * @return {@link Actions}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   Actions getActions( ActionFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all actions in chronological order. Returns a list of Action objects.
    *
    * @param pageNo for pagination
    * @return {@link Actions}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   Actions getActions( Integer pageNo ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Method returns complete information for given container ID
    *
    * @param uuid the id of the container
    * @return {@link Container}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   Container getContainer( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Method returns the logs for a specified container
    *
    * @param uuid the id of the container
    * @return {@link String}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   String getContainerLogs( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all containers in chronological order. Returns a list of Action objects.
    *
    * @return {@link Containers}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   List<Container> getContainers() throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all containers in chronological order. Returns a list of Action objects.
    *
    * @param filter Filters that can be applied to the containers that are returned
    * @return {@link Containers}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   List<Container> getContainers( ContainerFilter filter ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all containers in chronological order. Returns a list of Action objects.
    *
    * @param filter Filters that can be applied to the containers that are returned
    * @param pageNo for pagination
    * @return {@link Containers}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   Containers getContainers( ContainerFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all containers in chronological order. Returns a list of Action objects.
    *
    * @param pageNo for pagination
    * @return {@link Containers}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   Containers getContainers( Integer pageNo ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Get all the details of an specific node
    *
    * @param uuid the id of the action
    * @return {@link Node}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   Node getNode( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Get all the details of an specific node cluster
    *
    * @param uuid the id of the action
    * @return {@link NodeCluster}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   NodeCluster getNodeCluster( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all current and recently terminated node clusters. Returns a list of NodeCluster objects.
    *
    * @return {@link NodeClusters}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   List<NodeCluster> getNodeClusters() throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all current and recently terminated node clusters. Returns a list of NodeCluster objects.
    *
    * @param pageNo for pagination
    * @return {@link NodeClusters}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   NodeClusters getNodeClusters( Integer pageNo ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all current and recently terminated node clusters. Returns a list of NodeCluster objects.
    *
    * @param filter Apply filter to the results
    * @return {@link NodeClusters}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   List<NodeCluster> getNodeClusters( NodeClusterFilter filter ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all current and recently terminated node clusters. Returns a list of NodeCluster objects.
    *
    * @param filter Apply filter to the results
    * @param pageNo for pagination
    * @return {@link NodeClusters}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   NodeClusters getNodeClusters( NodeClusterFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Method returns the tags for a specified node cluster
    *
    * @param uuid the id of the node cluster
    * @return {@link Tags}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   List<Tag> getNodeClusterTags( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all current and recently terminated nodes. Returns a list of Node objects.
    *
    * @return {@link Nodes}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   List<Node> getNodes() throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all current and recently terminated nodes. Returns a list of Node objects.
    *
    * @param pageNo for pagination
    * @return {@link Nodes}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   Nodes getNodes( Integer pageNo ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all current and recently terminated nodes. Returns a list of Node objects.
    *
    * @param filter Apply filter to results
    * @return {@link Nodes}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   List<Node> getNodes( NodeFilter filter ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all current and recently terminated nodes. Returns a list of Node objects.
    *
    * @param filter Apply filter to results
    * @param pageNo for pagination
    * @return {@link Nodes}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   Nodes getNodes( NodeFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Method returns the tags for a specified node
    *
    * @param uuid the id of the node
    * @return {@link Tags}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   List<Tag> getNodeTags( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Get all the details of a specific node type
    *
    * @param providerName Name of the provider
    * @param name Name of the node type
    * @return {@link NodeType}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   NodeType getNodeType( String providerName, String name ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all node types of all supported cloud providers. Returns a list of NodeType objects.
    *
    * @return {@link NodeTypes}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   List<NodeType> getNodeTypes() throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all node types of all supported cloud providers. Returns a list of NodeType objects.
    *
    * @param pageNo for pagination
    * @return {@link NodeTypes}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   NodeTypes getNodeTypes( Integer pageNo ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all node types of all supported cloud providers. Returns a list of NodeType objects.
    *
    * @param filter Apply filters to the results
    * @return {@link NodeTypes}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   List<NodeType> getNodeTypes( NodeTypeFilter filter ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all node types of all supported cloud providers. Returns a list of NodeType objects.
    *
    * @param filter Apply filters to the results
    * @param pageNo for pagination
    * @return {@link NodeTypes}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   NodeTypes getNodeTypes( NodeTypeFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Method returns complete information for given provider name
    *
    * @param name Name of the provider
    * @return {@link Provider}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   Provider getProvider( String name ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all supported cloud providers. Returns a list of Provider objects.
    *
    * @return {@link Providers}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   List<Provider> getProviders() throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all supported cloud providers. Returns a list of Provider objects.
    *
    * @param pageNo for pagination
    * @return {@link Providers}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   Providers getProviders( Integer pageNo ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all supported cloud providers. Returns a list of Provider objects.
    *
    * @param filter Filters that can be applied to the results
    * @return {@link Providers}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   List<Provider> getProviders( ProviderFilter filter ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all supported cloud providers. Returns a list of Provider objects.
    *
    * @param filter Filters that can be applied to the results
    * @param pageNo for pagination
    * @return {@link Providers}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   Providers getProviders( ProviderFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Method returns complete information for given provider name and region name
    *
    * @param providerName Name of the provider
    * @param name Name of the region
    * @return {@link Region}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   Region getRegion( String providerName, String name ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all regions of all supported cloud providers. Returns a list of Region objects.
    *
    * @return {@link Regions}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   List<Region> getRegions() throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all regions of all supported cloud providers. Returns a list of Region objects.
    *
    * @param pageNo for pagination
    * @return {@link Regions}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   Regions getRegions( Integer pageNo ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all regions of all supported cloud providers. Returns a list of Region objects.
    *
    * @param filter Filters that can be applied to the results
    * @return {@link Regions}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   List<Region> getRegions( RegionFilter filter ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all regions of all supported cloud providers. Returns a list of Region objects.
    *
    * @param filter Filters that can be applied to the results
    * @param pageNo for pagination
    * @return {@link Regions}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   Regions getRegions( RegionFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Method returns complete information for given service ID
    *
    * @param uuid the id of the service
    * @return {@link Service}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   Service getService( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Method returns the logs for a specified service
    *
    * @param uuid the id of the service
    * @return {@link String}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   String getServiceLogs( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all services in chronological order. Returns a list of Action objects.
    *
    * @return {@link Services}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   List<Service> getServices() throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all services in chronological order. Returns a list of Action objects.
    *
    * @param filter Filters that can be applied to the services that are returned
    * @return {@link Services}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   List<Service> getServices( ServiceFilter filter ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all services in chronological order. Returns a list of Action objects.
    *
    * @param filter Filters that can be applied to the services that are returned
    * @param pageNo for pagination
    * @return {@link Services}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   Services getServices( ServiceFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all services in chronological order. Returns a list of Action objects.
    *
    * @param pageNo for pagination
    * @return {@link Services}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   Services getServices( Integer pageNo ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Method returns the tags for a specified service
    *
    * @param uuid the id of the service
    * @return {@link Tags}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   List<Tag> getServiceTags( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all tags in chronological order. Returns a list of Tag objects.
    *
    * @param type Type of resource to get tags for
    * @param uuid Identifier of the resource to get tags for
    * @return {@link Tags}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   List<Tag> getTags( TagResourceType type, String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all tags in chronological order. Returns a list of Tag objects.
    *
    * @param type Type of resource to get tags for
    * @param uuid Identifier of the resource to get tags for
    * @param pageNo for pagination
    * @return {@link Tags}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   Tags getTags( TagResourceType type, String uuid, Integer pageNo ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all tags in chronological order. Returns a list of Tag objects.
    *
    * @param type Type of resource to get tags for
    * @param uuid Identifier of the resource to get tags for
    * @param filter Filters that can be applied to the tags that are returned
    * @return {@link Tags}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   List<Tag> getTags( TagResourceType type, String uuid, TagFilter filter ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all tags in chronological order. Returns a list of Tag objects.
    *
    * @param type Type of resource to get tags for
    * @param uuid Identifier of the resource to get tags for
    * @param filter Filters that can be applied to the tags that are returned
    * @param pageNo for pagination
    * @return {@link Tags}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   Tags getTags( TagResourceType type, String uuid, TagFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Method returns complete information for given volume ID
    *
    * @param uuid the id of the volume
    * @return {@link Volume}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   Volume getVolume( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Method returns complete information for given volume group ID
    *
    * @param uuid the id of the volume group
    * @return {@link VolumeGroup}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   VolumeGroup getVolumeGroup( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all volume group in chronological order. Returns a list of Volume Group objects.
    *
    * @return {@link VolumeGroups}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   List<VolumeGroup> getVolumeGroups() throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all volume group in chronological order. Returns a list of Volume Group objects.
    *
    * @param pageNo for pagination
    * @return {@link VolumeGroups}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   VolumeGroups getVolumeGroups( Integer pageNo ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all volume group in chronological order. Returns a list of Volume Group objects.
    *
    * @param filter Filters that can be applied to the volume group that are returned
    * @return {@link VolumeGroups}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   List<VolumeGroup> getVolumeGroups( VolumeGroupFilter filter ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all volume group in chronological order. Returns a list of Volume Group objects.
    *
    * @param filter Filters that can be applied to the volume group that are returned
    * @param pageNo for pagination
    * @return {@link VolumeGroups}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   VolumeGroups getVolumeGroups( VolumeGroupFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all volumes in chronological order. Returns a list of Volume objects.
    *
    * @return {@link Volumes}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   List<Volume> getVolumes() throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all volumes in chronological order. Returns a list of Volume objects.
    *
    * @param pageNo for pagination
    * @return {@link Volumes}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   Volumes getVolumes( Integer pageNo ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all volumes in chronological order. Returns a list of Volume objects.
    *
    * @param filter Filters that can be applied to the volumes that are returned
    * @return {@link Volumes}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   List<Volume> getVolumes( VolumeFilter filter ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all volumes in chronological order. Returns a list of Volume objects.
    *
    * @param filter Filters that can be applied to the volumes that are returned
    * @param pageNo for pagination
    * @return {@link Volumes}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   Volumes getVolumes( VolumeFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Redeploys all containers in the service with the current service configuration.
    *
    * @param service Service object with the UUID of the service to redeployed
    * @return {@link Service}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   Service redeployService( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Starts a stopped container.
    *
    * @param container Container object with the UUID of the container to start
    * @return {@link Container}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   Container startContainer( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Starts all containers in a stopped or partly running service.
    *
    * @param service Service object with the UUID of the service to start
    * @return {@link Service}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   Service startService( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Stops a running container.
    *
    * @param container Container object with the UUID of the container to stop
    * @return {@link Container}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   Container stopContainer( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Stops all containers in a running or partly running service.
    *
    * @param service Service object with the UUID of the service to stop
    * @return {@link Service}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   Service stopService( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Terminates the specified container. This is not reversible. All data stored in the container will be permanently deleted.
    *
    * @param container Container object with the UUID of the container to terminated
    * @return {@link Container}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   Container terminateContainer( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Terminates the specified node. For security reasons, only nodes with no running containers can be terminated,
    * otherwise the API call will fail.
    *
    * @param uuid the id of the node
    * @return {@link NodeCluster}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   Node terminateNode( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Terminates all the nodes in a node cluster and the node cluster itself. This is not reversible.
    *
    * @param uuid the id of the node cluster
    * @return {@link NodeCluster}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   NodeCluster terminateNodeCluster( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Terminate all the containers in a service and the service itself.
    * This is not reversible. All the data stored in all containers of the service will be permanently deleted.
    *
    * @param service Service object with the UUID of the service to terminated
    * @return {@link Service}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   Service terminateService( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Replaces the old tags in the node for the new list provided.
    *
    * @param node Node object with the UUID of the cluster to update
    * @return {@link Node}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   Node updateNode( Node node ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Updates the node cluster details and applies the changes automatically.
    *
    * @param cluster Cluster object with the UUID of the cluster to update set
    * @return {@link NodeCluster}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   NodeCluster updateNodeCluster( NodeCluster cluster ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Updates the service details and applies the changes automatically.
    *
    * @param service Service object with the UUID of the service to update set
    * @return {@link Service}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   Service updateService( Service service ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Upgrades the docker daemon of the node. This will restart your containers on that node.
    *
    * @param uuid the id of the node
    * @return {@link Node}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   Node upgradeDockerOnNode( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Upgrades the Docker Daemon of all the nodes in the cluster.
    *
    * @param uuid the id of the node cluster
    * @return {@link NodeCluster}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   NodeCluster upgradeDockerOnNodeCluster( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Tags the specified resource
    *
    * @param type Type of resource to tag
    * @param uuid the id of the resource
    * @return {@link List<Tag>}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   List<Tag> tag( TagResourceType type, String uuid, Tag... tags ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Deletes a tag from the specified resource
    *
    * @param type Type of resource to tag
    * @param uuid the id of the resource
    * @param name Name of the tag to delete
    * @return {@link Tag}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   Tag deleteTag( TagResourceType type, String uuid, String name ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Lists all current webhook handlers the service has associated to. Returns a list of Service Webhook Handler objects.
    *
    * @param uuid Identifier of the service to list the webhooks of
    * @return {@link WebhookHandler}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   List<WebhookHandler> getWebhooks( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Get all the details of an specific webhook handler 
    *
    * @param serviceUuid Identifier of the service to list the webhooks of
    * @param uuid Identifier of the webhook
    * @return {@link WebhookHandler}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    **/
   WebhookHandler getWebhook( String serviceUuid, String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Creates a new service webhook handler.
    *
    * @param uuid Identifier of the service to list the webhooks of
    * @return {@link WebhookHandler}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   WebhookHandler createWebhook( String uuid ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Creates a new service webhook handler.
    *
    * @param uuid Identifier of the service to list the webhooks of
    * @param name Optional name to assign the new webhook
    * @return {@link WebhookHandler}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   WebhookHandler createWebhook( String uuid, String name ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Deletes specific webhook handler. It will be no longer available to be called.
    *
    * @param webhook Webhook that should be deleted
    * @return {@link WebhookHandler}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   WebhookHandler deleteWebhook( WebhookHandler webhook ) throws TutumException, RequestUnsuccessfulException;

   /**
    * Executes the webhook and redeploys the associated service.
    *
    * @param webhook Webhook that should be called
    * @return {@link WebhookHandler}
    * @throws TutumException
    * @throws RequestUnsuccessfulException
    *
    * @since v1.0
    */
   WebhookHandler callWebhook( WebhookHandler webhook ) throws TutumException, RequestUnsuccessfulException;

}
