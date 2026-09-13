package com.example.brenda.Cece.s.Art.repository;

import com.example.brenda.Cece.s.Art.model.entity.User;
import com.example.brenda.Cece.s.Art.model.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    boolean existsByEmail(String email);
    Page<User> findAllByRoleOrderByCreatedAtDesc(Role role, Pageable pageable);
    List<User> findAllByRole(Role role);

}
