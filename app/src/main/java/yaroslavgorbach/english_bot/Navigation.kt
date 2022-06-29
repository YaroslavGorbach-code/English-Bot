package yaroslavgorbach.english_bot

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import kotlinx.coroutines.InternalCoroutinesApi
import yaroslavgorbach.english_bot.data.common.model.BotName
import yaroslavgorbach.english_bot.feature.bots.ui.BotsScreen

const val BOT_NAME_ARG = "PUZZLE_NAME_ARG"

sealed class Screen(val route: String) {
    object Bots : Screen("Bots")
}

private sealed class LeafScreen(
    private val route: String,
) {
    fun createRoute(root: Screen) = "${root.route}/$route"

    object Bots : LeafScreen("Bots")

    object Chat : LeafScreen("Chat/{${BOT_NAME_ARG}}") {
        fun createRoute(root: Screen, botName: BotName): String {
            return "${root.route}/Chat/$botName"
        }
    }
}

@Composable
internal fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Bots.route,
        modifier = modifier,
    ) {
        addBotsTopLevel(navController)
    }
}

private fun NavGraphBuilder.addBotsTopLevel(
    navController: NavController,
) {
    navigation(
        route = Screen.Bots.route,
        startDestination = LeafScreen.Bots.createRoute(Screen.Bots),
    ) {
        addBots(navController, Screen.Bots)
        addChat(navController, Screen.Bots)
    }
}

private fun NavGraphBuilder.addBots(
    navController: NavController,
    root: Screen,
) {
    composable(LeafScreen.Bots.createRoute(root)) {
        BotsScreen(onBack = {}, navigateToChat = {})
    }
}


private fun NavGraphBuilder.addChat(
    navController: NavController,
    root: Screen,
) {
    composable(
        LeafScreen.Chat.createRoute(root), arguments = listOf(
            navArgument(BOT_NAME_ARG) {
                type = NavType.EnumType(BotName::class.java)
            })
    ) { backStackEntry ->
//        PuzzleUi {
//            navController.popBackStack()
//        }
    }
}

