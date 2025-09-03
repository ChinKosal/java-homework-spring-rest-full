package com.app.dialy_cafe.repository;

import com.app.dialy_cafe.model.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<Users, Long>, JpaSpecificationExecutor<Users> {
    Optional<Users> findByName(String name);
    Optional<Users> findByEmail(String email);
    boolean existsByName(String name);
    boolean existsByEmail(String email);
}
