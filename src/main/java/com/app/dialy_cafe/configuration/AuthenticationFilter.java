package com.app.dialy_cafe.configuration;

import com.app.dialy_cafe.exception.constand.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private static final AntPathMatcher MATCHER = new AntPathMatcher();

    private final List<String> EXCLUDED_PATHS = List.of(
            "/health",
            "/v1/api/auth/login",
            "/v1/api/auth/register"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final var token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            throw new UnauthorizedException("Bearer Token is required");
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return EXCLUDED_PATHS.stream().anyMatch(pattern ->
                MATCHER.match(pattern, request.getRequestURI())
        );
    }
}
