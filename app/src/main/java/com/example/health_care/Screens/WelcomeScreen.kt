package com.example.health_care.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.health_care.R

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
            Image(
                painter = painterResource(R.drawable.image1),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(R.drawable.opera_news_personalized_news_2019_07_23),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(300.dp)
                    .height(200.dp),

            )
            Text(
                text = "WELCOME",
                fontSize = 30.sp,
                modifier = Modifier.padding(top = 16.dp),
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight(700)
            )
            Text(
                text ="to health care system",
                        textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace,
                fontSize = 18.sp,
                fontWeight = FontWeight(500),

            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { /*TODO*/ },
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF85B9C8)

                ),
                modifier=Modifier.fillMaxWidth()
                    .height(55.dp)

            ) {
                Text(
                    text = "Sign up with Email",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight(500),
                        fontFamily = FontFamily.Monospace,
                        color = Color(0xFF000000)
                    )
                )

            }
            Row(
                modifier=Modifier.padding(top= 12.dp, bottom = 50.dp),
            ) {
                Text(
                    text= "Dont have an account?",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight(500),
                        fontFamily = FontFamily.Monospace,
                        color = Color(0xFF000000)
                    )
                )
                Text(
                    text= "Sign up",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight(800),
                        fontFamily = FontFamily.Monospace,
                        color = Color.Red
                    )
                )
            }

        }
    }
}


@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreen()
}