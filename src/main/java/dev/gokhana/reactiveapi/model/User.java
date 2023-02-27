package dev.gokhana.reactiveapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("reactive_user")
public class User {
    @Id
    private Integer id;
    private String name;
    private Integer score;

    @Column("address_id")
    private Long addressId;

    @Transient
    private Address address;

    public User() {
    }

    public User(Integer id, String name, Integer score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public User( String name, Integer score, Long addressId) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.addressId = addressId;
    }

    public User(Integer id, String name, Integer score, Long addressId, Address address) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.addressId = addressId;
        this.address = address;
    }

    public User( String name, Integer score, Address address) {
        this.name = name;
        this.score = score;
        this.address = address;
    }

    public User(String name, int score) {
        this.name = name;
        this.score = score;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", addressId=" + addressId +
                ", address=" + address +
                '}';
    }
}