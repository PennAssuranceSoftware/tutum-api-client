package com.pennassurancesoftware.tutum.type;

public enum AutoRestartType implements CodeEnum {
   /** if the container stops, regardless of the exit code, it won't be autorestarted and will stay in Stopped state. */
   Off("OFF"),
   /** if the container stops with an exit code different from 0, it will be autorestarted. */
   OnFailure("ON_FAILURE"),
   /** if the container stops, regardless of the exit code, it will be autorestarted. */
   Always("ALWAYS"),
   /** Unknown or Not Applicable */
   Null(""), ;

   private String value;

   private AutoRestartType( String value ) {
      this.value = value;
   }

   @Override
   public String value() {
      return value;
   }
}
