package com.jnasser.pokeapp.features.detail.presentation.composables

import android.widget.Toast
import com.jnasser.pokeapp.core.presentation.designsystem.components.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jnasser.pokeapp.core.domain.models.pokemon_detail.PokemonDetail
import com.jnasser.pokeapp.core.presentation.designsystem.PokeAppTheme
import com.jnasser.pokeapp.core.presentation.ui.ObserveAsEvents
import com.jnasser.pokeapp.features.detail.presentation.PokemonDetailAction
import com.jnasser.pokeapp.features.detail.presentation.PokemonDetailViewModel
import com.jnasser.pokeapp.features.detail.presentation.PokemonDetailViewState
import com.jnasser.pokeapp.features.home.presentation.PokemonListEvent
import com.jnasser.pokeapp.features.home.presentation.composables.CharacterList
import com.jnasser.pokeapp.features.home.presentation.composables.Loading
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokemonDetailScreenRoot(
    viewModel: PokemonDetailViewModel = koinViewModel(),
    onReturn: () -> Unit
) {
    val context = LocalContext.current

    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            is PokemonListEvent.Error -> Toast.makeText(
                context, event.error.asString(context), Toast.LENGTH_SHORT
            ).show()
        }
    }

    PokemonDetailScreen(
        state = viewModel.state,
        onAction = { action ->
            when(action) {
                PokemonDetailAction.OnReturn -> onReturn()
            }
        }
    )
}

@Composable
private fun PokemonDetailScreen(
    state: PokemonDetailViewState,
    onAction: (PokemonDetailAction) -> Unit
) {
    when {
        state.isLoading -> {
            Loading()
        }
        else -> {
            PokemonDetail(state = state, onAction = onAction)
        }
    }
}

@Composable
fun PokemonDetail(state: PokemonDetailViewState, onAction: (PokemonDetailAction) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 40.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onSurface)
                .padding(50.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                imageUrl = state.pokemonDetail.sprites?.image,
                progressColor = MaterialTheme.colorScheme.background
            )
        }

    }
}

@Preview
@Composable
private fun PokemonDetailScreenPreview() {
    PokeAppTheme {
        PokemonDetailScreen(
            state = PokemonDetailViewState(isLoading = false),
            onAction = {}
        )
    }
}