package org.demo1.service;

import java.security.SecureRandom;
import java.util.Base64;

public class JwtKeyGenerator {
    public static void main(String[] args) {
        byte[] key = new byte[32]; // 256 bits
        new SecureRandom().nextBytes(key);
        String base64Key = Base64.getEncoder().encodeToString(key);
        System.out.println("Clave secreta en Base64: " + base64Key);
    }
}