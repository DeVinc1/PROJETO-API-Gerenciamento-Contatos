package com.connectiva.app.rest_api_connectiva.repository;

import com.connectiva.app.rest_api_connectiva.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository <Contact, Long> {

}
