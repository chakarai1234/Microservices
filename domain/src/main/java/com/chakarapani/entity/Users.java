package com.chakarapani.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = {
		@UniqueConstraint(name = "EmailConstraint", columnNames = "email"),
		@UniqueConstraint(name = "UsernameConstraint", columnNames = "username")
})
@SuppressWarnings("unused")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, columnDefinition = "uuid")
	private UUID id;

	@Column(name = "firstname")
	@JsonProperty("firstname")
	private String firstname;

	@Column(name = "lastname")
	@JsonProperty("lastname")
	private String lastname;

	@Column(name = "username")
	@JsonProperty("username")
	private String username;

	@JsonProperty("dob")
	@Column(name = "dob")
	private LocalDate dateOfBirth;

	@Transient
	@Column(name = "age")
	@JsonProperty("age")
	private Integer age;

	@Column(name = "email")
	@JsonProperty("email")
	private String email;

	public Integer getAge() {
		return Period.between(dateOfBirth, LocalDate.now()).getYears();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Users users = (Users) o;
		return getId() != null && Objects.equals(getId(), users.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
