package com.example.person

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.person.ui.theme.PersonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PersonTheme {
                Screen()
            }
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    fun Screen() {
        val removeId = remember { mutableIntStateOf(0) }
        val addId = remember { mutableIntStateOf(0) }
        val addName = remember { mutableStateOf(" ") }
        val addAge = remember { mutableIntStateOf(0) }
        val personList = remember { mutableStateListOf(
            Person(1, "Иванова Мария Андреева", 32),
            Person(2, "Сергеев Иван Петрович", 28),
            Person(3, "Кузнецова Ольга Николаевна", 52),
            Person(4, "Васильев Андрей Сергеевич", 44),
            Person(5, "Смирнов Дмитрий Алексеевич", 27),
            Person(6, "Петрова Анна Сергеевна", 35),
            Person(7, "Сидоров Михаил Иванович", 48),
            Person(8, "Козлова Елена Владимировна", 29),
            Person(9, "Федоров Алексей Николаевич", 61),
            Person(10, "Романова Татьяна Дмитриевна", 24),
        )}

        Column(Modifier.fillMaxSize().padding(8.dp)) {
            TextField(value = addId.intValue.toString(), onValueChange = { addId.intValue = it.toIntOrNull() ?: 0 },
                label = { Text("Id:") })
            TextField(value = addName.value, onValueChange = { addName.value = it },
                label = { Text("Name:") })
            TextField(value = addAge.intValue.toString(), onValueChange = { addAge.intValue = it.toIntOrNull() ?: 0},
                label = { Text("Age:") })
            Button(onClick = { personList.add(Person(addId.intValue, addName.value, addAge.intValue))}) { Text("Добавить") }
            TextField(value = removeId.intValue.toString(), onValueChange = { removeId.intValue = it.toIntOrNull() ?: 0},
                label = { Text("Id:") })
            Button(onClick = { personList.removeAll { it.id == removeId.intValue } }) { Text("Удалить") }
            Table(personList)
        }
    }

    data class Person(val id: Int, val name: String, val age: Int)

    @Composable
    fun RowScope.TableCell(
        text: String = "test",
        weight: Float = 1.0f
    ) {
        Text(
            text = text,
            Modifier
                .border(1.dp, Color.Black)
                .weight(weight)
                .padding(6.dp)
                .height(50.dp)
        )
    }

    @Composable
    fun Table(data: List<Person>) {
        val column1Weight = .3f
        val column2Weight = 2f
        val column3Weight = .55f
        LazyColumn(Modifier.fillMaxWidth()) {
            item {
                Row(Modifier.background(Color.LightGray)) {
                    TableCell("Id", column1Weight)
                    TableCell("ФИО", column2Weight)
                    TableCell("Возраст", column3Weight)
                }
            }
            items(data) {
                val (id, name, age) = it
                Row {
                    TableCell(id.toString(), column1Weight)
                    TableCell(name, column2Weight)
                    TableCell(age.toString(), column3Weight)
                }
            }
        }
    }
}