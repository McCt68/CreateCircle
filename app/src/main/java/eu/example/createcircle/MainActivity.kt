package eu.example.createcircle

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import eu.example.createcircle.ui.theme.CreateCircleTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreateCircleTheme {
                // A surface container using t he 'background' color from the theme
                MyApp()
            }
        }
    }

    // CreateCircle takes two parameters
    // an integer representing the start value of moneyCounter
    // and a function, that takes a integer as parameter, and don't return anything
    @Composable
    fun CreateCircle(moneyCounter: Int = 0, updateMoneyCounter: (Int) -> Unit) {

        Card(
            modifier = Modifier
                .padding(3.dp)
                .size(105.dp)

                // Invoke a clickable event to card via modifier
                // updateMoneyCounter for each click
                .clickable {
                    updateMoneyCounter(moneyCounter + 1)

                    Log.d("Tap", "CreateCircle: ${moneyCounter}")
                },
            shape = CircleShape,
            elevation = 4.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = "Tap")
            }


        }
    }


    @Composable
    fun MyApp() {

        // Hoisting the state in the top Composable function

        // set the var moneyCounter to a state that can change over time ( Mutable)
        // with an initial value to zero
        // using remember so moneyCounter always remembers its last state
        // remember keyword can store a mutable object
        // the moneyCounter will be recomposed (Redrawn) each time its value is changed
        // The code where its value is changed is inside Card modifier .clickAble.
//        var moneyCounter by remember {
//            mutableStateOf(0)
//        }

        // Another way of remember state
        // In this case when we observe the state we have to use myCounter.value
        val myCounter = remember {
            mutableStateOf(0)
        }
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Color(0xFF546E7A)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // set text to the value of myCounter from card clickable
                Text(text = "$${myCounter.value}",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 33.sp,
                    fontWeight = FontWeight.ExtraBold
                ))
                Spacer(modifier = Modifier.height(30.dp))

                // call createCircle with parameters
                // moneyCounter is the state value of myCounter
                // and then set the new value to + 1
                // which will trigger a recomposition
                CreateCircle( moneyCounter = myCounter.value){ newValue ->
                    myCounter.value = newValue
                }

                // Using my counter to make logic
                if (myCounter.value > 10) {
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(text = "Your rich",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 33.sp,
                            fontWeight = FontWeight.ExtraBold
                        ))
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        CreateCircleTheme {
            MyApp()
        }
    }
}