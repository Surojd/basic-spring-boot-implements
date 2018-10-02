package com.techinherit.language.dao;

import com.techinherit.language.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageDao extends JpaRepository<Language, Integer> {

    Language findByLang(String lang);
}
