// src/main/java/ru/avangard/website/service/AuthService.java
package ru.avangard.website.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.avangard.website.entity.Admin;
import ru.avangard.website.repository.IAdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class AuthService {

    private final IAdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AdminUserDetailsService userDetailsService;

    public AuthService(IAdminRepository adminRepository,
                       JwtService jwtService,
                       AdminUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(String login, String password) {
        // Проверяем пароль вручную (без AuthenticationManager для упрощения)
        System.out.println(1111111111);
        Admin admin = adminRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Неверный логин или пароль"));

        System.out.println(2222222);
        if (!passwordEncoder.matches(password, admin.getPassword())) {
            System.out.println(33333333);
            throw new RuntimeException("Неверный логин или пароль");
        }

        // Загружаем UserDetails для сравнения пароля
        UserDetails userDetails = userDetailsService.loadUserByUsername(login);
        if (!userDetails.getPassword().equals(password)) {
            // ⚠️ В реальности используй passwordEncoder.matches(raw, encoded)
            throw new RuntimeException("Неверный логин или пароль");
        }

        return jwtService.generateToken(userDetails);
    }

    public boolean validateToken(String token) {
        try {
            String username = jwtService.extractUsername(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            return jwtService.isTokenValid(token, userDetails);
        } catch (Exception e) {
            return false;
        }
    }
}