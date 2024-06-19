package org.d3if3169.doghotel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.d3if3169.doghotel.navigation.SetUpnavGraph
import org.d3if3169.doghotel.ui.screen.LandingScreen
import org.d3if3169.doghotel.ui.screen.MainScreen
import org.d3if3169.doghotel.ui.theme.DoghotelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                SetUpnavGraph()
        }
    }
}