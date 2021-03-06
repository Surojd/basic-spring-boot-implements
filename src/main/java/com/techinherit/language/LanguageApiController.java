package com.techinherit.language;

import com.techinherit.basic.response.ServiceResponse;
import com.techinherit.language.config.MessageResolverMethod;
import com.techinherit.language.dao.LanguageDao;
import com.techinherit.language.dao.LanguageKeyDao;
import com.techinherit.language.dao.LanguageTextDao;
import com.techinherit.language.dto.KeyDto;
import com.techinherit.language.dto.LanguageDto;
import com.techinherit.language.dto.LanguageTextDto;
import com.techinherit.language.mapper.LanguageMapper;
import com.techinherit.language.model.Language;
import com.techinherit.language.model.LanguageKey;
import com.techinherit.language.model.LanguageText;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/language")
@Secured("ROLE_ADMIN")
public class LanguageApiController {

    @Autowired
    private LanguageDao languageDao;

    @Autowired
    private LanguageTextDao textDao;

    @Autowired
    private LanguageKeyDao keyDao;

    @Autowired
    private MessageResolverMethod resolverMethod;

    private ServiceResponse response;

    @Autowired
    private LanguageMapper languageMapper;

    @GetMapping
    public ServiceResponse<List<Language>> getLanguages() throws Exception {
        response = new ServiceResponse<>();
        response.setData(languageDao.findAll());
        return response;
    }

    @PostMapping
    public ServiceResponse addLanguage(@RequestBody LanguageDto dto) throws Exception {
        response = new ServiceResponse<>();
        Language lang = languageMapper.toLanguage(dto);
        languageDao.save(lang);
        return response;
    }

    @PutMapping("/{id}")
    public ServiceResponse updateLanguage(@RequestBody LanguageDto dto, @PathVariable Integer id) throws Exception {
        response = new ServiceResponse<>();
        Language lang = languageDao.findById(id).orElse(null);
        if (lang == null) {
            throw new Exception("Language Id not found");
        }
        languageMapper.toLanguage(lang, dto);
        languageDao.save(lang);
        return response;
    }

    @GetMapping("/keys")
    public ServiceResponse<List<LanguageKey>> getKeys() throws Exception {
        response = new ServiceResponse<>();
        response.setData(keyDao.findAll());
        return response;
    }

    @PostMapping("/key")
    public ServiceResponse addKey(@RequestBody KeyDto dto) throws Exception {
        response = new ServiceResponse<>();
        LanguageKey key = new LanguageKey();
        key.setKeyName(dto.getKey());
        keyDao.save(key);
        return response;
    }

    @PutMapping("/key/{id}")
    public ServiceResponse getKeys(@RequestBody KeyDto dto, @PathVariable Integer id) throws Exception {
        response = new ServiceResponse<>();
        LanguageKey key = keyDao.findById(id).orElse(null);
        if (key == null) {
            throw new Exception("Key not found");
        }
        key.setKeyName(dto.getKey());
        keyDao.save(key);
        return response;
    }

    @GetMapping("/text/key/{key}")
    public ServiceResponse<String> getText(@PathVariable String key) throws Exception {
        response = new ServiceResponse<>();
        response.setData(resolverMethod.getMessage(key));
        return response;
    }

    @GetMapping("/text/{id}")
    public ServiceResponse<LanguageText> updateText(@RequestBody LanguageTextDto dto, @PathVariable Integer id) throws Exception {
        response = new ServiceResponse<>();
        LanguageText key = textDao.findById(id).orElse(null);
        if (key == null) {
            throw new Exception("Language Text Id not found");
        }
        key.setText(dto.getText().getBytes(key.getLangId().getCharSet()));
        textDao.save(key);
        return response;
    }

    @PostMapping("/{langId}/{keyId}/text")
    public ServiceResponse addLang(@RequestBody LanguageTextDto dto, @PathVariable Integer langId, @PathVariable Integer keyId) throws Exception {
        response = new ServiceResponse();
        Language lang = languageDao.findById(langId).orElse(null);
        if (lang == null) {
            throw new Exception("Language Id not found");
        }
        LanguageKey key = keyDao.findById(keyId).orElse(null);
        if (key == null) {
            throw new Exception("Key Id not found");
        }
        LanguageText lt = new LanguageText();
        lt.setKeyId(key);
        lt.setLangId(lang);
        lt.setText(dto.getText().getBytes(lang.getCharSet()));
        textDao.save(lt);
        return response;
    }

}
