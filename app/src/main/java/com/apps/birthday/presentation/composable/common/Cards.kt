package com.apps.birthday.presentation.composable.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.apps.birthday.data.entity.BirthdayEntity
import com.apps.birthday.presentation.semantics.Semantic
import java.util.Locale

@Composable
fun Cards(birthdayEntity: BirthdayEntity) {

    val dobString = "${String.format(Locale.getDefault(),"%02d", birthdayEntity.dayOfMonth)}/${String.format(
        Locale.getDefault(), "%02d", birthdayEntity.monthOfYear)}/${birthdayEntity.year}"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Semantic.Padding.VAL_12, vertical = Semantic.Padding.VAL_8),
        shape = RoundedCornerShape(Semantic.Padding.VAL_12),
        elevation = CardDefaults.cardElevation(defaultElevation = Semantic.Padding.VAL_4),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(Semantic.Padding.VAL_16)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = birthdayEntity.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = birthdayEntity.relation,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            Spacer(modifier = Modifier.height(Semantic.Padding.VAL_12))

            DetailRow(
                icon = Icons.Default.Cake,
                label = "DOB",
                value = dobString
            )
            DetailRow(
                icon = Icons.Default.Phone,
                label = "Contact",
                value = birthdayEntity.contact
            )

            if (birthdayEntity.message.isNotBlank()) {
                Spacer(modifier = Modifier.height(Semantic.Padding.VAL_12))
                Text(
                    text = "Message/Note:",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = birthdayEntity.message,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = Semantic.Padding.VAL_8)
                )
            }
        }
    }
}