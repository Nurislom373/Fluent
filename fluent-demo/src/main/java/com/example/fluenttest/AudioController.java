package com.example.fluenttest;

import org.khasanof.annotation.UpdateController;
import org.khasanof.annotation.methods.HandleAudio;
import org.khasanof.enums.MatchType;
import org.khasanof.enums.scopes.AudioScope;
import org.khasanof.service.template.FluentTemplate;

/**
 * @author Nurislom
 * @see com.example.fluenttest
 * @since 3/31/2024 4:38 AM
 */
@UpdateController
public class AudioController {

    private final FluentTemplate fluentTemplate;

    public AudioController(FluentTemplate fluentTemplate) {
        this.fluentTemplate = fluentTemplate;
    }

    @HandleAudio(value = "mp3", match = MatchType.ENDS_WITH, property = AudioScope.FILE_NAME)
    public void handleAudio() {
        fluentTemplate.sendText("Hello World!");
    }
}
