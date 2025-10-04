package com.apps.birthday.presentation.composable

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.apps.birthday.presentation.semantics.Semantic
import com.apps.birthday.presentation.transformer.DateTransformer

@Composable
fun BirthdayTextField(
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String? = null,
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    shouldAddTransformer: Boolean = false
) {

    val textFieldShape = RoundedCornerShape(Semantic.Padding.VAL_12)

    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        modifier = Modifier,
        singleLine = singleLine,
        visualTransformation = if(shouldAddTransformer) DateTransformer() else VisualTransformation.None,
        placeholder = { placeholder?.let { Text(text = it) } },
        shape = textFieldShape,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Blue.copy(alpha = 0.8f),
            unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            cursorColor = Color.Blue,
            unfocusedPlaceholderColor = Color.Gray.copy(alpha = 0.5f),
            focusedPlaceholderColor = Color.Gray.copy(alpha = 0.5f),
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        textStyle = MaterialTheme.typography.bodyLarge
    )
}