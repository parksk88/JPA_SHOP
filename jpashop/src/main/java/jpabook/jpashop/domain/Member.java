package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {
		
		@Id @GeneratedValue
		@Column(name = "member_id")
		private Long id;
		
		private String name;
		
		@Embedded
		private Address address;
		
		@OneToMany(mappedBy = "member")  //member에 의해서 맵핑되는 거울이다 어떤값을 넣는다고해서 order의 foreign키가 변경되지않는다.
		private List<Order> orders = new ArrayList<>();
}
