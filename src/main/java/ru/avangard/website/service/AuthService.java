// src/main/java/ru/avangard/website/service/AuthService.java
package ru.avangard.website.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.avangard.website.entity.Admin;
import ru.avangard.website.repository.IAdminRepository;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final IAdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    // Секретный ключ (в реальном проекте вынеси в application.properties!)
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public AuthService(IAdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(String login, String password) {
        Admin admin = adminRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Неверный логин или пароль"));

        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw new RuntimeException("Неверный логин или пароль");
        }

        return generateToken(admin);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String generateToken(Admin admin) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("login", admin.getLogin());
        claims.put("id", admin.getId());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(admin.getLogin())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 часа
                .signWith(key)
                .compact();
    }
}