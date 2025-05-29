package com.tyro.calculatorapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tyro.calculatorapp.R
import com.tyro.calculatorapp.view_models.CalculatorViewModel


@Composable
fun Calculator(viewModel: CalculatorViewModel = viewModel()) {

    val input = remember { mutableStateOf("") }
    val calculation = remember { mutableStateOf("") }

    val buttons = listOf(
        listOf("C","DEL","%","÷"),
        listOf("7","8","9","×"),
        listOf("4","5","6","-"),
        listOf("1","2","3","+")
    )


    Column(modifier = Modifier.fillMaxSize().padding(16.dp).statusBarsPadding().navigationBarsPadding(), verticalArrangement = Arrangement.spacedBy(10.dp)) {

        Column(modifier = Modifier.fillMaxWidth().weight(1f), horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(text = viewModel.input.value, fontSize = 50.sp, fontWeight = FontWeight.Light, modifier = Modifier.padding(horizontal = 15.dp))
            Text(text = viewModel.result.value, fontSize = 100.sp, fontWeight = FontWeight.Light)
        }

        Column(modifier = Modifier.fillMaxWidth().padding(bottom = 18.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)) {

            buttons.forEach{
                row ->
                Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    row.forEach { label ->

                        Button(onClick = {viewModel.onButtonClick(label)}, modifier = Modifier.weight(1f).aspectRatio(1f),

                            colors = if(label in listOf("÷","×","+","-")){
                                ButtonDefaults.buttonColors(colorResource(id = R.color.orange))
                            }else if(label in listOf("C","DEL","%")){
                                ButtonDefaults.buttonColors(containerColor = Color.Gray)
                            }else{
                                ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                            }
                        ) {
                            Text(text=label, fontSize = if(label == "DEL"){20.sp}else{38.sp}, fontWeight = FontWeight.Light)
                        }

                    }

                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)){
                Button(onClick = {viewModel.onButtonClick("0")}, modifier = Modifier.weight(2f).aspectRatio(2f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                    ) {
                    Text(text="0", fontSize = 38.sp, fontWeight = FontWeight.Light)
                }
                Button(onClick = {viewModel.onButtonClick(".")}, modifier = Modifier.weight(1f).aspectRatio(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                    ) {
                    Text(text=".", fontSize = 38.sp, fontWeight = FontWeight.Light)
                }
                Button(onClick = {viewModel.onButtonClick("=")}, modifier = Modifier.weight(1f).aspectRatio(1f),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.orange))
                    ) {
                    Text(text="=", fontSize = 38.sp, fontWeight = FontWeight.Light)
                }
            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    Calculator()
}
