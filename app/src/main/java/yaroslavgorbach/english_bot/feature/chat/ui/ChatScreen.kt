package yaroslavgorbach.english_bot.feature.chat.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import yaroslavgorbach.english_bot.R
import yaroslavgorbach.english_bot.data.chat.local.model.MessageType
import yaroslavgorbach.english_bot.feature.chat.model.ChatActions
import yaroslavgorbach.english_bot.feature.chat.model.ChatState
import yaroslavgorbach.english_bot.feature.chat.presentation.ChatViewModel
import yaroslavgorbach.english_bot.feature.common.ui.Subtitle
import yaroslavgorbach.english_bot.feature.common.ui.Toolbar

@Composable
fun ChatScreen(onBack: () -> Unit) {
    ChatScreen(
        viewModel = hiltViewModel(),
        onBack = onBack
    )
}

@Composable
internal fun ChatScreen(
    viewModel: ChatViewModel,
    onBack: () -> Unit
) {
    val viewState = viewModel.state.collectAsState()
    ChatScreen(
        state = viewState.value,
        actioner = viewModel::submitAction,
        onBack = onBack,
        clearMessage = viewModel::clearMessage
    )
}

@Composable
internal fun ChatScreen(
    state: ChatState,
    actioner: (ChatActions) -> Unit,
    onBack: () -> Unit,
    clearMessage: (id: Long) -> Unit
) {
    state.message?.let { message -> }
    Column(modifier = Modifier.fillMaxSize()) {
        Toolbar(title = stringResource(id = state.botName.resId) + " Bot", onBack = { onBack() })
        Subtitle(
            text = stringResource(id = R.string.chat_subtitle),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        LazyColumn(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxHeight()
        ) {
            items(state.messages) { message ->
                when (message.messageType) {
                    MessageType.BOT -> {
                        MessageFromBotUi(message = message)
                    }
                    MessageType.ME -> {

                    }
                }
            }
        }
    }
}