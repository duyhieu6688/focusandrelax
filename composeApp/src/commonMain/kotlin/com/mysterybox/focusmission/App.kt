package com.mysterybox.focusmission

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mysterybox.focusmission.screen.*
import com.mysterybox.focusmission.uiDefine.DuolingoBlue
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

@Composable
fun App() {
    val settings = remember { Settings() }
    
    // Đọc trạng thái từ bộ nhớ
    val savedName = settings.getStringOrNull("user_name")
    val isPermissionGranted = settings.getBoolean("permission_granted", false)

    var currentScreen by remember { 
        mutableStateOf<Screen>(
            when {
                savedName == null -> Screen.Login
                !isPermissionGranted -> Screen.Permission
                else -> Screen.Home
            }
        )
    }

    when (currentScreen) {
        is Screen.Login -> {
            LoginName(onNameSaved = { name ->
                settings["user_name"] = name
                currentScreen = Screen.Permission
            })
        }
        is Screen.Permission -> {
            CheckPermissionScreen(onPermissionGranted = {
                settings["permission_granted"] = true
                currentScreen = Screen.Home
            })
        }
        else -> {
            MainScaffold(
                currentScreen = currentScreen,
                onScreenChange = { currentScreen = it }
            )
        }
    }
}

@Composable
fun MainScaffold(
    currentScreen: Screen,
    onScreenChange: (Screen) -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = currentScreen.route,
                onTabSelected = onScreenChange
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentScreen) {
                is Screen.Home -> HomeScreen()
                is Screen.Streak -> StreakScreen()
                is Screen.Lessons -> LessonsScreen() // Giữ nguyên fallback hoặc xử lý riêng
                is Screen.Ranking -> RankingScreen()
                is Screen.Profile -> ProfileScreen()
                else -> HomeScreen()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(currentRoute: String?, onTabSelected: (Screen) -> Unit) {
    val screens = listOf(
        Screen.Home,
        Screen.Streak,
        Screen.Lessons,
        Screen.Ranking,
        Screen.Profile
    )

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        screens.forEach { screen ->
            NavigationBarItem(
                label = { Text(text = screen.title, fontSize = 15.sp) },
                icon = {
                    Text(text = getIconForScreen(screen), fontSize = 25.sp)
                },
                selected = currentRoute == screen.route,
                onClick = { onTabSelected(screen) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = DuolingoBlue,
                    selectedTextColor = DuolingoBlue,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = DuolingoBlue.copy(alpha = 0.1f)
                )
            )
        }
    }
}

@Composable
fun getIconForScreen(screen: Screen): String {
    return when (screen) {
        is Screen.Home -> "🏠"
        is Screen.Streak -> "🔥"
        is Screen.Lessons -> "🎥"
        is Screen.Ranking -> "🏆"
        is Screen.Profile -> "👤"
        else -> ""
    }
}
