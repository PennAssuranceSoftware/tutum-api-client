/*
 * The MIT License
 *
 * Copyright (c) 2010-2014 Jeevanandam M. (myjeeva.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.pennassurancesoftware.tutum.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.SerializedName;

/** Represents an Action of Tutum. */
public class Action implements Serializable {
   private static final long serialVersionUID = 1110964622197874436L;

   private String action;
   @SerializedName("end_date")
   private Date endDate;
   private String ip;
   private String location;
   private String method;
   private String object;
   private String path;
   @SerializedName("resource_uri")
   private String resourceUri;
   @SerializedName("start_date")
   private Date startDate;
   private String state;
   @SerializedName("user_agent")
   private String userAgent;
   private String uuid;

   public String getAction() {
      return action;
   }

   public Date getEndDate() {
      return endDate;
   }

   public String getIp() {
      return ip;
   }

   public String getLocation() {
      return location;
   }

   public String getMethod() {
      return method;
   }

   public String getObject() {
      return object;
   }

   public String getPath() {
      return path;
   }

   public String getResourceUri() {
      return resourceUri;
   }

   public Date getStartDate() {
      return startDate;
   }

   public String getState() {
      return state;
   }

   public String getUserAgent() {
      return userAgent;
   }

   public String getUuid() {
      return uuid;
   }

   public void setAction( String action ) {
      this.action = action;
   }

   public void setEndDate( Date endDate ) {
      this.endDate = endDate;
   }

   public void setIp( String ip ) {
      this.ip = ip;
   }

   public void setLocation( String location ) {
      this.location = location;
   }

   public void setMethod( String method ) {
      this.method = method;
   }

   public void setObject( String object ) {
      this.object = object;
   }

   public void setPath( String path ) {
      this.path = path;
   }

   public void setResourceUri( String resourceUri ) {
      this.resourceUri = resourceUri;
   }

   public void setStartDate( Date startDate ) {
      this.startDate = startDate;
   }

   public void setState( String state ) {
      this.state = state;
   }

   public void setUserAgent( String userAgent ) {
      this.userAgent = userAgent;
   }

   public void setUuid( String uuid ) {
      this.uuid = uuid;
   }

   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }

}
