package org.khasanof.constants;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.io.InputStream;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.constants
 * @since 29.07.2023 16:18
 */
public class ParamConstants {

    public static final Class<?>[] DEFAULT_HANDLER_PARAM = new Class[]{Update.class};
    public static final List<Class<?>> PROCESS_FILE_PARAMS = List.of(Update.class, InputStream.class);
}
