package org.khasanof.config;

import lombok.Data;
import org.khasanof.enums.ProcessType;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 8/4/2023 7:20 AM
 */
@Data
@ConfigurationProperties(prefix = "fluent")
public class FluentProperties {

    private Bot bot = new Bot();

    @Data // inner class remove
    public static class Bot {
        private String token;
        private String username;
        private ProcessType processType = ProcessType.UPDATE;
    }

}
