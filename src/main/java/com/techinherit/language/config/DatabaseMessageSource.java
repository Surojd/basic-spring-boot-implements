package com.techinherit.language.config;

import com.techinherit.language.dao.LanguageDao;
import com.techinherit.language.dao.LanguageKeyDao;
import com.techinherit.language.dao.LanguageTextDao;
import com.techinherit.language.model.Language;
import com.techinherit.language.model.LanguageKey;
import com.techinherit.language.model.LanguageText;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

@Component
public class DatabaseMessageSource implements MessageSource {

    @Autowired
    LanguageTextDao textDao;

    @Autowired
    LanguageDao languageDao;

    @Autowired
    LanguageKeyDao keyDao;

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return resolveMessage(code, args, locale);
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        return resolveMessage(code, args, locale);
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        for (String code : resolvable.getCodes()) {
            String message = resolveMessage(code, resolvable.getArguments(), locale);
            if (message != null) {
                return message;
            }
        }
        return "";
    }

    private String resolveMessage(String code, Object[] args, Locale locale) {
        LanguageKey key = keyDao.findByKeyName(code);
        if (key == null) {
            return "";
        }
        Language lang = languageDao.findByLang(locale.getLanguage());
        if (lang == null) {
            lang = new Language(1);
        }
        LanguageText text = textDao.findByKeyIdAndLangId(key, lang);
        if (text == null) {
            return "";
        }
        String str = new String(text.getText(), Charset.forName(lang.getCharSet()));
        str = str.replaceAll("'", "&apos;");
        MessageFormat messageFormat = new MessageFormat(str, locale);
        return messageFormat.format(args);
    }
}
