package ru.softlogic.carservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import groovy.transform.Canonical

@Entity
@Table(name = "mechanic", schema = "public", indexes = @Index(columnList = "id"))
public class Mechanic {
	
	@Id
	@SequenceGenerator(name = "mech_gen", sequenceName = "mech_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mech_gen")
	private Long id;
	@Column(nullable = false)
	@NotBlank(message = "Mechanic firstName is required")
	private String firstName;
	@Column(nullable = false)
	@NotBlank(message = "Mechanic lastName is required")
	private String lastName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return "Mechanic [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(firstName, id, lastName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mechanic other = (Mechanic) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName);
	}
	
	
}
