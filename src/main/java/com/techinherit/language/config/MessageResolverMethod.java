package com.techinherit.language.config;

import java.nio.charset.Charset;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

@Component
public class MessageResolverMethod {

    @Autowired
    private DatabaseMessageSource messageSource;
    @Autowired
    private LocaleResolver localeResolver;
    @Autowired
    private HttpServletRequest request;

    public Object getMessage(String code) {
        if (code == null || code.isEmpty()) {
            return "";
        }
        return messageSource.getMessage(code, null, localeResolver.resolveLocale(request));
    }

    public String bytes(byte[] b, String charSet) {
        String str = new String(b, Charset.forName(charSet));
        str = str.replaceAll("'", "&apos;");
        return str;
    }
}
