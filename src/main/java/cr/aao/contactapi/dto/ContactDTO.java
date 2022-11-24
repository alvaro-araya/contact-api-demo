/******************************************************************************
 * Alvaro Araya O. coder@aao.cr aaocr +506 8899 8899                          *
 * Copyright (c) 2022.                                                        *
 ******************************************************************************/

package cr.aao.contactapi.dto;

import java.util.Date;

public class ContactDTO {

  private Date birthday;
  private String email;
  private Boolean enabled;
  private String firstName;
  private Integer id;
  private String lastName;

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("ContactDTO{");
    sb.append("id=").append(id);
    sb.append(", birthday=").append(birthday);
    sb.append(", email='").append(email).append('\'');
    sb.append(", enabled=").append(enabled);
    sb.append(", firstName='").append(firstName).append('\'');
    sb.append(", lastName='").append(lastName).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
