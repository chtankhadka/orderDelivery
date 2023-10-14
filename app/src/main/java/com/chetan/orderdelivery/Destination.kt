package com.chetan.orderdelivery

open class Destination(open val route: String) {

    object Screen{

        //common
        object CommonSignInScreen : Destination("common-sign-in-screen")



        //Admin
        object AdminDashboardScreen : Destination("admin-dashboard-screen")
        object AdminAddFoodScreen : Destination("admin-add-food-screen")
        object AdminRatingUpdateScreen : Destination("admin-rating-update-screen")


        //User
        object UserDashboardScreen : Destination("user-dashboard-screen")
        object UserFoodOrderDescriptionScreen : Destination("user-food-order-description-screen")
        object UserOrderCheckoutScreen: Destination("user-order-checkout-screen")
    }
}