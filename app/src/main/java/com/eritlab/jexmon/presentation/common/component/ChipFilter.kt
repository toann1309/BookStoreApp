package com.eritlab.jexmon.presentation.common.component

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.eritlab.jexmon.presentation.ui.theme.PrimaryColor

data class ChipsModel(
    val name: String,
    val order:Int,
    val subList: List<String>? = null,
    val textExpanded: String? = null,
    val leadingIcon: ImageVector? = null,
    val trailingIcon: ImageVector? = null,
    val selectedIcon: ImageVector? = null
)
//@SuppressLint("UnrememberedMutableState")
//@OptIn(ExperimentalMaterialApi::class)
////@SuppressLint("UnrememberedMutableState")
//@Composable
//fun TutorialFilterChip() {
//    val filterList = listOf(
//        ChipsModel(
//            name = "platform",
//            subList = listOf("browser", "mobile", "console"),
//            trailingIcon = Icons.Default.ArrowDropDown,
//            selectedIcon = Icons.Default.Check,
//            order = 0,
//        ),
//        ChipsModel(
//            name = "category",
//            subList = listOf("mmorpg", "shooter","ARPG"),
//            trailingIcon = Icons.Default.ArrowDropDown,
//            selectedIcon = Icons.Default.Check,
//            order = 1,
//        ),
//        ChipsModel(
//            name = "sort-by",
//            subList = listOf("release-date", "date-ago"),
//            trailingIcon = Icons.Default.ArrowDropDown,
//            selectedIcon = Icons.Default.Check,
//            order = 2,
//        ),
//    )
//
//    val selectedItems = mutableStateListOf<String>()
////    val selectedItems = mutableStateListOf<ChipsModel>()
//    var isSelected by remember { mutableStateOf(false) }
//
//    Column {
//        for (item in filterList){
//            isSelected = selectedItems.contains(item.name)
//            Spacer(modifier = Modifier.padding(5.dp))
//            if (item.subList != null) {
//                ChipWithSubItems(chipLabel = item.name, chipItems = item.subList)
//            } else {
//                FilterChip(
//                    selected = isSelected,
//                    onClick = {
//                        when (selectedItems.contains(item.name)) {
//                            true -> selectedItems.remove(item.name)
//                            false -> selectedItems.add(item.name)
//                        }
////                        selectedItems.sortBy {it.order}
//                    },
//                    content = {Text(text = item.name)},
//                    leadingIcon = {
//                        val isCheckIcon = item.leadingIcon == Icons.Default.Check
//                        if (item.leadingIcon != null && isCheckIcon && isSelected) {
//                            Icon(item.leadingIcon, contentDescription = item.name)
//                        }
//                        if (item.leadingIcon != null && !isCheckIcon) {
//                            Icon(item.leadingIcon, contentDescription = item.name)
//                        }
//                    },
//                    trailingIcon = {
//                        if (item.trailingIcon != null && isSelected)
//                            Icon(item.trailingIcon, contentDescription = item.name)
//                    },
//                )
//            }
//        }
//    }
//}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipWithSubItems(chipLabel: String, chipItems: List<String>, arrayList: SnapshotStateList<String>, order: Int) {
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
                        if(order == 0){
                            arrayList[order] = filterName
                            Log.e("0",filterName)
                        }
                        if(order == 1){
                            arrayList[order] = filterName
                            Log.e("1",filterName)
                        }
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

        }
    }
}
