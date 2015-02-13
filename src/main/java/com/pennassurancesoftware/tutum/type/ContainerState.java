package com.pennassurancesoftware.tutum.type;

public enum ContainerState implements CodeEnum {
   /** The container object has been created but hasn’t being deployed yet. Possible actions in this state: start, terminate. */
   Init("Init"),
   /** The container is being deployed (from Init) or started (from Stopped). No actions allowed in this state. */
   Starting("Starting"),
   /** The container is deployed and running. Possible actions in this state: stop, terminate. */
   Running("Running"),
   /** The container is being stopped. No actions allowed in this state. */
   Stopping("Stopping"),
   /** The container is stopped. Possible actions in this state: start, terminate. */
   Stopped("Stopped"),
   /** The container is being deleted. No actions allowed in this state. */
   Terminating("Terminating"),
   /** The container has been deleted. No actions allowed in this state. */
   Terminated("Terminated"),
   /** Unknown or Not Applicable */
   Null(""), ;

   private String value;

   private ContainerState( String value ) {
      this.value = value;
   }

   @Override
   public String value() {
      return value;
   }
}
