package com.jnasser.pokeapp.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.jnasser.pokeapp.features.detail.presentation.composables.PokemonDetailScreenRoot
import com.jnasser.pokeapp.features.home.presentation.composables.PokemonListScreenRoot
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationRoot(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = GraphRoutes.HOME_ROUTE
    ) {
        homeGraph(navController)
    }
}

private fun NavGraphBuilder.homeGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = NavigationRoutes.POKEMON_LIST_ROUTE,
        route = GraphRoutes.HOME_ROUTE
    ) {
        composable(route = NavigationRoutes.POKEMON_LIST_ROUTE) {
            PokemonListScreenRoot(
                onPokemonClick = {
                    navController.navigate(NavigationRoutes.POKEMON_DETAIL_ROUTE) {
                        popUpTo(NavigationRoutes.POKEMON_LIST_ROUTE) {
                            inclusive = true
                            saveState = true
                        }
                    }
                }
            )
        }
        composable(route = NavigationRoutes.POKEMON_DETAIL_ROUTE) {
            PokemonDetailScreenRoot(
                onReturn = {
                    navController.navigate(NavigationRoutes.POKEMON_LIST_ROUTE) {
                        popUpTo(NavigationRoutes.POKEMON_DETAIL_ROUTE) {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                }
            )
        }
    }
}