package com.trabalhoFinal.apiEcommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabalhoFinal.apiEcommerce.entities.Role;
import com.trabalhoFinal.apiEcommerce.entities.RoleEnum;



@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByName(RoleEnum name);
}