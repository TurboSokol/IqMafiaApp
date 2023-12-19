package com.turbosokol.iqmafiaapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.turbosokol.iqmafiaapp.theme.Dimensions

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

@Composable
fun IQPlayerNameRow(
    modifier: Modifier,
    slot: Int,
    textName: String,
    isInputEnabled: Boolean,
    colorSlot: Color = MaterialTheme.colorScheme.inversePrimary.copy(alpha = 0.65f),
    colorName: Color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
    onSlotClick: (() -> Unit?)? = null,
    onTextChanged: (String) -> Unit,
    PlayerFromBEList : List<String>
//    viewModel : ReduxViewModel
) {
//    val stateFlow: StateFlow<AppState> = viewModel.store.observeState()
//    val appState by stateFlow.collectAsState(Dispatchers.Main)
//    val playersState: MutableState<PlayersState> = remember { mutableStateOf(appState.getPlayersState()) }
//
//    val allNames = mutableListOf<String>()
//    playersState.value.allProfilesFromBE.forEach {
//            profileUIModel ->   allNames.add(profileUIModel.nickName)
//    }
//
//    var expanded by remember {
//        mutableStateOf(false)
//    }
//
//    var textFieldSize by remember {
//        mutableStateOf(Size.Zero)
//    }
//
    Row(
        modifier = modifier
            .border(0.5.dp, MaterialTheme.colorScheme.outline)
            .background(color = MaterialTheme.colorScheme.onBackground)
            .background(color = colorSlot)
    ) {
        TextButton(
            onClick = {
                if (onSlotClick != null) {
                    onSlotClick()
                }
            },
            modifier = Modifier.align(Alignment.CenterVertically).wrapContentWidth(),
            enabled = onSlotClick != null
        ) {
            Text(
                text = (slot + 1).toString(), textAlign = TextAlign.Center,
                fontSize = Dimensions.TextSize.smedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        val playerName = remember { mutableStateOf(textName) }

//        Row() {
            OutlinedTextField(
                value = playerName.value,
                modifier = Modifier.weight(0.85f)
                    .background(MaterialTheme.colorScheme.onBackground)
                    .background(color = colorName),
                onValueChange = { changedValue: String ->
                    playerName.value = changedValue
                    if (playerName.value == changedValue) {
                        onTextChanged(changedValue)
//                        expanded = true
                    }
                },

                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = if (slot == 9) ImeAction.Done else ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                textStyle = TextStyle(fontSize = Dimensions.TextSize.smedium),
                singleLine = true,
                readOnly = !isInputEnabled,
                shape = MaterialTheme.shapes.large,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                ),

//                trailingIcon = {
//                    IconButton(onClick = { expanded = !expanded }) {
//                        Icon(
//                            modifier = Modifier.size(24.dp),
//                            imageVector = Icons.Rounded.KeyboardArrowDown,
//                            contentDescription = "arrow",
//                            tint = Color.Black
//                        )
//                    }
//                }

            )
        }

    }

//    AnimatedVisibility(visible = expanded) {
//        Card(
//            modifier = Modifier
//                .padding(horizontal = 5.dp)
//                .width(textFieldSize.width.dp),
//            shape = RoundedCornerShape(10.dp)
//        ) {
//
//            LazyColumn(
//                modifier = Modifier.heightIn(max = 150.dp),
//            ) {
//
//                if (category.isNotEmpty()) {
//                    items(
//                        allNames.filter {
//                            it.lowercase()
//                                .contains(category.lowercase()) || it.lowercase()
//                                .contains("others")
//                        }
//                            .sorted()
//                    ) {
//                        ItemsCategory(title = it) { title ->
//                            category = title
//                            expanded = false
//                        }
//                    }
//                } else {
//                    items(
//                        allNames.sorted()
//                    ) {
//                        ItemsCategory(title = it) { title ->
//                            category = title
//                            expanded = false
//                        }
//                    }
//                }
//
//            }
//        }
//    }
//
//}


