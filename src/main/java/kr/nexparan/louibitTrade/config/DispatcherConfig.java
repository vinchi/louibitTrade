package kr.nexparan.louibitTrade.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Locale;

@EnableWebMvc
@Configuration
public class DispatcherConfig implements WebMvcConfigurer {
    @Bean
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.KOREA); // 기본 한국어
        localeResolver.setCookieName("locale"); // 쿠키 이름 ; locale
        localeResolver.setCookieMaxAge(60 * 60); // 쿠키 살려둘 시간, -1로 하면 브라우져를 닫을 때 없어짐.
        localeResolver.setCookiePath("/"); // Path를 지정해 주면 해당하는 path와 그 하위 path에서만 참조
        return localeResolver;
    }//:
}
