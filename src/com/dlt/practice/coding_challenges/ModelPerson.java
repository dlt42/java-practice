package com.dlt.practice.coding_challenges;

public class ModelPerson {
    public static void main(String[] args) {
        Person person1 = new Person("Bob", "Smith", 23);
        Person person2 = new Person("Mary", "Jones", 37);
        Person person3 = new Person("Jim", "Bob", 10);
        System.out.println(person1.intreoduction());
        System.out.println(person2.intreoduction());
        System.out.println(person3.intreoduction());
    }

}

class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String intreoduction() {
        StringBuilder result = new StringBuilder();
        result.append("Hello, my name is ");
        result.append(this.getFirstName());
        result.append(" ");
        result.append(this.getLastName());
        result.append(", and I am ");
        result.append(this.getAge());
        result.append(" years old.");
        return result.toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
