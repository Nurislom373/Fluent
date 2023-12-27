package org.khasanof.collector.method;
import org.khasanof.config.FluentProperties;
import org.khasanof.config.Config;
import org.khasanof.enums.ProcessType;
import org.khasanof.utils.AnnotationUtils;
import org.khasanof.utils.MethodUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.collector.methodChecker
 * @since 19.07.2023 21:40
 */
@Component
public class MethodCheckerAdapter implements Config {

    private final FluentProperties.Bot bot;
    private final ApplicationContext applicationContext;
    private final List<AbstractMethodChecker> methodCheckers = new ArrayList<>();

    public MethodCheckerAdapter(FluentProperties properties, ApplicationContext applicationContext) {
        this.bot = properties.getBot();
        this.applicationContext = applicationContext;
    }

    public boolean valid(Method method) {
        AbstractMethodChecker abstractMethodChecker = methodCheckers.stream()
                .filter(methodChecker -> matchChecker(method, methodChecker))
                .findFirst().orElse(null);
        if (Objects.nonNull(abstractMethodChecker)) {
            return abstractMethodChecker.valid(method);
        }
        return false;
    }

    private boolean matchChecker(Method method, AbstractMethodChecker methodChecker) {
        return AnnotationUtils.hasAnnotation(method, methodChecker.getType(), methodChecker.hasSuperAnnotation());
    }

    @Override
    public void runnable() {
        ProcessType processType = bot.getProcessType();
        Class<? extends Annotation> processToType = MethodUtils.processToType(processType);
        applicationContext.getBeansOfType(AbstractMethodChecker.class).forEach((beanName, instance) -> {
            if (instance.hasAny()) {
                methodCheckers.add(instance);
            } else {
                if (Objects.isNull(processToType)) {
                    methodCheckers.add(instance);
                } else if (instance.getType().equals(processToType)) {
                    methodCheckers.add(instance);
                }
            }
        });
    }

    @Override
    public ProcessType processType() {
        return ProcessType.BOTH;
    }

}
