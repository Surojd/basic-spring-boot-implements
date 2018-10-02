package com.techinherit.language.dao;

import com.techinherit.language.model.LanguageKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageKeyDao extends JpaRepository<LanguageKey, Integer> {

    LanguageKey findByKeyName(String lang);

    public Page<LanguageKey> findByKeyNameContaining(String search, Pageable pg);
}
