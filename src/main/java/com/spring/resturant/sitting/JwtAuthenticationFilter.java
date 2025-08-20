package com.spring.resturant.sitting;

import com.spring.resturant.config.JwtTokenHandler;
import com.spring.resturant.dto.TokenDto;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenHandler jwtTokenHandler;

    public JwtAuthenticationFilter(JwtTokenHandler jwtTokenHandler) {
        this.jwtTokenHandler = jwtTokenHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // استخراج التوكن من الهيدر
        String token = getTokenFromRequest(request);

        if (StringUtils.hasText(token) && jwtTokenHandler.validateToken(token)) {
            // التوكن صحيح ➔ نكمل

            Claims claims = jwtTokenHandler.getJwtParser().parseClaimsJws(token).getBody();
            String username = claims.getSubject(); // اسم المستخدم

            // استرجاع الأدوار
            List<Map<String, Object>> roles = claims.get("roles", List.class);

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            if (roles != null) {
                for (Map<String, Object> roleMap : roles) {
                    String code = (String) roleMap.get("code");
                    if (code != null) {
                        authorities.add(new SimpleGrantedAuthority(code));
                    }
                }
            }

            // إنشاء الـ Authentication بالتوكين والمستخدم والصلاحيات
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            authorities
                    );

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // نضع المصادقة داخل SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        // نمرر الطلب للفلاتر التالية أو الكونترولر
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // إزالة كلمة Bearer مع الفراغ
        }
        return null;
    }

}


