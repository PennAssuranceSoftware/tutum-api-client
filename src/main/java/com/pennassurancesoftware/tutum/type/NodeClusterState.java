package com.pennassurancesoftware.tutum.type;

public enum NodeClusterState implements CodeEnum {
   Init("Init"),
   Deploying("Deploying", true),
   Deployed("Deployed"),
   PartlyDeployed("Partly deployed"),
   Scaling("Scaling", true),
   Terminating("Terminating", true),
   Terminated("Terminated"),
   EmptyCluster("Empty cluster"),
   Null(""), ;

   private boolean pendingOperation = false;
   private String value;

   private NodeClusterState( String value ) {
      this.value = value;
   }

   private NodeClusterState( String value, boolean pendingOperation ) {
      this.value = value;
      this.pendingOperation = pendingOperation;
   }

   @Override
   public String value() {
      return value;
   }

   public boolean isPendingOperation() {
      return pendingOperation;
   }
}
