package com.jnasser.pokeapp.features.detail.presentation.composables

import android.widget.Toast
import com.jnasser.pokeapp.core.presentation.designsystem.components.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jnasser.pokeapp.R
import com.jnasser.pokeapp.core.domain.models.pokemon_detail.PokemonDetail
import com.jnasser.pokeapp.core.domain.models.pokemon_detail.ability.Ability
import com.jnasser.pokeapp.core.presentation.designsystem.PokeAppTheme
import com.jnasser.pokeapp.core.presentation.designsystem.components.CustomActionButton
import com.jnasser.pokeapp.core.presentation.ui.ObserveAsEvents
import com.jnasser.pokeapp.features.detail.data.models.DataWithName
import com.jnasser.pokeapp.features.detail.presentation.PokemonDetailAction
import com.jnasser.pokeapp.features.detail.presentation.PokemonDetailViewModel
import com.jnasser.pokeapp.features.detail.presentation.PokemonDetailViewState
import com.jnasser.pokeapp.features.home.presentation.PokemonListEvent
import com.jnasser.pokeapp.features.home.presentation.composables.CharacterList
import com.jnasser.pokeapp.features.home.presentation.composables.Loading
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

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
            when (action) {
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
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 60.dp)
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
        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp)
            ) {
                ContentWithValue(
                    value = stringResource(
                        id = R.string.height,
                        state.pokemonDetail.height ?: 0
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))
                ContentWithValue(
                    value = stringResource(
                        id = R.string.base_experience,
                        state.pokemonDetail.height ?: 0
                    )
                )
            }
            Text(
                text = stringResource(id = R.string.id, state.pokemonDetail.id ?: 0),
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = stringResource(id = R.string.name, state.pokemonDetail.name),
                color = MaterialTheme.colorScheme.onSurface
            )

            val stats = state.pokemonDetail.stats.map { it.stat.name }.toList()
            val formattedStats = stats.joinToString(" ") { "\"$it\"" }
            Text(
                text = stringResource(id = R.string.stats, formattedStats),
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = stringResource(id = R.string.weight, state.pokemonDetail.weight ?: 0),
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(30.dp))
            DataValue(
                title = stringResource(id = R.string.abilities),
                data = state.pokemonDetail.abilities.map { DataWithName(it.ability.name) }
            )
            Spacer(modifier = Modifier.height(30.dp))
            DataValue(
                title = stringResource(id = R.string.movements),
                data = state.pokemonDetail.moves.map { DataWithName(it.move.name) }
            )
            if(state.pokemonDetail.heldItems.isNotEmpty()) {
                Spacer(modifier = Modifier.height(30.dp))
                DataValue(
                    title = stringResource(id = R.string.held_items),
                    data = state.pokemonDetail.heldItems.map { DataWithName(it.item.name) }
                )
            }
            Spacer(modifier = Modifier.height(80.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                CustomActionButton(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(id = R.string.return_string)
                ) {
                    onAction(PokemonDetailAction.OnReturn)
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
fun ContentWithValue(value: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp)
    ) {
        Text(
            text = value,
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun DataValue(title: String, data: List<DataWithName>) {
    Text(text = title, style = MaterialTheme.typography.headlineMedium)
    LazyRow(
        modifier = Modifier
            .padding(bottom = 15.dp)
    ) {
        items(data.size) { index ->
            ContentWithValue(value = data[index].value, modifier = Modifier.padding(end = 8.dp))
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