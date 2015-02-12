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

   public QueryParamBuilder setDateFormat( String dateFormat ) {
      this.dateFormat = dateFormat;
      return this;
   }

   @Retention(RetentionPolicy.RUNTIME)
   @Target(ElementType.FIELD)
   public static @interface QueryParamName {
      String value();
   }

   public String createQueryString( Object obj ) {
      final Map<String, List<String>> params = createQueryParams( obj );
      final StringBuffer buffer = new StringBuffer();
      boolean firstParamFlag = true;
      if( !params.isEmpty() ) {
         buffer.append( "?" );
         for( String key : params.keySet() ) {
            for( String value : params.get( key ) ) {
               try {
                  if( !firstParamFlag ) {
                     buffer.append( "&" );
                  }
                  buffer.append( key ).append( "=" ).append( URLEncoder.encode( value, "UTF-8" ) );
                  firstParamFlag = false;
               }
               catch( Exception exception ) {
                  throw new RuntimeException( String.format( "Error encoding value: %s", value ), exception );
               }
            }
         }
      }
      return buffer.toString();
   }

   public Map<String, List<String>> createQueryParams( Object obj ) {
      final Map<String, List<String>> result = new HashMap<String, List<String>>();
      if( obj != null ) {
         final Field[] fields = obj.getClass().getDeclaredFields();
         for( Field field : fields ) {
            add( result, obj, field );
         }
      }
      return result;
   }

   @SuppressWarnings("unchecked")
   private void add( Map<String, List<String>> result, Object obj, Field field ) {
      final boolean accessible = field.isAccessible();
      try {
         field.setAccessible( true );
         final Object rawValue = field.get( obj );
         if( rawValue != null ) {
            if( rawValue instanceof List ) {
               for( Object item : ( List<Object> )rawValue ) {
                  add( result, getKeyName( field ), item );
               }
            }
            else {
               add( result, getKeyName( field ), rawValue );
            }
         }
      }
      catch( IllegalAccessException e ) {
         throw new RuntimeException( "Error creating query params from object", e );
      }
      finally {
         field.setAccessible( accessible );
      }
   }

   private String getKeyName( Field field ) {
      return field.isAnnotationPresent( QueryParamName.class ) ? field.getAnnotation( QueryParamName.class ).value() : field.getName();
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

   private void add( Map<String, List<String>> map, String key, Object obj ) {
      final String value = formatValue( obj );
      if( value != null ) {
         add( map, key, value );
      }
   }

   private void add( Map<String, List<String>> map, String key, String value ) {
      if( !map.containsKey( key ) ) {
         map.put( key, new ArrayList<String>() );
      }
      map.get( key ).add( value );
   }
}
