object TodoValidator {

  sealed trait ValidationResult
  case object Valid extends ValidationResult
  final case class Invalid(message: String) extends ValidationResult

  def validate(restaurant: RestaurantLike): ValidationResult =
    if (restaurant.enName.isEmpty) {
    Invalid("English Name can not be empty.")
  } else if (restaurant.arName.isEmpty) {
    Invalid("Arabic Name can not be empty.")
  } else {
    Valid
  }
}
