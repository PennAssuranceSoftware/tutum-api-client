package com.pennassurancesoftware.tutum.type;

public enum TutumErrorType implements CodeEnum {
   /** Bad Request – There’s a problem in the content of your request. Retrying the same request will fail. */
   BadRequest("400"),
   /** Unauthorized – Your API key is wrong or your account has been deactivated. */
   Unauthorized("401"),
   /** Not Found – The requested object cannot be found. */
   NotFound("404"),
   /** Method Not Allowed – The endpoint requested does not implement the method sent. */
   MethodNotAllowed("405"),
   /** Unsupported Media Type – Make sure you are using Accept and Content-Type headers as application/json and that the data your are POST-ing or PATCH-ing is in valid JSON format. */
   UnsupportedMediaType("415"),
   /** Too Many Requests – You are being throttled because of too many requests in a short period of time. */
   TooManyRequests("429"),
   /** Internal Server Error – There was a server error while processing your request. Try again later, or contact support. */
   InternalServerError("500"),
   /** Service Unavailable – We’re temporarially offline for maintanance. Please try again later. */
   ServiceUnavailable("503"),
   /** Unknown or Not Applicable */
   Null(""), ;

   private String value;

   private TutumErrorType( String value ) {
      this.value = value;
   }

   @Override
   public String value() {
      return value;
   }
}
