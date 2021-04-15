package com.mphasis.laboratory.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Patient")
public class Patient implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long patientId;
	private String patientFirstname;
	private String patientLastName;
	private String age;
	private String gender;
	private String patientphoneNumber;
	private String patientEmail;
	private String password;
	 @OneToMany(cascade =  CascadeType.ALL,
	            mappedBy = "patient")
	 private List<Appointment> appointmentList;
 //	    private Appointment appointment;
	 
	 @OneToMany(cascade= CascadeType.ALL,
			 	mappedBy = "patient")
	 private List<TestReport> testReport;
	 
	
	
	public Patient() {}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getPatientphoneNumber() {
		return patientphoneNumber;
	}
	public void setPatientphoneNumber(String patientphoneNumber) {
		this.patientphoneNumber = patientphoneNumber;
	}
	public String getPatientEmail() {
		return patientEmail;
	}
	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPatientFirstname() {
		return patientFirstname;
	}
	public void setPatientFirstname(String patientFirstname) {
		this.patientFirstname = patientFirstname;
	}
	public String getPatientLastName() {
		return patientLastName;
	}
	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}
	public Patient(Long patientId, String patientFirstname, String patientLastName, String age, String gender,
			String patientphoneNumber, String patientEmail, String password) {
		super();
		this.patientId = patientId;
		this.patientFirstname = patientFirstname;
		this.patientLastName = patientLastName;
		this.age = age;
		this.gender = gender;
		this.patientphoneNumber = patientphoneNumber;
		this.patientEmail = patientEmail;
		this.password = password;
	}
	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientFirstname=" + patientFirstname + ", patientLastName="
				+ patientLastName + ", age=" + age + ", gender=" + gender + ", phoneNumber=" + patientphoneNumber + ", email="
				+ patientEmail + ", password=" + password + "]";
	}
	
}