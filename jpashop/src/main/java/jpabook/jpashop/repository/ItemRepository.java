package jpabook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
	
	private final EntityManager em;
	
	public void save(Item item) {
		if (item.getId() == null) { //아이템은 등록전에 id가 없기때문에 새로 등록한다
			em.persist(item);
		} else {
//														↓파라미터로 넘어온것은 영속성 상태로 바뀌진 않는다.
			Item merge = em.merge(item); //있으면 업데이트한다의 의미
//			↑영속성 컨텍스트로 관리되는객체
		}
	}
	
	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}
	
	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class)
				.getResultList();
	}
}
