package org.d3if3169.doghotel.ui.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.d3if3169.doghotel.ui.theme.Poppins


@Composable
fun RadioForm(modifier: Modifier = Modifier, text: String, isSelected: Boolean, onClick: () -> Unit) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = isSelected, onClick = { onClick() })
        Text(text = text, fontFamily = Poppins)
    }
}