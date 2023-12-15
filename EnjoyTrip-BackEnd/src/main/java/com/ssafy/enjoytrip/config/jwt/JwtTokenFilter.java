package com.ssafy.enjoytrip.config.jwt;

import com.ssafy.enjoytrip.config.auth.PrincipalDetails;
import com.ssafy.enjoytrip.service.MemberService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final String key;
    private final MemberService memberService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(header == null || !header.startsWith("Bearer ")) {
            log.error("Error occurs while getting header. header is null or invalid {}",request.getRequestURL());
            filterChain.doFilter(request,response);
            return;
        }

        try {
            final String token = header.split(" ")[1].trim();
            if(JwtUtil.isExpired(token, key)) {
                log.error("Key is expired");
                filterChain.doFilter(request,response);
                return;
            }

            String userName = JwtUtil.getUserName(token, key);

            // check the userName is valid
            PrincipalDetails principalDetails = memberService.loadUserByUserName(userName);

            log.info("principalDetails.getId():"+principalDetails.getId());
            log.info("principalDetails.getAuthorities():"+principalDetails.getAuthorities());

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    principalDetails,null, principalDetails.getAuthorities());

            log.info("authentication.getName() : "+authentication.getName());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (RuntimeException e) {
            log.error("Error occurs while validation. {}", e.toString());
            filterChain.doFilter(request,response);
            return;
        }

        filterChain.doFilter(request,response);

    }
}
