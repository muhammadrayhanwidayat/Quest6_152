package com.example.project07.view.uicontroller


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project07.view.FormIsian
import com.example.project07.view.TampilData
import com.example.project07.viewmodel.SiswaViewModel

enum class Navigasi {
    FormIsian,
    DetailSiswa
}

@Composable
fun DataApp(
    navController: NavHostController = rememberNavController(),
    siswaViewModel: SiswaViewModel = viewModel()
) {
    Scaffold { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Navigasi.FormIsian.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            // ⬅️ Halaman Form
            composable(Navigasi.FormIsian.name) {
                FormIsian(
                    pilihanJK = listOf("Laki-laki", "Perempuan"),
                    onSubmitButtonClicked = { listData ->
                        siswaViewModel.setSiswa(listData)
                        navController.navigate(Navigasi.DetailSiswa.name)
                    }
                )
            }

            // ⬅️ Halaman Detail
            composable(Navigasi.DetailSiswa.name) {

                val state = siswaViewModel.statusUI.collectAsState()

                TampilData(
                    statusUISiswa = state.value,
                    onBackBtnClick = {
                        backToForm(navController)
                    }
                )
            }
        }
    }
}

private fun backToForm(navController: NavHostController) {
    navController.popBackStack(
        route = Navigasi.FormIsian.name,
        inclusive = false
    )
}