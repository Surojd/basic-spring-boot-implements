package com.techinherit.language.mapper;

import com.techinherit.language.dto.LanguageDto;
import com.techinherit.language.model.Language;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LanguageMapper {

    Language toLanguage(LanguageDto dto);

    void toLanguage(@MappingTarget Language lang, LanguageDto dto);
}
