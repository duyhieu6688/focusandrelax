package com.mysterybox.focusmission.screen

sealed class Screen(val route: String, val title: String) {
    object Login : Screen("login", "Login")
    object Permission : Screen("permission", "Permission")
    object Home : Screen("home", "Home")
    object Streak : Screen("streak", "Streak")
    object Lessons : Screen("lessons", "Lessons")
    object Ranking : Screen("ranking", "Ranking")
    object Profile : Screen("profile", "Profile")
}
