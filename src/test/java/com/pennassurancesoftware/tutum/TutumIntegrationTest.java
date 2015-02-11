package com.pennassurancesoftware.tutum;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pennassurancesoftware.tutum.client.TutumClient;
import com.pennassurancesoftware.tutum.dto.Action;
import com.pennassurancesoftware.tutum.dto.Actions;
import com.pennassurancesoftware.tutum.dto.NodeCluster;
import com.pennassurancesoftware.tutum.dto.NodeClusters;
import com.pennassurancesoftware.tutum.dto.NodeType;
import com.pennassurancesoftware.tutum.dto.NodeTypes;
import com.pennassurancesoftware.tutum.dto.Provider;
import com.pennassurancesoftware.tutum.dto.Providers;
import com.pennassurancesoftware.tutum.dto.Region;
import com.pennassurancesoftware.tutum.dto.Regions;
import com.pennassurancesoftware.tutum.dto.Tag;

public class TutumIntegrationTest {
   private static final Logger LOG = LoggerFactory.getLogger( TutumIntegrationTest.class );

   /** Fill in your auth token here should be in the format: [USER]:[API_KEY] */
   private Tutum apiClient = new TutumClient( "pennassurancesoftware:1aae8e176f52132240f270320d122589dd66fec0" );

   @Test
   public void testActions() throws Exception {
      final Actions actions = apiClient.getActions( 1 );

      Assert.assertNotNull( actions );
      Assert.assertTrue( ( actions.getObjects().size() > 0 ) );

      for( Action action : actions.getObjects() ) {
         LOG.info( action.toString() );
      }

   }

   @Test
   public void testAction() throws Exception {
      final String actionid = "1d261569-7e17-4bf6-8987-6574de7bdd12";
      final Action action = apiClient.getAction( actionid );

      Assert.assertNotNull( action );
      LOG.info( action.toString() );
   }

   @Test
   public void testProviders() throws Exception {
      final Providers providers = apiClient.getProviders( 1 );

      Assert.assertNotNull( providers );
      Assert.assertTrue( ( providers.getObjects().size() > 0 ) );

      for( Provider provider : providers.getObjects() ) {
         LOG.info( provider.toString() );
      }
   }

   @Test
   public void testProvider() throws Exception {
      final String providerName = "digitalocean";
      final Provider provider = apiClient.getProvider( providerName );

      Assert.assertNotNull( provider );
      LOG.info( provider.toString() );
   }

   @Test
   public void testRegions() throws Exception {
      final Regions regions = apiClient.getRegions( 1 );

      Assert.assertNotNull( regions );
      Assert.assertTrue( ( regions.getObjects().size() > 0 ) );

      for( Region provider : regions.getObjects() ) {
         LOG.info( provider.toString() );
      }
   }

   @Test
   public void testRegion() throws Exception {
      final String providerName = "digitalocean";
      final String regionName = "sgp1";
      final Region region = apiClient.getRegion( providerName, regionName );

      Assert.assertNotNull( region );
      LOG.info( region.toString() );
   }

   @Test
   public void testNodeTypes() throws Exception {
      final NodeTypes nodeTypes = apiClient.getNodeTypes( 1 );

      Assert.assertNotNull( nodeTypes );
      Assert.assertTrue( ( nodeTypes.getObjects().size() > 0 ) );

      for( NodeType type : nodeTypes.getObjects() ) {
         LOG.info( type.toString() );
      }
   }

   @Test
   public void testNodeType() throws Exception {
      final String providerName = "digitalocean";
      final String nodeTypeName = "32gb";
      final NodeType nodeType = apiClient.getNodeType( providerName, nodeTypeName );

      Assert.assertNotNull( nodeType );
      LOG.info( nodeType.toString() );
   }

   // @Test
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

   @Test
   public void testTemp() throws Exception {
      final String uuid = "36e31f04-74f8-40fe-83e1-1e39fd243d5b";

      // Update (After Deployed)
      NodeCluster current = apiClient.getNodeCluster( uuid );
      while( !current.isDeployed() ) {
         LOG.info( "Cluster: {} State: {}", current.getName(), current.getState().value() );
         Thread.sleep( 2000 );
         current = apiClient.getNodeCluster( uuid );
      }
      LOG.info( "Cluster: {} State: {}", current.getName(), current.getState().value() );

      current.getTags().add( new Tag( "updated" ) );
      apiClient.updateNodeCluster( current );

      // Upgrade
      current = apiClient.upgradeDockerOnNodeCluster( current.getUuid() );
      LOG.info( "Cluster After Upgrade: {}", current );

      // Terminate
      current = apiClient.getNodeCluster( uuid );
      while( !current.isDeployed() ) {
         LOG.info( "Cluster: {} State: {}", current.getName(), current.getState().value() );
         Thread.sleep( 2000 );
         current = apiClient.getNodeCluster( uuid );
      }
      LOG.info( "Cluster: {} State: {}", current.getName(), current.getState().value() );
      current = apiClient.terminateNodeCluster( current.getUuid() );
      LOG.info( "Cluster After Terminate: {}", current );
   }

}
