package com.mysterybox.focusmission.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mysterybox.focusmission.uiDefine.DarkGray
import com.mysterybox.focusmission.uiDefine.DuolingoBlue
import com.mysterybox.focusmission.uiDefine.DuolingoRed
import com.mysterybox.focusmission.uiDefine.MyTextSize3
import com.mysterybox.focusmission.uiDefine.MyTextStyle
import com.mysterybox.focusmission.uiDefine.OrangeBear
import com.mysterybox.focusmission.uiDefine.PathBrown
import com.mysterybox.focusmission.uiDefine.SkyBlue
import com.mysterybox.focusmission.uiDefine.White
import missionfocused.composeapp.generated.resources.Res
import missionfocused.composeapp.generated.resources.coins_icon
import missionfocused.composeapp.generated.resources.main_background_home_screen
import missionfocused.composeapp.generated.resources.main_hiha_charactor
import missionfocused.composeapp.generated.resources.streak_icon
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SkyBlue),

    ) {
        Image(
            painter = painterResource(Res.drawable.main_background_home_screen),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        // Main Content
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            HeaderSection()
//            UnitProgressCard()
//            Spacer(modifier = Modifier.height(20.dp).background(SkyBlue).width(100.dp))
//            LessonPath()
            MainCharactor()
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
            ResourceItem("\uD83D\uDD25", count = "245")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            ResourceItem("\uD83E\uDE99", count = "5")
        }
    }
}

@Composable
fun MainCharactor() {
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(Res.drawable.main_hiha_charactor),
            contentDescription = null,
            modifier = Modifier.fillMaxHeight().height(300.dp).width(300.dp)
        )
    }
}

@Composable
fun ResourceItem(icon: String, count: String) {
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
                    .size(36.dp)
            ) {
                Text(icon, fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = count, fontWeight = FontWeight.Bold, fontSize = 18.sp)
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
                Text("\uD83D\uDC3B\u200D❄\uFE0F", fontSize = 50.sp)
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

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
