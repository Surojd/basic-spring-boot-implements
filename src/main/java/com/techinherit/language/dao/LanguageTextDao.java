package com.techinherit.language.dao;

import com.techinherit.language.model.Language;
import com.techinherit.language.model.LanguageKey;
import com.techinherit.language.model.LanguageText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageTextDao extends JpaRepository<LanguageText, Integer> {

    LanguageText findByKeyIdAndLangId(LanguageKey key, Language language);
}
