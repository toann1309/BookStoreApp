package com.eritlab.jexmon.presentation.screens.home_screen.component

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.eritlab.jexmon.common.Constrains
import com.eritlab.jexmon.presentation.dashboard_screen.component.AppBar
import com.eritlab.jexmon.presentation.dashboard_screen.component.NavigationBar
import com.eritlab.jexmon.presentation.dashboard_screen.component.FilterButton
import com.eritlab.jexmon.presentation.graphs.detail_graph.DetailScreen
import com.eritlab.jexmon.presentation.graphs.home_graph.HomeNavGraph
import com.eritlab.jexmon.presentation.graphs.option_graph.OptionScreen


@SuppressLint("RememberReturnType")
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    boxScrollState: ScrollState = rememberScrollState(),
) {
    //topBar visibility state
    val topBarVisibilityState = remember {
        mutableStateOf(true)
    }
    val filterBarVisibilityState = remember {
        mutableStateOf(true)
    }
    val ctx = LocalContext.current
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {

            Column() {
                AppBar(
                    navController = navController,
                    isVisible = topBarVisibilityState.value,
                    searchCharSequence = {
                        Log.e("Hell2", it)
                    },
                    onCartIconClick = {
                        navController.navigate(DetailScreen.CartScreen.route)
                    },
                    onSearchClick = {
                        if(it != ""){
                            navController.navigate(OptionScreen.SearchScreen.route +"/${it}")
                        }else{
                            Toast.makeText(ctx,"Cần nhập ký tự", Toast.LENGTH_LONG).show()
                        }
                    }
                )
                FilterButton(navController = navController, isVisible = filterBarVisibilityState.value)
            }

        },
        bottomBar = {
            NavigationBar(navController = navController) { isVisibleTopBar, isVisibleFilter ->
                topBarVisibilityState.value = isVisibleTopBar
                filterBarVisibilityState.value = isVisibleFilter
            }
        }

    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(boxScrollState)
                .fillMaxSize(),
            ) {
            HomeNavGraph(navHostController = navController)
        }
    }
}