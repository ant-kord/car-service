package ru.softlogic.carservice.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "order", schema = "public", indexes = @Index(columnList = "id"))
public class Order  {

	@Id
	@SequenceGenerator(name = "order_gen", sequenceName = "order_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_gen")
	private Long id;
	@Column(nullable = false, updatable = false)
	@CreatedDate
	private Date createdDate;
	@CreatedBy
	private String createdUser;
	@LastModifiedDate
	private Date modifiedDate;
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private Car car;
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private Mechanic mechanic;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	public void setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", createdDate=" + createdDate + ", createdUser=" + createdUser + ", modifiedDate="
				+ modifiedDate + ", car=" + car + ", mechanic=" + mechanic + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(car, createdDate, createdUser, id, mechanic, modifiedDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(car, other.car) && Objects.equals(createdDate, other.createdDate) &&
				Objects.equals(createdUser, other.createdUser) && Objects.equals(id, other.id) &&
				Objects.equals(mechanic, other.mechanic) && Objects.equals(modifiedDate, other.modifiedDate);
	}
	 
	
}
