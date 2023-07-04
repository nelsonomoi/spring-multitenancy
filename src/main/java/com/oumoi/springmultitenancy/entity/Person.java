package com.oumoi.springmultitenancy.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.TenantId;

@Entity
@Table(name = "t_persons")
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    partitioning
    @TenantId
    private String tenant;
}
