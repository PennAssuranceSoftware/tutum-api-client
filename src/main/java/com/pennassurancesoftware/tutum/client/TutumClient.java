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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
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
import com.pennassurancesoftware.tutum.dto.Container;
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
import com.pennassurancesoftware.tutum.dto.Services;
import com.pennassurancesoftware.tutum.dto.Token;
import com.pennassurancesoftware.tutum.dto.Volume;
import com.pennassurancesoftware.tutum.dto.VolumeFilter;
import com.pennassurancesoftware.tutum.dto.VolumeGroup;
import com.pennassurancesoftware.tutum.dto.VolumeGroupFilter;
import com.pennassurancesoftware.tutum.dto.VolumeGroups;
import com.pennassurancesoftware.tutum.dto.Volumes;
import com.pennassurancesoftware.tutum.exception.RequestUnsuccessfulException;
import com.pennassurancesoftware.tutum.exception.TutumException;
import com.pennassurancesoftware.tutum.util.QueryParamBuilder;

/** Tutum API client wrapper methods Implementation */
public class TutumClient implements Tutum {
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
   public NodeCluster createNodeCluster( NodeCluster cluster ) throws TutumException, RequestUnsuccessfulException {
      if( null == cluster
            || null == cluster.getName()
            || null == cluster.getRegion()
            || null == cluster.getNodeType() ) {
         throw new IllegalArgumentException( "Missing required parameters [Name, Region, Node Type] for create node cluster." );
      }
      return ( NodeCluster )perform( new ApiRequest( ApiAction.CREATE_NODECLUSTER, cluster ) ).getData();
   }

   @Override
   public Service createService( Service service ) throws TutumException, RequestUnsuccessfulException {
      if( null == service
            || null == service.getImage()
            || null == service.getName()
            || null == service.getTargetNumContainers() ) {
         throw new IllegalArgumentException( "Missing required parameters [Image, Name, Targe Number Of Containers] for create service." );
      }
      return ( Service )perform( new ApiRequest( ApiAction.CREATE_SERVICE, service ) ).getData();
   }

   @Override
   public String createToken() throws TutumException, RequestUnsuccessfulException {
      return ( ( Token )perform( new ApiRequest( ApiAction.CREATE_TOKEN, ( Object )null ) ).getData() ).getToken();
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
   public Actions getActions() throws TutumException, RequestUnsuccessfulException {
      return getActions( 1 );
   }

   @Override
   public Actions getActions( ActionFilter filter ) throws TutumException, RequestUnsuccessfulException {
      return getActions( filter, 1 );
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
   public NodeClusters getNodeClusters() throws TutumException, RequestUnsuccessfulException {
      return getNodeClusters( 1 );
   }

   @Override
   public NodeClusters getNodeClusters( Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      return getNodeClusters( null, pageNo );
   }

   @Override
   public NodeClusters getNodeClusters( NodeClusterFilter filter ) throws TutumException, RequestUnsuccessfulException {
      return getNodeClusters( filter, 1 );
   }

   @Override
   public NodeClusters getNodeClusters( NodeClusterFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      validatePageNo( pageNo );
      return ( NodeClusters )perform( new ApiRequest( ApiAction.NODECLUSTERS, getQueryParams( pageNo, filter ) ) ).getData();
   }

   @Override
   public Nodes getNodes() throws TutumException, RequestUnsuccessfulException {
      return getNodes( 1 );
   }

   @Override
   public Nodes getNodes( Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      return getNodes( null, pageNo );
   }

   @Override
   public Nodes getNodes( NodeFilter filter ) throws TutumException, RequestUnsuccessfulException {
      return getNodes( filter, 1 );
   }

   @Override
   public Nodes getNodes( NodeFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      validatePageNo( pageNo );
      return ( Nodes )perform( new ApiRequest( ApiAction.NODES, getQueryParams( pageNo, filter ) ) ).getData();
   }

   @Override
   public NodeType getNodeType( String providerName, String name ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( providerName, "Missing required parameter - Provider Name." );
      checkNullAndThrowError( name, "Missing required parameter - Node Type Name." );
      final Object[] params = { providerName, name };
      return ( NodeType )perform( new ApiRequest( ApiAction.GET_NODETYPE, params ) ).getData();
   }

   @Override
   public NodeTypes getNodeTypes() throws TutumException, RequestUnsuccessfulException {
      return getNodeTypes( 1 );
   }

   @Override
   public NodeTypes getNodeTypes( Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      return getNodeTypes( null, pageNo );
   }

   @Override
   public NodeTypes getNodeTypes( NodeTypeFilter filter ) throws TutumException, RequestUnsuccessfulException {
      return getNodeTypes( filter, 1 );
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
   public Providers getProviders() throws TutumException, RequestUnsuccessfulException {
      return getProviders( 1 );
   }

   @Override
   public Providers getProviders( Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      return getProviders( null, pageNo );
   }

   @Override
   public Providers getProviders( ProviderFilter filter ) throws TutumException, RequestUnsuccessfulException {
      return getProviders( filter, 1 );
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
   public Regions getRegions() throws TutumException, RequestUnsuccessfulException {
      return getRegions( 1 );
   }

   @Override
   public Regions getRegions( Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      return getRegions( null, pageNo );
   }

   @Override
   public Regions getRegions( RegionFilter filter ) throws TutumException, RequestUnsuccessfulException {
      return getRegions( filter, 1 );
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
   public Services getServices() throws TutumException, RequestUnsuccessfulException {
      return getServices( 1 );
   }

   @Override
   public Services getServices( ActionFilter filter ) throws TutumException, RequestUnsuccessfulException {
      return getServices( filter, 1 );
   }

   @Override
   public Services getServices( ActionFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      validatePageNo( pageNo );
      return ( Services )perform( new ApiRequest( ApiAction.SERVICES, getQueryParams( pageNo, filter ) ) ).getData();
   }

   @Override
   public Services getServices( Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      return getServices( null, pageNo );
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
   public Service startService( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( Service )perform( new ApiRequest( ApiAction.START_SERVICE, ( Object )null, params ) ).getData();
   }

   @Override
   public Service stopService( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( Service )perform( new ApiRequest( ApiAction.STOP_SERVICE, ( Object )null, params ) ).getData();
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
      return ( Node )perform( new ApiRequest( ApiAction.UPDATE_NODE, params ) ).getData();
   }

   @Override
   public NodeCluster updateNodeCluster( NodeCluster cluster ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( cluster.getUuid(), "Missing required parameter - UUID." );
      final Object[] params = { cluster.getUuid() };
      return ( NodeCluster )perform( new ApiRequest( ApiAction.UPDATE_NODECLUSTER, params ) ).getData();
   }

   @Override
   public Service updateService( Service service ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( service.getUuid(), "Missing required parameter - UUID." );
      final Object[] params = { service.getUuid() };
      return ( Service )perform( new ApiRequest( ApiAction.UPDATE_SERVICE, params ) ).getData();
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

   private String evaluateResponse( HttpResponse httpResponse ) throws TutumException {
      int statusCode = httpResponse.getStatusLine().getStatusCode();
      String response = "";

      if( HttpStatus.SC_OK == statusCode || HttpStatus.SC_CREATED == statusCode
            || HttpStatus.SC_ACCEPTED == statusCode ) {
         response = httpResponseToString( httpResponse );
      }
      else if( HttpStatus.SC_NO_CONTENT == statusCode ) {
         // in a way its always true from client perspective if there is no exception.
         response = String.format( Constants.NO_CONTENT_JSON_STRUCT, statusCode );
      }

      if( ( statusCode >= 400 && statusCode < 510 ) ) {
         String jsonStr = httpResponseToString( httpResponse );
         jsonStr = jsonStr == null || "".equals( jsonStr ) ? "{}" : jsonStr;
         LOG.error( "JSON Response: " + jsonStr );

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
         response = evaluateResponse( httpResponse );
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
      result.put( Constants.PARAM_PAGE_NO, Arrays.asList( pageNo.toString() ) );
      return result;
   }

   private Header[] getRequestHeaders() {
      Header[] headers =
      { new BasicHeader( "X-User-Agent", "Tutum API Client by myjeeva.com" ),
            new BasicHeader( "Content-Type", Constants.JSON_CONTENT_TYPE ),
            new BasicHeader( "Authorization", "ApiKey " + authToken ) };
      return headers;
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

   private ApiResponse perform( ApiRequest request ) throws TutumException,
         RequestUnsuccessfulException {

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
         return readString( entity.getContent() );
      }
      catch( Exception exception ) {
         throw new RuntimeException( "Error reading String Entity", exception );
      }
   }

   private void validatePageNo( Integer pageNo ) {
      checkNullAndThrowError( pageNo, "Missing required parameter - pageNo." );
   }

   @Override
   public Containers getContainers() throws TutumException, RequestUnsuccessfulException {
      return getContainers( 1 );
   }

   @Override
   public Containers getContainers( ActionFilter filter ) throws TutumException, RequestUnsuccessfulException {
      return getContainers( filter, 1 );
   }

   @Override
   public Containers getContainers( ActionFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      validatePageNo( pageNo );
      return ( Containers )perform( new ApiRequest( ApiAction.CONTAINERS, getQueryParams( pageNo, filter ) ) ).getData();
   }

   @Override
   public Containers getContainers( Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      return getContainers( null, pageNo );
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
   public Container startContainer( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( Container )perform( new ApiRequest( ApiAction.START_CONTAINER, ( Object )null, params ) ).getData();
   }

   @Override
   public Container stopContainer( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( Container )perform( new ApiRequest( ApiAction.STOP_CONTAINER, ( Object )null, params ) ).getData();
   }

   @Override
   public Container terminateContainer( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( Container )perform( new ApiRequest( ApiAction.TERMINATE_CONTAINER, params ) ).getData();
   }

   @Override
   public VolumeGroup getVolumeGroup( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( VolumeGroup )perform( new ApiRequest( ApiAction.GET_VOLUMEGROUP, params ) ).getData();
   }

   @Override
   public VolumeGroups getVolumeGroups() throws TutumException, RequestUnsuccessfulException {
      return getVolumeGroups( 1 );
   }

   @Override
   public VolumeGroups getVolumeGroups( VolumeGroupFilter filter ) throws TutumException, RequestUnsuccessfulException {
      return getVolumeGroups( filter, 1 );
   }

   @Override
   public VolumeGroups getVolumeGroups( VolumeGroupFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      validatePageNo( pageNo );
      return ( VolumeGroups )perform( new ApiRequest( ApiAction.VOLUMEGROUPS, getQueryParams( pageNo, filter ) ) ).getData();
   }

   @Override
   public VolumeGroups getVolumeGroups( Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      return getVolumeGroups( null, pageNo );
   }

   @Override
   public Volume getVolume( String uuid ) throws TutumException, RequestUnsuccessfulException {
      checkNullAndThrowError( uuid, "Missing required parameter - UUID." );
      final Object[] params = { uuid };
      return ( Volume )perform( new ApiRequest( ApiAction.GET_VOLUME, params ) ).getData();
   }

   @Override
   public Volumes getVolumes() throws TutumException, RequestUnsuccessfulException {
      return getVolumes( 1 );
   }

   @Override
   public Volumes getVolumes( Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      return getVolumes( null, pageNo );
   }

   @Override
   public Volumes getVolumes( VolumeFilter filter ) throws TutumException, RequestUnsuccessfulException {
      return getVolumes( filter, 1 );
   }

   @Override
   public Volumes getVolumes( VolumeFilter filter, Integer pageNo ) throws TutumException, RequestUnsuccessfulException {
      validatePageNo( pageNo );
      return ( Volumes )perform( new ApiRequest( ApiAction.VOLUMES, getQueryParams( pageNo, filter ) ) ).getData();
   }
}
