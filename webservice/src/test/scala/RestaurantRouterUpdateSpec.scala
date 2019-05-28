import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}

class RestaurantRouterUpdateSpec
    extends WordSpec
    with Matchers
    with ScalatestRouteTest {
  import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
  import io.circe.generic.auto._

  private val restaurants = Seq(
    Restaurant(
      "5dc95ee4",
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
      false
    ),
    Restaurant(
      "5dc8c6e0",
      "Abdo Kofta",
      "عبده كفتة",
      "PUBLISHED",
      "null",
      "i3qf6gym1p833di.jpg",
      "null",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      false,
      false,
      true,
      true,
      false
    )
  )

  "A RestaurantRouter" should {

    "reject invalid restaurant" in {
      val updateRestaurant = UpdateRestaurant(
        "",
        "عبده كفتة",
        "PUBLISHED",
        "null",
        "i3qf6gym1p833di.jpg",
        "null",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        false,
        false,
        true,
        true,
        closed = true
      )
      val repository = new InMemoryRestaurantRepository(restaurants)

      val router = new RestaurantRouter(repository)

      Put("/restaurant/id2", updateRestaurant) ~> router.route ~> check {
        status shouldBe StatusCodes.BadRequest
      }
    }

    "return NotFound with non existing restaurant" in {
      val repository = new InMemoryRestaurantRepository(restaurants)
      val updateRestaurant = UpdateRestaurant(
        "2",
        "ss",
        "PUBLISHED",
        "null",
        "i3qf6gym1p833di.jpg",
        "null",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        false,
        false,
        true,
        true,
        closed = true
      )
      val router = new RestaurantRouter(repository)

      Put("/restaurant/hello", updateRestaurant) ~> router.route ~> check {
        status shouldBe StatusCodes.NotFound
      }
    }

    "update valid restaurant" in {
      val updateId = "5dc8c6e0"
      val updateRestaurant = UpdateRestaurant(
        "11",
        "ss",
        "PUBLISHED",
        "null",
        "i3qf6gym1p833di.jpg",
        "null",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        false,
        false,
        true,
        true,
        closed = true
      )
      val repository = new InMemoryRestaurantRepository(restaurants)

      val router = new RestaurantRouter(repository)

      Put("/restaurant/" + updateId, updateRestaurant) ~> router.route ~> check {
        status shouldBe StatusCodes.OK
      }
    }

  }

}
