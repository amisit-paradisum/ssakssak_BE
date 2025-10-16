package review.dto

import java.time.OffsetDateTime

class ReviewResponse (
    val id:String,
    val star: Int?,
    var school: String,
    val date: OffsetDateTime,
    val menu: String
)