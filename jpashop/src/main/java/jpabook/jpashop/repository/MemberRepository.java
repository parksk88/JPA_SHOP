package jpabook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
		
		@PersistenceContext
		private final EntityManager em;
		
		public void save(Member member) {
			em.persist(member);
		}
		
		public Member findOne(Long id) {
			//Member member = em.find(Member.class, id); 랑 같다
			return em.find(Member.class, id);
		}
		
		public List<Member> findAll() {
			
//			List<Member> result = em.createQuery("select m from Member m", Member.class)  //쿼리 , 반환타입
			return em.createQuery("select m from Member m", Member.class)  //쿼리 , 반환타입 //jpql은 from의대상이 table이 아니라 entity다
			.getResultList();		
		}
		
		public List<Member> findByName(String name) {
			return em.createQuery("select m from Member m where m.name = :name", Member.class)
					.setParameter("name", name)
					.getResultList();
		}
}
