package com.hrm.test.business.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Pattern(regexp = "^[\\u0600-\\u06FF]+$", message = "Invalid Name!")
    private String name;

    @Column(unique = true)
    @Size(max = 14, min = 14)
    private String nationalId;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    private Branch branch;
}
