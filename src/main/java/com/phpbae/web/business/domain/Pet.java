package com.phpbae.web.business.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pet")
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer petIdx;

    private String petName;
    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Owner.class)
    @JoinColumn(name = "ownerIdx", referencedColumnName = "ownerIdx")
    private Owner owner;
}
