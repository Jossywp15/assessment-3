package org.d3if3169.doghotel.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3169.doghotel.R
import org.d3if3169.doghotel.ui.theme.DarkBlueDefault
import org.d3if3169.doghotel.ui.theme.Poppins
import org.d3if3169.doghotel.ui.theme.Typography

@Composable
fun AboutScreen(navHostController: NavHostController) {
    ScaffoldComponent(
        navHostController = navHostController,
        title = stringResource(R.string.tentang_aplikasi_title)
    ) {
        ScreenContent(modifier = it)
    }
}

@Composable
private fun ScreenContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.about),
            style = Typography.bodyLarge,
            color = DarkBlueDefault,
            textAlign = TextAlign.Justify
        )
        Text(text = "Copyright© Jossy Wicaksono 2024.", style = Typography.bodySmall, fontFamily = Poppins)
    }
}

@Preview
@Composable
private fun Prev() {
    AboutScreen(navHostController = rememberNavController())
}