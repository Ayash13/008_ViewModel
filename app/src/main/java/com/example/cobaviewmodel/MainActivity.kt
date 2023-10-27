package com.example.cobaviewmodel

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cobaviewmodel.dataFolder.DataClass
import com.example.cobaviewmodel.dataFolder.DataSource.jenis
import com.example.cobaviewmodel.dataFolder.DataSource.status
import com.example.cobaviewmodel.ui.theme.CobaViewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CobaViewModelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TampilLayout()
                }
            }
        }
    }
}

@Composable
fun TampilLayout(modifier: Modifier = Modifier){

    Card (
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ){
        Column (
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(20.dp)
        ){
            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Image(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = "Arrow Back")
                Text("Register", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(0.dp))
            }
            Text("Create Your Account", fontWeight = FontWeight.Bold, fontSize = 30.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            Tampilan()
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tampilan(cobaViewModel: ViewController = viewModel()) {
    var txtUsername by remember { mutableStateOf("") }
    var textTlp by remember { mutableStateOf("") }
    var txtEmail by remember { mutableStateOf("") }
    var txtAlamat by remember { mutableStateOf("") }
    val context = LocalContext.current
    val dataClass: DataClass
    val uiState by cobaViewModel.uiState.collectAsState()
    dataClass = uiState;

    OutlinedTextField(
        value = txtUsername,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(text = "Username")
        },
        onValueChange = {
            txtUsername = it
        }
    )
    OutlinedTextField(
        value = textTlp,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(text = "Telephone")
        },
        onValueChange = { textTlp = it }
    )
    OutlinedTextField(
        value = txtEmail, // New address text field
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(text = "Email")
        },
        onValueChange = { txtEmail = it }
    )
    Text(text = "Jenis Kelamin")
    SelectJK(
        options = jenis.map { id -> context.resources.getString(id) },
        onSelectionChanged = { cobaViewModel.setJenisKelamin(it) }
    )
    Text(text = "Status")
    SelectStatus(
        options = status.map { id -> context.resources.getString(id) },
        onSelectionChanged = { cobaViewModel.setStatus(it) }
    )
    OutlinedTextField(
        value = txtAlamat, // New address text field
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(text = "Alamat")
        },
        onValueChange = { txtAlamat = it }
    )
    Button(
        modifier = Modifier.fillMaxWidth().height(40.dp),
        onClick = { cobaViewModel.insertData(txtUsername, textTlp, txtEmail, dataClass.jk, dataClass.status, txtAlamat) }
    ) {
        Text(text = stringResource(R.string.submit), fontSize = 16.sp)
    }
    TextResult(
        jenisnya = cobaViewModel.email_usr,
        statusnya = cobaViewModel.status_usr,
        alamatnya = cobaViewModel.alamat_usr,
        emailnya = cobaViewModel.jenisKlmn,
    )
}

@Composable
fun TextResult( jenisnya: String, emailnya: String, statusnya : String, alamatnya : String) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            text = "Jenis kelamin : " + jenisnya,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
        Text(
            text = "Status : " + statusnya,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )

        Text(
            text = "Alamat : " + alamatnya,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )

        Text(
            text = "Email : " + emailnya,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun SelectJK(
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {}
) {
    var selectedValue by rememberSaveable { mutableStateOf("") }
    Row (
        modifier = Modifier
            .padding(10.dp)
    ){
        options.forEach{
                item ->
            Row(
                modifier = Modifier
                    .selectable(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                )
                Text(item)
            }
        }
    }
}

@Composable
fun SelectStatus(
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {}
) {
    var selectedValue by rememberSaveable { mutableStateOf("") }
    Row (
        modifier = Modifier
            .padding(10.dp)
    ){
        options.forEach{
                item ->
            Row(
                modifier = Modifier
                    .selectable(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                )
                Text(item)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CobaViewModelTheme {
        TampilLayout()
    }
}