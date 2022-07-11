package com.charan.projectStudentManagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.charan.projectStudentManagement.model.StudentRecord;
import com.charan.projectStudentManagement.service.StudentManagementService;

@RestController
public class StudentManagementController {

	@Autowired
	private StudentManagementService studentManagementService;
	
	@SuppressWarnings("rawtypes")
	@CrossOrigin(origins = "http://localhost:3000/")
	@GetMapping("studentrecord/{rollNo}")
	
	public ResponseEntity getStudentRecord(@PathVariable int rollNo) {
		try {
			Optional<StudentRecord> result =studentManagementService.getStudentRecord(rollNo);
			if(result.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No records found for rollNo "+rollNo);
			}
			return ResponseEntity.status(HttpStatus.OK).body(result.get());
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("studentrecord")
	public ResponseEntity getStudentRecords() {
		try {
			List<StudentRecord> result =studentManagementService.getStudentRecords();
			if(result.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No records found");
			}
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("studentrecord/add")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity addStudentRecord(@RequestBody StudentRecord studentRecord) {
		try {
			studentManagementService.addStudentRecord(studentRecord);
			return ResponseEntity.status(HttpStatus.CREATED).body("Student Records added successfully");
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("studentrecord/edit/{rollNo}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity editStudentRecord(@PathVariable int rollNo,@RequestBody StudentRecord studentRecord) {
		try {
			boolean result = studentManagementService.editStudentRecord(studentRecord);
			if(result) {
				return ResponseEntity.status(HttpStatus.OK).body("Student record updated successfully");
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student record not found for rollNo "+rollNo);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("studentrecord/delete/{rollNo}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity deleteStudentRecord(@PathVariable int rollNo) {
		try {
			boolean result = studentManagementService.deleteStudentRecord(rollNo);
			if(result) {
				return ResponseEntity.status(HttpStatus.OK).body("Student record deleted successfully");
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student record not found for rollNo "+rollNo);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}
}
