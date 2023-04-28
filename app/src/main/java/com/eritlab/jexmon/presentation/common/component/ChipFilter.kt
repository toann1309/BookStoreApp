package com.eritlab.jexmon.presentation.common.component

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.eritlab.jexmon.presentation.ui.theme.PrimaryColor

data class ChipsModel(
    val name: String,
    val subList: List<String>? = null,
    val textExpanded: String? = null,
    val leadingIcon: ImageVector? = null,
    val trailingIcon: ImageVector? = null,
    val selectedIcon: ImageVector? = null
)
@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun TutorialFilterChip() {
    val filterList = listOf(
        ChipsModel(
            name = "Type",
            subList = listOf("Horror", "Romance", "Law"),
            trailingIcon = Icons.Default.ArrowDropDown,
            selectedIcon = Icons.Default.Check
        ),
        ChipsModel(
            name = "Price",
            subList = listOf("High to Low", "Low to High"),
            trailingIcon = Icons.Default.ArrowDropDown,
            selectedIcon = Icons.Default.Check
        ),
    )

    val selectedItems = mutableStateListOf<String>()
    var isSelected by remember { mutableStateOf(false) }

    Row {
        for (item in filterList){
            isSelected = selectedItems.contains(item.name)
            Spacer(modifier = Modifier.padding(5.dp))
            if (item.subList != null) {
                ChipWithSubItems(chipLabel = item.name, chipItems = item.subList)
            } else {
                FilterChip(
                    selected = isSelected,
                    onClick = {
                        when (selectedItems.contains(item.name)) {
                            true -> selectedItems.remove(item.name)
                            false -> selectedItems.add(item.name)
                        }
                    },
                    content = {Text(text = item.name)},
                    leadingIcon = {
                        val isCheckIcon = item.leadingIcon == Icons.Default.Check
                        if (item.leadingIcon != null && isCheckIcon && isSelected) {
                            Icon(item.leadingIcon, contentDescription = item.name)
                        }
                        if (item.leadingIcon != null && !isCheckIcon) {
                            Icon(item.leadingIcon, contentDescription = item.name)
                        }
                    },
                    trailingIcon = {
                        if (item.trailingIcon != null && isSelected)
                            Icon(item.trailingIcon, contentDescription = item.name)
                    },
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipWithSubItems(chipLabel: String, chipItems: List<String>) {
    var isSelected by remember { mutableStateOf(false) }
    var showSubList by remember { mutableStateOf(false) }
    var filterName by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = showSubList,
        onExpandedChange = { showSubList = !showSubList }
    ) {
        FilterChip(
            selected = isSelected,
            onClick = {
                isSelected = true
            },
            content = { Text(text = filterName.ifEmpty { chipLabel })},
            trailingIcon = {
                Icon(
                    modifier = Modifier.rotate(if (showSubList) 180f else 0f),
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "List"
                )
            }
        )
        ExposedDropdownMenu(
            expanded = showSubList,
            onDismissRequest = { showSubList = false },
        ) {
            chipItems.forEach { subListItem ->
                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        filterName = subListItem
                        showSubList = false
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = if (subListItem == filterName || subListItem == chipLabel) {
                            MaterialTheme.colors.PrimaryColor
                        } else { Color.Transparent }
                    )
                ) {
                    Text(text = subListItem, color = Color.Black)
                }
            }
            Log.e("filter", filterName)
        }
    }
}
