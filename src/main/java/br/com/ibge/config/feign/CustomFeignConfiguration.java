package br.com.ibge.config.feign;

import br.com.ibge.config.feign.decoder.CustomGZIPResponseDecoder;
import feign.RequestInterceptor;
import feign.optionals.OptionalDecoder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class CustomFeignConfiguration {

    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    public RequestInterceptor gzipInterceptor() {
        return template -> template.header("Accept-Encoding", "gzip, deflate");
    }

    @Bean
    public CustomGZIPResponseDecoder customGZIPResponseDecoder() {
        OptionalDecoder feignDecoder = new OptionalDecoder(new ResponseEntityDecoder(new SpringDecoder(this.messageConverters)));
        return new CustomGZIPResponseDecoder(feignDecoder);
    }
}
