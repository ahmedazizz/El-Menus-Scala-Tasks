import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route

trait Router {
  def route: Route
}

class RestaurantRouter(restaurantRepository: RestaurantRepository)
    extends Router
    with CustomDirectives {
  import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
  import io.circe.generic.auto._

  def route: Route = pathPrefix("restaurant") {
    path("closed") {
      handleFailureWithDefault(restaurantRepository.closed()) { restaurant =>
        complete(restaurant)
      }
    } ~ path("pending") {
      handleFailureWithDefault(restaurantRepository.pending()) { restaurant =>
        complete(restaurant)
      }
    } ~ get {
      handleFailureWithDefault(restaurantRepository.all()) { restaurant =>
        complete(restaurant)
      }
    } ~ post {
      entity(as[CreateRestaurant]) { createRestaurant =>
        validateTodo(createRestaurant) {
          handleFailureWithDefault(restaurantRepository.save(createRestaurant)) {
            restaurant =>
              complete(restaurant)
          }
        }
      }
    } ~ path(Segment) { id =>
      put {
        entity(as[UpdateRestaurant]) { updateRestaurant =>
          validateTodo(updateRestaurant) {
            handleFailureWithNotFound(
              restaurantRepository.update(id, updateRestaurant)
            ) { _ =>
              complete(StatusCodes.OK)
            }
          }
        }
      } ~ delete {
        handleFailureWithNotFound(restaurantRepository.delete(id)) { _ =>
          complete(StatusCodes.OK)
        }
      }
    }
  }

}
