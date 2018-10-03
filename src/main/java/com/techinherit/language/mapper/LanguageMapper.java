package com.techinherit.language.mapper;

import com.techinherit.language.dto.LanguageDto;
import com.techinherit.language.model.Language;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {})
public interface LanguageMapper {

    LanguageMapper INSTANCE = Mappers.getMapper(LanguageMapper.class);

    Language toLanguage(LanguageDto dto);

    void toLanguage(@MappingTarget Language lang, LanguageDto dto);
}
