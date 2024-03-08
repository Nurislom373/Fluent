package org.khasanof.service.runnable.invoker;

import org.khasanof.feature.interceptor.PostFindInvokerInterceptor;
import org.khasanof.service.FindBeansOfTypeService;
import org.khasanof.service.interceptor.invoker.PostFindInvokerInterceptorService;
import org.khasanof.service.runnable.PostProcessor;
import org.springframework.stereotype.Service;

/**
 * @author Nurislom
 * @see org.khasanof.service.runnable.invoker
 * @since 2/28/2024 11:28 PM
 */
@Service
public class PostFindInvokerInterceptorPostProcessor implements PostProcessor {

    private final FindBeansOfTypeService findBeansOfTypeService;
    private final PostFindInvokerInterceptorService invokerInterceptorService;

    public PostFindInvokerInterceptorPostProcessor(FindBeansOfTypeService findBeansOfTypeService,
                                                   PostFindInvokerInterceptorService invokerInterceptorService) {

        this.findBeansOfTypeService = findBeansOfTypeService;
        this.invokerInterceptorService = invokerInterceptorService;
    }

    @Override
    public void run() {
        invokerInterceptorService.addInterceptors(findBeansOfTypeService.findAllByList(PostFindInvokerInterceptor.class));
    }
}
