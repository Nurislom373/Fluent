package org.khasanof.config.registry;

import org.khasanof.annotation.process.ProcessUpdate;
import org.khasanof.registry.annotation.DefaultFluentAnnotationRegistry;
import org.khasanof.registry.annotation.FluentAnnotationsRegistry;
import org.khasanof.utils.ReflectionUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config.registry
 * @since 2/19/2024 9:26 PM
 */
@Configuration
public class FluentAnnotationRegistryConfiguration {

    /**
     *
     * @return {@link FluentAnnotationsRegistry} bean
     */
    @Bean
    public FluentAnnotationsRegistry fluentAnnotationsRegistry() {
        DefaultFluentAnnotationRegistry annotationRegistry = new DefaultFluentAnnotationRegistry();
        annotationRegistry.addAnnotations(ReflectionUtils.getSubTypesSuperAnnotation(ProcessUpdate.class));
        return annotationRegistry;
    }
}
