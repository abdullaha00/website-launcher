package com.example.app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.app.ui.theme.AppTheme
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.ui.Alignment
import androidx.core.net.toUri
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults

class MainActivity : ComponentActivity() {

    private val viewModel: WebsiteViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppTheme() {
                Scaffold(

                    bottomBar = {
                        BottomAppBar(actions = {
                            IconButton(onClick = { /* TODO: Search */ }) {
                                Icon(
                                    Icons.Filled.Search, contentDescription = "Search"
                                )
                            }
                            IconButton(onClick = { /* TODO: Add */ }) {
                                Icon(
                                    Icons.Filled.Add,
                                    contentDescription = "Add Website",
                                )
                            }
                            IconButton(onClick = { /* TODO: Refresh */ }) {
                                Icon(
                                    Icons.Filled.Refresh,
                                    contentDescription = "Refresh",
                                )
                            }
                            IconButton(onClick = { /* TODO: Dark mode toggle */ }) {
                                Icon(
                                    Icons.Filled.DarkMode,
                                    contentDescription = "Dark mode toggle",
                                )
                            }

                        }, floatingActionButton = {
                            SortFAB(
                                onClick = { viewModel.toggleSort() },
                            )
                        })
                    },

                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ), title = {
                                Text("Website Launcher")
                            })
                    },

                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    ListWebsites(viewModel, Modifier.padding(innerPadding))

                }
            }
        }
    }
}

@Composable
fun SortFAB(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
    ) {
        Icon(
            Icons.Filled.SortByAlpha, "Sort."
        )
    }
}

@Composable
fun ListItem(website: Website) {
    val context = LocalContext.current
    Log.d("AsyncImage", "Loading ${website.icon}")

    OutlinedCard(

        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {

                val intent = Intent(Intent.ACTION_VIEW, website.url.toUri())
                context.startActivity(intent)

            },

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,

            )

    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            AsyncImage(
                model = website.icon,
                contentDescription = null,
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp)
                    .padding(4.dp)
                    .padding(start = 16.dp)
            )
            Text(

                text = website.name, modifier = Modifier.padding(24.dp)

            )

        }
    }
}

@Composable
fun ListWebsites(viewModel: WebsiteViewModel, modifier: Modifier) {

    val data = viewModel.data.observeAsState(emptyList()).value

    LazyColumn(modifier = modifier) {

        items(data) {

                website ->
            ListItem(website)

        }

    }
}



