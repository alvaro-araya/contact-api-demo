/******************************************************************************
 * Alvaro Araya O. coder@aao.cr aaocr +506 8899 8899                          *
 * Copyright (c) 2022.                                                        *
 ******************************************************************************/

package cr.aao.contactapi.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Cacheable(value = false)
@Table(name = "contact", schema = "api_db")
public class Contact {

  private Date birthday;
  private String email;
  private Boolean enabled;
  private String firstName;
  private Integer id;
  private String lastName;

  @Basic
  @Column(name = "birthday")
  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  @Basic
  @Column(name = "email", unique = true, length = 200, nullable = false)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Basic
  @Column(name = "enabled")
  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  @Basic
  @Column(name = "first_name", length = 100, nullable = false)
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Basic
  @Column(name = "last_name", length = 100, nullable = false)
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public int hashCode() {
    return Objects.hash(birthday, email, enabled, firstName, id, lastName);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Contact contact = (Contact) o;
    return Objects.equals(birthday, contact.birthday) && Objects.equals(email, contact.email) && Objects.equals(enabled, contact.enabled) && Objects.equals(firstName, contact.firstName) && Objects.equals(id, contact.id) && Objects.equals(lastName, contact.lastName);
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Contact{");
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
