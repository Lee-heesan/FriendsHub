package com.shop.service;

import com.shop.dto.MemberEditDTO;
import com.shop.dto.MemberFormDto;
import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import com.shop.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember!=null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public void updateMember(Member member, MemberEditDTO memberEditDTO) {
        member.setName(memberEditDTO.getName());
        member.setEmail(memberEditDTO.getEmail());
        member.setAddress(memberEditDTO.getAddress());
        member.setPhoneNumber(memberEditDTO.getPhoneNumber());

        memberRepository.save(member);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Member member = memberRepository.findByEmail(email);
        if(member == null){
            throw new UsernameNotFoundException(email);
        }
        return new CustomUserDetails(member);
    }
}
