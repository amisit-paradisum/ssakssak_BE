package review

import org.example.ssakssakmeal.Entities.Review
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.ErrorResponseException
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import review.dto.ReviewCreateRequest
import review.dto.ReviewResponse


@RestController
@RequestMapping("/api/review")
class ReviewControllerRestController(
    val reviewService: ReviewService
) {

    @PostMapping
    fun createReview(@RequestBody request: ReviewCreateRequest): ResponseEntity<ReviewResponse> {
        val response = reviewService.createReview(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @GetMapping("/{id}")
    fun getReview(@PathVariable id: String): ResponseEntity<ReviewResponse> {
        val response = reviewService.getReview(id)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/school/{school}")
    fun getReviewBySchool(@PathVariable school: String): ResponseEntity<List<ReviewResponse>?> {
        val response = reviewService.getReviewsBySchool(school)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/star/{star}")
    fun getReviewByStar(@PathVariable star: Int): ResponseEntity<List<ReviewResponse>?> {
        val response = reviewService.getReviewsByStar(star)
        return ResponseEntity.ok(response)
    }

    @PutMapping("/{id}")
    fun updateReview(
        @PathVariable id: String,
        @RequestBody request: ReviewCreateRequest
    ): ResponseEntity<ReviewResponse> {
        val response = reviewService.updateReview(id, request.star, request.menu)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun deleteReview(@PathVariable id: String): ResponseEntity<Unit> {
        reviewService.deleteReview(id)
        return ResponseEntity.noContent().build()
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<ErrorResponse?> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(e.message ?: "리소스를 찾을 수 없습니다."))
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(e.message ?: "잘못된 요청입니다."))
    }

    data class ErrorResponse(val message: String)
}