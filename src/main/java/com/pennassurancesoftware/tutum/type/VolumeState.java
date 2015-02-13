package com.pennassurancesoftware.tutum.type;

public enum VolumeState implements CodeEnum {
   /** The volume object has been created in the node and it is available */
   Created("Created"),
   /** The volume has been deleted in the node. */
   Terminated("Terminated"),
   /** Unknown or Not Applicable */
   Null(""), ;

   private String value;

   private VolumeState( String value ) {
      this.value = value;
   }

   @Override
   public String value() {
      return value;
   }
}
