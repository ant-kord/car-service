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
@Table(name = "car", schema = "public", indexes = @Index(columnList = "id"))
public class Car {
	
	@Id
	@SequenceGenerator(name = "car_gen",sequenceName = "car_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_gen")
	private Long id;
	@Column(nullable = false)
	@NotBlank(message = "Car brand is required")
	private String brand;
	@Column(nullable = false)
	@NotBlank(message = "Car model is required")
	private String model;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	@Override
	public String toString() {
		return "Car [id=" + id + ", brand=" + brand + ", model=" + model + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(brand, id, model);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return Objects.equals(brand, other.brand) && Objects.equals(id, other.id) && Objects.equals(model, other.model);
	}
	
	
	

	
}
