package com.mysterybox.focusmission.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mysterybox.focusmission.uiDefine.DarkGray
import com.mysterybox.focusmission.uiDefine.DuolingoBlue
import com.mysterybox.focusmission.uiDefine.DuolingoRed
import com.mysterybox.focusmission.uiDefine.MyTextStyle
import com.mysterybox.focusmission.uiDefine.OrangeBear
import com.mysterybox.focusmission.uiDefine.PathBrown
import com.mysterybox.focusmission.uiDefine.SkyBlue
import com.mysterybox.focusmission.uiDefine.White

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SkyBlue)
    ) {
        // Main Content
        Column(modifier = Modifier.fillMaxSize()) {
            HeaderSection()
            UnitProgressCard()
            Spacer(modifier = Modifier.height(20.dp))
            LessonPath()
        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Good Morning",
                style = MyTextStyle
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "🇬🇧 English", fontSize = 14.sp, color = DarkGray)
                Text(text = " ▾", color = DarkGray)
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            ResourceItem(iconColor = DuolingoBlue, count = "245")
            Spacer(modifier = Modifier.width(8.dp))
            ResourceItem(iconColor = DuolingoRed, count = "5")
        }
    }
}


@Composable
fun ResourceItem(iconColor: Color, count: String) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = White,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(iconColor, CircleShape)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = count, fontWeight = FontWeight.Bold, fontSize = 12.sp)
        }
    }
}

@Composable
fun UnitProgressCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(PathBrown, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder for Paw icon
                Text("🐾", fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Level 5, Unit 2:",
                    fontSize = 12.sp,
                    color = DuolingoBlue,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "The Unpredictable Park",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkGray
                )
            }
            Text("≡", color = DuolingoBlue, fontSize = 24.sp)
        }
    }
}


@Composable
fun LessonPath() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Simple Path background
        Canvas(modifier = Modifier.fillMaxSize()) {
            val path = Path().apply {
                moveTo(size.width / 2, 0f)
                cubicTo(
                    size.width * 0.2f, size.height * 0.2f,
                    size.width * 0.8f, size.height * 0.4f,
                    size.width / 2, size.height * 0.6f
                )
                cubicTo(
                    size.width * 0.2f, size.height * 0.8f,
                    size.width * 0.8f, size.height * 0.9f,
                    size.width / 2, size.height
                )
            }
            drawPath(
                path = path,
                color = PathBrown.copy(alpha = 0.3f),
                style = Stroke(width = 80.dp.toPx())
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Bear character
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(OrangeBear, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text("🐻", fontSize = 50.sp)
            }
            
            Spacer(modifier = Modifier.height(40.dp))
            
            LessonNode(icon = "🏁", offset = (-40).dp)
            Spacer(modifier = Modifier.height(80.dp))
            LessonNode(icon = "🏋️", offset = 40.dp)
            Spacer(modifier = Modifier.height(80.dp))
            LessonNode(icon = "🧩", offset = (-20).dp)
        }
    }
}

@Composable
fun LessonNode(icon: String, offset: Dp) {
    Surface(
        modifier = Modifier
            .offset(x = offset)
            .size(60.dp),
        shape = CircleShape,
        color = White,
        shadowElevation = 4.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(icon, fontSize = 24.sp)
        }
    }
}
