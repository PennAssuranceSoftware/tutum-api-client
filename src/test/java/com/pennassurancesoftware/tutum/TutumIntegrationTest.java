package com.pennassurancesoftware.tutum;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.pennassurancesoftware.tutum.client.TutumClient;
import com.pennassurancesoftware.tutum.dto.Action;
import com.pennassurancesoftware.tutum.dto.ActionFilter;
import com.pennassurancesoftware.tutum.dto.Actions;
import com.pennassurancesoftware.tutum.dto.Container;
import com.pennassurancesoftware.tutum.dto.Containers;
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
import com.pennassurancesoftware.tutum.dto.Volume;
import com.pennassurancesoftware.tutum.dto.VolumeGroup;
import com.pennassurancesoftware.tutum.dto.VolumeGroups;
import com.pennassurancesoftware.tutum.dto.Volumes;
import com.pennassurancesoftware.tutum.dto.WebhookHandler;
import com.pennassurancesoftware.tutum.dto.WebhookHandlers;
import com.pennassurancesoftware.tutum.type.ContainerState;
import com.pennassurancesoftware.tutum.type.NodeClusterState;
import com.pennassurancesoftware.tutum.type.ServiceState;
import com.pennassurancesoftware.tutum.type.TagResourceType;
import com.pennassurancesoftware.tutum.util.IdUtils;

public class TutumIntegrationTest {
   private static final Logger LOG = LoggerFactory.getLogger( TutumIntegrationTest.class );

   /** Fill in your auth token here should be in the format: [USER]:[API_KEY] */
   private Tutum apiClient = new TutumClient( "" );

   @Test(groups = { "integration" }, enabled = false)
   public void testActions() throws Exception {
      final Actions actions = apiClient.getActions( 1 );

      Assert.assertNotNull( actions );
      Assert.assertTrue( ( actions.getObjects().size() > 0 ) );

      for( Action action : actions.getObjects() ) {
         LOG.info( action.toString() );
      }

   }

   @Test(groups = { "integration" }, enabled = false)
   public void testActionsWithFilter() throws Exception {
      final ActionFilter filter = new ActionFilter()
            .setEndDateLte( new Date() )
            .setStartDateGte( new Date( new Date().getTime() - 1000000000 ) );

      final Actions actions = apiClient.getActions( filter, 1 );

      Assert.assertNotNull( actions );
      Assert.assertTrue( ( actions.getObjects().size() > 0 ) );

      for( Action action : actions.getObjects() ) {
         LOG.info( action.toString() );
      }

   }

   @Test(groups = { "integration" }, enabled = false)
   public void testAction() throws Exception {
      final String actionid = "1d261569-7e17-4bf6-8987-6574de7bdd12";
      final Action action = apiClient.getAction( actionid );

      Assert.assertNotNull( action );
      LOG.info( action.toString() );
   }

   @Test(groups = { "integration" }, enabled = false)
   public void testProviders() throws Exception {
      final Providers providers = apiClient.getProviders( 1 );

      Assert.assertNotNull( providers );
      Assert.assertTrue( ( providers.getObjects().size() > 0 ) );

      for( Provider provider : providers.getObjects() ) {
         LOG.info( provider.toString() );
      }
   }

   @Test(groups = { "integration" }, enabled = false)
   public void testProvider() throws Exception {
      final String providerName = "digitalocean";
      final Provider provider = apiClient.getProvider( providerName );

      Assert.assertNotNull( provider );
      LOG.info( provider.toString() );
   }

   @Test(groups = { "integration" }, enabled = false)
   public void testRegions() throws Exception {
      final Regions regions = apiClient.getRegions( 1 );

      Assert.assertNotNull( regions );
      Assert.assertTrue( ( regions.getObjects().size() > 0 ) );

      for( Region provider : regions.getObjects() ) {
         LOG.info( provider.toString() );
      }
   }

   @Test(groups = { "integration" }, enabled = false)
   public void testRegion() throws Exception {
      final String providerName = "digitalocean";
      final String regionName = "sgp1";
      final Region region = apiClient.getRegion( providerName, regionName );

      Assert.assertNotNull( region );
      LOG.info( region.toString() );
   }

   @Test(groups = { "integration" }, enabled = false)
   public void testNodeTypes() throws Exception {
      final NodeTypes nodeTypes = apiClient.getNodeTypes( 1 );

      Assert.assertNotNull( nodeTypes );
      Assert.assertTrue( ( nodeTypes.getObjects().size() > 0 ) );

      for( NodeType type : nodeTypes.getObjects() ) {
         LOG.info( type.toString() );
      }
   }

   @Test(groups = { "integration" }, enabled = false)
   public void testNodeType() throws Exception {
      final String providerName = "digitalocean";
      final String nodeTypeName = "32gb";
      final NodeType nodeType = apiClient.getNodeType( providerName, nodeTypeName );

      Assert.assertNotNull( nodeType );
      LOG.info( nodeType.toString() );
   }

   @Test(groups = { "integration" }, enabled = false)
   public void testToken() throws Exception {
      final String token = apiClient.createToken();

      Assert.assertNotNull( token );
      LOG.info( token.toString() );
   }

   @Test(groups = { "integration" }, enabled = false)
   public void testServices() throws Exception {
      final Services services = apiClient.getServices();

      Assert.assertNotNull( services );
      Assert.assertTrue( ( services.getObjects().size() > 0 ) );

      for( Service service : services.getObjects() ) {
         LOG.info( service.toString() );
      }
   }

   @Test(groups = { "integration" }, enabled = false)
   public void testWebhookParsing() throws Exception {
      final WebhookHandler handler = new WebhookHandler( "unit" );
      handler.setUrl( "/api/v1/service/62115059-c6e3-4e8e-908e-e75a9de95330/webhook/handler/b6d47330-2b70-4587-a3c2-7b9fc2da56ca/call/" );

      Assert.assertNotNull( handler.getServiceUuid() );
      Assert.assertNotNull( handler.getWebhookUuid() );

      LOG.info( "Service: {}", handler.getServiceUuid() );
      LOG.info( "Webhook: {}", handler.getWebhookUuid() );
   }

   @Test(groups = { "integration" }, enabled = true)
   public void testWebhooks() throws Exception {
      final Services services = apiClient.getServices();

      Assert.assertNotNull( services );
      Assert.assertTrue( ( services.getObjects().size() > 0 ) );

      for( Service service : services.getObjects() ) {
         if( !ServiceState.Terminated.equals( service.getState() ) ) {

            final WebhookHandler created = apiClient.createWebhook( service.getUuid() );
            final WebhookHandler created2 = apiClient.createWebhook( service.getUuid(), "unit-1-" + IdUtils.smallRandom() );
            Assert.assertNotNull( created );
            Assert.assertNotNull( created2 );

            final WebhookHandlers hooks = apiClient.getWebhooks( service.getUuid() );
            Assert.assertNotNull( hooks );
            Assert.assertTrue( ( hooks.getObjects().size() > 0 ) );
            for( WebhookHandler handler : hooks.getObjects() ) {

               final WebhookHandler current = apiClient.getWebhook( handler.getServiceUuid(), handler.getWebhookUuid() );
               LOG.info( current.toString() );

               apiClient.callWebhook( current );

               Service currentSrv = apiClient.getService( handler.getServiceUuid() );
               while( currentSrv.isPendingOperation() ) {
                  LOG.info( "Service: {} State: {}", currentSrv.getName(), currentSrv.getState().value() );
                  waitFor( 2000 );
                  currentSrv = apiClient.getService( handler.getServiceUuid() );
               }

               final WebhookHandler deleted = apiClient.deleteWebhook( current );
               Assert.assertNotNull( deleted );

               break;
            }
         }
      }
   }

   @Test(groups = { "integration" }, enabled = false)
   public void testContainers() throws Exception {
      final Containers containers = apiClient.getContainers();

      Assert.assertNotNull( containers );
      Assert.assertTrue( ( containers.getObjects().size() > 0 ) );

      for( Container container : containers.getObjects() ) {
         LOG.info( container.toString() );

         final Container current = apiClient.getContainer( container.getUuid() );
         Assert.assertNotNull( current );

         final String logs = apiClient.getContainerLogs( current.getUuid() );
         LOG.info( "==================================================" );
         LOG.info( "Container {} Logs", current.getName() );
         LOG.info( "==================================================" );
         final BufferedReader br = new BufferedReader( new StringReader( logs ) );
         String line;
         while( ( line = br.readLine() ) != null ) {
            LOG.info( line );
         }

         stop( current );
         start( current );
         stop( current );
         terminate( current );
      }
   }

   @Test(groups = { "integration" }, enabled = false)
   public void testVolumeGroups() throws Exception {
      final VolumeGroups volGrps = apiClient.getVolumeGroups();

      Assert.assertNotNull( volGrps );
      Assert.assertTrue( ( volGrps.getObjects().size() > 0 ) );

      for( VolumeGroup group : volGrps.getObjects() ) {
         LOG.info( group.toString() );

         final VolumeGroup current = apiClient.getVolumeGroup( group.getUuid() );
         Assert.assertNotNull( current );
      }
   }

   @Test(groups = { "integration" }, enabled = false)
   public void testVolume() throws Exception {
      final Volumes volumes = apiClient.getVolumes();

      Assert.assertNotNull( volumes );
      Assert.assertTrue( ( volumes.getObjects().size() > 0 ) );

      for( Volume volume : volumes.getObjects() ) {
         LOG.info( volume.toString() );

         final Volume current = apiClient.getVolume( volume.getUuid() );
         Assert.assertNotNull( current );
      }
   }

   @Test(groups = { "integration" }, enabled = false)
   public void testTagging() throws Exception {
      final TagResourceType type = TagResourceType.Service;
      final String uuid = "62115059-c6e3-4e8e-908e-e75a9de95330";
      final String name = "unit-" + IdUtils.smallRandom();

      final List<Tag> result = apiClient.tag( type, uuid, new Tag( name ) );

      Assert.assertNotNull( result );
      Assert.assertTrue( ( result.size() > 0 ) );
      LOG.info( result.toString() );

      final Tag deleted = apiClient.deleteTag( type, uuid, name );

      Assert.assertNotNull( deleted );
      LOG.info( "Deleted: {}", result.toString() );
   }

   private void terminate( Container container ) {
      LOG.info( "Terminate Container: {}", container.getName() );
      Container current = apiClient.getContainer( container.getUuid() );
      while( Arrays.asList( ContainerState.Starting, ContainerState.Stopping, ContainerState.Terminating ).contains( current.getState() ) ) {
         LOG.info( "Container: {} State: {}", current.getName(), current.getState().value() );
         waitFor( 2000 );
         current = apiClient.getContainer( container.getUuid() );
      }
      if( !ContainerState.Terminated.equals( current.getState() ) ) {
         apiClient.terminateContainer( container.getUuid() );
      }
   }

   private void start( Container container ) {
      LOG.info( "Start Container: {}", container.getName() );
      Container current = apiClient.getContainer( container.getUuid() );
      while( ContainerState.Stopping.equals( current.getState() ) ) {
         LOG.info( "Container: {} State: {}", current.getName(), current.getState().value() );
         waitFor( 2000 );
         current = apiClient.getContainer( container.getUuid() );
      }
      if( Arrays.asList( ContainerState.Init, ContainerState.Stopped ).contains( current.getState() ) ) {
         apiClient.startContainer( current.getUuid() );
      }
   }

   private void stop( Container container ) {
      LOG.info( "Stop Container: {}", container.getName() );
      Container current = apiClient.getContainer( container.getUuid() );
      while( ContainerState.Starting.equals( current.getState() ) ) {
         LOG.info( "Container: {} State: {}", current.getName(), current.getState().value() );
         waitFor( 2000 );
         current = apiClient.getContainer( container.getUuid() );
      }
      if( ContainerState.Running.equals( current.getState() ) ) {
         apiClient.stopContainer( current.getUuid() );
      }
   }

   private void waitFor( Integer mili ) {
      try {
         Thread.sleep( mili );
      }
      catch( Exception exception ) {
         throw new RuntimeException( "Error sleeping", exception );
      }
   }

   @Test(groups = { "integration" }, enabled = false)
   public void testCreateService() throws Exception {
      final Service create = new Service();
      create.setName( "hello-world-" + IdUtils.smallRandom() );
      create.setImage( "tutum/hello-world" );
      create.setTargetNumContainers( 1 );

      final Service service = apiClient.createService( create );

      Assert.assertNotNull( service );
      LOG.info( service.toString() );

      final Service existing = apiClient.getService( service.getUuid() );
      Assert.assertNotNull( existing );
      LOG.info( existing.toString() );

      existing.getTags().add( new Tag( "hello" ) );
      final Service updated = apiClient.updateService( existing );
      Assert.assertNotNull( updated );
      LOG.info( updated.toString() );

      final Service started = apiClient.startService( existing.getUuid() );
      Assert.assertNotNull( started );
      LOG.info( started.toString() );

      Service current = apiClient.getService( existing.getUuid() );
      while( !ServiceState.Running.equals( current.getState() ) ) {
         LOG.info( "Service: {} State: {}", current.getName(), current.getState().value() );
         Thread.sleep( 2000 );
         current = apiClient.getService( existing.getUuid() );
      }
      apiClient.stopService( current.getUuid() );

      current = apiClient.getService( existing.getUuid() );
      while( !ServiceState.Stopped.equals( current.getState() ) ) {
         LOG.info( "Service: {} State: {}", current.getName(), current.getState().value() );
         Thread.sleep( 2000 );
         current = apiClient.getService( existing.getUuid() );
      }
      apiClient.redeployService( current.getUuid() );

      current = apiClient.getService( existing.getUuid() );
      while( ServiceState.Redeploying.equals( current.getState() ) ) {
         LOG.info( "Service: {} State: {}", current.getName(), current.getState().value() );
         Thread.sleep( 2000 );
         current = apiClient.getService( existing.getUuid() );
      }
      apiClient.terminateService( current.getUuid() );
   }

   @Test(groups = { "integration" }, enabled = false)
   public void testNodeClusters() throws Exception {
      // Create
      final NodeCluster create = new NodeCluster();
      create.setName( "junit3" );
      create.setRegion( "/api/v1/region/digitalocean/nyc3/" );
      create.setNodeType( "/api/v1/nodetype/digitalocean/512mb/" );
      create.setTargetNumNodes( 1 );
      create.setProvider( "digitalocean" );

      final NodeCluster created = apiClient.createNodeCluster( create );

      Assert.assertNotNull( created );
      LOG.info( created.toString() );

      // List
      final NodeClusters clusters = apiClient.getNodeClusters( 1 );

      Assert.assertNotNull( clusters );
      Assert.assertTrue( ( clusters.getObjects().size() > 0 ) );

      for( NodeCluster cluster : clusters.getObjects() ) {
         LOG.info( cluster.toString() );
      }

      // Get
      final String clusterid = clusters.getObjects().get( 0 ).getUuid();
      final NodeCluster cluster = apiClient.getNodeCluster( clusterid );

      Assert.assertNotNull( cluster );
      LOG.info( cluster.toString() );

      // Deploy
      final NodeCluster deployed = apiClient.deployNodeCluster( created.getUuid() );

      Assert.assertNotNull( deployed );
      LOG.info( deployed.toString() );

      // Update (After Deployed)
      NodeCluster current = apiClient.getNodeCluster( deployed.getUuid() );
      while( !current.isDeployed() ) {
         LOG.info( "Cluster: {} State: {}", current.getName(), current.getState().value() );
         Thread.sleep( 2000 );
         current = apiClient.getNodeCluster( deployed.getUuid() );
      }
      LOG.info( "Cluster: {} State: {}", current.getName(), current.getState().value() );

      current.getTags().add( new Tag( "updated" ) );
      apiClient.updateNodeCluster( current );

      // Upgrade
      current = apiClient.upgradeDockerOnNodeCluster( current.getUuid() );
      LOG.info( "Cluster After Upgrade: {}", current );

      // Terminate
      current = apiClient.getNodeCluster( deployed.getUuid() );
      while( !current.isDeployed() ) {
         LOG.info( "Cluster: {} State: {}", current.getName(), current.getState().value() );
         Thread.sleep( 2000 );
         current = apiClient.getNodeCluster( deployed.getUuid() );
      }
      LOG.info( "Cluster: {} State: {}", current.getName(), current.getState().value() );
      current = apiClient.terminateNodeCluster( current.getUuid() );
      LOG.info( "Cluster After Terminate: {}", current );
   }

   @Test(groups = { "integration" }, enabled = false)
   public void testNodes() throws Exception {
      final String clusterName = "cluster-" + IdUtils.smallRandom();
      // final String nodeName = "node-" + IdUtils.smallRandom();

      // Create Cluster
      final NodeCluster create = new NodeCluster();
      create.setName( clusterName );
      create.setRegion( "/api/v1/region/digitalocean/nyc3/" );
      create.setNodeType( "/api/v1/nodetype/digitalocean/512mb/" );
      create.setTargetNumNodes( 1 );
      create.setProvider( "digitalocean" );

      final NodeCluster created = apiClient.createNodeCluster( create );

      Assert.assertNotNull( created );
      LOG.info( created.toString() );

      // Deploy
      final NodeCluster deployed = apiClient.deployNodeCluster( created.getUuid() );
      NodeCluster current = apiClient.getNodeCluster( deployed.getUuid() );
      while( !current.isDeployed() ) {
         LOG.info( "Cluster: {} State: {}", current.getName(), current.getState().value() );
         LOG.info( "Cluster: {}", current );
         Thread.sleep( 2000 );
         current = apiClient.getNodeCluster( deployed.getUuid() );
         if( NodeClusterState.Init.equals( current.getState() ) ) {
            apiClient.deployNodeCluster( deployed.getUuid() );
         }
      }
      LOG.info( "Cluster: {} State: {}", current.getName(), current.getState().value() );

      // List Nodes
      final Nodes nodes = apiClient.getNodes( 1 );

      Assert.assertNotNull( nodes );
      Assert.assertTrue( ( nodes.getObjects().size() > 0 ) );

      for( Node node : nodes.getObjects() ) {
         LOG.info( node.toString() );
      }
   }
}
