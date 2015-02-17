package com.pennassurancesoftware.tutum.type;

public enum ProtocolType implements CodeEnum {
   TCP("tcp"),
   UDP("udp"),
   /** Unknown or Not Applicable */
   Null(""), ;

   private String value;

   private ProtocolType( String value ) {
      this.value = value;
   }

   @Override
   public String value() {
      return value;
   }
}
