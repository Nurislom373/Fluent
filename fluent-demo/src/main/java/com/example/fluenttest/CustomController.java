package com.example.fluenttest;

import com.example.fluenttest.custom.annotation.HandleSticker;
import org.khasanof.annotation.UpdateController;
import org.khasanof.service.template.FluentTemplate;

/**
 * @author Nurislom
 * @see com.example.fluenttest
 * @since 2/21/2024 12:14 AM
 */
@UpdateController
public class CustomController {

    private final FluentTemplate fluentTemplate;

    public CustomController(FluentTemplate fluentTemplate) {
        this.fluentTemplate = fluentTemplate;
    }

    @HandleSticker("regular")
    public void handleLikeSticker() {
        fluentTemplate.sendText("Custom Annotation is worked!");
    }

}
