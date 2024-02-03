package org.khasanof.service.interceptor;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.service.interceptor
 * @since 2/3/2024 6:28 PM
 */
public interface FluentInterceptorService {

    boolean intercept(Update update);

}
