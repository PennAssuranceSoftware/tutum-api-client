package com.pennassurancesoftware.tutum.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.pennassurancesoftware.tutum.client.Constants;
import com.pennassurancesoftware.tutum.dto.ActionFilter;
import com.pennassurancesoftware.tutum.dto.NodeTypeFilter;
import com.pennassurancesoftware.tutum.type.ActionState;

public class TestQueryParamUtils {

   @Test
   public void testActionFilter() throws Exception {
      // Fixture
      final ActionFilter filter = new ActionFilter();
      filter.setEndDateLte( new Date() );
      filter.setStartDateGte( new Date() );
      filter.setState( ActionState.InProgress );

      // Call
      final QueryParamBuilder builder = new QueryParamBuilder().setDateFormat( Constants.QUERY_PARAM_DATE_FORMAT );
      final Map<String, List<String>> params = builder.createQueryParams( filter );
      final String queryString = builder.createQueryString( filter );

      // Assert
      System.out.println( params );
      System.out.println( queryString );
   }

   @Test
   public void testNodeTypeFilter() throws Exception {
      // Fixture
      final NodeTypeFilter filter = new NodeTypeFilter();
      filter.setName( "test" );
      filter.addRegion( "test1Region", "test2Region", "test 3 region" );

      // Call
      final QueryParamBuilder builder = new QueryParamBuilder().setDateFormat( Constants.QUERY_PARAM_DATE_FORMAT );
      final Map<String, List<String>> params = builder.createQueryParams( filter );
      final String queryString = builder.createQueryString( filter );

      // Assert
      System.out.println( params );
      System.out.println( queryString );
   }
}
