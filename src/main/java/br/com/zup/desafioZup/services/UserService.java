package br.com.zup.desafioZup.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.desafioZup.dto.UserDTO;
import br.com.zup.desafioZup.entities.User;
import br.com.zup.desafioZup.repositories.UserRepository;
import br.com.zup.desafioZup.services.exceptions.DatabaseException;
import br.com.zup.desafioZup.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Transactional(readOnly = true )
	public Page<UserDTO> findAllPaged(PageRequest pageResquest){
		Page<User> list = repository.findAll(pageResquest);
		return  list.map( x -> new UserDTO(x));
	}
	
	@Transactional(readOnly = true )
	public UserDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new UserDTO(entity);
	}
	
	@Transactional
	public UserDTO insert(UserDTO dto) {
		User entity = new User();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new UserDTO(entity);
	}
	
	@Transactional
	public UserDTO updtate(Long id, UserDTO dto) {
		try {
			User entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new UserDTO(entity);
		
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		
		}
		catch(EmptyResultDataAccessException e) {
			throw new  ResourceNotFoundException("Id mot Found "  + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity Violation");
		}
	}

	
	
	private void copyDtoToEntity(UserDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setCpf(dto.getCpf());
		entity.setBirthDate(dto.getBirthDate());
	}
	

}
