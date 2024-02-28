package org.khasanof.service.interceptor.invoker;

import org.khasanof.feature.interceptor.PostFindInvokerInterceptor;
import org.khasanof.models.invoker.SimpleInvoker;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.service.interceptor.invoker
 * @since 2/27/2024 10:39 PM
 */
public interface PostFindInvokerInterceptorService {

    /**
     *
     * @param simpleInvoker
     */
    boolean intercept(SimpleInvoker simpleInvoker);

    /**
     *
     * @param interceptors
     */
    void addInterceptors(List<PostFindInvokerInterceptor> interceptors);

    /**
     *
      * @param interceptor
     */
    void addInterceptor(PostFindInvokerInterceptor interceptor);
}
