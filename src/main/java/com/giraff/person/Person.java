package com.giraff.person;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
public class Person implements Serializable{
	
	private static final long serialVersionUID = -493381936105006768L;
	
	@XmlRootElement(name = "Gender")
	public enum Gender {
		Female("Female"), Male("Male");

		 private final String value;

		 Gender(String value) {
		   this.value = value;
		 }

		 public static Gender fromValue(String value) {
		   if (value != null) {
		     for (Gender gender : values()) {
		       if (gender.value.equals(value)) {
		         return gender;
		       }
		     }
		   }

		   throw new IllegalArgumentException("Invalid gender: " + value);
		 }

		 public String toValue() {
		   return value;
		 }
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@XmlElement
	@NotNull
	@Size(min=3, max=255)
	private String name;
	
	@XmlElement
	@Size(min=3, max=255)
	private String givenName;
	
	@XmlElement
	@Size(min=3, max=255)
	private String familyName;
	
	@Enumerated(EnumType.STRING)
	@XmlElement
	private Gender gender;
	
	@XmlElement
	private String mbox;
	
	@XmlElement
	private String homepage;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getMbox() {
		return mbox;
	}
	public void setMbox(String mbox) {
		this.mbox = mbox;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

}
