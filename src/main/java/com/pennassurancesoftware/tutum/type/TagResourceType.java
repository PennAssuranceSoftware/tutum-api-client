package com.pennassurancesoftware.tutum.type;

public enum TagResourceType implements CodeEnum {
   NodeCluster("nodecluster"),
   Node("node"),
   Service("service"),
   /** Unknown or Not Applicable */
   Null(""), ;

   private String value;

   private TagResourceType( String value ) {
      this.value = value;
   }

   @Override
   public String value() {
      return value;
   }
}
