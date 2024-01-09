package org.khasanof.utils;

import org.apache.commons.lang3.RandomUtils;
import org.telegram.telegrambots.meta.api.objects.User;

/**
 * @author Nurislom
 * @see org.khasanof.utils
 * @since 1/9/2024 9:26 PM
 */
public abstract class BaseUtils {


    public static int getRandomUpdateId() {
        return RandomUtils.nextInt(0, (Integer.MAX_VALUE - 1));
    }

    public static User defaultFrom() {
        User user = new User();
        user.setId(RandomUtils.nextLong());
        user.setUserName("fluent");
        user.setFirstName("Fluent");
        user.setIsPremium(true);
        user.setIsBot(false);
        user.setLanguageCode("EN");
        user.setCanJoinGroups(true);
        return user;
    }

}
