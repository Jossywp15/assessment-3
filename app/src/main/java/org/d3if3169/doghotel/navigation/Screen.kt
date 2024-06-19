package org.d3if3169.doghotel.navigation

const val KEY_NOTES_ID = "idNotes"

sealed class Screen (val route: String) {
    data object Landing: Screen("landingScreen")
    data object Main: Screen("mainScreen")
    data object About: Screen("aboutScreen")
    data object Form: Screen("formScreen")
    data object Rules: Screen("rulesScreen")
    data object Notes: Screen("notesScreen")
    data object DogPhotos: Screen("dogPhotosScreen")
    data object AddNotes: Screen("detailNotesScreen")
    data object EditNotes: Screen("detailNotesScreen/{$KEY_NOTES_ID}") {
        fun withId(id: Int) = "detailNotesScreen/$id"
    }
}