package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleWebAppData;
import org.khasanof.config.ApplicationConstants;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppData;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 2/1/2024 9:38 PM
 */
@Component
public class DefaultWebAppData extends GenericMatcher<HandleWebAppData, WebAppData> {

    public DefaultWebAppData() {
        super(ApplicationConstants.MATCHER_MAP);
    }

    @Override
    public boolean matcher(HandleWebAppData annotation, WebAppData value) {
        return true;
    }

    @Override
    public Class<HandleWebAppData> getType() {
        return HandleWebAppData.class;
    }
}
