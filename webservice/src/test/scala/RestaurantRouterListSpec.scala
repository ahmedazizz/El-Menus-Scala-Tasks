import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}

class RestaurantRouterListSpec
    extends WordSpec
    with Matchers
    with ScalatestRouteTest {
  import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
  import io.circe.generic.auto._

  "A RestaurantRouter" should {

    "return all the Restaurants" in {
      val restaurants = Seq(
        Restaurant(
          "5dc95ee4-add9-11e7-b988-0242ac110002",
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
          "5dc8c6e0-add9-11e7-b988-0242ac110003",
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

      val repository = new InMemoryRestaurantRepository(restaurants)

      val router = new RestaurantRouter(repository)

      Get("/restaurant") ~> router.route ~> check {
        status shouldBe StatusCodes.OK
        val respRestaurants = responseAs[Seq[Restaurant]]
        respRestaurants shouldBe restaurants
      }
    }

    "return all the closed restaurants" in {
      val restaurants = Seq(
        Restaurant(
          "5dc8c6e0-add9-11e7-b988-0242ac110003",
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
          closed = true
        ),
        Restaurant(
          "5dc8c6e0-add9-11e7-b988-0242ac110003",
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
          closed = false
        )
      )
      val repository = new InMemoryRestaurantRepository(restaurants)

      val router = new RestaurantRouter(repository)

      Get("/restaurant/closed") ~> router.route ~> check {
        status shouldBe StatusCodes.OK
        val respRestaurants = responseAs[Seq[Restaurant]]
        respRestaurants shouldBe restaurants.filter(!_.closed)
      }
    }
  }

}
