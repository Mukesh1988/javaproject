package com.mintisoft.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;

import lombok.Data;

@Data
@Entity
@Table(name = "users_tab")
public class User {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@Column(name = "uname")
	private String userName;

	@Column(name = "umail")
	private String userEmail;

	@Column(name = "upwd")
	private String userPwd;

	@ElementCollection (fetch = FetchType.EAGER) // this is helping to featching the data from roles table
	@CollectionTable(name = "roles_tab", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "role")
	private List<String> roles;
}