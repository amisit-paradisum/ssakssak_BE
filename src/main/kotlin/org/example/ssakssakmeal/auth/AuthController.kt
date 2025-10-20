package org.example.ssakssakmeal.auth

import org.example.ssakssakmeal.auth.dto.SignResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(val authService: AuthService) {

    @PostMapping("/signup")
    fun signup(@RequestBody oauth: String): ResponseEntity<SignResponseDto> {
        val res = authService.signUp(oauth)
        if(res == null) return ResponseEntity(HttpStatus.BAD_REQUEST)//todo: http status 정하기
        return ResponseEntity.ok(res);
    }

    @GetMapping("/login")
    fun login(@RequestHeader info: String): ResponseEntity<SignResponseDto> {
        val res = authService.signIn(info);
        if(res == null) return ResponseEntity(HttpStatus.BAD_REQUEST)
        return ResponseEntity.ok(res)
    }
    @GetMapping("/signInWithRefresh")
    fun signInWithRefresh(@RequestHeader token: String): ResponseEntity<SignResponseDto> {
        val res = authService.signInWithRefresh(token);
        if(res == null) return ResponseEntity(HttpStatus.BAD_REQUEST)
        return ResponseEntity.ok(res)
    }
}