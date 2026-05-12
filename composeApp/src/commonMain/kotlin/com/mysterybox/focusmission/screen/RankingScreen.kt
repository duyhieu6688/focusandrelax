package com.mysterybox.focusmission.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mysterybox.focusmission.uiDefine.*

@Composable
fun RankingScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(RankingGreenStart, RankingGreenEnd)
                )
            )
            .verticalScroll(scrollState)
            .padding(bottom = 32.dp)
    ) {
        // Top Toolbar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Close, contentDescription = "Close", tint = White)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Avatars placeholder
                Box(modifier = Modifier.size(32.dp).background(Color.Gray, CircleShape))
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = {}) {
                    Icon(Icons.Default.CheckCircle, contentDescription = "Menu", tint = White)
                }
            }
        }

        // Streak Info
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Text(
                text = "Số ngày Streak",
                color = White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "53",
                color = White,
                fontSize = 48.sp,
                fontWeight = FontWeight.Black
            )
        }

        // Character Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentAlignment = Alignment.Center
        ) {
            // Character Placeholder (Emoji or icon)
            Text(text = "🌽", fontSize = 120.sp)
            
            // Right Arrow
            IconButton(
                onClick = {},
                modifier = Modifier.align(Alignment.CenterEnd).padding(end = 8.dp)
            ) {
                Icon(Icons.Default.Check, contentDescription = "Next", tint = White.copy(alpha = 0.7f), modifier = Modifier.size(40.dp))
            }
        }

        // Pet Name and Progress
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Thú cưng streak",
                    color = Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(Icons.Default.Edit, contentDescription = "Edit", tint = Black, modifier = Modifier.size(16.dp))
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Progress Bar
            Box(
                modifier = Modifier
                    .width(200.dp)
                    .height(24.dp)
                    .background(White, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                // Progress Fill
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.53f)
                        .fillMaxHeight()
                        .align(Alignment.CenterStart)
                        .background(RankingGreenEnd, RoundedCornerShape(12.dp))
                )
                Text(
                    text = "53/100",
                    color = White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "47 điểm để mở khóa diện mạo tiếp theo",
                color = Black.copy(alpha = 0.6f),
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Task Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Nuôi Thú cưng của bạn",
                    style = MyTextStyle.copy(fontSize = 16.sp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                TaskItem(text = "2 thành viên gửi tin nhắn", subtext = "+1 điểm trưởng thành", isDone = true)
                Divider(color = DividerColor, thickness = 1.dp, modifier = Modifier.padding(vertical = 12.dp))
                TaskItem(text = "2 thành viên gửi ảnh hoặc video", subtext = "+3 điểm trưởng thành", isDone = true)
                Divider(color = DividerColor, thickness = 1.dp, modifier = Modifier.padding(vertical = 12.dp))
                TaskItem(text = "3 thành viên gửi tin nhắn", subtext = "+3 điểm trưởng thành", isDone = true)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Badge Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Huy hiệu Streak của nhóm",
                    style = MyTextStyle.copy(fontSize = 16.sp)
                )
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BadgeItem(label = "3d", icon = "🔥", color = Color(0xFFFF9800))
                    BadgeLine()
                    BadgeItem(label = "10d", icon = "🔥", color = Color(0xFFFF5722))
                    BadgeLine()
                    BadgeItem(label = "30d", icon = "🔥", color = Color(0xFFF44336))
                    BadgeLine()
                    BadgeItem(label = "100d", icon = "🔥", color = Color.Gray)
                    BadgeLine()
                    BadgeItem(label = "200d", icon = "🔥", color = Color.Gray)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Tìm hiểu thêm về Streak của nhóm",
            color = DuolingoBlue,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TaskItem(text: String, subtext: String, isDone: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(if (isDone) Color(0xFFE8F5E9) else Color.Transparent, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            if (isDone) {
                Icon(Icons.Default.Check, contentDescription = "Done", tint = Color(0xFF4CAF50), modifier = Modifier.size(16.dp))
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = text, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(text = subtext, color = GrayText, fontSize = 12.sp)
        }
    }
}

@Composable
fun BadgeItem(label: String, icon: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = icon, fontSize = 24.sp, color = color)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, color = GrayText, fontSize = 10.sp)
    }
}

@Composable
fun BadgeLine() {
    Box(
        modifier = Modifier
            .width(20.dp)
            .height(2.dp)
            .background(DividerColor)
    )
}

@Preview
@Composable
fun RankingScreenPreview() {
    RankingScreen()
}