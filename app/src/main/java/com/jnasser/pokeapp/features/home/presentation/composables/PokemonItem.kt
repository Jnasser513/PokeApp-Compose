package com.jnasser.pokeapp.features.home.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jnasser.pokeapp.BuildConfig
import com.jnasser.pokeapp.R
import com.jnasser.pokeapp.core.presentation.designsystem.components.Image
import com.jnasser.pokeapp.core.domain.models.Pokemon
import com.jnasser.pokeapp.core.presentation.designsystem.PokeAppTheme
import com.jnasser.pokeapp.core.presentation.designsystem.White

@Composable
fun PokemonItem(
    pokemon: Pokemon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val pokemonPosition = extractPokemonIdFromUrl(pokemon.url)
    val imageUrl = "${BuildConfig.IMAGE_BASE_URL}/${pokemonPosition}.png"

    Box(
        modifier = Modifier.border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = modifier
                .background(Color.Transparent)
                .clickable { onClick() },
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(imageUrl = imageUrl, Modifier.padding(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.onSurface)
                    .padding(12.dp),
            ) {
                Text(text = stringResource(id = R.string.pokemon_number, pokemonPosition), style = MaterialTheme.typography.bodyMedium)
                Text(text = pokemon.name, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

private fun extractPokemonIdFromUrl(url: String): Int {
    val idPattern = "(\\d+)/$".toRegex()
    val matchResult = idPattern.find(url)
    return matchResult?.groupValues?.get(1)?.toInt() ?: 0
}

@Preview
@Composable
private fun PokemonItemPreview() {
    PokeAppTheme {
        PokemonItem(
            pokemon = Pokemon(
                "Pikachu",
                "https://pokeapi.co/api/v2/pokemon/1/"),
            onClick = {  }
        )
    }
}