package com.pennassurancesoftware.tutum.type;

public enum AutoDestoryType implements CodeEnum {
   /** if the container stops, regardless of the exit code, Tutum will not terminate it and will it in Stopped state. */
   Off("OFF"),
   /** if the container stops with an exit code different from 0, Tutum will automatically terminate it. Otherwise, it will leave it in Stopped state. */
   OnFailure("ON_FAILURE"),
   /** if the container stops, regardless of the exit code, Tutum will automatically terminate it. */
   Always("ALWAYS"),
   /** Unknown or Not Applicable */
   Null(""), ;

   private String value;

   private AutoDestoryType( String value ) {
      this.value = value;
   }

   @Override
   public String value() {
      return value;
   }
}
