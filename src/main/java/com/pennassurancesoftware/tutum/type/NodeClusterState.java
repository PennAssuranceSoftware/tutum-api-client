package com.pennassurancesoftware.tutum.type;

public enum NodeClusterState implements CodeEnum {
   Init("Init"),
   Deploying("Deploying"),
   Deployed("Deployed"),
   PartlyDeployed("Partly deployed"),
   Scaling("Scaling"),
   Terminating("Terminating"),
   Terminated("Terminated"),
   EmptyCluster("Empty cluster"),
   Null(""), ;

   private String value;

   private NodeClusterState( String value ) {
      this.value = value;
   }

   @Override
   public String value() {
      return value;
   }
}
