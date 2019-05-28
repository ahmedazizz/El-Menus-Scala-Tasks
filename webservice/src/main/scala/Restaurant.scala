trait RestaurantLike {
  def enName: String
  def arName: String
  def state: String
  def routingMethod: String
  def logo: String
  def coverPhoto: String
  def enDescription: String
  def arDescription: String
  def shortNumber: String
  def facebookLink: String
  def twitterLink: String
  def youtubeLink: String
  def website: String
  def onlinePayment: Boolean
  def client: Boolean
  def pendingInfo: Boolean
  def pendingMenu: Boolean
  def closed: Boolean
}

case class CreateRestaurant(
    enName: String,
    arName: String,
    state: String,
    routingMethod: String,
    logo: String,
    coverPhoto: String,
    enDescription: String,
    arDescription: String,
    shortNumber: String,
    facebookLink: String,
    twitterLink: String,
    youtubeLink: String,
    website: String,
    onlinePayment: Boolean,
    client: Boolean,
    pendingInfo: Boolean,
    pendingMenu: Boolean,
    closed: Boolean
) extends RestaurantLike

case class UpdateRestaurant(
    enName: String,
    arName: String,
    state: String,
    routingMethod: String,
    logo: String,
    coverPhoto: String,
    enDescription: String,
    arDescription: String,
    shortNumber: String,
    facebookLink: String,
    twitterLink: String,
    youtubeLink: String,
    website: String,
    onlinePayment: Boolean,
    client: Boolean,
    pendingInfo: Boolean,
    pendingMenu: Boolean,
    closed: Boolean
) extends RestaurantLike

case class Restaurant(
    uuid: String,
    enName: String,
    arName: String,
    state: String,
    routingMethod: String,
    logo: String,
    coverPhoto: String,
    enDescription: String,
    arDescription: String,
    shortNumber: String,
    facebookLink: String,
    twitterLink: String,
    youtubeLink: String,
    website: String,
    onlinePayment: Boolean,
    client: Boolean,
    pendingInfo: Boolean,
    pendingMenu: Boolean,
    closed: Boolean
) extends RestaurantLike
