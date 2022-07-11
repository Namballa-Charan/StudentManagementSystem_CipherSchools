package com.charan.projectStudentManagement.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.charan.projectStudentManagement.model.StudentRecord;

@Repository
public class StudentManagementRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void addStudentRecord(StudentRecord studentRecord) {
		jdbcTemplate.update("Insert into studentrecord(rollNo,StudentName,contactNo) values (?,?,?)",
				new Object[] { studentRecord.getRollNo(), studentRecord.getName(), studentRecord.getContactNo() });
	}

	public Optional<StudentRecord> getStudentRecord(int rollNo) {
		@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
		List<StudentRecord> studentRecords = jdbcTemplate.query("select * from studentrecord where rollNo=?",
				new Object[] { rollNo }, new RowMapper() {
					@Override
					public StudentRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
						StudentRecord studentRecord = new StudentRecord();
						studentRecord.setRollNo(rs.getInt("rollNo"));
						studentRecord.setName(rs.getString("StudentName"));
						studentRecord.setContactNo(rs.getLong("contactNo"));
						return studentRecord;
					}
				});
		if (studentRecords.isEmpty()) {
			return Optional.empty();
		}
		return Optional.ofNullable(studentRecords.get(0));
	}

	public List<StudentRecord> getStudentRecords() {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<StudentRecord> studentRecord = jdbcTemplate.query("select * from studentrecord", new RowMapper() {
			@Override
			public StudentRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
				StudentRecord studentRecord = new StudentRecord();
				studentRecord.setRollNo(rs.getInt("rollNo"));
				studentRecord.setName(rs.getString("StudentName"));
				studentRecord.setContactNo(rs.getLong("contactNo"));
				return studentRecord;
			}
		});
		return studentRecord;
	}

	public boolean editStudentRecord(StudentRecord studentRecord) {
		int num = jdbcTemplate.update("update studentrecord set StudentName=?,contactNo=? where rollNo=?",
				new Object[] { studentRecord.getName(), studentRecord.getContactNo(), studentRecord.getRollNo() });
		return num>0?true:false;
	}
	
	public boolean deleteStudentRecord(int rollNo) {
		int num = jdbcTemplate.update("Delete from studentrecord where rollNo=?",
				new Object[] {rollNo });
		return num>0?true:false;
	}
}
