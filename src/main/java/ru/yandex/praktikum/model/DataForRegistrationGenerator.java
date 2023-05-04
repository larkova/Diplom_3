package ru.yandex.praktikum.model;

import org.apache.commons.lang3.RandomStringUtils;

public class DataForRegistrationGenerator {
    public static DataForRegistration getRandomCorrectData () {
        String name = RandomStringUtils.randomAlphabetic(10);
        String email = RandomStringUtils.randomAlphabetic(10)+ "@mail.ru";
        String password = RandomStringUtils.randomAlphabetic(10);

        return new DataForRegistration(name, email, password);
    }
    public static DataForRegistration getRandomIncorrectData () {
        String name = RandomStringUtils.randomAlphabetic(10);
        String email = RandomStringUtils.randomAlphabetic(10)+ "@mail.ru";
        String password = RandomStringUtils.randomAlphabetic(4);

        return new DataForRegistration(name, email, password);
    }
}

