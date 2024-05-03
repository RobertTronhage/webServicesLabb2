package se.tronhage.webserviceslabb2.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "age",
        "city"
})

public class Person {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("city")
    private String city;

    public Person() {
    }

    public Person(Long id, String name, Integer age, String city) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.city = city;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }
    @JsonProperty("name")
    public String getName() {
        return name;
    }
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }
    @JsonProperty("age")
    public Integer getAge() {
        return age;
    }
    @JsonProperty("age")
    public void setAge(Integer age) {
        this.age = age;
    }
    @JsonProperty("city")
    public String getCity() {
        return city;
    }
    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                '}';
    }
}
