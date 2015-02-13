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

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.SerializedName;

/**
 * Represents Meta attributes
 *
 * @since v1.0
 */
public class Meta implements Serializable {
   private static final long serialVersionUID = -1915567012431554421L;
   
   private Integer limit;
   private String next;
   private Integer offset;
   private String previous;
   @SerializedName("total_count")
   private Integer totalCount;

   public Integer getLimit() {
      return limit;
   }

   public String getNext() {
      return next;
   }

   public Integer getOffset() {
      return offset;
   }

   public String getPrevious() {
      return previous;
   }

   public Integer getTotalCount() {
      return totalCount;
   }

   public void setLimit( Integer limit ) {
      this.limit = limit;
   }

   public void setNext( String next ) {
      this.next = next;
   }

   public void setOffset( Integer offset ) {
      this.offset = offset;
   }

   public void setPrevious( String previous ) {
      this.previous = previous;
   }

   public void setTotalCount( Integer totalCount ) {
      this.totalCount = totalCount;
   }

   @Override
   public String toString() {
      return ReflectionToStringBuilder.toString( this );
   }
}
