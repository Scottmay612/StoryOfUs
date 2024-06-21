package com.example.storyofus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.storyofus.ui.theme.StoryOfUsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StoryOfUsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),

                    ) {
                    StoryOfUsLayout()
                }
            }
        }
    }
}

@Composable
fun StoryOfUsLayout() {
    // Remember the count so that the right info is displayed.
    var count by remember { mutableIntStateOf(0)}

    // Remember the image count so that the right image is displayed.
    var imgCount by remember { mutableIntStateOf(0)}

    // Declare the show details boolean.
    var showDetails by remember {mutableStateOf(false)}

    // Display a different picture for each page.
    val imageResource = when (count) {
        0 -> R.drawable.welcome_to_district
        1 -> R.drawable.you_leaving
        2 -> R.drawable.pen_pals
        3 -> R.drawable.im_home
        4 -> R.drawable.engaged
        else -> R.drawable.married
    }


    // Display a different title for each page.
    val textResource = when (count) {
        0 -> R.string.welcome
        1 -> R.string.mission_end
        2 -> R.string.pen_pals
        3 -> R.string.im_home
        4 -> R.string.engaged
        5 -> R.string.married
        else -> R.string.app_name
    }

    // Display a different date for each page.
    val dateString = when(count) {
        0 -> R.string.welcome_date
        1 -> R.string.mission_friends_date
        2 -> R.string.pen_pals_date
        3-> R.string.im_home_date
        4 -> R.string.engaged_date
        5 -> R.string.married_date
        else -> R.string.pen_pals
    }

    // Display different details for each page.
    val detailsString = when(count) {
        0 -> R.string.welcome_details
        1 -> R.string.leaving_details
        2 -> R.string.pen_pals_details
        3 -> R.string.im_home_details
        4 -> R.string.engaged_details
        else -> R.string.married_details
    }

    // Creates the main surface for the app.
    Surface(
        // The surface will fill the whole screen.
        modifier = Modifier
            .fillMaxSize(),

        // The color is set to gray.
        color = colorResource(id = R.color.gray)
    ) {
        // The Box makes it so everything is the same color
        // regardless of the padding.
        Box(modifier = Modifier.padding(10.dp)) {

            // Creates the main column.
            Column(

                modifier = Modifier
                    // Makes it so the page can scroll if necessary.
                    .verticalScroll(rememberScrollState())

                    // Adds white space to top of screen.
                    .padding(top = 30.dp),

                // All items in the column are centered horizontally.
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {


                // Displays the main page title.
                Text(
                    text = "The Story Of Us",
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Cursive
                )

                // Adds space between the title and image.
                Spacer(Modifier.padding(top = 30.dp))

                Box(modifier = Modifier
                    .fillMaxHeight()
                    .clickable { imgCount ++ }) {
                    // Displays image with rounded corners.
                    Image(
                        painter = painterResource(
                            id = imageResource),
                        contentDescription = textResource.toString(),
                        modifier = Modifier.clip(RoundedCornerShape(30.dp))
                    )
                }


                // Adds space between the image and date.
                Spacer(modifier = Modifier
                    .padding(bottom = 30.dp))

                // Displays the date.
                Text(text = "Date: ${stringResource(id = dateString)}",
                    fontSize = 25.sp,
                    fontFamily = FontFamily.Serif
                )

                // Adds space between the date and the subtitle.
                Spacer(modifier = Modifier.padding(10.dp))

                // Displays the subtitle.
                Text(text = stringResource(id = textResource),
                    fontSize = 25.sp,
                    fontFamily = FontFamily.Serif
                )

                // Adds space between the subtitle and the buttons.
                Spacer(modifier = Modifier
                    .padding(bottom = 30.dp))

                // Makes a row for the buttons.
                Row {

                    // Won't show previous button if its the first page.
                    if (count > 0) {

                        Button(
                            // Decreases page count.
                            // Hides the details again.
                            onClick = {
                                count --
                                showDetails = false
                            },
                            // Changes button background color.
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.DarkGray,
                            ),

                            // Adds a rounded edge to the button.
                            shape = RoundedCornerShape(25)

                        ) {
                            Text(text = "Previous")
                        }

                        // Adds padding between buttons.
                        Spacer(modifier = Modifier.padding(15.dp))
                    }
                    Button(
                        onClick = {
                            showDetails = !showDetails
                        },
                        shape = RoundedCornerShape(25),
                    ) {
                        Text(text = "Details")
                    }
                    if (count < 5) {

                        // Adds padding between buttons.
                        Spacer(modifier = Modifier.padding(15.dp))
                        Button(
                            onClick = {
                                count ++ // Increases page count.
                                showDetails = false // Hides details again.
                            },

                            // Changes the button background color.
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.DarkGray,
                            ),

                            // Makes the buttons have rounded edges.
                            shape = RoundedCornerShape(25),
                        ) {
                            Text(text = "Next")
                        }
                    }
                }
                // Only shows the details if the detail button is clicked.
                if (showDetails) {
                    Text(
                        text = stringResource(id = detailsString),
                        fontFamily = FontFamily.Serif
                    )
                }
            }
        }
    }
}

// Preview function.
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StoryOfUsTheme {
        StoryOfUsLayout()
    }
}