package com.example.ejercicio2_estudio_ia_dex.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.ejercicio2_estudio_ia_dex.R

@Composable
fun ErrorModal(
    message: String,
    onClick: () -> Unit,
    onClose: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().zIndex(10f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0x22000000))
                .weight(2f)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF004C76))
                .weight(1f)
                .padding(25.dp),
        ) {
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Ha ocurrido un error",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    IconButton(
                        onClick = onClose
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_close),
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.width(40.dp))

                Text(
                    text = message,
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    color = Color(0xFFF0F0F0)
                )

                ElevatedButton(
                    onClick = onClick,
                    modifier = Modifier.fillMaxWidth().padding(0.dp, 10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6166FF))
                ) {
                    Text(
                        text = "Reintentar",
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}