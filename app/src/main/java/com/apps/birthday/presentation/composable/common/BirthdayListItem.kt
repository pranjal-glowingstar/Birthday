package com.apps.birthday.presentation.composable.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.apps.birthday.R
import com.apps.birthday.core.common.AppUtils
import com.apps.birthday.data.entity.BirthdayEntity
import com.apps.birthday.presentation.semantics.Semantic

@Composable
fun BirthdayListItem(
    birthdayEntity: BirthdayEntity,
    onEditClicked: (String) -> Unit,
    onDeleteClicked: (String) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Semantic.Padding.VAL_16, vertical = Semantic.Padding.VAL_4),
        shape = RoundedCornerShape(Semantic.Padding.VAL_16),
        elevation = CardDefaults.cardElevation(defaultElevation = Semantic.Padding.VAL_4),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isExpanded = !isExpanded }
                    .padding(Semantic.Padding.VAL_12),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = birthdayEntity.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = birthdayEntity.relation,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.padding(horizontal = Semantic.Padding.VAL_12)
                ) {
                    val age = AppUtils.getCurrentYear() - birthdayEntity.year + 1
                    Text(
                        text = "$age years",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    val daysLeft = AppUtils.getDateDifference(
                        birthdayEntity.dayOfMonth,
                        birthdayEntity.monthOfYear
                    )
                    Text(
                        text = "(in $daysLeft days)",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Icon(
                    imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = if (isExpanded) "Collapse details" else "Expand details",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(Semantic.Padding.VAL_24)
                )
            }

            if (isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Semantic.Padding.VAL_16)
                        .padding(bottom = Semantic.Padding.VAL_12),
                    verticalArrangement = Arrangement.spacedBy(Semantic.Padding.VAL_4)
                ) {

                    HorizontalDivider(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .padding(horizontal = Semantic.Padding.VAL_8)
                        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
                    )

                    DetailRow(
                        icon = Icons.Filled.Cake,
                        label = stringResource(R.string.dob),
                        value = AppUtils.getFormattedDate(birthdayEntity)
                    )

                    DetailRow(
                        icon = Icons.Filled.Phone,
                        label = stringResource(R.string.contact),
                        value = birthdayEntity.contact
                    )

                    if (birthdayEntity.message.isNotBlank()) {
                        DetailRow(
                            icon = Icons.Filled.Person,
                            label = stringResource(R.string.message),
                            value = birthdayEntity.message,
                            isMessage = true
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = Semantic.Padding.VAL_8),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(onClick = { onEditClicked(birthdayEntity.id) }) {
                            Icon(
                                imageVector = Icons.Outlined.Edit,
                                contentDescription = "Edit",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        IconButton(onClick = { onDeleteClicked(birthdayEntity.id) }) {
                            Icon(
                                imageVector = Icons.Outlined.Delete,
                                contentDescription = "Delete",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }
        }
    }
}