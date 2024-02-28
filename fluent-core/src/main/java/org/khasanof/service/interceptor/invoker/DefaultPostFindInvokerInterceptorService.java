package org.khasanof.service.interceptor.invoker;

import org.khasanof.feature.interceptor.PostFindInvokerInterceptor;
import org.khasanof.models.invoker.SimpleInvoker;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Nurislom
 * @see org.khasanof.service.interceptor.invoker
 * @since 2/27/2024 10:40 PM
 */
public class DefaultPostFindInvokerInterceptorService implements PostFindInvokerInterceptorService {

    private final List<PostFindInvokerInterceptor> interceptors = new CopyOnWriteArrayList<>();

    @Override
    public boolean intercept(SimpleInvoker simpleInvoker) {
        return interceptors.stream()
                .allMatch(interceptor -> interceptor.postHandle(simpleInvoker));
    }

    @Override
    public void addInterceptors(List<PostFindInvokerInterceptor> interceptors) {
        if (Objects.nonNull(interceptors)) {
            this.interceptors.addAll(interceptors);
        }
    }

    @Override
    public void addInterceptor(PostFindInvokerInterceptor interceptor) {
        if (Objects.nonNull(interceptor)) {
            this.interceptors.add(interceptor);
        }
    }
}
