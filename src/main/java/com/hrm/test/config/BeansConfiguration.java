package com.hrm.test.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.mappers.ModelMapper;
import springfox.documentation.swagger2.mappers.ModelMapperImpl;

import java.util.List;

@Configuration
public class BeansConfiguration implements WebMvcConfigurer {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapperImpl();
    }

    //Hibernate lazy collections configurations
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                ObjectMapper mapper = ((MappingJackson2HttpMessageConverter) converter).getObjectMapper();
                Hibernate5Module hibernate5Module = new Hibernate5Module();
                hibernate5Module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
                hibernate5Module.enable(Hibernate5Module.Feature.WRITE_MISSING_ENTITIES_AS_NULL);
                mapper.registerModule(hibernate5Module);
            }
        }
    }

}
