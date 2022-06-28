package yaroslavgorbach.english_bot.feature.bots.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import yaroslavgorbach.english_bot.R
import yaroslavgorbach.english_bot.feature.bots.model.BotsAction
import yaroslavgorbach.english_bot.feature.bots.model.BotsState
import yaroslavgorbach.english_bot.feature.bots.presentation.BotsViewModel
import yaroslavgorbach.english_bot.feature.common.ui.Title

@Composable
fun BotsScreen(onBack: () -> Unit) {
    Log.i("dssdsd", "dssd")
    BotsScreen(
        viewModel = hiltViewModel(),
        onBack = onBack
    )
}

@Composable
internal fun BotsScreen(
    viewModel: BotsViewModel,
    onBack: () -> Unit
) {
    val viewState = viewModel.state.collectAsState()
    BotsScreen(
        state = viewState.value,
        actioner = viewModel::submitAction,
        onBack = onBack,
        clearMessage = viewModel::clearMessage
    )

}

@Composable
internal fun BotsScreen(
    state: BotsState,
    actioner: (BotsAction) -> Unit,
    onBack: () -> Unit,
    clearMessage: (id: Long) -> Unit
) {

    state.message?.let { message -> }
    Column(modifier = Modifier.fillMaxSize()) {
        Title(text = stringResource(id = R.string.available_bots))
    }
}

