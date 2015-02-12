package com.pennassurancesoftware.tutum.type;

public enum ActionState implements CodeEnum {
   /** The action needed asynchronous execution and is being performed */
   InProgress("In progress"),
   /** The action was executed successfully */
   Success("Success"),
   /** There was an issue when the action was being performed. Check the logs for more information. */
   Failed("Failed"),
   Null(""), ;

   private String value;

   private ActionState( String value ) {
      this.value = value;
   }

   @Override
   public String value() {
      return value;
   }
}
