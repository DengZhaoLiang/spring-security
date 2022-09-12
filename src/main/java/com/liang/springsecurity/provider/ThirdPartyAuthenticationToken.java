package com.liang.springsecurity.provider;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 第三方授权token
 *
 * @author Liang
 * 2022-09-12
 */
public class ThirdPartyAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public ThirdPartyAuthenticationToken(Object thirdParty) {
        super(thirdParty, thirdParty);
    }

    public ThirdPartyAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public ThirdPartyAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
