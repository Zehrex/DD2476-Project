1
https://raw.githubusercontent.com/Bekzatiitu/Final_Bekzat_Bekarys_ishs1901/master/Final%20Bekzat%20Bekarys%20ishs1901/src/kenn/shi/You.java
package kenn.shi;

import java.io.Serializable;

public class You implements Serializable {
    public Integer id;
    public String name;
    public String surname;
    public int age;
    public String city;
    public String desiredJobs;

    public You(Integer id, String name, String surname, int age, String city, String desiredJobs) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.city = city;
        this.desiredJobs = desiredJobs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDesiredJobs() {
        return desiredJobs;
    }

    public void setDesiredJobs(String desiredJobs) {
        this.desiredJobs = desiredJobs;
    }

    public String toString(){
        return id+ " " + "name: " + name + " surname: " + surname + " age: " + age +  " city: " + city + " desired job: " + desiredJobs;
    }
}