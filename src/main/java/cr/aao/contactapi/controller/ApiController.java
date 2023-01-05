/******************************************************************************
 * Alvaro Araya O. coder@aao.cr aaocr +506 8899 8899                          *
 * Copyright (c) 2022.                                                        *
 ******************************************************************************/

package cr.aao.contactapi.controller;

import cr.aao.contactapi.dto.ContactDTO;
import cr.aao.contactapi.dto.ContactResponse;
import cr.aao.contactapi.dto.ContactsResponse;
import cr.aao.contactapi.entity.Contact;
import cr.aao.contactapi.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

  private final ContactService contactService;
  private final Logger logger = LoggerFactory.getLogger(ApiController.class);

  @Autowired
  public ApiController(ContactService contactService) {
    this.contactService = contactService;
  }

  @PostMapping(value = "/contact", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ContactResponse> createContact(@Valid @RequestBody ContactDTO contactDTO, Errors errors) {
    logger.info("create-contact-post");
    logger.info(contactDTO.toString());
    logger.info(errors.toString());
    HttpStatus httpStatus = HttpStatus.NO_CONTENT;
    ContactResponse contactResponse = new ContactResponse();
    if (errors.hasErrors()) {
      logger.warn("Errores en el ingreso de datos: " + errors);
      contactResponse.setCode("ERR-002");
      contactResponse.setMessage("Datos inválidos en la llamada.");
      httpStatus = HttpStatus.BAD_REQUEST;
      return new ResponseEntity<>(contactResponse, httpStatus);
    }
    try {
      if (contactDTO.getEmail() != null && !contactDTO.getEmail().isBlank()) {
        Contact contact = new Contact();
        Optional<Contact> contactResult;
        contact.setId(null);
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact.setEmail(contactDTO.getEmail());
        contact.setBirthday(contactDTO.getBirthday());
        contact.setEnabled(true);
        contactResult = contactService.create(contact);
        if (contactResult.isPresent()) {
          httpStatus = HttpStatus.OK;
          contactResponse.setContact(contact);
          contactResponse.setCode("CT-1001");
          contactResponse.setMessage("Contacto creado satisfactoriamente.");
        }
      } else {
        contactResponse.setCode("ERR-004");
        contactResponse.setMessage("Datos requeridos no están presentes.");
        httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(contactResponse, httpStatus);
      }
    } catch (Exception ex) {
      logger.error("Error " + ex.getMessage());
      httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
      contactResponse.setContact(null);
      contactResponse.setCode("ERR-004");
      contactResponse.setMessage("Error al crear el contacto.");
    }
    return new ResponseEntity<>(contactResponse, httpStatus);
  }

  @DeleteMapping(value = "/contact/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ContactResponse> deleteContact(@PathVariable Integer id) {
    logger.info("delete-contact");
    HttpStatus httpStatus = HttpStatus.NO_CONTENT;
    ContactResponse contactResponse = new ContactResponse();
    try {
      if (id != null && id > 0) {
        Optional<Contact> contact = contactService.findById(id);
        if (contact.isPresent()) {
          if (contactService.delete(contact.get())) {
            contactResponse.setCode("OK");
            contactResponse.setMessage("Contacto eliminado correctamente");
            httpStatus = HttpStatus.OK;
          }
        } else {
          contactResponse.setCode("ERR-005");
          contactResponse.setMessage("No se encontró el contacto con el ID proporcionado");
          httpStatus = HttpStatus.BAD_REQUEST;
        }
      } else {
        contactResponse.setCode("ERR-005");
        contactResponse.setMessage("Debe proporcionar un ID válido");
        httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(contactResponse, httpStatus);
      }
    } catch (Exception ex) {
      logger.error("Error " + ex.getMessage());
      httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
      contactResponse.setContact(null);
      contactResponse.setCode("ERR-005");
      contactResponse.setMessage("Error al eliminar el contacto.");
    }
    return new ResponseEntity<>(contactResponse, httpStatus);
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
      contactsResponse.setCode("ERR-001");
      contactsResponse.setMessage(ex.getMessage());
      logger.error("ERROR " + ex.getMessage());
      return new ResponseEntity<>(contactsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping(value = "/get-version")
  public ResponseEntity<String> getVersion() {
    return new ResponseEntity<>("1.0.0", HttpStatus.ACCEPTED);
  }

  @PatchMapping(value = "/contact", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ContactResponse> updateContact(@Valid @RequestBody ContactDTO contactDTO, Errors errors) {
    logger.info("update-contact-patch");
    logger.info(contactDTO.toString());
    logger.info(errors.toString());
    HttpStatus httpStatus = HttpStatus.NO_CONTENT;
    ContactResponse contactResponse = new ContactResponse();
    if (errors.hasErrors()) {
      logger.warn("Errores en el ingreso de datos: " + errors);
      contactResponse.setCode("ERR-002");
      contactResponse.setMessage("Datos inválidos en la llamada.");
      httpStatus = HttpStatus.BAD_REQUEST;
      return new ResponseEntity<>(contactResponse, httpStatus);
    }
    try {
      if (contactDTO.getId() != null && contactDTO.getEmail() != null && !contactDTO.getEmail().isBlank()) {
        Optional<Contact> contact = contactService.findById(contactDTO.getId());
        Optional<Contact> contactResult;
        if (contact.isPresent()) {
          contact.get().setFirstName(contactDTO.getFirstName());
          contact.get().setLastName(contactDTO.getLastName());
          contact.get().setEmail(contactDTO.getEmail());
          contact.get().setBirthday(contactDTO.getBirthday());
          contact.get().setEnabled(true);
          contactResult = contactService.update(contact.get());
          if (contactResult.isPresent()) {
            httpStatus = HttpStatus.OK;
            contactResponse.setContact(contactResult.get());
            contactResponse.setCode("CT-1002");
            contactResponse.setMessage("Contacto actualizado satisfactoriamente.");
          }
        } else {
          contactResponse.setCode("ERR-004");
          contactResponse.setMessage("No se encontró el contacto con el ID proporcionado");
          httpStatus = HttpStatus.BAD_REQUEST;
        }

      } else {
        contactResponse.setCode("ERR-003");
        contactResponse.setMessage("Datos requeridos no están presentes.");
        httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(contactResponse, httpStatus);
      }
    } catch (Exception ex) {
      logger.error("Error " + ex.getMessage());
      httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
      contactResponse.setContact(null);
      contactResponse.setCode("ERR-003");
      contactResponse.setMessage("Error al actualizar el contacto.");
    }
    return new ResponseEntity<>(contactResponse, httpStatus);
  }
}
