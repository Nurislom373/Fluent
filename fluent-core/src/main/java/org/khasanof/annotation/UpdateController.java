package org.khasanof.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 18.06.2023
 * <br/>
 * Time: 21:53
 * <br/>
 * Package: org.khasanof.main.annotation
 */
@Component
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UpdateController {
}
