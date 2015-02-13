package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class NodeTypeFilter implements Serializable {
   private static final long serialVersionUID = 5403973179024666700L;

   private String name;
   private List<String> regions = new ArrayList<String>();

   public List<String> getRegions() {
      return regions;
   }

   public void addRegion( String... region ) {
      regions.addAll( Arrays.asList( region ) );
   }

   public NodeTypeFilter setRegions( List<String> regions ) {
      this.regions = regions;
      return this;
   }

   public String getName() {
      return name;
   }

   public NodeTypeFilter setName( String name ) {
      this.name = name;
      return this;
   }

   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }
}
