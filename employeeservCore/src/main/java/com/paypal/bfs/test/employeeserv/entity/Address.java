package com.paypal.bfs.test.employeeserv.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(name = "line_1")
    private String line1;

    @Column(name = "line_2")
    private String line2;

    @NonNull
    @Column(name = "state")
    private String state;

    @NonNull
    @Column(name = "country")
    private String country;

    @NonNull
    @Column(name = "zip_code", length = 6)
    private String zipCode;
}
