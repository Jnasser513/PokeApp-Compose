@file:OptIn(ExperimentalFoundationApi::class)

package com.jnasser.pokeapp.features.home.presentation.composables

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jnasser.pokeapp.core.domain.models.pokemon_list.Pokemon
import com.jnasser.pokeapp.core.presentation.designsystem.PokeAppTheme
import com.jnasser.pokeapp.core.presentation.designsystem.components.CustomCircularProgressIndicator
import com.jnasser.pokeapp.core.presentation.ui.ObserveAsEvents
import com.jnasser.pokeapp.features.home.presentation.PokemonListAction
import com.jnasser.pokeapp.features.home.presentation.PokemonListEvent
import com.jnasser.pokeapp.features.home.presentation.PokemonListViewState
import com.jnasser.pokeapp.features.home.presentation.PokemonListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokemonListScreenRoot(
    viewModel: PokemonListViewModel = koinViewModel(),
    onPokemonClick: (String) -> Unit
) {
    val context = LocalContext.current

    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            is PokemonListEvent.Error -> Toast.makeText(
                context, event.error.asString(context), Toast.LENGTH_SHORT
            ).show()
        }
    }

    PokemonListScreen(state = viewModel.state, onAction = { action ->
        when (action) {
            is PokemonListAction.OnPokemonClick -> {
                onPokemonClick(action.pokemonName)
            }
        }
        viewModel.onAction(action)
    })
}

@Composable
private fun PokemonListScreen(
    state: PokemonListViewState, onAction: (PokemonListAction) -> Unit
) {
    when {
        state.isLoading -> {
            Loading()
        }
        else -> {
            CharacterList(state = state, onAction = onAction)
        }
    }
}

@Composable
fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        ) {
        CustomCircularProgressIndicator()
    }
}

@Composable
fun CharacterList(
    modifier: Modifier = Modifier,
    state: PokemonListViewState,
    onAction: (PokemonListAction) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyVerticalGrid(
            GridCells.Fixed(2),
            modifier = modifier
                .fillMaxSize()
                .nestedScroll(rememberNestedScrollInteropConnection())
                .padding(horizontal = 40.dp),
            verticalArrangement = Arrangement.spacedBy(35.dp),
            horizontalArrangement = Arrangement.spacedBy(25.dp),
        ) {
            items(state.pokemonList.size) { index ->
                PokemonItem(
                    pokemon = state.pokemonList[index],
                    onClick = {
                        onAction(PokemonListAction.OnPokemonClick(state.pokemonList[index].name))
                        },
                    modifier = Modifier.animateItemPlacement()
                )
            }
        }
    }
}

class PokemonListScreenPreviews {
    @Preview
    @Composable
    private fun PokemonListScreenPreview() {
        PokeAppTheme {
            PokemonListScreen(state = (PokemonListViewState(isLoading = false, pokemonList = listOf(
                Pokemon("Pikachu", "")
            ))), onAction = {})
        }
    }

    @Preview
    @Composable
    private fun LoadingPreview() {
        PokeAppTheme {
            PokemonListScreen(state = (PokemonListViewState()), onAction = {})
        }
    }
}