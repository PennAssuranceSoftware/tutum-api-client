package com.pennassurancesoftware.tutum.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.pennassurancesoftware.tutum.dto.ActionFilter;
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
      final QueryParamBuilder builder = new QueryParamBuilder();
      builder.setDateFormat( "yyyy-MM-dd" );
      final Map<String, List<String>> params = builder.createQueryParams( filter );
      final String queryString = builder.createQueryString( filter );

      // Assert
      System.out.println( params );
      System.out.println( queryString );
   }
}
