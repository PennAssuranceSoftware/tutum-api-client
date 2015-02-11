package com.pennassurancesoftware.tutum;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pennassurancesoftware.tutum.client.TutumClient;
import com.pennassurancesoftware.tutum.dto.Action;
import com.pennassurancesoftware.tutum.dto.Actions;

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
}
