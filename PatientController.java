package com.mphasis.laboratory.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.mphasis.laboratory.entity.Patient;
import com.mphasis.laboratory.service.PatientService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RequestMapping("/patient")
public class PatientController {
	@Autowired
	PatientService ps;
	@GetMapping("/")
	public List<Patient> getAllPatients()
	{
		List<Patient>patients=ps.read();
		return patients;
		
	}
	@GetMapping("/{patientId}")
	public Patient findPatientById(@PathVariable("patientId") Long patientId)
	{
		return ps.read(patientId);

	}
	@PostMapping("/")
	public Patient addPatient(Long patientId,@RequestParam("patientFirstname")String patientFirstname,@RequestParam("patientLastName") String patientLastName,@RequestParam("age") String age,@RequestParam("gender") String gender,@RequestParam("patientphoneNumber") String patientphoneNumber,@RequestParam("patientEmail")String patientEmail,@RequestParam("password" )String password)
	{
		
		Patient patient=new Patient(patientId, patientFirstname, patientLastName, age, gender, patientphoneNumber, patientEmail, password);
//		System.out.println(id);
		
//		System.out.println(email);
		
		
		return ps.create(patient);
		
	}
	@PostMapping("/register")
	public Patient registerPatient(@RequestParam("patientId") Long patientId,@RequestParam("patientEmail")String patientEmail)
	{
		if(patientId==null)
			return null;
		else
		{
			sendEmail(patientEmail, "Welcome to health technologies your registration number is "+patientId+" Do not share this with anybody");
			return ps.register(patientId, patientEmail);
						
		}
	}
	
	@PostMapping("/login")
	public Patient validateLogin(@RequestParam("patientEmail") String patientEmail, @RequestParam("password") String password)
	{
		if(patientEmail==null)
			
		{
//			System.out.println("not valid crendentials");
			return null;
		}
		return ps.validateLogin(patientEmail, password);
	}
	@PutMapping("/")
	public Patient modifyPatient(@RequestBody Patient patient)
	{
		return ps.update(patient);
	}
	@DeleteMapping("/{patientId}")
	public void deletePatient(@PathVariable("patientId") Long patientId)
	{
		ps.delete(patientId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//send patient email
	private void sendEmail(String to, String message)
	{
		 Properties props = new Properties();
	        props.put("mail.smtp.host", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.auth", "true");
	        //Establishing a session with required user details
	        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("healthtechnologies.pvt.ltd@gmail.com", "laboratory");
	            }
	        });
	        try {
	            //Creating a Message object to set the email content
	            MimeMessage msg = new MimeMessage(session);
	            //Storing the comma seperated values to email addresses
//	            String to = "rjagadeeswaran@yahoo.com";
	            /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email
	            addresses in an array of InternetAddress objects*/
	            InternetAddress[] address = InternetAddress.parse(to, true);
	            //Setting the recepients from the address variable
	            msg.setRecipients(Message.RecipientType.TO, address);
	            String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());
	            msg.setSubject("Otp : " + timeStamp);
	            msg.setSentDate(new Date());
	            msg.setText(message);
	            msg.setHeader("XPriority", "1");
	           
	            Transport.send(msg);
	            System.out.println("Mail has been sent successfully");
	        } catch (MessagingException mex) {
	            System.out.println("Unable to send an email:\n" + mex);
	        }
	}


}
