import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}

class RestaurantRouterSaveSpec
    extends WordSpec
    with Matchers
    with ScalatestRouteTest {
  import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
  import io.circe.generic.auto._

  "A RestaurantRouter" should {

    "reject invalid Restaurant" in {
      val createRestaurant = CreateRestaurant(
        "",
        "عالقهوة كافيه",
        "PUBLISHED",
        "null",
        "i3qf6gym1p833di.jpg",
        "null",
        "s",
        "s",
        "s",
        "s",
        "s",
        "s",
        "s",
        false,
        false,
        true,
        true,
        true
      )
      val repository = new InMemoryRestaurantRepository()

      val router = new RestaurantRouter(repository)

      Post("/restaurant", createRestaurant) ~> router.route ~> check {
        status shouldBe StatusCodes.BadRequest
      }
    }

    "save valid Restaurant" in {
      val createRestaurant = CreateRestaurant(
        "3al Ahwa Cafe",
        "عالقهوة كافيه",
        "PUBLISHED",
        "null",
        "i3qf6gym1p833di.jpg",
        "null",
        "s",
        "s",
        "s",
        "s",
        "s",
        "s",
        "s",
        false,
        false,
        true,
        true,
        true
      )
      val repository = new InMemoryRestaurantRepository()

      val router = new RestaurantRouter(repository)

      Post("/restaurant", createRestaurant) ~> router.route ~> check {
        status shouldBe StatusCodes.OK
        val restaurant = responseAs[Restaurant]
        restaurant.enName shouldBe createRestaurant.enName
        restaurant.arName shouldBe createRestaurant.arName
      }
    }

  }

}
