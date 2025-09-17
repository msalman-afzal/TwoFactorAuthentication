package com.spring.security.userRepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.EntityCls.UserSecrectkey;

@Repository
public interface UserOtpRepo extends JpaRepository<UserSecrectkey, Integer> {
	
	Optional<UserSecrectkey>  findByUsername(String username);

}
