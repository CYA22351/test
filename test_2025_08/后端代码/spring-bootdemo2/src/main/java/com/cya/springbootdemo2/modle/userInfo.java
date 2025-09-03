package com.cya.springbootdemo2.modle;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/8 9:07
 * @description：
 * @modified By：
 * @version:
 */
public class userInfo {
    private String name;
    private int gender;
    private int age;

    public userInfo() {
    }


    public userInfo(String name, int gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "userInfo{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}