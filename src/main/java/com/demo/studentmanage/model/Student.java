package com.demo.studentmanage.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;


}
