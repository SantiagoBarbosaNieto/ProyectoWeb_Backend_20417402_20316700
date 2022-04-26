package com.backend.proyectoweb.proyectoweb_backend.security.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.backend.proyectoweb.proyectoweb_backend.model.UsuarioSys;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager=authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
                try{
                    UsuarioSys credentials = new ObjectMapper().readValue(request.getInputStream(), UsuarioSys.class);

                    return authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                            credentials.getEmail(), credentials.getPassword(), new ArrayList<>()));
                }catch(IOException ex){
                    throw new RuntimeException(ex);
                }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        
        final String authorities = authResult.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        
        final Map<String,Object> claims = new HashMap<>();
        claims.put("Authorities", authorities);

        String token = Jwts.builder()
        .setIssuedAt(new Date())
        .setIssuer("proyectoweb_backend")
        .setClaims(claims)
        .setSubject(((org.springframework.security.core.userdetails.User)authResult.getPrincipal())
        .getUsername())
        .setExpiration(new Date(System.currentTimeMillis() + 3600000))
        .signWith(SignatureAlgorithm.HS512, "12345")
        .compact();

        response.addHeader("Authorization","Bearer "+ token); 
    }
}
