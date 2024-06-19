package org.d3if3169.doghotel.ui.screen

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3169.doghotel.R
import org.d3if3169.doghotel.ui.component.RegularText

@Composable
fun RulesScreen(navHostController: NavHostController) {
    ScaffoldComponent(navHostController = navHostController, title = stringResource(R.string.peraturan_anjing_title)) {
        ScreenContent(modifier = it)
    }
}

@Composable
private fun ScreenContent(modifier: Modifier) {
    val rules = listOf(
        R.string.rules1,
        R.string.rules2,
        R.string.rules3,
        R.string.rules4,
        R.string.rules5,
        R.string.rules6,
        R.string.rules7,
        R.string.rules8,
        R.string.rules9,
        R.string.rules10
    )

    LazyColumn(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 32.dp, vertical = 80.dp)) {
        var number = rules.size - (rules.size - 1)
        items(rules) {
            RegularText(text = "$number. ${stringResource(id = it)}")
            Spacer(modifier = Modifier.height(4.dp))
            number++
        }
    }
}

@Preview
@Composable
private fun Prev() {
    RulesScreen(navHostController = rememberNavController())
}