package com.pennassurancesoftware.tutum.type;

public enum NodeState implements CodeEnum {
   /** The node has been created and has not been deployed yet. Possible actions in this state: deploy, terminate. */
   Init("Init"),
   /** The node is being deployed in the cloud provider. No actions allowed in this state. */
   Deploying("Deploying"),
   /** Our agent is being installed and configured on the node. No actions allowed in this state. */
   Provisioning("Provisioning"),
   /** The node is deployed and provisioned and is ready to deploy containers. Possible actions in this state: terminate. */
   Deployed("Deployed"),
   /** The node is being terminated in the cloud provider. No actions allowed in this state. */
   Terminating("Terminating"),
   /** The node has been terminated and is no longer present in the cloud provider. No actions allowed in this state. */
   Terminated("Terminated"),
   Null(""), ;

   private String value;

   private NodeState( String value ) {
      this.value = value;
   }

   @Override
   public String value() {
      return value;
   }
}
