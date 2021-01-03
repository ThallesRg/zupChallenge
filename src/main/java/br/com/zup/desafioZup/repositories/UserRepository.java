package br.com.zup.desafioZup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.desafioZup.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
