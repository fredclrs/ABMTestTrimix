package com.testtrimix.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.testtrimix.demo.model.Person;

public interface PersonDao extends JpaRepository<Person, Long> {
			
	List<Person> findByNombreContainingIgnoreCase (@Param("nombre") String nombre);
	
	List<Person>findByTipoDocumento(String tipoDocumento);
	
	List<Person> findAllByNombreAndTipoDocumentoContainingIgnoreCase (@Param("nombre") String nombre, @Param("tipoDocumento") String tipoDocumento);
	 

}