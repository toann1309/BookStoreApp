package com.eritlab.jexmon.presentation.dashboard_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.eritlab.jexmon.common.Constrains
import com.eritlab.jexmon.presentation.graphs.auth_graph.AuthScreen
import com.eritlab.jexmon.presentation.graphs.detail_graph.DetailScreen
import com.eritlab.jexmon.presentation.graphs.filter_graph.FilterGameScreen
import com.eritlab.jexmon.presentation.graphs.home_graph.ShopHomeScreen
import com.eritlab.jexmon.presentation.graphs.option_graph.OptionScreen
import com.eritlab.jexmon.presentation.screens.home_screen.BottomNavItem
import com.eritlab.jexmon.presentation.ui.theme.PrimaryColor
import com.eritlab.jexmon.presentation.ui.theme.TextColor

@Composable
fun NavigationBar(
    navController: NavController,
    isVisible: (Boolean, Boolean) -> Unit,
) {
    val navItemList = listOf(
        BottomNavItem.HomeNav,
        BottomNavItem.FavouriteNav,
        BottomNavItem.ProfileNav,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var bottomNavVisibility by remember { mutableStateOf<Boolean>(true) }

    if (bottomNavVisibility) {
        BottomNavigation(
            backgroundColor = Color.White,
            modifier = Modifier
                .background(color = Color.White)
                .shadow(
                    shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
                    elevation = 12.dp,
                )
                .clip(RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp))


        ) {
            navItemList.forEach { screen ->
                BottomNavigationItem(
                    selected = navBackStackEntry?.destination?.route == screen.route,
                    icon = {
                        Icon(
                            painter = painterResource(id = screen.icon),
                            contentDescription = null,
                            tint = if (navBackStackEntry?.destination?.route == screen.route) MaterialTheme.colors.PrimaryColor else LocalContentColor.current,
                        )
                    },
                    //  label = { Text(text = screen.tittle) },
                    onClick = {
                        navController.navigate(screen.route)
                    },
                    unselectedContentColor = MaterialTheme.colors.TextColor,
                )
            }
        }
    }

    //hide topBar
    when (navBackStackEntry?.destination?.route) {
        ShopHomeScreen.DashboardScreen.route -> {
            bottomNavVisibility = true
            isVisible(true, false)
        }
        ShopHomeScreen.FavouriteScreen.route->{
            bottomNavVisibility = true
            isVisible(true,true)
        }
        DetailScreen.ProductDetailScreen.route + "/{${Constrains.PRODUCT_ID_PARAM}}" -> {

            bottomNavVisibility = false
            isVisible(false,false)
        }
        DetailScreen.CartScreen.route -> {
            bottomNavVisibility = false
            isVisible(false, false)
        }
        DetailScreen.NotificationScreen.route -> {
            bottomNavVisibility = false
            isVisible(false,false)
        }
        OptionScreen.SearchScreen.route + "/{${Constrains.SEARCH_BOOK}}"-> {
            bottomNavVisibility = true
            isVisible(true,true)
        }
        FilterGameScreen.FilterScreen.route + "/{${Constrains.PRICE}}/{${Constrains.PUBLISHER}}" ->{
            bottomNavVisibility = true
            isVisible(true,true)
        }
        DetailScreen.CheckOut.route ->{
            bottomNavVisibility = false
            isVisible(false, false)
        }
        DetailScreen.CheckoutBuyNow.route ->{
            bottomNavVisibility = false
            isVisible(false, false)
        }
        DetailScreen.DetailCheckOut.route->{
            bottomNavVisibility = false
            isVisible(false, false)
        }
        DetailScreen.DetailCheckoutBuyNow.route->{
            bottomNavVisibility = false
            isVisible(false, false)
        }
        AuthScreen.SignInScreen.route->{
            bottomNavVisibility = false
            isVisible(false, false)
        }
        AuthScreen.ResetPasswordScreen.route->{
            bottomNavVisibility = false
            isVisible(false, false)
        }
        AuthScreen.OTPScreen.route->{
            bottomNavVisibility = false
            isVisible(false, false)
        }
        AuthScreen.SignUpScreen.route->{
            bottomNavVisibility = false
            isVisible(false, false)
        }
        AuthScreen.ForgetPasswordScreen.route->{
            bottomNavVisibility = false
            isVisible(false, false)
        }
        AuthScreen.SignInSuccess.route->{
            bottomNavVisibility = false
            isVisible(false, false)
        }
        else -> {
            bottomNavVisibility = true
            isVisible(false,false)
        }
    }
}