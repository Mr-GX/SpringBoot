package com.spring.boot.server.demo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class TokenAuthService {
    private static final String SECRET = "12345678910";

    //构造token
    public String createToken(String mobile, Collection<?> authorities) {
        long current_time = System.currentTimeMillis();
        Claims claims = Jwts.claims().setSubject(mobile);
        claims.put("GrantedAuthority", authorities.stream().map(s -> s.toString()).toArray()); //权限参数必须
        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("guoxin")
                .setIssuedAt(new Date(current_time))
                .setExpiration(new Date(current_time + 600_000))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    //认证token
    public Authentication authToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        Logger.getLogger("auth").info(authorization);
        if (authorization != null) {
            Claims claims = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(authorization)
                    .getBody();
            String user = claims.getSubject();
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(claims.get("GrantedAuthority").toString());
            return user != null ? new UsernamePasswordAuthenticationToken(user, null, authorities) : null;
        }
        return null;
    }

}
