package com.granvilledon.grapiclientsdk;

import com.granvilledon.grapiclientsdk.client.GrapiClient;
import com.granvilledon.grapiclientsdk.model.User;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("grapi.client")
@Data
@ComponentScan
public class GrapiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public GrapiClient grapiClient() {
        return new GrapiClient(accessKey, secretKey);
    }
}
