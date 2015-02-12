package com.pennassurancesoftware.tutum.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

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

   public ActionFilter setEndDateGte( Date endDateGte ) {
      this.endDateGte = endDateGte;
      return this;
   }

   public ActionFilter setEndDateLte( Date endDateLte ) {
      this.endDateLte = endDateLte;
      return this;
   }

   public ActionFilter setObject( String object ) {
      this.object = object;
      return this;
   }

   public ActionFilter setStartDateGte( Date startDateGte ) {
      this.startDateGte = startDateGte;
      return this;
   }

   public ActionFilter setStartDateLte( Date startDateLte ) {
      this.startDateLte = startDateLte;
      return this;
   }

   public ActionFilter setState( String state ) {
      this.state = state;
      return this;
   }

   public ActionFilter setState( ActionState state ) {
      setState( state.value() );
      return this;
   }

   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }
}
