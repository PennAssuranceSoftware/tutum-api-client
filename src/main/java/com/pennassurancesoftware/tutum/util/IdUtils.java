package com.pennassurancesoftware.tutum.util;

import java.security.SecureRandom;
import java.util.Random;

public class IdUtils {

   public static String smallRandom() {
      return random( 4 );
   }

   public static String generateName( String prefix ) {
      return String.format( "%s-%s", prefix, smallRandom() );
   }

   public static String random( int bytes ) {
      final Random ranGen = new SecureRandom();
      byte[] key = new byte[4]; // 16 bytes = 128 bits
      ranGen.nextBytes( key );
      return hexEncode( key );
   }

   /**
    * The byte[] returned by MessageDigest does not have a nice
    * textual representation, so some form of encoding is usually performed.
    *
    * This implementation follows the example of David Flanagan's book
    * "Java In A Nutshell", and converts a byte array into a String
    * of hex characters.
    *
    * Another popular alternative is to use a "Base64" encoding.
    */
   static private String hexEncode( byte[] aInput ) {
      StringBuilder result = new StringBuilder();
      char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
      for( int idx = 0; idx < aInput.length; ++idx ) {
         byte b = aInput[idx];
         result.append( digits[( b & 0xf0 ) >> 4] );
         result.append( digits[b & 0x0f] );
      }
      return result.toString();
   }
}
