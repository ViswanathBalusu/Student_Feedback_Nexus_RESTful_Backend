package com.swe645.assignment3;

import jakarta.persistence.*;

@Entity
@Table(name = "responses")
public class StudentDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column(name = "first_name")
    public String first_name;

    @Column(name = "last_name")
    public String last_name;

    @Column(name = "street_address1")
    public String street_address1;

    @Column(name = "street_address2")
    public String street_address2;

    @Column(name = "city")
    public String city;

    @Column(name = "state")
    public String state;

    @Column(name = "zip")
    public String zip;

    @Column(name = "phone")
    public String phone;

    @Column(name = "email")
    public String email;

    @Column(name = "dos")
    public String dos;

    @Column(name = "checkbox")
    public String checkbox;

    @Column(name = "radio")
    public String radio;

    @Column(name = "drop_down")
    public String drop_down;

    public StudentDTO(){

    }

    public StudentDTO(
            String first_name,
            String last_name,
            String street_address1,
            String street_address2,
            String city,
            String state,
            String zip,
            String phone,
            String email,
            String dos,
            String checkbox,
            String radio,
            String drop_down
            ) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.street_address1 = street_address1;
        this.street_address2 = street_address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
        this.dos = dos;
        this.checkbox = checkbox;
        this.radio = radio;
        this.drop_down = drop_down;
    }
}
