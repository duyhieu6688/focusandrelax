package com.mysterybox.focusmission.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mysterybox.focusmission.uiDefine.*
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import missionfocused.composeapp.generated.resources.Res

import missionfocused.composeapp.generated.resources.star
import org.jetbrains.compose.resources.painterResource
data class Challenge(
    val id: Int,
    val icon: String,
    val title: String,
    val xpReward: Int,
    var isCompleted: Boolean = false,
    val target: Int = 1,
    var progress: Int = 0
)

@Composable
fun StreakScreen() {
    val settings = remember { Settings() }
    var streakCount by remember { mutableStateOf(25) }
    val days = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
    val completedDays = remember { mutableStateListOf(0, 1, 2, 3, 4, 5, 6) }
    var showReward by remember { mutableStateOf(false) }

    // XP State
    var totalXP by remember { mutableStateOf(settings.getInt("total_xp", 0)) }
    val levelThreshold = 30
    val currentLevelXP = totalXP % levelThreshold
    val currentLevel = (totalXP / levelThreshold) + 1

    val challenges = remember {
        mutableStateListOf(
            Challenge(1, "💧", "Drink water", 5, target = 8),
            Challenge(2, "🦒", "Take a stretch break", 5),
            Challenge(3, "🪥", "Brush teeth", 5),
            Challenge(4, "📖", "Read 10 pages", 10),
            Challenge(5, "🧘", "Meditate 5 mins", 10)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Brush.verticalGradient(listOf(Color(0xFFFFF9E6), Color(0xFFFFFFFF))))
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "🔥", fontSize = 100.sp)
                    Text(text = streakCount.toString(), fontSize = 64.sp, fontWeight = FontWeight.Black, color = DarkGray)
                    Text(text = "Day's", fontSize = 24.sp, color = DuolingoYellow, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Streak Completed", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, color = DarkGray)
                    Text(text = "You are getting smarter day by day...", fontSize = 16.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(24.dp))
                    Surface(
                        modifier = Modifier.fillMaxWidth().border(2.dp, DuolingoYellow.copy(alpha = 0.5f), RoundedCornerShape(24.dp)),
                        shape = RoundedCornerShape(24.dp), color = White
                    ) {
                        Row(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            days.forEachIndexed { index, day -> DayItem(day = day, isCompleted = completedDays.contains(index)) }
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = {
                            val todayIndex = 6 // Giả sử hôm nay là Thứ 4 (Wed)
                            if (!completedDays.contains(todayIndex)) {
                                completedDays.add(todayIndex)
                                streakCount++
                                if (completedDays.size >= 7) {
                                    showReward = true
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = DuolingoYellow)
                    ) {
                        Text(text = "Continue", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DarkGray)
                    }
                }
            }

            // XP Section
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF2ABDBB))
                        .padding(20.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("⚡", fontSize = 24.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text("${currentLevel}th Adventure", color = Color.White, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(20.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color.White.copy(alpha = 0.3f))
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(fraction = (currentLevelXP.toFloat() / levelThreshold).coerceIn(0f, 1f))
                                        .fillMaxHeight()
                                        .background(DuolingoYellow)
                                )
                                Text(
                                    "$currentLevelXP / $levelThreshold",
                                    modifier = Modifier.align(Alignment.Center),
                                    fontSize = 12.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("🗓️", color = Color.White)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("${challenges.count { !it.isCompleted }} goals left for today!", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                        Row {
                            Text("≡", color = Color.White, fontSize = 20.sp)
                            Spacer(modifier = Modifier.width(12.dp))
                            Text("∷", color = Color.White, fontSize = 20.sp)
                        }
                    }
                }
            }

            // Challenges List
            items(challenges) { challenge ->
                ChallengeItem(
                    challenge = challenge,
                    onComplete = {
                        if (challenge.target > 1) {
                            if (challenge.progress < challenge.target) {
                                challenge.progress++
                                if (challenge.progress == challenge.target) {
                                    challenge.isCompleted = true
                                    totalXP += challenge.xpReward
                                    settings["total_xp"] = totalXP
                                }
                                // Trigger recomposition for progress
                                val index = challenges.indexOf(challenge)
                                challenges[index] = challenge.copy()
                            }
                        } else {
                            if (!challenge.isCompleted) {
                                challenge.isCompleted = true
                                totalXP += challenge.xpReward
                                settings["total_xp"] = totalXP
                                // Trigger recomposition
                                val index = challenges.indexOf(challenge)
                                challenges[index] = challenge.copy()
                            }
                        }
                    }
                )
            }
            
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }

        if (showReward) {
            AlertDialog(
                onDismissRequest = { showReward = false },
                title = { Text("Chúc mừng!") },
                text = { Text("Bạn đã hoàn thành chuỗi 7 ngày và nhận được 50 kim cương! 💎") },
                confirmButton = {
                    TextButton(onClick = { 
                        showReward = false 
                        completedDays.clear() // Reset cho tuần mới
                    }) {
                        Text("Nhận thưởng")
                    }
                }
            )
        }
    }
}

@Composable
fun ChallengeItem(challenge: Challenge, onComplete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(challenge.icon, fontSize = 28.sp)
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (challenge.target > 1) {
                            Text(
                                "${challenge.progress} / ${challenge.target}",
                                color = if (challenge.isCompleted) DuolingoGreen else Color.Gray,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        Text(
                            text = challenge.title,
                            fontWeight = FontWeight.Bold,
                            color = DarkGray
                        )
                    }
                }
            }
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("${challenge.xpReward} ⚡", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.width(12.dp))
                IconButton(
                    onClick = onComplete,
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            if (challenge.isCompleted) Color(0xFFE5E5E5) else Color(0xFFF7F7F7),
                            RoundedCornerShape(12.dp)
                        )
                ) {
                    Icon(
                        painter = if(challenge.isCompleted) painterResource(Res.drawable.star) else painterResource(Res.drawable.star),
                        contentDescription = null,
                        tint = if (challenge.isCompleted) DuolingoGreen else Color.LightGray
                    )
                }
            }
        }
    }
}

@Composable
fun DayItem(day: String, isCompleted: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(
                    if (isCompleted) DuolingoYellow else Color.Transparent,
                    CircleShape
                )
                .border(
                    2.dp,
                    if (isCompleted) DuolingoYellow else Color.LightGray,
                    CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isCompleted) {
                Text("✓", color = White, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = day,
            fontSize = 12.sp,
            color = if (isCompleted) DarkGray else Color.Gray,
            fontWeight = if (isCompleted) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
@Preview
fun StreakScreenPreview() {
    StreakScreen()
}