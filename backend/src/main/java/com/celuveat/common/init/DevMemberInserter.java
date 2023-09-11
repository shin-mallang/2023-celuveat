package com.celuveat.common.init;


import static com.celuveat.common.auth.AuthConstant.JSESSION_ID;

import com.celuveat.auth.command.domain.OauthId;
import com.celuveat.auth.command.domain.OauthMember;
import com.celuveat.auth.command.domain.OauthMemberRepository;
import com.celuveat.auth.command.domain.OauthServerType;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@Profile("dev")
@RequiredArgsConstructor
@RestController
public class DevMemberInserter {

    private final OauthMemberRepository oauthMemberRepository;


    @PostConstruct
    public void save() {
        OauthMember mallang = OauthMember.builder()
                .oauthId(new OauthId("11", OauthServerType.KAKAO))
                .nickname("mallang")
                .profileImageUrl("https://avatars.githubusercontent.com/u/52229930?v=4")
                .build();
        oauthMemberRepository.save(mallang);
    }

    @GetMapping("/dev/login")
    public void login(HttpServletRequest httpServletRequest) {
        Optional<OauthMember> oauthId = oauthMemberRepository.findByOauthId(new OauthId("11", OauthServerType.KAKAO));
        OauthMember oauthMember = oauthId.get();
        HttpSession session = httpServletRequest.getSession(false);
        session.setAttribute(JSESSION_ID, oauthMember.oauthId());
    }
}
