package com.emr.project.webservice.domain.resp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "Response"
)
@XmlAccessorType(XmlAccessType.FIELD)
public class EcgRes {
   @XmlElement(
      name = "IsSuccess"
   )
   private int isSuccess;
   @XmlElement(
      name = "Message"
   )
   private String message;

   public int getIsSuccess() {
      return this.isSuccess;
   }

   public void setIsSuccess(int isSuccess) {
      this.isSuccess = isSuccess;
   }

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String message) {
      this.message = message;
   }
}
