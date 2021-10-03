package com.me.Fakers;

import com.github.javafaker.Faker;

/**
 * @author zs
 * @date 2021/10/3.
 * 虚假名字生成器
 */
public class Name {
    public static void main(String[] args) {
        Faker faker = new Faker();

        String name = faker.name().fullName(); // Miss Samanta Schmidt
        String firstName = faker.name().firstName(); // Emory
        String lastName = faker.name().lastName(); // Barton

        String streetAddress = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449

        System.out.println(name);
        System.out.println(streetAddress);
    }
}
