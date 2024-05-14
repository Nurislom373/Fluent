package org.khasanof.config;

import lombok.Data;
import org.khasanof.enums.BotType;
import org.khasanof.enums.ProcessType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.updatesreceivers.DefaultWebhook;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 8/4/2023 7:20 AM
 */
@Data
@ConfigurationProperties(prefix = "fluent")
public class FluentProperties {

    private Bot bot = new Bot();
    private Webhook webhook = new Webhook();

    @Data // inner class remove
    public static class Bot {
        private String token;
        private String username;
        private ProcessType processType = ProcessType.UPDATE;
        private BotType type = BotType.LONG_POLLING;
    }

    @Data
    public static class Webhook {
        private String url;
        private String keystoreServerFile;
        private String keystoreServerPwd;
        /**
         * Maximum allowed number of simultaneous HTTPS connections to the webhook for update delivery, 1-100.
         * Defaults to 40. Use lower values to limit the load on your bot‘s server,
         * and higher values to increase your bot’s throughput.
         */
        private Integer maxConnections;
        /**
         * List the types of updates you want your bot to receive.
         * For example, specify [“message”, “edited_channel_post”, “callback_query”] to only receive
         * updates of these types. Specify an empty list to receive all updates regardless of type (default).
         * If not specified, the previous setting will be used.
         *
         * Please note that this parameter doesn't affect updates created before the call to the setWebhook,
         * so unwanted updates may be received for a short period of time.
         */
        private List<String> allowedUpdates;
        private Boolean dropPendingUpdates;
        private String secretToken;
    }
}
