package jpabook.jpashop.service;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.service.MemberService;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
//상기두가지 어노테이션이있어야 실제로 스프링부트를 올려서 테스트할 수 있다.
@Transactional //이 어노테이션이있어야 롤백을 할 수 있다.
public class MemberServiceTest {
	
		@Autowired MemberService memberService;
		@Autowired MemberRepository memberRepository;
		@Autowired EntityManager em;

		@Test
		//@Rollback(false)
		public void 회원가입() throws Exception {
			//given
			Member member = new Member();
			member.setName("kim");
			
			//when
			Long savedId = memberService.join(member);
			
			//then
			em.flush();
			Assert.assertEquals(member, memberRepository.findOne(savedId));
			
		}
		
		@Test(expected = IllegalStateException.class)
		public void 중복_회원_예외() throws Exception {
			//given
			Member member1 = new Member();
			member1.setName("kim");
			
			Member member2 = new Member();
			member2.setName("kim");
			
			//when
			memberService.join(member1);
			memberService.join(member2);
			/*
			try {
				memberService.join(member2);
			} catch (IllegalStateException e) {
				return;
			}
			*/
			//then
			fail("에외가 발생해야 한다.");
		}
}
