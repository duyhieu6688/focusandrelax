package com.mysterybox.focusmission.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mysterybox.focusmission.uiDefine.*
import missionfocused.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource

data class InventoryItem(
    val name: String,
    val quantity: Int,
    val stars: Int,
    val emoji: String
)

@Composable
fun ProfileScreen() {
    val items = listOf(
        InventoryItem("Maroon Twin Ponytail", 1, 2, "💇"),
        InventoryItem("Doll Braids", 1, 3, "👱"),
        InventoryItem("High Street Two-Tone Colored Hair", 1, 2, "👩"),
        InventoryItem("White Witch Twin Ponytail", 1, 2, "👧"),
        InventoryItem("White Side-parted Hair", 1, 1, "💇"),
        InventoryItem("Girl-next-door's Braided Pigtail", 1, 2, "👧")
    )

    var selectedTab by remember { mutableIntStateOf(2) } // 2 is Shirt/Clothes tab

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ProfileBackground)
    ) {
        // Top Section: Character Preview
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            // Background shadow/decoration under character
            Box(
                modifier = Modifier
                    .size(180.dp, 45.dp)
                    .offset(y = 85.dp)
                    .background(Color(0xFFFFC0CB).copy(alpha = 0.5f), CircleShape)
            )

            // Character Hiha
            Image(
                painter = painterResource(Res.drawable.main_hiha_charactor),
                contentDescription = "Hiha Character",
                modifier = Modifier
                    .fillMaxHeight(0.8f)
                    .aspectRatio(1f),
                contentScale = ContentScale.Fit
            )

            // Navigation Arrows
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier.background(White.copy(alpha = 0.3f), CircleShape)
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Previous",
                        modifier = Modifier.size(32.dp),
                        tint = Color(0xFF8B4513)
                    )
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier.background(White.copy(alpha = 0.3f), CircleShape)
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Next",
                        modifier = Modifier.size(32.dp),
                        tint = Color(0xFF8B4513)
                    )
                }
            }
        }

        // Bottom Panel
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.3f),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            color = ProfilePanelBackground
        ) {
            Column {
                // Tabs Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    TabItem("🏠", selectedTab == 0, hasNotification = true) { selectedTab = 0 }
                    TabItem("🍴", selectedTab == 1, hasNotification = true) { selectedTab = 1 }
                    TabItem("👕", selectedTab == 2, hasNotification = false) { selectedTab = 2 }
                    
                    Spacer(modifier = Modifier.weight(1f))
                    
                    // Close/Down button
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = White,
                        modifier = Modifier
                            .size(44.dp)
                            .border(2.dp, TabUnselected, RoundedCornerShape(12.dp))
                            .clickable { }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                Icons.Default.KeyboardArrowDown,
                                contentDescription = "Close",
                                tint = Color(0xFFFF5A5A),
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                }

                // Grid of items
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(items) { item ->
                        InventoryCard(item)
                    }
                }
            }
        }
    }
}

@Composable
fun TabItem(icon: String, isSelected: Boolean, hasNotification: Boolean, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        color = if (isSelected) ProfilePanelBackground else TabUnselected,
        modifier = Modifier
            .height(50.dp)
            .width(70.dp)
            .then(
                if (isSelected) Modifier.border(
                    width = 2.dp,
                    color = Color(0xFFE8DCC4).copy(alpha = 0.5f),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                ) else Modifier
            )
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(icon, fontSize = 28.sp)
            if (hasNotification) {
                // Small dot indicator from the image
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color.Red, CircleShape)
                        .align(Alignment.TopEnd)
                        .offset(x = (-8).dp, y = 8.dp)
                )
            }
        }
    }
}

@Composable
fun InventoryCard(item: InventoryItem) {
    Card(
        colors = CardDefaults.cardColors(containerColor = ProfileItemGray),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.aspectRatio(0.85f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = item.name,
                style = MyTextSize6,
                textAlign = TextAlign.Center,
                maxLines = 2,
                lineHeight = 12.sp,
                color = Color(0xFF8B4513).copy(alpha = 0.7f),
                modifier = Modifier.height(24.dp)
            )
            
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                // Using emoji for now as placeholder for hair/item icons
                Text(item.emoji, fontSize = 52.sp)
            }
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "x${item.quantity}",
                    style = MyTextSize6,
                    color = Color(0xFF8B4513).copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.width(6.dp))
                repeat(item.stars) {
                    Image(
                        painter = painterResource(Res.drawable.star),
                        contentDescription = null,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
        }
    }
}
