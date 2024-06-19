package org.d3if3169.doghotel.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3169.doghotel.R
import org.d3if3169.doghotel.database.CatatanDb
import org.d3if3169.doghotel.model.Catatan
import org.d3if3169.doghotel.navigation.Screen
import org.d3if3169.doghotel.ui.component.RegularText
import org.d3if3169.doghotel.ui.component.SmallText
import org.d3if3169.doghotel.ui.theme.DarkBlueDefault
import org.d3if3169.doghotel.util.SettingsDataStore
import org.d3if3169.doghotel.util.ViewModelFactory

@Composable
fun NotesScreen(navHostController: NavHostController) {
    val dataStore = SettingsDataStore(LocalContext.current)
    val showList by dataStore.layoutFlow.collectAsState(true)

    ScaffoldComponent(
        navHostController = navHostController,
        title = stringResource(id = R.string.catatan_anjing_notes),
        fab = {
            FloatingActionButton(
                onClick = { navHostController.navigate(Screen.AddNotes.route) },
                containerColor = DarkBlueDefault,
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.ikon_tombol_aksi_mengambang)
                )
            }
        },
        actions = {
            IconButton(onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    dataStore.saveLayout(!showList)
                }
            }) {
                Icon(
                    painter = painterResource(
                        if (showList) R.drawable.baseline_grid_view_24
                        else R.drawable.baseline_view_list_24
                    ), contentDescription = stringResource(
                        if (showList) R.string.grid
                        else R.string.list
                    ),
                    tint = Color.White
                )
            }
        }
    ) {
        ScreenContent(modifier = it, navHostController, showList)
    }
}

@Composable
private fun ScreenContent(modifier: Modifier = Modifier, navHostController: NavHostController, showList: Boolean) {
    val context = LocalContext.current
    val db = CatatanDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel: NotesViewModel = viewModel(factory = factory)
    val data by viewModel.data.collectAsState()
    if (data.isEmpty()) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(206.dp, 210.dp),
                painter = painterResource(id = R.drawable.empty_state_doghotel),
                contentDescription = stringResource(R.string.gambar_data_kosong)
            )
            RegularText(text = stringResource(R.string.notes_kosong_text))
        }
    } else {
        if (showList) {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(bottom = 84.dp)
            ) {
                items(data) {
                    ListItem(catatan = it) {
                        navHostController.navigate(Screen.EditNotes.withId(it.id))
                    }
                    Divider()
                }
            }
        } else {
            LazyVerticalStaggeredGrid(
                modifier = modifier.fillMaxSize(),
                columns = StaggeredGridCells.Fixed(2),
                verticalItemSpacing = 8.dp,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp, 8.dp, 8.dp, 84.dp)
            ) {
                items(data) {
                    GridItem(catatan = it) {
                        navHostController.navigate(Screen.EditNotes.withId(it.id))
                    }
                }
            }
        }
    }
}




@Preview
@Composable
private fun Prev() {
    NotesScreen(navHostController = rememberNavController())
}