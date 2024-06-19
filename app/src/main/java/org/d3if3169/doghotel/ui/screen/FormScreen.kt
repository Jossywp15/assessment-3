package org.d3if3169.doghotel.ui.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3169.doghotel.R
import org.d3if3169.doghotel.navigation.Screen
import org.d3if3169.doghotel.ui.theme.DarkBlueDefault
import org.d3if3169.doghotel.ui.theme.Poppins

@Composable
fun FormScreen(navHostController: NavHostController) {
    ScaffoldComponent(
        navHostController = navHostController,
        title = stringResource(R.string.form_pendaftaran_title),
        actions = {
            IconButton(onClick = { navHostController.navigate(Screen.About.route) }) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = stringResource(R.string.ikon_tentang_aplikasi))
            }
        }
    ) {
        ScreenContent(modifier = it)
    }
}

@Composable
private fun ScreenContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var nama by rememberSaveable { mutableStateOf("") }
    var umur by rememberSaveable { mutableStateOf("") }
    var berat by rememberSaveable { mutableStateOf("") }
    var lamaMenginap by rememberSaveable { mutableStateOf("") }
    var harga by rememberSaveable { mutableStateOf("") }
    var lamaMenginapConverted by rememberSaveable { mutableIntStateOf(0) }
    var hitungClicked by remember { mutableStateOf(false) }

    val options = listOf(
        stringResource(R.string.lama_menginap_1),
        stringResource(R.string.lama_menginap_2),
        stringResource(R.string.lama_menginap_3),
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = stringResource(R.string.nama_doggy_title), fontFamily = Poppins,
                modifier = Modifier
                    .padding(start = 4.dp, bottom = 4.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                textStyle = TextStyle(fontFamily = Poppins),
                value = nama,
                maxLines = 1,
                onValueChange = { nama = it },
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                )
            )
            Text(
                text = stringResource(R.string.umur_title), fontFamily = Poppins,
                modifier = Modifier
                    .padding(start = 4.dp, bottom = 4.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                textStyle = TextStyle(fontFamily = Poppins),
                value = umur,
                maxLines = 1,
                onValueChange = { umur = it },
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                suffix = {
                    Text(
                        text = stringResource(R.string.tahun_suffix),
                        fontFamily = Poppins
                    )
                }
            )
            Text(
                text = stringResource(R.string.umur_text_limit), fontFamily = Poppins,
                modifier = Modifier
                    .padding(start = 4.dp, bottom = 8.dp)
                    .fillMaxWidth(),
                fontSize = 12.sp
            )
            Text(
                text = stringResource(R.string.berat_title), fontFamily = Poppins,
                modifier = Modifier
                    .padding(start = 4.dp, bottom = 4.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = berat,
                maxLines = 1,
                textStyle = TextStyle(fontFamily = Poppins),
                onValueChange = { berat = it },
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                suffix = { Text(text = stringResource(R.string.kg_suffix), fontFamily = Poppins) }
            )
            Text(
                text = stringResource(R.string.berat_text_limit), fontFamily = Poppins,
                modifier = Modifier
                    .padding(start = 4.dp, bottom = 8.dp)
                    .fillMaxWidth(),
                fontSize = 12.sp
            )
            Text(
                text = stringResource(R.string.lama_menginap_title), fontFamily = Poppins,
                modifier = Modifier
                    .padding(start = 4.dp, bottom = 4.dp)
                    .fillMaxWidth()
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                options.forEach() {
                    RadioForm(text = it, isSelected = lamaMenginap == it) {
//                        isSelected = !isSelected
                        lamaMenginap = it
                    }
                }
                lamaMenginapConverted = when (lamaMenginap) {
                    stringResource(R.string.lama_menginap_1) -> 1
                    stringResource(R.string.lama_menginap_2) -> 2
                    else -> 3
                }

            }
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    enabled = !hitungClicked,
                    contentPadding = PaddingValues(vertical = 16.dp, horizontal = 32.dp),
                    shape = RoundedCornerShape(20.dp),
                    onClick = {
                        if (umur.isNotEmpty() && umur.toInt() > 50) {
                            Toast.makeText(
                                context,
                                context.getString(R.string.toast_umur_limit), Toast.LENGTH_SHORT
                            ).show()
                        } else if (berat.isNotEmpty() && berat.toDouble() > 200.0) {
                            Toast.makeText(
                                context,
                                context.getString(R.string.toast_berat_limit), Toast.LENGTH_SHORT
                            ).show()
                        } else if (nama.isEmpty() || umur.isEmpty() || berat.isEmpty() || lamaMenginap.isEmpty()) {
                            Toast.makeText(
                                context,
                                context.getString(R.string.toast_empty), Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            hitungClicked = true
                            harga = hitungBiayaPenitipan(
                                umur.toInt(),
                                berat.toDouble(),
                                lamaMenginapConverted
                            ).toString()
                        }
                    },
                    colors = buttonColors(
                        containerColor = DarkBlueDefault,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = stringResource(R.string.hitung_button), fontFamily = Poppins)
                }
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                if (hitungClicked) {
                    Text(
                        text = stringResource(R.string.total_harga_label),
                        fontFamily = Poppins,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = formatRupiah(harga.toLong()),
                        fontFamily = Poppins,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { hitungClicked = false; harga = ""; lamaMenginap = "" }) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = stringResource(R.string.ikon_reset),
                                tint = DarkBlueDefault
                            )
                        }
                        IconButton(onClick = {
                            shareData(
                                context = context,
                                message = context.getString(
                                    R.string.bagikan_template,
                                    nama, umur, berat, formatRupiah(harga.toLong())
                                )
                            )
                        }) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = stringResource(R.string.ikon_bagikan),
                                tint = DarkBlueDefault
                            )
                        }
                    }
                }
            }

        }
    }
}



fun hitungBiayaPenitipan(umur: Int, berat: Double, lamaMenginap: Int): Int {
    val tarifDasar = 50000.0

    val faktorUmur = when {
        umur < 1 -> 1.2
        umur in 1..7 -> 1.0
        else -> 1.3
    }

    val faktorBerat = when {
        berat < 10 -> 1.0
        berat in 10.0..25.0 -> 1.1
        else -> 1.2
    }

    return (tarifDasar * faktorUmur * faktorBerat * lamaMenginap).toInt()
}

@SuppressLint("DefaultLocale")
fun formatRupiah(amount: Long): String {
    val formattedAmount = String.format("%,d", amount).replace(',', '.')
    return "Rp. $formattedAmount"
}


private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}

@Preview
@Composable
private fun Prev() {
    FormScreen(navHostController = rememberNavController())
}