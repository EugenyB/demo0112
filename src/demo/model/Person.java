package demo.model;

import java.util.Objects;

/**
 * Created by eugeny on 01.12.2016.
 */
public class Person {
    private String name;
    private int age;
    private double salary;
    private String city;

    public Person(String name, int age, double salary, String city) {
        this.setName(name);
        this.setAge(age);
        this.setSalary(salary);
        this.setCity(city);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Double.compare(person.salary, salary) == 0 &&
                Objects.equals(name, person.name) &&
                Objects.equals(city, person.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, salary, city);
    }

}
