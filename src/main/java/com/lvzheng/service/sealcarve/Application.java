package com.lvzheng.service.sealcarve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 跨域过滤器
     * @return CorsFilter
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConf = new CorsConfiguration();
        corsConf.addAllowedOrigin("http://kezhang.xw.eanimal.net");
        corsConf.addAllowedOrigin("http://jebe.lvzheng.com");
        corsConf.addAllowedHeader("*");
        corsConf.addAllowedMethod("*");
        corsConf.setAllowCredentials(true);
        corsConf.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConf); // 4

        return new CorsFilter(source);
    }
}
