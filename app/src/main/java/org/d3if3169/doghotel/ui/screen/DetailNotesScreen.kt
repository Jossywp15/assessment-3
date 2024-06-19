package org.d3if3169.doghotel.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults.itemColors
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3169.doghotel.R
import org.d3if3169.doghotel.database.CatatanDb
import org.d3if3169.doghotel.ui.component.RegularText
import org.d3if3169.doghotel.ui.component.SmallText
import org.d3if3169.doghotel.ui.theme.Poppins
import org.d3if3169.doghotel.util.ViewModelFactory

@Composable
fun DetailNotesScreen(navHostController: NavHostController, id: Int? = null) {
    val context = LocalContext.current
    val db = CatatanDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel: AddNotesViewModel = viewModel(factory = factory)
    var showDialog by remember { mutableStateOf(false) }


    var namaDoggy by rememberSaveable {
        mutableStateOf("")
    }
    var lamaMenginap by rememberSaveable {
        mutableStateOf("")
    }
    var lamaMenginapConvert by rememberSaveable {
        mutableStateOf("0")
    }
    var catatan by rememberSaveable {
        mutableStateOf("")
    }
    var stop by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(true) {
        if (id == null) return@LaunchedEffect
        val data = viewModel.getCatatan(id) ?: return@LaunchedEffect
        namaDoggy = data.nama
        lamaMenginap = data.lamaMenginap
        catatan = data.catatan
    }
    if (lamaMenginap != "" && !stop) {
        Log.d("DetailNotes", "lamaMenginap b4: $lamaMenginap")
        lamaMenginap = when (lamaMenginap) {
            "1" -> stringResource(id = R.string.lama_menginap_1)
            "2" -> stringResource(id = R.string.lama_menginap_2)
            else -> stringResource(id = R.string.lama_menginap_3)
        }
        stop = true
        Log.d("DetailNotes", "lamaMenginap after: $lamaMenginap")
    }
    ScaffoldComponent(
        navHostController = navHostController,
        title = stringResource(R.string.catatan_untuk_anjing_kamu_title),
        actions = {
            IconButton(onClick = {
                if (namaDoggy.isEmpty() || catatan.isEmpty() ||
                    lamaMenginap.isEmpty()
                ) {
                    Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                    return@IconButton
                }
                if (id == null) {
                    viewModel.insert(namaDoggy, catatan, lamaMenginapConvert)
                } else {
                    viewModel.update(id, namaDoggy, catatan, lamaMenginapConvert)
                }
                navHostController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Outlined.Check,
                    contentDescription = stringResource(
                        id = R.string.simpan
                    ), tint = Color.White
                )
            }
            if (id != null) {
                DeleteAction { showDialog = true }
                DisplayAlertDialog(
                    openDialog = showDialog,
                    onDismissRequest = { showDialog = false }) {
                    showDialog = false
                    viewModel.delete(id)
                    navHostController.popBackStack()
                }
            }
        }
    ) {
        AddNotesForm(
            modifier = it,
            namaDoggy = namaDoggy, onNameChange = { namaDoggy = it },
            lamaMenginap = lamaMenginap, onMenginapChange = { lamaMenginap = it },
            onMenginapConvert = { lamaMenginapConvert = it },
            catatan = catatan, onCatatanChange = { catatan = it },
        )
    }
}

@Composable
fun AddNotesForm(
    modifier: Modifier,
    namaDoggy: String, onNameChange: (String) -> Unit,
    lamaMenginap: String, onMenginapChange: (String) -> Unit,
    onMenginapConvert: (String) -> Unit,
    catatan: String, onCatatanChange: (String) -> Unit,
) {
    val options = listOf(
        stringResource(R.string.lama_menginap_1),
        stringResource(R.string.lama_menginap_2),
        stringResource(R.string.lama_menginap_3),
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 72.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RegularText(
            text = stringResource(id = R.string.nama_doggy_title), modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, bottom = 4.dp)
        )
        OutlinedTextField(
            value = namaDoggy,
            maxLines = 1,
            onValueChange = { onNameChange(it) },
            textStyle = TextStyle(fontFamily = Poppins),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words, imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxWidth()) {
                options.forEach() {
                    RadioForm(
                        modifier = Modifier.fillMaxWidth(),
                        text = it,
                        isSelected = lamaMenginap == it
                    ) {
                        onMenginapChange(it)
                    }
                }
                when (lamaMenginap) {
                    stringResource(id = R.string.lama_menginap_1) -> onMenginapConvert("1")
                    stringResource(id = R.string.lama_menginap_2) -> onMenginapConvert("2")
                    stringResource(id = R.string.lama_menginap_3) -> onMenginapConvert("3")
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        RegularText(
            text = stringResource(R.string.catatan_title),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = catatan,
            onValueChange = { onCatatanChange(it) },
            textStyle = TextStyle(fontFamily = Poppins),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences, imeAction = ImeAction.Done
            )
        )
    }
}

@Composable
fun DeleteAction(delete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(id = R.string.lainnya),
            tint = Color.White
        )
        DropdownMenu(
            modifier = Modifier.background(Color.White),
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                text = { SmallText(text = stringResource(id = R.string.hapus)) },
                onClick = {
                    expanded = false
                    delete()
                }
            )
        }
    }
}

@Preview
@Composable
private fun Prev() {
    DetailNotesScreen(navHostController = rememberNavController())
}