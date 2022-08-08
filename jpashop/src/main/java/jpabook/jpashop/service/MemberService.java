package jpabook.jpashop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
 //데이터변경은 트랜잭션이 꼭 있어야한다.
//@AllArgsConstructor
@RequiredArgsConstructor
public class MemberService {
	
	@Autowired
	private final MemberRepository memberRepository; 
	//↑이렇게도 많이쓰지만 private으로 테스트를 하거할때 바꿔야하는데 바꾸기가 어려움 그래서 ↓와같이 setter injection을 써줌
	//바로 들어오는게 아니라 아래를 거쳐서 주입함
//	public void setMemberRepository(MemberRepository memberRepository) {
//		this.memberRepository = memberRepository;
//	}
	//바꿀일이 없기때문에 생성자 injection을 써줌 그러나 ↓코드를 lombok에서제공하는 @AllArgsConstructor를 써서 대체할 수 있음
	// 또다른 어노테이션 @RequiredArgsConstructor를 써주면 final이 붙은 repository대상만 injection해줌
//	public MemberService(MemberRepository memberRepository) {
//		this.memberRepository = memberRepository;
//	}

	//회원가입
	@Transactional
	public Long join(Member member) {
		validateDuplicateMember(member); //중복 회원 검증
		memberRepository.save(member);
		return member.getId();
	}
	
	private void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		//중복성검사할때 was가 여러개가 뜨면 동시에 중복검사를했을때 문제가 될 수 있음 그래서 name에 unique제약조건을 추가해줘야한다.
		if (!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 있는 회원입니다.");
		}
	}
	
	//회원 전체 조회
//	@Transactional(readOnly = true) //insert 말고 조회쿼리에 reonly true하면 성능 최적화함 더티체킹? readonly라 메모리 별로안잡는다?
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}
}
