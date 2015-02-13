package com.pennassurancesoftware.tutum.type;

public enum VolumeGroupState implements CodeEnum {
   /** The volume group object has been created and it is available */
   Created("Created"),
   /** The volume group and its associated volumes have been deleted */
   Terminated("Terminated"),
   /** Unknown or Not Applicable */
   Null(""), ;

   private String value;

   private VolumeGroupState( String value ) {
      this.value = value;
   }

   @Override
   public String value() {
      return value;
   }
}
