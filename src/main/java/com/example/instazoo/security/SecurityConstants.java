package com.example.instazoo.security;

public class SecurityConstants {

    public static final String SIGN_UP_URLS = "/api/auth/**";
    public static final String SECRET = "YWRramhmYmtkamNtS0hHRkJZWUpMdztwLi8=";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
    public static final long EXPIRATION_TYPE =  1_800_000; //30min


}
