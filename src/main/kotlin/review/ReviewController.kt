package review

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/review")
class ReviewControllerRestController {

    @GetMapping
    fun addReview(): String {

    }
}