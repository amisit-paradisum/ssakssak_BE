package auth

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/auth")
class AuthController {

    @PostMapping("/signup")
    fun signup(): String {
        println("signup")
        return "😇 signup"
    }

    @GetMapping("/login")
    fun login(): String {
        println("login")
        return "😇 Login"
    }
}