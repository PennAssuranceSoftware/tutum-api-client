package com.pennassurancesoftware.tutum.type;

public enum ServiceState implements CodeEnum {
   /** The service has been created and has no deployed containers yet. Possible actions in this state: start, terminate. */
   Init("Init", false, true),
   /** All containers for the service are either starting or already running. No actions allowed in this state. */
   Starting("Starting", true),
   /** All containers for the service are deployed and running. Possible actions in this state: stop, redeploy, terminate. */
   Running("Running"),
   /** One or more containers of the service are deployed and running. Possible actions in this state: stop, redeploy, terminate. */
   PartlyRunning("Partly running"),
   /** The service is either deploying new containers or destroying existing ones responding to a scaling request. No actions allowed in this state. */
   Scaling("Scaling", true),
   /** The service is redeploying all its containers with the updated configuration. No actions allowed in this state. */
   Redeploying("Redeploying", true),
   /** All containers for the service are either stopping or already stopped. No actions allowed in this state. */
   Stopping("Stopping", true),
   /** All containers for the service are stopped. Possible actions in this state: start, redeploy, terminate. */
   Stopped("Stopped", false, true),
   /** All containers for the service are either being terminated or already terminated. No actions allowed in this state. */
   Terminating("Terminating", true),
   /** The service and all its containers have been terminated. No actions allowed in this state. */
   Terminated("Terminated"),
   /** There are no containers to be deployed for this service. Possible actions in this state: terminate. */
   NotRunning("Not running"),
   /** Unknown or Not Applicable */
   Null(""), ;

   private String value;
   private boolean pendingOperation = false;
   private boolean canStartFrom = false;

   private ServiceState( String value ) {
      this.value = value;
   }

   private ServiceState( String value, boolean pendingOperation ) {
      this.value = value;
      this.pendingOperation = pendingOperation;
   }

   private ServiceState( String value, boolean pendingOperation, boolean canStartFrom ) {
      this.value = value;
      this.pendingOperation = pendingOperation;
      this.canStartFrom = canStartFrom;
   }

   public boolean canStartFrom() {
      return canStartFrom;
   }

   public boolean isPendingOperation() {
      return pendingOperation;
   }

   @Override
   public String value() {
      return value;
   }
}
