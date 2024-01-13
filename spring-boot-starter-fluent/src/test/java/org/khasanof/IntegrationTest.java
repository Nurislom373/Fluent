package org.khasanof;

import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/14/2024 1:00 AM
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = {FluentTestConfiguration.class}, properties = {
        "fluent.bot.token=5101184142:AAF2StFB5HciO6N4--eh3mDP6RJ4Helmooo",
        "fluent.bot.username=@lunch_pdp_bot",
        "fluent.bot.process-type=update",
})
public @interface IntegrationTest {
}
