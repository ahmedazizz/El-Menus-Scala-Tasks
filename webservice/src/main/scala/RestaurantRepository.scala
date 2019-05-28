import java.util.UUID

import scala.concurrent.{ExecutionContext, Future}

trait RestaurantRepository {
  def save(createRestaurant: CreateRestaurant): Future[Restaurant]
  def update(id: String, updateRestaurant: UpdateRestaurant): Future[Unit]
  def delete(id: String): Future[Unit]
  def all(): Future[Seq[Restaurant]]
  def closed(): Future[Seq[Restaurant]]
  def pending(): Future[Seq[Restaurant]]
}

object RestaurantRepository {
  case object RestaurantNotFound extends Throwable
}

class InMemoryRestaurantRepository(
    initialRestaurants: Seq[Restaurant] = Seq.empty
)(implicit ec: ExecutionContext)
    extends RestaurantRepository {
  import RestaurantRepository._

  private var restaurants: Vector[Restaurant] = initialRestaurants.toVector

  override def save(createRestaurant: CreateRestaurant): Future[Restaurant] =
    Future.successful {
      val id = UUID.randomUUID().toString
      val restaurant = Restaurant(
        id,
        createRestaurant.enName,
        createRestaurant.arName,
        createRestaurant.state,
        createRestaurant.routingMethod,
        createRestaurant.logo,
        createRestaurant.coverPhoto,
        createRestaurant.enDescription,
        createRestaurant.arDescription,
        createRestaurant.shortNumber,
        createRestaurant.facebookLink,
        createRestaurant.twitterLink,
        createRestaurant.youtubeLink,
        createRestaurant.website,
        createRestaurant.onlinePayment,
        createRestaurant.client,
        createRestaurant.pendingInfo,
        createRestaurant.pendingMenu,
        closed = false
      )
      restaurants = restaurants :+ restaurant
      restaurant
    }

  override def update(
      id: String,
      updateRestaurant: UpdateRestaurant
  ): Future[Unit] =
    if (!restaurants.exists(_.uuid == id)) {
      Future.failed(RestaurantNotFound)
    } else {
      restaurants = restaurants.map { t =>
        if (t.uuid == id) {
          Restaurant(
            t.uuid,
            updateRestaurant.enName,
            updateRestaurant.arName,
            updateRestaurant.state,
            updateRestaurant.routingMethod,
            updateRestaurant.logo,
            updateRestaurant.coverPhoto,
            updateRestaurant.enDescription,
            updateRestaurant.arDescription,
            updateRestaurant.shortNumber,
            updateRestaurant.facebookLink,
            updateRestaurant.twitterLink,
            updateRestaurant.youtubeLink,
            updateRestaurant.website,
            updateRestaurant.onlinePayment,
            updateRestaurant.client,
            updateRestaurant.pendingInfo,
            updateRestaurant.pendingMenu,
            updateRestaurant.closed
          )
        } else {
          t
        }
      }
      Future.successful()
    }

  override def delete(id: String): Future[Unit] =
    if (!restaurants.exists(_.uuid == id)) {
      Future.failed(RestaurantNotFound)
    } else {
      restaurants = restaurants.filterNot(_.uuid == id)
      Future.successful()
    }

  override def all(): Future[Seq[Restaurant]] = Future.successful(restaurants)

  override def closed(): Future[Seq[Restaurant]] =
    Future.successful(restaurants.filter(!_.closed))

  override def pending(): Future[Seq[Restaurant]] =
    Future.successful(restaurants.filterNot(_.closed))
}
