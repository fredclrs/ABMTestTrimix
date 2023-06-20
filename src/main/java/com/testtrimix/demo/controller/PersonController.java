package com.testtrimix.demo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.xml.ws.http.HTTPException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.testtrimix.demo.dao.PersonDao;
import com.testtrimix.demo.model.Person;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/secured/person")
public class PersonController {
	
	@Autowired
	private PersonDao personDao;
	
	@PostMapping
	public ResponseEntity<Person> addPerson(@RequestBody Person person){
		try {
			person = personDao.save(person);
			return ResponseEntity.ok(person);
			
		} catch (HTTPException e) {
			return ResponseEntity.status(e.getStatusCode()).build();			
		}
	}
	
	@GetMapping
	@RequestMapping("/list")
	public ResponseEntity<List<Person>> getPersons(){
		return ResponseEntity.ok(personDao.findAll(Sort.by(Sort.Direction.DESC,"id")));
	}
	
	@GetMapping
	@RequestMapping("/like")
	public ResponseEntity<List<Person>> getPersonsBuyLike(@RequestParam String nombre){
		return ResponseEntity.ok(personDao.findByNombreContainingIgnoreCase(nombre));
	}
	
	@GetMapping
	@RequestMapping("/by-tipoDocumento")
	public ResponseEntity<List<Person>> getPersonsByTipoDocumento(@RequestParam String tipoDocumento){
		return ResponseEntity.ok(personDao.findByTipoDocumento(tipoDocumento));
	}
	
	@GetMapping
	@RequestMapping("/nombre-and-tipoDocumento")
	public ResponseEntity<Set<Person>> getPersonsByNombreTipoDocumento(@RequestParam String nombre,@RequestParam String tipoDocumento){
		List<Person> personsNombre = personDao.findByNombreContainingIgnoreCase(nombre);
		List<Person> personsDni = personDao.findByTipoDocumento(tipoDocumento);
		Set<Person> personNombreDni = new HashSet<>();
		personNombreDni.addAll(personsNombre);
		personNombreDni.addAll(personsDni);
		return ResponseEntity.ok(personNombreDni);
	}
	
	@PutMapping	
	public ResponseEntity<Person> editPerson(@RequestBody Person person){
		try {
			Optional<Person> persona = personDao.findById(person.getId());
			if(persona.isPresent()) {
				person = personDao.save(person);
				return ResponseEntity.ok(person);
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			
			
		} catch (HTTPException e) {
			return ResponseEntity.status(e.getStatusCode()).build();			
		}
	}
	
	@DeleteMapping
	public ResponseEntity<?> deletePerson(@RequestParam Long personId){
		try {
			Optional<Person> persona = personDao.findById(personId);
			if(persona.isPresent()) {
				personDao.delete(persona.get());;
				return ResponseEntity.ok(true);
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			
			
		} catch (HTTPException e) {
			return ResponseEntity.status(e.getStatusCode()).build();			
		}
	}
	
		
	
}
