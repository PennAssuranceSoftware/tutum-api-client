package com.pennassurancesoftware.tutum.dto;

import java.util.Date;

import com.pennassurancesoftware.tutum.type.ActionState;
import com.pennassurancesoftware.tutum.util.EnumerationUtils;
import com.pennassurancesoftware.tutum.util.QueryParamBuilder.QueryParamName;

public class ActionFilter {
   @QueryParamName("end_date__gte")
   private Date endDateGte;
   @QueryParamName("end_date__lte")
   private Date endDateLte;
   private String object;
   @QueryParamName("start_date__gte")
   private Date startDateGte;
   @QueryParamName("start_date__lte")
   private Date startDateLte;
   private String state;

   public Date getEndDateGte() {
      return endDateGte;
   }

   public Date getEndDateLte() {
      return endDateLte;
   }

   public String getObject() {
      return object;
   }

   public Date getStartDateGte() {
      return startDateGte;
   }

   public Date getStartDateLte() {
      return startDateLte;
   }

   public ActionState getState() {
      return EnumerationUtils.lookup( ActionState.class, state );
   }

   public void setEndDateGte( Date endDateGte ) {
      this.endDateGte = endDateGte;
   }

   public void setEndDateLte( Date endDateLte ) {
      this.endDateLte = endDateLte;
   }

   public void setObject( String object ) {
      this.object = object;
   }

   public void setStartDateGte( Date startDateGte ) {
      this.startDateGte = startDateGte;
   }

   public void setStartDateLte( Date startDateLte ) {
      this.startDateLte = startDateLte;
   }

   public void setState( String state ) {
      this.state = state;
   }

   public void setState( ActionState state ) {
      setState( state.value() );
   }
}
