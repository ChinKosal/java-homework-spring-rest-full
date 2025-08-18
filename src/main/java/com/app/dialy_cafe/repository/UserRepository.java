package com.app.dialy_cafe.repository;

import com.app.dialy_cafe.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<Users, Long>, JpaSpecificationExecutor<Users> {
}
