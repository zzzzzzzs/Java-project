package com.me.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

/**
 * @author zs
 * @date 2021/9/20.
 */
@Data
@AllArgsConstructor
public class Person {
    private String name;
    private int age;
}
