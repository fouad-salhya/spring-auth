package com.auth.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.auth.entities.UserEntity;


@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>, JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByEmail(String email);
	
}
