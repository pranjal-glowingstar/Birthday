package com.apps.birthday.presentation.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.apps.birthday.data.entity.BirthdayEntity
import com.apps.birthday.presentation.semantics.Semantic

@Composable
fun Cards(birthdayEntity: BirthdayEntity? = null) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Semantic.Padding.VAL_12, vertical = Semantic.Padding.VAL_8),
        shape = RoundedCornerShape(0.2f)
    ) {
        
    }
}