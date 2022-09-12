package com.liang.springsecurity.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.liang.springsecurity.model.UserDetail;
import com.liang.springsecurity.provider.ThirdPartyAuthenticationToken;
import com.liang.springsecurity.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Liang
 * 2022-09-12
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(UserDetail userDetail) {
        return authenticate(new UsernamePasswordAuthenticationToken(userDetail.getUsername(), userDetail.getPassword()));
    }

    @Override
    public String thirdPartyLogin(String certificate) {
        return authenticate(new ThirdPartyAuthenticationToken(certificate));
    }

    private <T extends AbstractAuthenticationToken> String authenticate(T token) {
        Authentication authenticate = authenticationManager.authenticate(token);
        // 简单的说就是获取当前用户 创建token，返回
        UserDetail principal = (UserDetail) authenticate.getPrincipal();
        // 颁发token
        Map<String, Object> claim = JSON.parseObject(JSON.toJSONString(principal), new TypeReference<Map<String, Object>>() {
        });
        List<String> authorities = authenticate.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claim.put("authorities", JSON.toJSONString(authorities));
        return JwtTokenUtils.createToken(principal.getUsername(), claim);
    }
}
