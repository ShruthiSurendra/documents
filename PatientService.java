package com.mphasis.laboratory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mphasis.laboratory.entity.Patient;
import com.mphasis.laboratory.repository.PatientRepository;

@Component("ps")
public class PatientService {
	@Autowired
	PatientRepository patientRepo;
	public Patient create(Patient pt)
	{
		return patientRepo.save(pt);
	}
	public List<Patient> read()
	{
		return patientRepo.findAll();
	}
	public Patient read(Long patientId)
	{
		return patientRepo.findById(patientId).get();
	}
	public Patient update(Patient Patient)
	{
		return patientRepo.save(Patient);
	}

	public Patient validateLogin(String patientEmail, String password) {
		return patientRepo.validateLogin(patientEmail, password);
	}

	public Patient register(Long patientId, String patientEmail) {
		return patientRepo.register(patientId, patientEmail);
	}
	public void delete(Long patientId)
	{
		patientRepo.delete(read(patientId));
	}
	

}
