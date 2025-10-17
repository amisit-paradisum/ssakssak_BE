package review

import org.example.ssakssakmeal.Entities.Review
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import review.dto.ReviewCreateRequest
import review.dto.ReviewResponse
import java.time.OffsetDateTime

@Service
class ReviewService (
    private val reviewRepository: ReviewRepository,
) {

    fun createReview(request: ReviewCreateRequest): ReviewResponse {
        request.star?.let {
            require(it in 1..5) { "평점은 1에서 5사이로 매겨주세요" }
        }

        val review = Review(
            id = request.id,
            star = request.star,
            school = request.school,
            date = OffsetDateTime.now(),
            menu = request.menu,
        )

        val savedReview = reviewRepository.save(review)
        return savedReview.toResponse()
    }

    //조회용 쫙쫙맨
    @Transactional(readOnly = true)
    open fun getReview(id: String): ReviewResponse {
        val review = reviewRepository.findById(id)
            .orElseThrow { NoSuchElementException("리뷰를 찾을 수 없습니다. ID: $id") }
        return review.toResponse()
    }

    @Transactional(readOnly = true)
    open fun getReviewsBySchool(school: String): List<ReviewResponse> {
        return reviewRepository.findBySchool(school).map { it.toResponse() }
    }

    @Transactional(readOnly = true)
    open fun getReviewsByStar(star: Int): List<ReviewResponse> {
        require(star in 1..5) { "평점은 1에서 5 사이여야 합니다" }
        return reviewRepository.findByStar(star)
            .map{ it.toResponse() }
    }

    //CRUD 쫚쫚맨
    fun updateReview(id:String, star:Int?, menu:String): ReviewResponse {
        star?.let {
            require(it in 1..5) {"평점은 1에서 5 사이여야 합니다."}
        }

        val review = reviewRepository.findById(id)
            .orElseThrow { NoSuchElementException("리뷰를 찾을 수 없습니다. ID: $id") }

        val updateReview = review.copy(star = star, menu = menu)

        return reviewRepository.save(updateReview).toResponse()
    }

    fun deleteReview(id: String) {
        if (!reviewRepository.existsById(id)) {
            throw NoSuchElementException("리뷰를 찾을 수 업서요! ID: $id")
        }
        reviewRepository.deleteById(id)
    }


    private fun Review.toResponse() = ReviewResponse (
        id = id,
        star = star,
        school = school,
        date = date,
        menu = menu
    )
}