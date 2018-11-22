package com.techinherit.yourpackage.dao;

import com.techinherit.yourpackage.enums.UserRole;
import com.techinherit.yourpackage.database.Users;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {

    public Users findByUsername(String username);

    public Page<Users> findByUserRole(UserRole userRole, Pageable pg);

    public List<Users> findByUserRole(UserRole userRole);

    public Optional<Users> findByUsernameAndVerificationCode(String username, int verificationCode);

    public Optional<Users> findByUsernameOrEmail(String username, String username0);

}
