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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.pennassurancesoftware.tutum.Tutum;
import com.pennassurancesoftware.tutum.dto.Action;
import com.pennassurancesoftware.tutum.dto.ActionFilter;
import com.pennassurancesoftware.tutum.dto.Actions;
import com.pennassurancesoftware.tutum.dto.Base;
import com.pennassurancesoftware.tutum.dto.Container;
import com.pennassurancesoftware.tutum.dto.ContainerFilter;
import com.pennassurancesoftware.tutum.dto.Containers;
import com.pennassurancesoftware.tutum.dto.Logs;
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
import com.pennassurancesoftware.tutum.dto.WebhookHandlers;
import com.pennassurancesoftware.tutum.exception.RequestUnsuccessfulException;
import com.pennassurancesoftware.tutum.exception.TutumException;
import com.pennassurancesoftware.tutum.type.TagResourceType;
import com.pennassurancesoftware.tutum.util.IdUtils;
import com.pennassurancesoftware.tutum.util.QueryParamBuilder;

/** Tutum API client wrapper methods Implementation */
public class TutumClient implements Tutum {
   public static interface PagingCallback<T extends Base<?>> {
      /**
       * Callback provides the next page based on the page parameter
       * @param pageNo Page to get from the service
       * @return List object for the specified page
       */
      T getNext( Integer pageNo );
   }

   /** Gson Parser instance for deserialize */
   private Gson deserialize;

   /** JSON Parser instance */
   private JsonParser jsonParser;

   private final Logger LOG = LoggerFactory.getLogger( TutumClient.class );

   /** Gson Parser instance for serialize */
   private Gson serialize;

   /** Tutum API Host is <code>dashboard.tutum.co</code> */
   protected String apiHost = "dashboard.tutum.co";

   /** Tutum API version. defaults to v1 from constructor */
   protected String apiVersion;

   /** OAuth Authorization Token for Accessing Tutum API */
   protected String authToken;

   protected HttpClient httpClient;

   public TutumClient( String authToken ) {
      this( "v1", authToken );
   }

   /**
    * Tutum Client Constructor
    *
    * @param apiVersion a {@link String} object
    * @param authToken a {@link String} object
    */
   public TutumClient( String apiVersion, String authToken ) {
      this( apiVersion, authToken, null );
   }

   /**
    * Tutum Client Constructor
    *
    * @param apiVersion a {@link String} object
    * @param authToken a {@link String} object
    * @param httpClient a {@link HttpClient} object
    */
   public TutumClient( String apiVersion, String authToken, HttpClient httpClient ) {
      if( !"v1".equalsIgnoreCase( apiVersion ) ) {
         throw new IllegalArgumentException( "Only API version 1 is supported." );
      }

      this.apiVersion = apiVersion;
      this.authToken = authToken;
      this.httpClient = httpClient;
      initialize();
   }

   @Override
   public WebhookHandler callWebhook( WebhookHandler webhook ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( webhook.getServiceUuid(), "Missing required parameter - URL." );
      checkNullAndThrowError( webhook.getWebhookUuid(), "Missing required parameter - URL." );
      final Object[] params = { webhook.getServiceUuid(), webhook.getWebhookUuid() };
      return ( WebhookHandler )perform( new ApiRequest( ApiAction.CALL_WEBHOOK_HANDLER, params ) ).getData();
   }

   @Override
   public NodeCluster createNodeCluster( NodeCluster cluster ) throws TutumException, RequestUnsuccessfulException {
      if( null == cluster || null == cluster.getName() || null == cluster.getRegion() || null == cluster.getNodeType() ) {
         throw new IllegalArgumentException( "Missing required parameters [Name, Region, Node Type] for create node cluster." );
      }
      return ( NodeCluster )perform( new ApiRequest( ApiAction.CREATE_NODECLUSTER, cluster ) ).getData();
   }

   @Override
   public Service createService( Service service ) throws TutumException, RequestUnsuccessfulException {
      if( null == service || null == service.getImage() || null == service.getName() ) {
         throw new IllegalArgumentException( "Missing required parameters [Image, Name] for create service." );
      }
      return ( Service )perform( new ApiRequest( ApiAction.CREATE_SERVICE, service ) ).getData();
   }

   @Override
   public String createToken() throws TutumException, RequestUnsuccessfulException {
      return ( ( Token )perform( new ApiRequest( ApiAction.CREATE_TOKEN, ( Object )null ) ).getData() ).getToken();
   }

   @Override
   public WebhookHandler createWebhook( String uuid ) throws TutumException, RequestUnsuccessfulException {
      return createWebhook( uuid, "webhook-" + IdUtils.smallRandom() );
   }

   @Override
   public WebhookHandler createWebhook( String uuid, String name ) throws TutumException, RequestUnsuccessfulException {
      final Object data = name != null ? new WebhookHandler( name ) : null;
      final Object[] params = { uuid };
      final WebhookHandler[] result = ( WebhookHandler[] )perform( new ApiRequest( ApiAction.CREATE_WEBHOOK_HANDLER, data, params ) ).getData();
      return result[0];
   }

   @Override
   public Tag deleteTag( TagResourceType type, String uuid, String name ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( type, "Missing required parameter - Resource Type." );
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      checkNullAndThrowError( name, "Missing required parameter - Tag Name." );
      final Object[] params = { type.value(), uuid, name };
      return ( Tag )perform( new ApiRequest( ApiAction.DELETE_TAG, params ) ).getData();
   }

   @Override
   public WebhookHandler deleteWebhook( WebhookHandler webhook ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( webhook.getServiceUuid(), "Missing required parameter - URL." );
      checkNullAndThrowError( webhook.getWebhookUuid(), "Missing required parameter - URL." );
      final Object[] params = { webhook.getServiceUuid(), webhook.getWebhookUuid() };
      return ( WebhookHandler )perform( new ApiRequest( ApiAction.DELETE_WEBHOOK_HANDLER, params ) ).getData();
   }

   @Override
   public Node deployNode( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( Node )perform( new ApiRequest( ApiAction.DEPLOY_NODE, params ) ).getData();
   }

   @Override
   public NodeCluster deployNodeCluster( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( NodeCluster )perform( new ApiRequest( ApiAction.DEPLOY_NODECLUSTER, params ) ).getData();
   }

   @Override
   public Action getAction( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( Action )perform( new ApiRequest( ApiAction.GET_ACTION, params ) ).getData();
   }

   @Override
   public List<Action> getActions() throws TutumException, RequestUnsuccessfulException {
      return getActions( ( ActionFilter )null );
   }

   @Override
   public List<Action> getActions( final ActionFilter filter ) throws TutumException, RequestUnsuccessfulException {
      return getAll( new PagingCallback<Actions>() {
         @Override
         public Actions getNext( Integer pageNo ) {
            return getActions( filter, pageNo );
         }
      } );
   }

   @Override
   public Actions getActions( ActionFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      validatePageNo( pageNo );
      return ( Actions )perform( new ApiRequest( ApiAction.ACTIONS, getQueryParams( pageNo, filter ) ) ).getData();
   }

   @Override
   public Actions getActions( Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      return getActions( null, pageNo );
   }

   /**
    * @return the apiVersion
    */
   public String getApiVersion() {
      return apiVersion;
   }

   /**
    * @return the authToken
    */
   public String getAuthToken() {
      return authToken;
   }

   @Override
   public Container getContainer( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( Container )perform( new ApiRequest( ApiAction.GET_CONTAINER, params ) ).getData();
   }

   @Override
   public String getContainerLogs( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( ( Logs )perform( new ApiRequest( ApiAction.GET_CONTAINER_LOGS, ( Object )null, params ) ).getData() ).getLogs();
   }

   @Override
   public List<Container> getContainers() throws TutumException, RequestUnsuccessfulException {
      return getContainers( ( ContainerFilter )null );
   }

   @Override
   public List<Container> getContainers( final ContainerFilter filter ) throws TutumException, RequestUnsuccessfulException {
      return getAll( new PagingCallback<Containers>() {
         @Override
         public Containers getNext( Integer pageNo ) {
            return getContainers( filter, pageNo );
         }
      } );
   }

   @Override
   public Containers getContainers( ContainerFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      validatePageNo( pageNo );
      return ( Containers )perform( new ApiRequest( ApiAction.CONTAINERS, getQueryParams( pageNo, filter ) ) ).getData();
   }

   @Override
   public Containers getContainers( Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      return getContainers( null, pageNo );
   }

   /**
    * @return the httpClient
    */
   public HttpClient getHttpClient() {
      return httpClient;
   }

   @Override
   public Node getNode( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( Node )perform( new ApiRequest( ApiAction.GET_NODE, params ) ).getData();
   }

   @Override
   public NodeCluster getNodeCluster( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( NodeCluster )perform( new ApiRequest( ApiAction.GET_NODECLUSTER, params ) ).getData();
   }

   @Override
   public List<NodeCluster> getNodeClusters() throws TutumException, RequestUnsuccessfulException {
      return getNodeClusters( ( NodeClusterFilter )null );
   }

   @Override
   public NodeClusters getNodeClusters( Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      return getNodeClusters( null, pageNo );
   }

   @Override
   public List<NodeCluster> getNodeClusters( final NodeClusterFilter filter ) throws TutumException, RequestUnsuccessfulException {
      return getAll( new PagingCallback<NodeClusters>() {
         @Override
         public NodeClusters getNext( Integer pageNo ) {
            return getNodeClusters( filter, pageNo );
         }
      } );
   }

   @Override
   public NodeClusters getNodeClusters( NodeClusterFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      validatePageNo( pageNo );
      return ( NodeClusters )perform( new ApiRequest( ApiAction.NODECLUSTERS, getQueryParams( pageNo, filter ) ) ).getData();
   }

   @Override
   public List<Tag> getNodeClusterTags( String uuid ) throws TutumException, RequestUnsuccessfulException {
      return getTags( TagResourceType.NodeCluster, uuid );
   }

   @Override
   public List<Node> getNodes() throws TutumException, RequestUnsuccessfulException {
      return getNodes( ( NodeFilter )null );
   }

   @Override
   public Nodes getNodes( Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      return getNodes( null, pageNo );
   }

   @Override
   public List<Node> getNodes( final NodeFilter filter ) throws TutumException, RequestUnsuccessfulException {
      return getAll( new PagingCallback<Nodes>() {
         @Override
         public Nodes getNext( Integer pageNo ) {
            return getNodes( filter, pageNo );
         }
      } );
   }

   @Override
   public Nodes getNodes( NodeFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      validatePageNo( pageNo );
      return ( Nodes )perform( new ApiRequest( ApiAction.NODES, getQueryParams( pageNo, filter ) ) ).getData();
   }

   @Override
   public List<Tag> getNodeTags( String uuid ) throws TutumException, RequestUnsuccessfulException {
      return getTags( TagResourceType.Node, uuid );
   }

   @Override
   public NodeType getNodeType( String providerName, String name ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( providerName, "Missing required parameter - Provider Name." );
      checkNullAndThrowError( name, "Missing required parameter - Node Type Name." );
      final Object[] params = { providerName, name };
      return ( NodeType )perform( new ApiRequest( ApiAction.GET_NODETYPE, params ) ).getData();
   }

   @Override
   public List<NodeType> getNodeTypes() throws TutumException, RequestUnsuccessfulException {
      return getNodeTypes( ( NodeTypeFilter )null );
   }

   @Override
   public NodeTypes getNodeTypes( Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      return getNodeTypes( null, pageNo );
   }

   @Override
   public List<NodeType> getNodeTypes( final NodeTypeFilter filter ) throws TutumException, RequestUnsuccessfulException {
      return getAll( new PagingCallback<NodeTypes>() {
         @Override
         public NodeTypes getNext( Integer pageNo ) {
            return getNodeTypes( filter, pageNo );
         }
      } );
   }

   @Override
   public NodeTypes getNodeTypes( NodeTypeFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      validatePageNo( pageNo );
      return ( NodeTypes )perform( new ApiRequest( ApiAction.NODETYPES, getQueryParams( pageNo, filter ) ) ).getData();
   }

   @Override
   public Provider getProvider( String name ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( name, "Missing required parameter - Name." );
      final Object[] params = { name };
      return ( Provider )perform( new ApiRequest( ApiAction.GET_PROVIDER, params ) ).getData();
   }

   @Override
   public List<Provider> getProviders() throws TutumException, RequestUnsuccessfulException {
      return getProviders( ( ProviderFilter )null );
   }

   @Override
   public Providers getProviders( Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      return getProviders( null, pageNo );
   }

   @Override
   public List<Provider> getProviders( final ProviderFilter filter ) throws TutumException, RequestUnsuccessfulException {
      return getAll( new PagingCallback<Providers>() {
         @Override
         public Providers getNext( Integer pageNo ) {
            return getProviders( filter, pageNo );
         }
      } );
   }

   @Override
   public Providers getProviders( ProviderFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      validatePageNo( pageNo );
      return ( Providers )perform( new ApiRequest( ApiAction.PROVIDERS, getQueryParams( pageNo, filter ) ) ).getData();
   }

   @Override
   public Region getRegion( String providerName, String name ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( providerName, "Missing required parameter - Provider Name." );
      checkNullAndThrowError( name, "Missing required parameter - Region Name." );
      final Object[] params = { providerName, name };
      return ( Region )perform( new ApiRequest( ApiAction.GET_REGION, params ) ).getData();
   }

   @Override
   public List<Region> getRegions() throws TutumException, RequestUnsuccessfulException {
      return getRegions( ( RegionFilter )null );
   }

   @Override
   public Regions getRegions( Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      return getRegions( null, pageNo );
   }

   @Override
   public List<Region> getRegions( final RegionFilter filter ) throws TutumException, RequestUnsuccessfulException {
      return getAll( new PagingCallback<Regions>() {
         @Override
         public Regions getNext( Integer pageNo ) {
            return getRegions( filter, pageNo );
         }
      } );
   }

   @Override
   public Regions getRegions( RegionFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      validatePageNo( pageNo );
      return ( Regions )perform( new ApiRequest( ApiAction.REGIONS, getQueryParams( pageNo, filter ) ) ).getData();
   }

   @Override
   public Service getService( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( Service )perform( new ApiRequest( ApiAction.GET_SERVICE, params ) ).getData();
   }

   @Override
   public String getServiceLogs( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( ( Logs )perform( new ApiRequest( ApiAction.GET_SERVICE_LOGS, ( Object )null, params ) ).getData() ).getLogs();
   }

   @Override
   public List<Service> getServices() throws TutumException, RequestUnsuccessfulException {
      return getServices( ( ServiceFilter )null );
   }

   @Override
   public Services getServices( Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      return getServices( null, pageNo );
   }

   @Override
   public List<Service> getServices( final ServiceFilter filter ) throws TutumException, RequestUnsuccessfulException {
      return getAll( new PagingCallback<Services>() {
         @Override
         public Services getNext( Integer pageNo ) {
            return getServices( filter, pageNo );
         }
      } );
   }

   @Override
   public Services getServices( ServiceFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      validatePageNo( pageNo );
      return ( Services )perform( new ApiRequest( ApiAction.SERVICES, getQueryParams( pageNo, filter ) ) ).getData();
   }

   @Override
   public List<Tag> getServiceTags( String uuid ) throws TutumException, RequestUnsuccessfulException {
      return getTags( TagResourceType.Service, uuid );
   }

   @Override
   public List<Tag> getTags( final TagResourceType type, final String uuid ) throws TutumException, RequestUnsuccessfulException {
      return getTags( type, uuid, ( TagFilter )null );
   }

   @Override
   public Tags getTags( TagResourceType type, String uuid, Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      return getTags( type, uuid, null, pageNo );
   }

   @Override
   public List<Tag> getTags( final TagResourceType type, final String uuid, final TagFilter filter ) throws TutumException, RequestUnsuccessfulException {
      return getAll( new PagingCallback<Tags>() {
         @Override
         public Tags getNext( Integer pageNo ) {
            return getTags( type, uuid, filter, pageNo );
         }
      } );
   }

   @Override
   public Tags getTags( TagResourceType type, String uuid, TagFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      validatePageNo( pageNo );
      checkNullAndThrowError( type, "Missing required parameter - Resource Type." );
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { type.value(), uuid };
      return ( Tags )perform( new ApiRequest( ApiAction.TAGS, params, getQueryParams( pageNo, filter ) ) ).getData();
   }

   @Override
   public Volume getVolume( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( Volume )perform( new ApiRequest( ApiAction.GET_VOLUME, params ) ).getData();
   }

   @Override
   public VolumeGroup getVolumeGroup( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( VolumeGroup )perform( new ApiRequest( ApiAction.GET_VOLUMEGROUP, params ) ).getData();
   }

   @Override
   public List<VolumeGroup> getVolumeGroups() throws TutumException, RequestUnsuccessfulException {
      return getVolumeGroups( ( VolumeGroupFilter )null );
   }

   @Override
   public VolumeGroups getVolumeGroups( Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      return getVolumeGroups( null, pageNo );
   }

   @Override
   public List<VolumeGroup> getVolumeGroups( final VolumeGroupFilter filter ) throws TutumException, RequestUnsuccessfulException {
      return getAll( new PagingCallback<VolumeGroups>() {
         @Override
         public VolumeGroups getNext( Integer pageNo ) {
            return getVolumeGroups( filter, pageNo );
         }
      } );
   }

   @Override
   public VolumeGroups getVolumeGroups( VolumeGroupFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      validatePageNo( pageNo );
      return ( VolumeGroups )perform( new ApiRequest( ApiAction.VOLUMEGROUPS, getQueryParams( pageNo, filter ) ) ).getData();
   }

   @Override
   public List<Volume> getVolumes() throws TutumException, RequestUnsuccessfulException {
      return getVolumes( ( VolumeFilter )null );
   }

   @Override
   public Volumes getVolumes( Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      return getVolumes( null, pageNo );
   }

   @Override
   public List<Volume> getVolumes( final VolumeFilter filter ) throws TutumException, RequestUnsuccessfulException {
      return getAll( new PagingCallback<Volumes>() {
         @Override
         public Volumes getNext( Integer pageNo ) {
            return getVolumes( filter, pageNo );
         }
      } );
   }

   @Override
   public Volumes getVolumes( VolumeFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      validatePageNo( pageNo );
      return ( Volumes )perform( new ApiRequest( ApiAction.VOLUMES, getQueryParams( pageNo, filter ) ) ).getData();
   }

   @Override
   public WebhookHandler getWebhook( String serviceUuid, String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( serviceUuid, "Missing required parameter - Service UUID." );
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { serviceUuid, uuid };
      return ( WebhookHandler )perform( new ApiRequest( ApiAction.GET_WEBHOOK_HANDLER, params ) ).getData();
   }

   @Override
   public List<WebhookHandler> getWebhooks( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return getAll( new PagingCallback<WebhookHandlers>() {
         @Override
         public WebhookHandlers getNext( Integer pageNo ) {
            return ( WebhookHandlers )perform( new ApiRequest( ApiAction.WEBHOOK_HANDLERS, params ) ).getData();
         }
      } );
   }

   @Override
   public Service redeployService( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( Service )perform( new ApiRequest( ApiAction.REDEPLOY_SERVICE, ( Object )null, params ) ).getData();
   }

   /**
    * @param apiVersion the apiVersion to set
    */
   public void setApiVersion( String apiVersion ) {
      this.apiVersion = apiVersion;
   }

   /**
    * @param authToken the authToken to set
    */
   public void setAuthToken( String authToken ) {
      this.authToken = authToken;
   }

   /**
    * @param httpClient the httpClient to set
    */
   public void setHttpClient( HttpClient httpClient ) {
      this.httpClient = httpClient;
   }

   @Override
   public Container startContainer( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( Container )perform( new ApiRequest( ApiAction.START_CONTAINER, ( Object )null, params ) ).getData();
   }

   @Override
   public Service startService( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( Service )perform( new ApiRequest( ApiAction.START_SERVICE, ( Object )null, params ) ).getData();
   }

   @Override
   public Container stopContainer( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( Container )perform( new ApiRequest( ApiAction.STOP_CONTAINER, ( Object )null, params ) ).getData();
   }

   @Override
   public Service stopService( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( Service )perform( new ApiRequest( ApiAction.STOP_SERVICE, ( Object )null, params ) ).getData();
   }

   @Override
   public List<Tag> tag( TagResourceType type, String uuid, Tag... tags ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( type, "Missing required parameter - Resource Type." );
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      checkNullAndThrowError( tags, "Missing required parameter - Tag." );
      if( tags.length > 1 ) {
         throw new RuntimeException( "Only one tag is supported with the current API binding" );
      }
      final List<Tag> result = new ArrayList<Tag>();
      if( tags.length == 1 ) {
         final Object[] params = { type.value(), uuid };
         result.addAll( Arrays.asList( ( Tag[] )perform( new ApiRequest( ApiAction.TAG_RESOURCE, tags[0], params ) ).getData() ) );
      }
      return result;
   }

   @Override
   public Container terminateContainer( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( Container )perform( new ApiRequest( ApiAction.TERMINATE_CONTAINER, params ) ).getData();
   }

   @Override
   public Node terminateNode( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( Node )perform( new ApiRequest( ApiAction.TERMINATE_NODE, params ) ).getData();
   }

   @Override
   public NodeCluster terminateNodeCluster( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( NodeCluster )perform( new ApiRequest( ApiAction.TERMINATE_NODECLUSTER, params ) ).getData();
   }

   @Override
   public Service terminateService( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( Service )perform( new ApiRequest( ApiAction.TERMINATE_SERVICE, params ) ).getData();
   }

   @Override
   public Node updateNode( Node node ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( node.getUuid(), "Missing required parameter - UUID." );
      final Object[] params = { node.getUuid() };
      return ( Node )perform( new ApiRequest( ApiAction.UPDATE_NODE, node, params ) ).getData();
   }

   @Override
   public NodeCluster updateNodeCluster( NodeCluster cluster ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( cluster.getUuid(), "Missing required parameter - UUID." );
      NodeCluster updateCluster = new NodeCluster();
      updateCluster.setUuid( cluster.getUuid() );
      updateCluster.setTags( cluster.getTags() );
      updateCluster.setTargetNumNodes( cluster.getTargetNumNodes() );

      final Object[] params = { updateCluster.getUuid() };
      return ( NodeCluster )perform( new ApiRequest( ApiAction.UPDATE_NODECLUSTER, updateCluster, params ) ).getData();
   }

   @Override
   public Service updateService( Service service ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( service.getUuid(), "Missing required parameter - UUID." );
      final Service updateService = new Service();
      updateService.setAutodestroy(service.getAutodestroy());
      updateService.setAutorestart(service.getAutorestart());
      updateService.setBindings(service.getBindings());
      updateService.setContainerEnvvars(service.getContainerEnvvars());
      updateService.setContainerPorts(service.getContainerPorts());
      updateService.setCpuShares(service.getCpuShares());
      updateService.setDeploymentStrategy(service.getDeploymentStrategy());
      updateService.setEntrypoint(service.getEntrypoint());
      updateService.setImage(service.getImage());
      updateService.setImageName(service.getImageName());
      updateService.setImageTag(service.getImageTag());
      updateService.setLinkedFromServices(service.getLinkedFromServices());
      updateService.setLinkedToService(service.getLinkedToService());
      updateService.setLinkVariables(service.getLinkVariables());
      updateService.setMemory(service.getMemory());
      updateService.setPrivileged(service.getPrivileged());
      updateService.setResourceUri(service.getResourceUri());
      updateService.setRoles(service.getRoles());
      updateService.setRunCommand(service.getRunCommand());
      updateService.setRunningNumContainers(service.getRunningNumContainers());
      updateService.setSequentialDeployment(service.getSequentialDeployment());
      updateService.setTags(service.getTags());
      updateService.setTargetNumContainers(service.getTargetNumContainers());
      updateService.setUniqueName(service.getUniqueName());
      updateService.setUuid(service.getUuid());

      final Object[] params = { service.getUuid() };
      return ( Service )perform( new ApiRequest( ApiAction.UPDATE_SERVICE, updateService, params ) ).getData();
   }

   @Override
   public Node upgradeDockerOnNode( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( Node )perform( new ApiRequest( ApiAction.UPGRADE_DOCKER_NODE, params ) ).getData();
   }

   @Override
   public NodeCluster upgradeDockerOnNodeCluster( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( NodeCluster )perform( new ApiRequest( ApiAction.UPGRADE_DOCKER_NODECLUSTER, params ) ).getData();
   }

   @SuppressWarnings("unused")
   private void checkEmptyAndThrowError( String str, String msg ) {
      if( StringUtils.isEmpty( str ) ) {
         LOG.error( msg );
         throw new IllegalArgumentException( msg );
      }
   }

   private void checkNullAndThrowError( Object val, String msg ) {
      if( null == val ) {
         LOG.error( msg );
         throw new IllegalArgumentException( msg );
      }
   }

   private String createPath( ApiRequest request ) {
      String path = Constants.URL_PATH_SEPARATOR + "api" + Constants.URL_PATH_SEPARATOR + apiVersion + request.getApiAction().getPath();
      return ( null == request.getParams() ? path : String.format( path, request.getParams() ) );
   }

   private StringEntity createRequestData( ApiRequest request ) {
      StringEntity data = null;
      if( null != request.getData() ) {
         final String inputData = serialize.toJson( request.getData() );
         data = new StringEntity( inputData, ContentType.create( Constants.JSON_CONTENT_TYPE ) );
      }
      return data;
   }

   private URI createUri( ApiRequest request ) {
      URIBuilder ub = new URIBuilder();
      ub.setScheme( Constants.HTTPS_SCHEME );
      ub.setHost( apiHost );
      ub.setPath( createPath( request ) );

      for( String paramName : request.getQueryParams().keySet() ) {
         final List<String> values = request.getQueryParams().get( paramName );
         for( String value : values ) {
            ub.setParameter( paramName, value );
         }
      }

      URI uri = null;
      try {
         uri = ub.build();
      }
      catch( URISyntaxException use ) {
         LOG.error( use.getMessage(), use );
      }

      return uri;
   }

   private String doDelete( URI uri ) throws TutumException, RequestUnsuccessfulException {
      HttpDelete delete = new HttpDelete( uri );
      delete.setHeaders( getRequestHeaders() );
      delete.setHeader( HttpHeaders.CONTENT_TYPE, Constants.FORM_URLENCODED_CONTENT_TYPE );
      return executeHttpRequest( delete );
   }

   private String doGet( URI uri ) throws TutumException, RequestUnsuccessfulException {
      HttpGet get = new HttpGet( uri );
      get.setHeaders( getRequestHeaders() );
      return executeHttpRequest( get );
   }

   private String doPatch( URI uri, StringEntity entity ) throws TutumException, RequestUnsuccessfulException {
      HttpPatch patch = new HttpPatch( uri );
      patch.setHeaders( getRequestHeaders() );

      if( null != entity ) {
         patch.setEntity( entity );
      }

      return executeHttpRequest( patch );
   }

   private String doPost( URI uri, StringEntity entity ) throws TutumException, RequestUnsuccessfulException {
      final HttpPost post = new HttpPost( uri );
      post.setHeaders( getRequestHeaders() );

      if( null != entity ) {
         post.setEntity( entity );
         LOG.debug( "POST Message: {}", readString( entity ) );
      }

      return executeHttpRequest( post );
   }

   private String doPut( URI uri, StringEntity entity ) throws TutumException, RequestUnsuccessfulException {
      HttpPut put = new HttpPut( uri );
      put.setHeaders( getRequestHeaders() );

      if( null != entity ) {
         put.setEntity( entity );
      }

      return executeHttpRequest( put );
   }

   private String evaluateResponse( HttpRequestBase request, HttpResponse httpResponse ) throws TutumException {
      int statusCode = httpResponse.getStatusLine().getStatusCode();
      String response = "";

      if( HttpStatus.SC_OK == statusCode || HttpStatus.SC_CREATED == statusCode || HttpStatus.SC_ACCEPTED == statusCode ) {
         response = httpResponseToString( httpResponse );
         if( LOG.isDebugEnabled() ) {
            String jsonStr = response;
            jsonStr = jsonStr == null || "".equals( jsonStr ) ? "{}" : jsonStr;
            LOG.debug( "Target URL: {}", request.getURI() );
            LOG.debug( "JSON Message: {}", StringUtils.defaultIfEmpty( getRequestMessage( request ), "N/A" ) );
            LOG.debug( "JSON Response: {}", jsonStr );
         }
      }
      else if( HttpStatus.SC_NO_CONTENT == statusCode ) {
         // in a way its always true from client perspective if there is no exception.
         response = String.format( Constants.NO_CONTENT_JSON_STRUCT, statusCode );
      }

      if( ( statusCode >= 400 && statusCode < 510 ) ) {
         String jsonStr = httpResponseToString( httpResponse );
         jsonStr = jsonStr == null || "".equals( jsonStr ) ? "{}" : jsonStr;
         LOG.error( "Target URL: {}", request.getURI() );
         LOG.error( "JSON Message: {}", getRequestMessage( request ) );
         LOG.error( "JSON Response: {}", jsonStr );

         final JsonObject jsonObj = jsonParser.parse( jsonStr ).getAsJsonObject();
         final String message = jsonObj.has( "error" ) ? jsonObj.get( "error" ).getAsString() : jsonStr;
         String errorMsg = String.format( "\nHTTP Status Code: %s\nError Message: %s", statusCode, message );
         LOG.debug( errorMsg );

         throw new TutumException( errorMsg, "N/A", statusCode );
      }

      return response;
   }

   private String executeHttpRequest( HttpRequestBase request ) throws TutumException, RequestUnsuccessfulException {
      String response = "";
      try {
         final HttpResponse httpResponse = httpClient.execute( request );
         LOG.debug( "HTTP Response Object:: " + httpResponse );
         response = evaluateResponse( request, httpResponse );
         LOG.debug( "Parsed Response:: " + response );
      }
      catch( ClientProtocolException cpe ) {
         throw new RequestUnsuccessfulException( cpe.getMessage(), cpe );
      }
      catch( IOException ioe ) {
         throw new RequestUnsuccessfulException( ioe.getMessage(), ioe );
      }
      finally {
         request.releaseConnection();
      }
      return response;
   }

   private <T, T2 extends Base<T>> List<T> getAll( PagingCallback<T2> callback ) {
      final List<T> result = new ArrayList<T>();
      int pageNo = 1;
      boolean continuePaging = true;
      while( continuePaging ) {
         Base<T> current = callback.getNext( pageNo );
         result.addAll( current.getObjects() );
         if( current.getMeta().getNext() != null ) {
            pageNo++;
         }
         else {
            continuePaging = false;
         }
      }
      return result;
   }

   @SuppressWarnings("unused")
   private String getDateString( String epochString, String dateFormat ) {
      long epoch = Long.parseLong( epochString );
      Date expiry = new Date( epoch * 1000 );

      SimpleDateFormat formatter = new SimpleDateFormat( dateFormat );
      String dateString = formatter.format( expiry );
      LOG.debug( dateString );
      return dateString;
   }

   private Map<String, List<String>> getQueryParams( Integer pageNo, Object filter ) {
      final Map<String, List<String>> result = new QueryParamBuilder().setDateFormat( Constants.QUERY_PARAM_DATE_FORMAT ).createQueryParams( filter );;
      if( pageNo > 1 ) {
         final Integer offset = ( pageNo - 1 ) * Constants.DEFAULT_LIMIT;
         result.put( Constants.PARAM_OFFSET, Arrays.asList( offset.toString() ) );
      }
      return result;
   }

   private Header[] getRequestHeaders() {
      Header[] headers = { new BasicHeader( "X-User-Agent", "Tutum API Client by PennAssuranceSoftware.com" ),
            new BasicHeader( "Content-Type", Constants.JSON_CONTENT_TYPE ),
            new BasicHeader( "Authorization", "ApiKey " + authToken ) };
      return headers;
   }

   private String getRequestMessage( HttpRequestBase request ) {
      String result = null;
      if( request instanceof HttpEntityEnclosingRequestBase ) {
         final HttpEntity entity = ( ( HttpEntityEnclosingRequestBase )request ).getEntity();
         result = readString( entity );
      }
      return result;
   }

   private String httpResponseToString( HttpResponse httpResponse ) {
      String response = "";
      if( null != httpResponse.getEntity() ) {
         try {
            response = EntityUtils.toString( httpResponse.getEntity(), Constants.UTF_8 );
         }
         catch( ParseException pe ) {
            LOG.error( pe.getMessage(), pe );
         }
         catch( IOException ioe ) {
            LOG.error( ioe.getMessage(), ioe );
         }
      }
      return response;
   }

   private void initialize() {
      this.deserialize = new GsonBuilder().setDateFormat( Constants.DATE_FORMAT ).create();

      this.serialize = new GsonBuilder().setDateFormat( Constants.DATE_FORMAT ).excludeFieldsWithoutExposeAnnotation().create();

      this.jsonParser = new JsonParser();

      if( null == this.httpClient ) {
         this.httpClient = new DefaultHttpClient( new PoolingClientConnectionManager() );
      }
   }

   private ApiResponse perform( ApiRequest request ) throws TutumException, RequestUnsuccessfulException {

      URI uri = createUri( request );
      String response = null;

      if( RequestMethod.GET == request.getMethod() ) {
         response = doGet( uri );
      }
      else if( RequestMethod.POST == request.getMethod() ) {
         response = doPost( uri, createRequestData( request ) );
      }
      else if( RequestMethod.PUT == request.getMethod() ) {
         response = doPut( uri, createRequestData( request ) );
      }
      else if( RequestMethod.PATCH == request.getMethod() ) {
         response = doPatch( uri, createRequestData( request ) );
      }
      else if( RequestMethod.DELETE == request.getMethod() ) {
         response = doDelete( uri );
      }

      final ApiResponse apiResponse = new ApiResponse( request.getApiAction(), true );

      try {
         apiResponse.setData( deserialize.fromJson( response, request.getClazz() ) );
      }
      catch( JsonSyntaxException jse ) {
         LOG.error( "Error occurred while parsing response: {}", response, jse );
         apiResponse.setRequestSuccess( false );
      }

      LOG.debug( "API Response:: " + apiResponse.toString() );

      return apiResponse;
   }

   private String readString( HttpEntity entity ) {
      String result = null;
      if( entity != null && entity instanceof StringEntity ) {
         result = readString( ( StringEntity )entity );
      }
      return result;
   }

   private String readString( InputStream inputStream ) {
      try {
         final ByteArrayOutputStream into = new ByteArrayOutputStream();
         byte[] buf = new byte[4096];
         for( int n; 0 < ( n = inputStream.read( buf ) ); ) {
            into.write( buf, 0, n );
         }
         into.close();
         return new String( into.toByteArray(), "UTF-8" ); // Or whatever encoding
      }
      catch( Exception exception ) {
         throw new RuntimeException( "Error reading InputStream", exception );
      }
   }

   private String readString( StringEntity entity ) {
      try {
         return entity != null ? readString( entity.getContent() ) : null;
      }
      catch( Exception exception ) {
         throw new RuntimeException( "Error reading String Entity", exception );
      }
   }

   private void validatePageNo( Integer pageNo ) {
      checkNullAndThrowError( pageNo, "Missing required parameter - pageNo." );
   }
}
