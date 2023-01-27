package com.rahil.acronymapp


import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.coroutines.flow.update


class MainActivity : AppCompatActivity() {
    private val viewModel = AcronymViewModel()

    @Composable
    fun SimpleAlertDialog() {
        var openDialog = viewModel.showDialog
        var show = true
        if(openDialog.collectAsState().value && show) {
            AlertDialog(
                onDismissRequest = {
                    viewModel.showDialog.value = false
                },
                title = {
                    Text(text = "Error in search")
                },
                text = {
                    Text(text = viewModel.errorMessage)
                },
                buttons = {
                    Row(
                        modifier = Modifier.padding(all = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {viewModel.showDialog.value = false}
                        ) {
                            Text("Dismiss")
                        }
                    }
                }

            )
        }

    }

    @Composable @Preview
    fun LongFormListContent() {
        val data by viewModel.data.observeAsState()

        Column {
            Row{
                var text by remember { mutableStateOf(TextFieldValue("")) }
                TextField(
                    value = text,
                    onValueChange = { newText ->
                        run {
                            text = newText

                        }
                    },
                    label = { Text("Enter acronym:") },
                    modifier = Modifier
                        .focusTarget()
                )
                Button(onClick = {
                    Log.e("mytag", " Got text "+ text.text)
                    viewModel.fetchData(text.text)
                }
                ) {
                    Text(text = "Search")
                }
            }
            SimpleAlertDialog()
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                if (data != null) {
                    Log.e("mytag", " Got Data")
                    items(data!!.toList()) { lf ->
                        LongFormListItem(lf)

                    }
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                LongFormListContent()
            }
        }
    }
}