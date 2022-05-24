package com.orchestrator.orchestrator.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JwtUtil {
    private static String secretToken = "SecretOrchestrator";
    private static final Long TOKEN_VALIDITY = 1000 * 60 * 60 * 24 * Long.valueOf(200);
    private static final String CLAIM_AUTHORITIES = "authorities";
    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";

    private static DecodedJWT getDecodedJwtFromRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader(HEADER);
        if (token != null && token.startsWith(PREFIX)) {
            Algorithm algorithm = Algorithm.HMAC256(secretToken.getBytes(StandardCharsets.UTF_8));
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token.replace(PREFIX, ""));
        }
        return null;
    }

    public static Authentication getAuthenticationFromRequest() {
        String username = getSubjectFromRequest();
        List<String> roles = getRolesFromRequest();
        if (username != null && !username.isEmpty() && roles != null && !roles.isEmpty()) {
            return new UsernamePasswordAuthenticationToken(username, null, roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        }
        return null;
    }

    public static List<String> getRolesFromRequest() {
        DecodedJWT decodedJWT = getDecodedJwtFromRequest();
        if (decodedJWT != null)
        {
            return decodedJWT.getClaim(CLAIM_AUTHORITIES).asList(String.class);
        }
        return null;
    }

    public static String getSubjectFromRequest() {
        DecodedJWT decodedJWT = getDecodedJwtFromRequest();
        if (decodedJWT != null) {
            return decodedJWT.getSubject();
        }
        return null;
    }

    public static String createToken(UserDetails userDetails) {
        Algorithm algorithm = Algorithm.HMAC256(secretToken.getBytes());
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                .withClaim(CLAIM_AUTHORITIES, userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }
}
