package br.com.zup.desafioZup.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import br.com.zup.desafioZup.entities.User;

public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Campo Obrigatório")
	private String name;
	
	@Email(message = "Favor inserir um email valido")
	private String email;
	
	@Pattern(regexp = "(\\d{3}.?\\d{3}.?\\d{3}-?\\d{2})")
	private String cpf;
	private Instant birthDate; 
	
	public UserDTO() {
	}

	public UserDTO(Long id, String name, String email, String cpf, Instant birthDate) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.birthDate = birthDate;
	}
	
	public UserDTO(User entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		cpf = entity.getCpf();
		birthDate = entity.getBirthDate();
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Instant getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Instant birthDate) {
		this.birthDate = birthDate;
	}
	
}
