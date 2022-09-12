package com.liang.springsecurity.filter;

import com.liang.springsecurity.model.UserDetail;
import com.liang.springsecurity.util.JwtTokenUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT授权过滤器
 *
 * @author Liang
 * 2022-09-10
 */
@Component
@AllArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(JwtTokenUtils.HEADER);

        if (authHeader != null && authHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {

            String authToken = authHeader.substring(JwtTokenUtils.TOKEN_PREFIX.length());
            UserDetail user = JwtTokenUtils.getUser(authToken);
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}
