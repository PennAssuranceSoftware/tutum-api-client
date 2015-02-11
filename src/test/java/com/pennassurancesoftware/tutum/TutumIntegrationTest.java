package com.pennassurancesoftware.tutum;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pennassurancesoftware.tutum.client.TutumClient;
import com.pennassurancesoftware.tutum.dto.Action;
import com.pennassurancesoftware.tutum.dto.Actions;
import com.pennassurancesoftware.tutum.dto.Provider;
import com.pennassurancesoftware.tutum.dto.Providers;

public class TutumIntegrationTest {
   private static final Logger LOG = LoggerFactory.getLogger( TutumIntegrationTest.class );

   /** Fill in your auth token here should be in the format: [USER]:[API_KEY] */
   private Tutum apiClient = new TutumClient( "" );

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
}
