package com.udemy.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.todo.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
