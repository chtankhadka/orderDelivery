package com.chetan.orderdelivery

open class Destination(open val route: String) {

    object Screen{

        //common
        object CommonSignInScreen : Destination("common-sign-in-screen")



        //Admin
        object AdminDashboardScreen : Destination("admin-dashboard-screen")
        object AdminAddFoodScreen : Destination("admin-add-food-screen")
        object AdminRatingUpdateScreen : Destination("admin-rating-update-screen")
        object AdminOrderDetailScreen : Destination("admin-order-detail-screen/{user}")


        //User
        object UserDashboardScreen : Destination("user-dashboard-screen")
        object UserFoodOrderDescriptionScreen : Destination("user-food-order-description-screen/{foodId}")
        object UserOrderCheckoutScreen: Destination("user-order-checkout-screen/{totalCost}")
        object UserNotificationScreen: Destination("user-notification-screen")
        object UserMoreFoodScreen: Destination("user-more-food-screen")
        object UserOutCartScreen: Destination("user-out-cart-screen")
    }
}