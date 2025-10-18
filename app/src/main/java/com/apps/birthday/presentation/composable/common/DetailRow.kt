package com.apps.birthday.presentation.composable.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.apps.birthday.presentation.semantics.Semantic

@Composable
fun DetailRow(
    icon: ImageVector,
    label: String,
    value: String,
    isMessage: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = if (isMessage) Alignment.Top else Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Semantic.Padding.VAL_8)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
            modifier = Modifier.size(Semantic.Padding.VAL_20).padding(top = if (isMessage) 4.dp else 0.dp)
        )
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            overflow = if (isMessage) TextOverflow.Ellipsis else TextOverflow.Visible,
            maxLines = if (isMessage) 3 else Int.MAX_VALUE,
            modifier = Modifier.weight(1f)
        )
    }
}