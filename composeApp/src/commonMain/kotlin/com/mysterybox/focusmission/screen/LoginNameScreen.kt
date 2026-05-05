package com.mysterybox.focusmission.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mysterybox.focusmission.uiDefine.DuolingoBlue
import com.mysterybox.focusmission.uiDefine.MyTextButtonSize
import com.mysterybox.focusmission.uiDefine.MyTextSize4
import com.mysterybox.focusmission.uiDefine.MyTextSize6
import com.mysterybox.focusmission.uiDefine.SkyBlue
import missionfocused.composeapp.generated.resources.Res
import missionfocused.composeapp.generated.resources.book_and_globe_1
import missionfocused.composeapp.generated.resources.button_blue
import org.jetbrains.compose.resources.painterResource

@Composable
fun LoginName(onNameSaved: (String) -> Unit) {
    var name by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize().background(SkyBlue)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                modifier = Modifier.weight(0.7f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.book_and_globe_1),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(0.3f)
            ) {
                Text(
                    "Xin chào! Hãy nhập tên của bạn", style = MyTextSize4
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Tên của bạn") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .width(180.dp)
                        .height(56.dp)
                        .clickable(enabled = name.isNotBlank()) { onNameSaved(name) },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(Res.drawable.button_blue),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds,
                        alpha = if (name.isNotBlank()) 1f else 0.5f
                    )
                    Text("Tiếp tục", style = MyTextButtonSize)
                }
            }

        }
    }
}

@Preview
@Composable
fun LoginNamePreview() {
    LoginName(onNameSaved = {})
}