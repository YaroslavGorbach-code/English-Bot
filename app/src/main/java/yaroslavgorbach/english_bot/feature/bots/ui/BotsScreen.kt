package yaroslavgorbach.english_bot.feature.bots.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.statusBarsPadding
import yaroslavgorbach.english_bot.R
import yaroslavgorbach.english_bot.data.common.model.BotName
import yaroslavgorbach.english_bot.feature.bots.model.BotsAction
import yaroslavgorbach.english_bot.feature.bots.model.BotsState
import yaroslavgorbach.english_bot.feature.bots.presentation.BotsViewModel
import yaroslavgorbach.english_bot.feature.common.ui.Subtitle
import yaroslavgorbach.english_bot.feature.common.ui.Title

@Composable
fun BotsScreen(onBack: () -> Unit, navigateToChat: (name: BotName) -> Unit) {
    BotsScreen(
        viewModel = hiltViewModel(),
        onBack = onBack,
        navigateToChat = navigateToChat
    )
}

@Composable
internal fun BotsScreen(
    viewModel: BotsViewModel,
    onBack: () -> Unit,
    navigateToChat: (name: BotName) -> Unit
) {
    val viewState = viewModel.state.collectAsState()
    BotsScreen(
        state = viewState.value,
        actioner = viewModel::submitAction,
        onBack = onBack,
        clearMessage = viewModel::clearMessage,
        navigateToChat = navigateToChat
    )
}

@Composable
internal fun BotsScreen(
    state: BotsState,
    actioner: (BotsAction) -> Unit,
    onBack: () -> Unit,
    clearMessage: (id: Long) -> Unit,
    navigateToChat: (name: BotName) -> Unit
) {
    state.message?.let { message -> }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Title(
            text = stringResource(id = R.string.available_bots),
            modifier = Modifier.fillMaxWidth()
        )
        Subtitle(
            text = stringResource(id = R.string.bots_subtitle),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        LazyColumn(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxHeight()
        ) {
            items(state.bots) { item ->
                BotUi(bot = item) {
                    navigateToChat(item.name)
                }
            }
        }
    }
}

