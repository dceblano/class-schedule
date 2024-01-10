package edu.seait.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_campus")
public class Campus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -392724739348620162L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "campus_Id")
	private Long id;

	@Column(name = "campus_code")
	private String campusCode;

	@Column(name = "campus_name")
	private String campusName;

	@Column(name = "address")
	private String address;

	@Column(name = "contact_no")
	private String contactNo;

	@JsonManagedReference
	@OneToMany(mappedBy = "campus")
	private List<Department> departments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCampusCode() {
		return campusCode;
	}

	public void setCampusCode(String campusCode) {
		this.campusCode = campusCode;
	}

	public String getCampusName() {
		return campusName;
	}

	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

}
