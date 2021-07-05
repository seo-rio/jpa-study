package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //given - 이렇게 주어졌을때
        Member member = new Member();
        member.setName("seo");

        //when - 이렇게 하면
        Long saveId = memberService.join(member);

        //then - 이렇게 된다
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_에외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("seo");

        Member member2 = new Member();
        member2.setName("seo");

        //when
        memberService.join(member1);

        //then
        fail("예외가 발생해야 합니다.");

    }
}