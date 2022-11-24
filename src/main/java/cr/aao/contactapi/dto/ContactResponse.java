/******************************************************************************
 * Alvaro Araya O. coder@aao.cr aaocr +506 8899 8899                          *
 * Copyright (c) 2022.                                                        *
 ******************************************************************************/

package cr.aao.contactapi.dto;

import cr.aao.contactapi.entity.Contact;

public class ContactResponse {

  private String code;
  private Contact contact;
  private String message;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("ContactResponse{");
    sb.append("code='").append(code).append('\'');
    sb.append(", contact=").append(contact);
    sb.append(", message='").append(message).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
