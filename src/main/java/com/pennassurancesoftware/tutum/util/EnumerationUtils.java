package com.pennassurancesoftware.tutum.util;

import com.pennassurancesoftware.tutum.type.CodeEnum;

public class EnumerationUtils {

   @SuppressWarnings("unchecked")
   public static <T extends Enum<T>> T lookup( Class<T> clazz, String name ) {
      T result = getNullEnum( clazz );
      if( name != null ) {
         for( Enum<T> value : clazz.getEnumConstants() ) {
            final String code = value instanceof CodeEnum ? ( ( CodeEnum )value ).value() : value.name();
            if( name.equalsIgnoreCase( code ) ) {
               result = ( T )value;
               break;
            }
         }
      }
      return result;
   }

   @SuppressWarnings("unchecked")
   public static <T extends Enum<T>> T getNullEnum( Class<T> clazz ) {
      T result = null;
      for( Enum<T> value : clazz.getEnumConstants() ) {
         if( "Null".equalsIgnoreCase( value.name() ) ) {
            result = ( T )value;
            break;
         }
      }
      if( result == null ) {
         for( Enum<T> value : clazz.getEnumConstants() ) {
            if( "NotDefined".equalsIgnoreCase( value.name() ) ) {
               result = ( T )value;
               break;
            }
         }
      }
      return result;
   }
}
