package com.techinherit.dao;

import com.techinherit.entity.Users;
import com.techinherit.enums.UserRole;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {

    public Users findByUsername(String username);

    public Page<Users> findByUserRole(UserRole userRole, Pageable pg);

    public List<Users> findByUserRole(UserRole userRole);

}
