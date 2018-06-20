package com.phpbae.web.business.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "owner")
public class Owner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ownerIdx;
    private String ownerName;

    //mappedBy : 양뱡향 관계에서 주체가 되는 쪽(Many쪽, 외래키가 있는 쪽)을 정의
    // 프록시가 실제 객체가 아니기 때문에 이러한 객체에 액세스 할 수 없다는 것을 의미
    @OneToMany(targetEntity = Pet.class, fetch = FetchType.LAZY, mappedBy = "owner")
    @JsonIgnore
    private List<Pet> pet;
}
