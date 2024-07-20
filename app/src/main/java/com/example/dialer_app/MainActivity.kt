package com.example.dialer_app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dialer_app.data.DataSource
import com.example.dialer_app.model.Contact
import com.example.dialer_app.ui.theme.Dialer_AppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Dialer_AppTheme {
                ScaffoldWrapper()
            }
        }
    }
}

@Composable
fun ContactGrid(contacts: List<Contact>) {
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
        items(contacts) {
            ContactCard(contact = it)
        }
    }
}


@Composable
fun ContactCard(contact: Contact, modifier: Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(165.dp)
            .height(220.dp)
    ) {
        val name = stringResource(id = contact.name)
        val number = stringResource(id = contact.number)
        val context = LocalContext.current
        Image(
            painter = painterResource(id = contact.picture),
            contentDescription = stringResource(id = R.string.auntie)
        )
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .wrapContentHeight(),
            shape = RectangleShape,

            ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(6.dp)
                )
                    SelectionContainer {
                        Text(
                            text = number,
                            style = MaterialTheme.typography.titleSmall,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .alpha(0.6f)
                                .clickable {
                                    val u = Uri.parse("tel:$number")

                                    val i = Intent(Intent.ACTION_DIAL, u)
                                    try {
                                        context.startActivity(i)
                                    } catch (s: SecurityException) {

                                        Toast
                                            .makeText(
                                                context,
                                                "An error occurred",
                                                Toast.LENGTH_LONG
                                            )
                                            .show()
                                    }
                                },

                            )
                    }

            }

        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWrapper() {
    Scaffold(
        topBar = {

            val number = stringResource(id = R.string.homeNumber)
            val context = LocalContext.current
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),

                title = {
                    Text(stringResource(id = R.string.app_name))


                }
            )

            Image(
                painter = painterResource(id = R.drawable.ic_home_24),
                contentDescription = stringResource(
                    id = (R.string.photo)
                ),
                modifier = Modifier
                    .offset(350.dp, 60.dp)
                    .size(50.dp)
                    .clickable {

                        val u = Uri.parse("tel:$number")

                        val i = Intent(Intent.ACTION_DIAL, u)
                        try {
                            context.startActivity(i)
                        } catch (s: SecurityException) {

                            Toast
                                .makeText(context, "An error occurred", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
            )

        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.surfaceContainer,
                contentColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.height(50.dp)
            ) {

            }
        },

        ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            ContactGrid(contacts = DataSource().getContactsData())
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ContactCardPreview() {
    Dialer_AppTheme {
        ScaffoldWrapper()
    }
}