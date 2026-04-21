package utill;

import api.User;

import java.util.Random;

public class UserGenerator {

    private static final Random random = new Random();

    public static User getRandomUser() {
        String timestamp = String.valueOf(System.currentTimeMillis());

        return new User(
                "t" + timestamp + "@yandex.ru", // уникальный email
                "123456", // пароль минимум 6 символов
                "t" + random.nextInt(99999) // случайное имя
        );
    }
}
