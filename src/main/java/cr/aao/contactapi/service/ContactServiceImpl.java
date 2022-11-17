/******************************************************************************
 * Alvaro Araya O. coder@aao.cr aaocr +506 8899 8899                          *
 * Copyright (c) 2022.                                                        *
 ******************************************************************************/

package cr.aao.contactapi.service;

import cr.aao.contactapi.entity.Contact;
import cr.aao.contactapi.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Profile("production")
public class ContactServiceImpl implements ContactService {

  private final ContactRepository contactRepository;

  @Autowired
  public ContactServiceImpl(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }

  @Override
  public Optional<Contact> create(Contact contact) throws Exception {
    try {
      contactRepository.save(contact);
      return Optional.of(contact);
    } catch (Exception ex) {
      System.out.println("Error " + ex.getMessage());
      throw new Exception("No pudo crearse el contact");
    }
  }

  @Override
  public Boolean delete(Contact contact) {
    return null;
  }

  @Override
  public List<Contact> findAll() throws Exception {
    try {
      return contactRepository.findAll();
    } catch (Exception ex) {
      System.out.println("Error " + ex.getMessage());
      throw new Exception("No pudo consultar la lista de contacts");
    }
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
