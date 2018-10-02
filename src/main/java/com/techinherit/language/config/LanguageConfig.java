package com.techinherit.language.config;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class LanguageConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    //for language
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SmartLocaleResolver slr = new SmartLocaleResolver();
        return slr;
    }

    class SmartLocaleResolver extends CookieLocaleResolver {

        @Override
        public Locale resolveLocale(HttpServletRequest request) {
            Locale locale = super.determineDefaultLocale(request);
            if (null == locale) {
                LocaleResolver lr = new AcceptHeaderLocaleResolver();
                locale = lr.resolveLocale(request);
            } else {
                setDefaultLocale(Locale.ENGLISH);
            }
            return locale;
        }

    }
}
