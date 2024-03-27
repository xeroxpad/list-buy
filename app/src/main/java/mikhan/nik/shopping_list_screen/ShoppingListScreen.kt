package mikhan.nik.shopping_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import mikhan.nik.dialog.MainDialog
import mikhan.nik.ui.theme.EmptyText
import mikhan.nik.ui.theme.PurpleGrey80
import mikhan.nik.ui.theme.WhiteBlue
import mikhan.nik.utils.UiEvent

@Composable
fun ShoppingListScreen(
    viewModel: ShoppingListViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val itemsList = viewModel.list.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{uiEvent ->
            when(uiEvent) {
                is UiEvent.Navigate -> {
                    onNavigate(uiEvent.route)
                }
                else -> {}
            }
        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        items(itemsList.value) { item ->
            UIShoppingListItem(item) { event ->
                viewModel.onEvent(event)
            }
        }
    }
    MainDialog(viewModel)
    if (itemsList.value.isEmpty()) {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(),
            text = "Пусто",
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            color = EmptyText,
        )
    }
}