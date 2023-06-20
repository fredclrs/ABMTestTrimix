package com.testtrimix.demo.model;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person", schema = "repositorio_trimix")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	@Column(name = "nombre", nullable = false)
	private String nombre;
	@Column(name = "apellido", nullable = false)
	private String apellido;
	@Column(name = "numero_documento", nullable = false)
	private Long numeroDocumento;
	@Column(name = "tipo_documento", nullable = false)
	private String tipoDocumento;
	@Column(name = "fecha_nacimiento", nullable = false)
	private String fechaNacimiento;
		
	public Person(Long id, String nombre, String apellido, Long numeroDocumento, String tipoDocumento,
			String fechaNacimiento) {		
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.numeroDocumento = numeroDocumento;
		this.tipoDocumento = tipoDocumento;
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public Person() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(id, other.id);
	}

	public Long getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Long getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	
}
