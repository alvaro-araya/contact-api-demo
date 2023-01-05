/******************************************************************************
 * Alvaro Araya O. coder@aao.cr aaocr +506 8899 8899                          *
 * Copyright (c) 2022.                                                        *
 ******************************************************************************/

package cr.aao.contactapi.service;

import cr.aao.contactapi.entity.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {

  Optional<Contact> create(Contact contact) throws Exception;

  Boolean delete(Contact contact) throws Exception;

  List<Contact> findAll() throws Exception;

  Optional<Contact> findById(Integer id) throws Exception;

  Optional<Contact> update(Contact contact) throws Exception;

}
