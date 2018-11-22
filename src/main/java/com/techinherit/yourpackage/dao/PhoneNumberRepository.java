package com.techinherit.yourpackage.dao;

import com.techinherit.yourpackage.database.PhoneNumbers;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumbers, String> {

    public Optional<PhoneNumbers> findByPhoneNumberAndVerificationCodeAndSignupDateBetween(String username, int verificationCode, Long min, Long max);

}
