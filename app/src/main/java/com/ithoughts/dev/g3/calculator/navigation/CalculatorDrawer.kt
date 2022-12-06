@file:OptIn(ExperimentalMaterial3Api::class)

package com.ithoughts.dev.g3.calculator.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ithoughts.dev.g3.calculator.R.drawable
import com.ithoughts.dev.g3.calculator.logic.Category

@Composable
fun CalculatorDrawer(selected: Category, onCategoryClick: (Category) -> Unit) {
    val categories = remember { Category.values() }
    ModalDrawerSheet(modifier = Modifier.padding(end = 50.dp)) {
        LazyColumn(contentPadding = PaddingValues(12.dp, 12.dp)) {
            item { AppTitle() }
            items(Category.calculators()) {
                NavItem(selected == it, it, onCategoryClick)
            }
            item {
                Divider(modifier = Modifier.padding(vertical = 14.dp))
            }
            item {
                Text(
                    "Converters",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 16.dp, start = 10.dp, end = 10.dp)
                )
            }
            items(categories) {
                NavItem(selected == it, it, onCategoryClick)
            }
        }
    }
}

@Composable
fun NavItem(selected: Boolean, category: Category, onClick: (Category) -> Unit) {
    NavigationDrawerItem(
        selected = selected,
        modifier = Modifier.fillMaxWidth(),
        onClick = { onClick(category) },
        label = { Text(category.name, style = MaterialTheme.typography.labelLarge) },
        shape = MaterialTheme.shapes.extraLarge,
        icon = {
            Icon(
                painter = painterResource(category.res ?: drawable.no_icon),
                tint = if (selected) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
                contentDescription = category.name,
                modifier = Modifier.size(24.dp)
            )
        }
    )
}

@Composable
fun AppTitle() {
    Text(
        text = "Calculator+",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(16.dp),
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold
    )
}