/******************************************************************************
 * Alvaro Araya O. coder@aao.cr aaocr +506 8899 8899                          *
 * Copyright (c) 2022.                                                        *
 ******************************************************************************/

package cr.aao.contactapi.service;

import cr.aao.contactapi.entity.Contact;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Profile("dev")
public class ContactServiceDevImpl implements ContactService {
  @Override
  public Optional<Contact> create(Contact contact) throws Exception {
    return Optional.empty();
  }

  @Override
  public Boolean delete(Contact contact) {
    return null;
  }

  @Override
  public List<Contact> findAll() throws Exception {
    ArrayList<Contact> contacts = new ArrayList<>();
    Contact contact = new Contact();
    contact.setEmail("x@aao.cr");
    contact.setFirstName("alvaro");
    contact.setLastName("araya");
    contacts.add(contact);
    return contacts;
  }

  @Override
  public Optional<Contact> findById(Integer id) {
    return Optional.empty();
  }

  @Override
  public Optional<Contact> update(Contact contact) {
    return Optional.empty();
  }
}
