package yaroslavgorbach.english_bot.feature.chat.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import yaroslavgorbach.english_bot.R
import yaroslavgorbach.english_bot.domain.chat.model.MessageType
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
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
                .weight(1f)
        ) {
            items(state.messages) { message ->
                when (message.type) {
                    MessageType.BOT -> {
                        MessageFromBotUi(message = message, state.botName)
                    }
                    MessageType.ME -> {
                        MessageFromMeUi(message = message)
                    }
                }
            }
        }
        InputField(state = state, actioner = actioner)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InputField(
    modifier: Modifier = Modifier,
    state: ChatState,
    actioner: (ChatActions) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            TextField(
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(text = "Type your answer...")
                },
                onValueChange = { text -> actioner(ChatActions.TypeText(text)) },
                value = state.typedValue,
                modifier = Modifier
                    .align(CenterVertically)
                    .weight(1f)
            )
            if (state.typedValue.isNotEmpty()) {
                Image(
                    ImageVector.vectorResource(id = R.drawable.ic_sent_message),
                    contentDescription = null,
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(end = 8.dp)
                        .size(35.dp)
                )
            }
        }
    }
}