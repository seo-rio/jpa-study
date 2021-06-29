package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long Id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // 1:다 관계 명시, mappedBy order table에 있는 member와 연결되있다. readonly!
    private List<Order> orders = new ArrayList<>();
}
