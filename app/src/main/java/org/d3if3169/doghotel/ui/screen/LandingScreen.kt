package org.d3if3169.doghotel.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults.outlinedButtonColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3169.doghotel.R
import org.d3if3169.doghotel.navigation.Screen
import org.d3if3169.doghotel.ui.theme.DarkBlueDefault
import org.d3if3169.doghotel.ui.theme.Poppins
import org.d3if3169.doghotel.ui.theme.Typography

@Composable
fun LandingScreen(navHostController: NavHostController) {
    Scaffold {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
                .padding(vertical = 100.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(361.dp, 424.dp),
                    painter = painterResource(id = R.drawable.dog_hotel),
                    contentDescription = stringResource(R.string.ilustrasi_dog_hotel)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.landing_header),
                    textAlign = TextAlign.Center,
                    style = Typography.bodyLarge,
                    color = DarkBlueDefault,
                )
            }
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = { navHostController.navigate(Screen.Main.route) },
                colors = outlinedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = DarkBlueDefault,

                    ),
                contentPadding = PaddingValues(horizontal = 50.dp, vertical = 21.dp),
                border = BorderStroke(1.dp, DarkBlueDefault)
            ) {
                Text(
                    text = stringResource(R.string.mulai_button),
                    fontSize = 16.sp,
                    fontFamily = Poppins
                )
            }
        }
    }
}

@Preview
@Composable
private fun Prev() {
    LandingScreen(rememberNavController())
}