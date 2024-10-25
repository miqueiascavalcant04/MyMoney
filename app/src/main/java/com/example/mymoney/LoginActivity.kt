package com.example.mymoney

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.contentDescription
import com.example.mymoney.ui.theme.MyMoneyTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMoneyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen()
                }
            }
        }
    }

    @Composable
    fun LoginScreen() {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var emailError by remember { mutableStateOf("") }
        var passwordError by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Login", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(24.dp))

            // Campo de Email
            TextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = "" // Limpa o erro ao mudar o valor
                },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .semantics { contentDescription = "Campo de email" }, // Acessibilidade
                isError = emailError.isNotEmpty(), // Indica erro se houver
                placeholder = { Text("exemplo@dominio.com") }
            )

            // Mensagem de erro para o email
            if (emailError.isNotEmpty()) {
                Text(
                    text = emailError,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de Senha
            TextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = "" // Limpa o erro ao mudar o valor
                },
                label = { Text("Senha") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .semantics { contentDescription = "Campo de senha" }, // Acessibilidade
                visualTransformation = PasswordVisualTransformation(),
                isError = passwordError.isNotEmpty() // Indica erro se houver
            )

            // Mensagem de erro para a senha
            if (passwordError.isNotEmpty()) {
                Text(
                    text = passwordError,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Estilo do botão
            Button(
                onClick = {
                    // Validação dos campos
                    var isValid = true

                    if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        emailError = "Por favor, insira um email válido."
                        isValid = false
                    }

                    if (password.length < 6) {
                        passwordError = "A senha deve ter pelo menos 6 caracteres."
                        isValid = false
                    }

                    if (isValid) {
                        // Lógica de login aqui
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp) // Ajusta a altura do botão
                    .padding(horizontal = 16.dp)
                    .semantics { contentDescription = "Botão para entrar" }, // Acessibilidade
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary, // Cor de fundo
                    contentColor = Color.White // Cor do texto
                ),
                shape = RoundedCornerShape(8.dp) // Cantos arredondados
            ) {
                Text("Entrar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botão para "Cadastre-se"
            TextButton(
                onClick = {
                    // Navegar para RegisterActivity
                    val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                    startActivity(intent)
                },
                modifier = Modifier.semantics { contentDescription = "Navegar para cadastro" } // Acessibilidade
            ) {
                Text("Cadastre-se")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Botão para "Esqueci minha senha"
            TextButton(
                onClick = {
                    // Lógica para recuperar senha
                },
                modifier = Modifier.semantics { contentDescription = "Recuperar senha" } // Acessibilidade
            ) {
                Text("Esqueci minha senha")
            }
        }
    }
}
