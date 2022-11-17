/******************************************************************************
 * Alvaro Araya O. coder@aao.cr aaocr +506 8899 8899                          *
 * Copyright (c) 2022.                                                        *
 ******************************************************************************/

package cr.aao.contactapi.controller;

import cr.aao.contactapi.dto.ContactsResponse;
import cr.aao.contactapi.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

  private final ContactService contactService;

  @Autowired
  public ApiController(ContactService contactService) {
    this.contactService = contactService;
  }

  @GetMapping(value = "/contact", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ContactsResponse> getContacts() {
    ContactsResponse contactsResponse = new ContactsResponse();
    HttpStatus httpStatus = HttpStatus.OK;
    try {
      contactsResponse.setContacts(contactService.findAll());
      contactsResponse.setMessage("OK");
      contactsResponse.setCode("CT-1000");
      return new ResponseEntity<>(contactsResponse, httpStatus);
    } catch (Exception ex) {
      contactsResponse.setCode("ERR-1020");
      contactsResponse.setMessage(ex.getMessage());
      System.out.println("ERROR " + ex.getMessage());
      return new ResponseEntity<>(contactsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping(value = "/get-version")
  public ResponseEntity<String> getVersion() {
    return new ResponseEntity<>("1.0.0", HttpStatus.ACCEPTED);
  }
}
