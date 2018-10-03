package com.techinherit.basic.dao;

import com.techinherit.basic.entity.Users;
import com.techinherit.basic.enums.UserRole;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {

    public Users findByUsername(String username);

    public Page<Users> findByUserRole(UserRole userRole, Pageable pg);

    public List<Users> findByUserRole(UserRole userRole);

}
