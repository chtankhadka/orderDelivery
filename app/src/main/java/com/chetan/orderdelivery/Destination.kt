package com.chetan.orderdelivery

open class Destination(open val route: String) {

    object Screen{

        //common
        object CommonSignInScreen : Destination("common-sign-in-screen")



        //Admin
        object AdminDashboardScreen : Destination("admin-dashboard-screen")


        //User
        object UserDashboardScreen : Destination("user-dashboard-screen")
    }
}