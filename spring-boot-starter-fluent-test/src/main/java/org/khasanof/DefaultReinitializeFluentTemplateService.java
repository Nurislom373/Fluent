package org.khasanof;

import org.khasanof.factories.proxy.ProxyFluentTemplateFactory;
import org.khasanof.memento.DefaultMethodInvokeHistory;
import org.khasanof.memento.MethodInvokeHistory;
import org.khasanof.service.ReinitializeFluentTemplateService;
import org.khasanof.service.template.FluentTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 2/10/2024 10:01 PM
 */
@Service
public class DefaultReinitializeFluentTemplateService implements ReinitializeFluentTemplateService, BeanPostProcessor {

    private FluentTemplate fluentTemplate;
    private MethodInvokeHistory methodInvokeHistory;
    private final ProxyFluentTemplateFactory proxyFluentTemplateFactory;

    public DefaultReinitializeFluentTemplateService(ProxyFluentTemplateFactory proxyFluentTemplateFactory) {
        this.proxyFluentTemplateFactory = proxyFluentTemplateFactory;
    }

    @Override
    public FluentTemplate getFluentTemplate() {
        return this.fluentTemplate;
    }

    @Override
    public MethodInvokeHistory getInvokeHistory() {
        return this.methodInvokeHistory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Object result = bean;
        if (Objects.equals(bean.getClass(), FluentTemplate.class) || FluentTemplate.class.isAssignableFrom(bean.getClass())) {
            this.methodInvokeHistory = new DefaultMethodInvokeHistory();
            this.fluentTemplate = proxyFluentTemplateFactory.create(this.methodInvokeHistory);
            result = this.fluentTemplate;
        }
        return result;
    }
}
