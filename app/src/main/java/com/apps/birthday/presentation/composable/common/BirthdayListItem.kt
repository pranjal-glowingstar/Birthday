package com.apps.birthday.presentation.composable.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.ArrowDropUp
import androidx.compose.material.icons.outlined.DeleteForever
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
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
    Column(
        modifier = Modifier
            .padding(horizontal = Semantic.Padding.VAL_16, vertical = Semantic.Padding.VAL_4)
            .background(
                color = Color.Red.copy(0.1f), shape = RoundedCornerShape(
                    Semantic.Padding.VAL_16
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Semantic.Padding.VAL_4, horizontal = Semantic.Padding.VAL_12),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = birthdayEntity.name, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = AppUtils.getFormattedDate(birthdayEntity),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = "${AppUtils.getCurrentYear() - birthdayEntity.year + 1} years",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "(in ${
                        AppUtils.getDateDifference(
                            birthdayEntity.dayOfMonth,
                            birthdayEntity.monthOfYear
                        )
                    } days)", style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.padding(start = Semantic.Padding.VAL_8))
            Icon(
                imageVector = if (!isExpanded) Icons.Outlined.ArrowDropDown else Icons.Outlined.ArrowDropUp,
                contentDescription = "",
                modifier = Modifier
                    .size(
                        Semantic.Padding.VAL_24
                    )
                    .clickable { isExpanded = !isExpanded })
            Icon(imageVector = Icons.Outlined.Edit, contentDescription = "", modifier = Modifier.size(
                Semantic.Padding.VAL_24).clickable{ onEditClicked(birthdayEntity.id) }, tint = Color.Green)
            Icon(imageVector = Icons.Outlined.DeleteForever, contentDescription = "", modifier = Modifier.size(
                Semantic.Padding.VAL_24).clickable{ onDeleteClicked(birthdayEntity.id) }, tint = Color.Red)
        }
        if (isExpanded) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth().padding(horizontal = Semantic.Padding.VAL_12, vertical = Semantic.Padding.VAL_4)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Semantic.Padding.VAL_8)
                ) {
                    Text(text = "${stringResource(R.string.relation)}: ")
                    Text(text = birthdayEntity.relation)
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Semantic.Padding.VAL_8)
                ) {
                    Text(text = "${stringResource(R.string.contact)}: ")
                    Text(text = birthdayEntity.contact)
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Semantic.Padding.VAL_8)
                ) {
                    Text(text = "${stringResource(R.string.message)}: ")
                    Text(text = birthdayEntity.message)
                }
            }
        }
    }
}