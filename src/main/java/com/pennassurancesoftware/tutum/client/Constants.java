package com.pennassurancesoftware.tutum.client;

public class Constants {

   // public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
   public static final String DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z";
   public static final String QUERY_PARAM_DATE_FORMAT = "yyyy-MM-dd"; // HH:MM[:ss[.uuuuuu]][TZ]
   public static final String FORM_URLENCODED_CONTENT_TYPE = "application/x-www-form-urlencoded";
   public static final String HTTPS_SCHEME = "https";
   public static final String JSON_CONTENT_TYPE = "application/json";
   public static final String NO_CONTENT_JSON_STRUCT = "{\"delete\": {\"request_status\": true, \"status_code\": %s}}";
   public static final String PARAM_PAGE_NO = "page";
   public static final String RATE_LIMIT_ELEMENT_NAME = "rate_limit";
   public static final String RATE_LIMIT_JSON_STRUCT = "\"rate_limit\": { \"limit\": %s, \"remaining\": %s, \"reset\": \"%s\"}";
   public static final Integer START_PAGE_NO = 1;
   public static final String URL_PATH_SEPARATOR = "/";
   public static final String UTF_8 = "utf-8";

}
