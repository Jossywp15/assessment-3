package org.d3if3169.doghotel.ui.screen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.d3if3169.doghotel.R
import org.d3if3169.doghotel.ui.component.SmallText
import org.d3if3169.doghotel.ui.theme.DarkBlueDefault

@Composable
fun DisplayAlertDialog(
    openDialog: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    if (openDialog) {
        AlertDialog(
            containerColor = Color.White,
            textContentColor = DarkBlueDefault,
            text = { SmallText(text = stringResource(R.string.pesan_hapus)) },
            confirmButton = {
                TextButton(
                    onClick = { onConfirmation() }) {
                    SmallText(text = stringResource(id = R.string.tombol_hapus), color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {onDismissRequest() }) {
                    SmallText(text = stringResource(id = R.string.tombol_batal), color = DarkBlueDefault)
                }
            },
            onDismissRequest = { onDismissRequest() })
    }
}

@Preview
@Composable
private fun DialogPreview() {
    DisplayAlertDialog(openDialog = true, onDismissRequest = { /*TODO*/ }) {

    }
}