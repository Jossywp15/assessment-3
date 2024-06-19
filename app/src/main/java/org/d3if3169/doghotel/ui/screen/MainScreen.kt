package org.d3if3169.doghotel.ui.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.ClearCredentialException
import androidx.credentials.exceptions.GetCredentialException
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3169.doghotel.BuildConfig
import org.d3if3169.doghotel.R
import org.d3if3169.doghotel.model.User
import org.d3if3169.doghotel.navigation.Screen
import org.d3if3169.doghotel.ui.component.SmallText
import org.d3if3169.doghotel.ui.theme.DarkBlueDefault
import org.d3if3169.doghotel.ui.theme.Poppins
import org.d3if3169.doghotel.util.SettingsDataStore

@Composable
fun MainScreen(navHostController: NavHostController) {
    val context = LocalContext.current
    val dataStore = SettingsDataStore(context)
    val user by dataStore.userFlow.collectAsState(initial = User())
    var loggedIn by remember { mutableIntStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(user) {
        Log.d("MainScreen", "loggedIn b4: $loggedIn")
        Log.d("MainScreen", "loggedIn b4: ${user.email}")
        if (user.email != "" && loggedIn == 1) {
            Toast.makeText(
                context,
                context.getString(R.string.selamat_datang_user, user.name), Toast.LENGTH_LONG
            ).show()
        }
        Log.d("MainScreen", "loggedIn after: $loggedIn")
        Log.d("MainScreen", "loggedIn after: ${user.email}")
    }

    ScaffoldComponent(
        isMain = true, navHostController = navHostController,
        title = "Dog Hotel",
        actions = {
            if (user.email != "") {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(user.photoUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(R.string.foto_profil_pengguna),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.loading_img),
                    error = painterResource(id = R.drawable.broken_image),
                    modifier = Modifier
                        .size(30.dp)
                        .clip(shape = CircleShape)
                        .clickable { showDialog = true }
                )
                Spacer(modifier = Modifier.width(12.dp))
            } else {
                IconButton(onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        signIn(
                            context,
                            dataStore
                        )
                    }
                    loggedIn = 1
                }) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(R.drawable.account_circle),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    ) {
        ScreenContent(modifier = it, navHostController)

        if (showDialog) {
            ProfilDialog(user = user, onDismissRequest = { showDialog = false }) {
                CoroutineScope(Dispatchers.IO).launch { signOut(context, dataStore) }
                showDialog = false
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenContent(modifier: Modifier = Modifier, navHostController: NavHostController) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(211.dp, 257.dp),
            alignment = Alignment.Center,
            painter = painterResource(id = R.drawable.dog_1),
            contentDescription = stringResource(
                R.string.gambar_anjing_home
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedCard(
                onClick = { navHostController.navigate(Screen.Rules.route) },
                colors = cardColors(
                    containerColor = Color.Transparent,
                    contentColor = DarkBlueDefault
                ),
                border = BorderStroke(1.dp, DarkBlueDefault)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Image(
                        modifier = Modifier.size(136.dp, 117.dp),
                        painter = painterResource(id = R.drawable.dog_rules),
                        contentDescription = stringResource(
                            R.string.peraturan_anjing_home
                        )
                    )
                    SmallText(
                        text = stringResource(id = R.string.peraturan_anjing_home),
                        textAlign = TextAlign.Center,
                        color = DarkBlueDefault
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedCard(
                onClick = { navHostController.navigate(Screen.Notes.route) },
                colors = cardColors(
                    containerColor = Color.Transparent,
                    contentColor = DarkBlueDefault
                ),
                border = BorderStroke(1.dp, DarkBlueDefault)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Image(
                        modifier = Modifier.size(136.dp, 117.dp),
                        painter = painterResource(id = R.drawable.notes),
                        contentDescription = stringResource(
                            R.string.catatan_anjing_home
                        )
                    )
                    SmallText(
                        text = stringResource(id = R.string.catatan_anjing_home),
                        textAlign = TextAlign.Center,
                        color = DarkBlueDefault
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedCard(
            onClick = { navHostController.navigate(Screen.DogPhotos.route) },
            colors = cardColors(
                containerColor = Color.Transparent,
                contentColor = DarkBlueDefault
            ),
            border = BorderStroke(1.dp, DarkBlueDefault)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Image(
                    modifier = Modifier.size(136.dp, 117.dp),
                    painter = painterResource(id = R.drawable.camera_logo),
                    contentDescription = stringResource(R.string.foto_anjing_img_desc)
                )
                SmallText(
                    text = stringResource(id = R.string.foto_anjing_home),
                    textAlign = TextAlign.Center,
                    color = DarkBlueDefault
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            shape = RoundedCornerShape(20.dp),
            onClick = { navHostController.navigate(Screen.Form.route) },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = DarkBlueDefault,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(horizontal = 62.dp, vertical = 16.dp),
            border = BorderStroke(1.dp, DarkBlueDefault)
        ) {
            Text(
                text = stringResource(R.string.pesan_button),
                fontSize = 16.sp,
                fontFamily = Poppins
            )
        }
    }
}


private suspend fun signIn(
    context: Context,
    dataStore: SettingsDataStore
) {
    val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
        .setFilterByAuthorizedAccounts(false)
        .setServerClientId(BuildConfig.API_KEY)
        .build()

    val request: GetCredentialRequest = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build()

    try {
        val credentialManager = CredentialManager.create(context)
        val result = credentialManager.getCredential(context, request)
        handleSignIn(result, dataStore)
    } catch (e: GetCredentialException) {
        Log.e("SIGN-IN", "Error: ${e.errorMessage}")
    }
}

private suspend fun handleSignIn(
    result: GetCredentialResponse,
    dataStore: SettingsDataStore
) {
    val credential = result.credential
    if (credential is CustomCredential &&
        credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
    ) {
        try {
            val googleId = GoogleIdTokenCredential.createFrom(credential.data)
            val nama = googleId.displayName ?: ""
            val email = googleId.id
            val photoUrl = googleId.profilePictureUri.toString()
            dataStore.saveData(User(nama, email, photoUrl))

        } catch (e: GoogleIdTokenParsingException) {
            Log.e("SIGN-IN", "Error: ${e.message}")
        }
    } else {
        Log.e("SIGN-IN", "Error: unrecognized custom credential type")
    }
}

private suspend fun signOut(context: Context, dataStore: SettingsDataStore) {
    try {
        val credentialManager = CredentialManager.create(context)
        credentialManager.clearCredentialState(
            ClearCredentialStateRequest()
        )
        dataStore.saveData(User())
    } catch (e: ClearCredentialException) {
        Log.e("SIGN-IN", "Error: ${e.errorMessage}")
    }
}

@Preview
@Composable
private fun Prev() {
    MainScreen(navHostController = rememberNavController())
}