package jpabook.jpashop.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("M") //싱글테이블이라 저장할때 구분할 수 있어야하는데 B로 지정
@Getter @Setter
public class Movie extends Item{

		private String director;
		private String actor;
}
