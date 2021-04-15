package com.mphasis.laboratory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mphasis.laboratory.entity.Patient;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
	@Query("select p from Patient p where p.patientEmail=:patientEmail and p.password=:password")
	public Patient validateLogin(@Param("patientEmail")String patientEmail, @Param("password") String password);
	
	@Query("select p from Patient p where p.patientId=:patientId and p.patientEmail=:patientEmail")
	public Patient register(@Param("patientId")Long patientId, @Param("patientEmail") String patientEmail);
	

}
