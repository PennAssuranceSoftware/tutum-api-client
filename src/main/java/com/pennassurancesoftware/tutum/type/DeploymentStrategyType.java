package com.pennassurancesoftware.tutum.type;

public enum DeploymentStrategyType implements CodeEnum {
   /** It will deploy containers to the node with the lower total amount of running containers (default). */
   EmptiestNode("EMPTIEST_NODE"),
   /** It will deploy containers to the node with the lower amount of running containers of the same service. */
   HighAvailability("HIGH_AVAILABILITY"),
   /** It will deploy one container on every node. The service won’t be able to scale manually. New containers will be deployed to new nodes automatically. */
   EveryNode("EVERY_NODE"),
   /** Unknown or Not Applicable */
   Null(""), ;

   private String value;

   private DeploymentStrategyType( String value ) {
      this.value = value;
   }

   @Override
   public String value() {
      return value;
   }
}
