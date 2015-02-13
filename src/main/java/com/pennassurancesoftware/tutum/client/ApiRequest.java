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
package com.pennassurancesoftware.tutum.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents Tutum API Request details
 * @since v2.0
 */
public class ApiRequest {
   private ApiAction apiAction;
   private Object data;
   private Object[] params;
   // private Integer pageNo;
   private Map<String, List<String>> queryParams = new HashMap<String, List<String>>();

   public ApiRequest() {}

   /**
    * Constructor
    * 
    * @param apiAction a info about api request
    * @param params a api request path variable value(s)
    */
   public ApiRequest( ApiAction apiAction, Object[] params ) {
      this( apiAction, null, params, null );
   }

   /**
    * Constructor
    * 
    * @param apiAction a info about api request
    * @param pageNo of the request pagination
    */
   public ApiRequest( ApiAction apiAction, Map<String, List<String>> queryParams ) {
      this( apiAction, null, null, queryParams );
   }

   /**
    * Constructor
    * 
    * @param apiAction a info about api request
    * @param data a api request body data object
    */
   public ApiRequest( ApiAction apiAction, Object data ) {
      this( apiAction, data, null, null );
   }

   /**
    * Constructor
    * 
    * @param apiAction a info about api request
    * @param params a api request path variable value(s)
    * @param pageNo of the request pagination
    */
   public ApiRequest( ApiAction apiAction, Object[] params, Map<String, List<String>> queryParams ) {
      this( apiAction, null, params, queryParams );
   }

   /**
    * Constructor
    * 
    * @param apiAction a info about api request
    * @param data a api request body data object
    * @param params a api request path variable value(s)
    */
   public ApiRequest( ApiAction apiAction, Object data, Object[] params ) {
      this( apiAction, data, params, null );
   }

   /**
    * Constructor
    * 
    * @param apiAction a info about api request
    * @param data a api request body data object
    * @param params a api request path variable value(s)
    * @param pageNo of the request pagination
    */
   public ApiRequest( ApiAction apiAction, Object data, Object[] params, Map<String, List<String>> queryParams ) {
      this.apiAction = apiAction;
      this.data = data;
      this.params = params;
      this.queryParams = queryParams != null ? queryParams : new HashMap<String, List<String>>();
   }

   public String getPath() {
      return apiAction.getPath();
   }

   public RequestMethod getMethod() {
      return apiAction.getMethod();
   }

   public Class<?> getClazz() {
      return apiAction.getClazz();
   }

   public ApiAction getApiAction() {
      return apiAction;
   }

   public void setApiAction( ApiAction apiAction ) {
      this.apiAction = apiAction;
   }

   public Object getData() {
      return data;
   }

   public void setData( Object data ) {
      this.data = data;
   }

   public Object[] getParams() {
      return params;
   }

   public void setParams( Object[] params ) {
      this.params = params;
   }

   public Map<String, List<String>> getQueryParams() {
      return queryParams;
   }

   public void setQueryParams( Map<String, List<String>> queryParams ) {
      this.queryParams = queryParams;
   }
}
