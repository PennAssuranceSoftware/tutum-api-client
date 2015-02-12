package com.pennassurancesoftware.tutum.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryParamBuilder {
   private String dateFormat;

   public String getDateFormat() {
      return dateFormat;
   }

   public void setDateFormat( String dateFormat ) {
      this.dateFormat = dateFormat;
   }

   @Retention(RetentionPolicy.RUNTIME)
   @Target(ElementType.FIELD)
   public static @interface QueryParamName {
      String value();
   }

   public String toQueryString( Object obj ) {
      final Map<String, List<String>> params = toQueryParams( obj );
      final StringBuffer buffer = new StringBuffer();
      if( !params.isEmpty() ) {
         buffer.append( "?" );
         final List<String> keys = new ArrayList<String>( params.keySet() );
         for( int index = 0; index < keys.size(); index++ ) {
            final String key = keys.get( index );
            for( String value : params.get( key ) ) {
               try {
                  buffer.append( key ).append( "=" ).append( URLEncoder.encode( value, "UTF-8" ) );
               }
               catch( Exception exception ) {
                  throw new RuntimeException( String.format( "Error encoding value: %s", value ), exception );
               }
            }
            if( index + 1 < keys.size() ) {
               buffer.append( "&" );
            }
         }
      }
      return buffer.toString();
   }

   public Map<String, List<String>> toQueryParams( Object obj ) {
      final Map<String, List<String>> result = new HashMap<String, List<String>>();
      if( obj != null ) {
         final Field[] fields = obj.getClass().getDeclaredFields();
         for( Field field : fields ) {
            final boolean accessible = field.isAccessible();
            try {
               field.setAccessible( true );
               final String value = formatValue( field.get( obj ) );
               if( value != null ) {
                  final String name = field.isAnnotationPresent( QueryParamName.class ) ? field.getAnnotation( QueryParamName.class ).value() : field.getName();
                  add( result, name, value );
               }
            }
            catch( IllegalAccessException e ) {
               throw new RuntimeException( "Error creating query params from object", e );
            }
            finally {
               field.setAccessible( accessible );
            }
         }
      }
      return result;
   }

   private String formatValue( Object value ) {
      String result = null;
      if( value != null ) {
         if( value instanceof Date && dateFormat != null ) {
            result = new SimpleDateFormat( dateFormat ).format( ( Date )value );
         }
         else {
            result = value.toString();
         }
      }
      return result;
   }

   private void add( Map<String, List<String>> map, String key, String value ) {
      if( !map.containsKey( key ) ) {
         map.put( key, new ArrayList<String>() );
      }
      map.get( key ).add( value );
   }
}
