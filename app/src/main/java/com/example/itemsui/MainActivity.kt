package com.example.itemsui

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.itemsui.ui.theme.ItemsUITheme
import com.example.itemsui.ui.theme.LufgaFont
import kotlin.random.Random
import kotlin.random.nextLong

val items = generateDynamicItems(100)


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ItemsUITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Items(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Items(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
            .clip(RoundedCornerShape(36.dp))
            .background(Color(0xFFF9F8F6))
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .clip(RoundedCornerShape(36.dp))
                .background(
                    Color(0xFFDADADA).copy(0.7f)
                )
                .padding(12.dp)
        ) {
            Text(
                modifier = Modifier.weight(0.1f),
                text = "SKU",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = LufgaFont,
                    fontWeight = FontWeight.Medium
                )
            )

            Text(
                modifier = Modifier.weight(0.3f),
                text = "Item Name",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = LufgaFont,
                    fontWeight = FontWeight.Medium
                )
            )

            Text(
                modifier = Modifier.weight(0.1f),
                text = "Size",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = LufgaFont,
                    fontWeight = FontWeight.Medium
                )
            )

            Text(
                modifier = Modifier.weight(0.1f),
                text = "Pack",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = LufgaFont,
                    fontWeight = FontWeight.Medium
                )
            )

            Text(
                modifier = Modifier.weight(0.1f),
                text = "Cost",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = LufgaFont,
                    fontWeight = FontWeight.Medium
                )
            )

            Text(
                modifier = Modifier.weight(0.1f),
                text = "Price",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = LufgaFont,
                    fontWeight = FontWeight.Medium
                )
            )

            Text(
                modifier = Modifier.weight(0.1f),
                text = "Stock",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = LufgaFont,
                    fontWeight = FontWeight.Medium
                )
            )

            Text(
                modifier = Modifier.weight(0.1f),
                text = "Type",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = LufgaFont,
                    fontWeight = FontWeight.Medium
                )
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            items(items) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    Text(
                        modifier = Modifier.weight(0.01f),
                        text = "${it.sku}",
                        style = TextStyle(
                            fontFamily = LufgaFont,
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp
                        )
                    )
                    Text(
                        modifier = Modifier.weight(0.03f),
                        text = it.itemName,
                        style = TextStyle(
                            fontFamily = LufgaFont,
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp
                        )
                    )
                    Text(
                        modifier = Modifier.weight(0.01f),
                        text = it.size ?: "",
                        style = TextStyle(
                            fontFamily = LufgaFont,
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp
                        )
                    )

                    Box(modifier = Modifier.weight(0.01f)) {
                        val packColor = it.pack?.packColor() ?: Color.Transparent
                        it.pack?.let {
                            Text(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(packColor.copy(alpha = 0.1f))
                                    .padding(vertical = 4.dp, horizontal = 8.dp),
                                text = "$it - Pack",
                                style = TextStyle(
                                    fontFamily = LufgaFont,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 20.sp,
                                    color = packColor
                                )
                            )
                        }
                    }

                    Box(modifier = Modifier.weight(0.01f)) {
                        val background = it.isCostUp?.let {
                            if (it) Color.Red else Color.Green
                        } ?: Color.Gray
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(background.copy(alpha = 0.1f))
                                .padding(start = 6.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
                        ) {
                            it.isCostUp?.let {
                                Icon(
                                    imageVector = if (it) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowUp,
                                    contentDescription = null,
                                    tint = background
                                )
                            }
                            Text(
                                text = "$${it.cost}",
                                style = TextStyle(
                                    fontFamily = LufgaFont,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 20.sp
                                )
                            )
                        }
                    }

                    Box(modifier = Modifier.weight(0.01f)) {
                        val background = it.isPriceUp?.let {
                            if (it) Color.Red else Color.Green
                        } ?: Color.Gray
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(background.copy(alpha = 0.1f))
                                .padding(start = 6.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
                        ) {
                            it.isPriceUp?.let {
                                Icon(
                                    imageVector = if (it) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                    contentDescription = null,
                                    tint = background
                                )
                            }
                            Text(
                                text = "$${it.price}",
                                style = TextStyle(
                                    fontFamily = LufgaFont,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 20.sp
                                )
                            )
                        }
                    }

                    Text(
                        modifier = Modifier.weight(0.01f),
                        text = "${it.stock}",
                        style = TextStyle(
                            fontFamily = LufgaFont,
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp
                        )
                    )

                    Text(
                        modifier = Modifier.weight(0.01f),
                        text = it.type,
                        style = TextStyle(
                            fontFamily = LufgaFont,
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp
                        )
                    )
                }
                HorizontalDivider()
            }
        }
    }
}

fun Int.packColor(): Color {
    return when (this) {
        1 -> Color.Blue
        2 -> Color.Magenta
        3 -> Color.Yellow
        4 -> Color.Green
        else -> Color.Gray
    }
}

@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = "spec:width=1920dp,height=1080dp,dpi=160"
)
@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:width=1920dp,height=1080dp,dpi=160"
)
@Preview(
    name = "Light Mode TAB",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.PIXEL_TABLET
)
@Composable
private fun ItemPrev() {
    ItemsUITheme {
        Items()
    }
}

/*val items = arrayOf(
    Item(
        sku = 656454,
        itemName = "Test Item",
        size = "32 ml",
        pack = null,
        isCostUp = false,
        cost = 7.5,
        isPriceUp = null,
        price = 14.99,
        stock = 48,
        type = "Standard"
    ),
    Item(
        sku = 564564,
        itemName = "Test Item",
        size = "4 kg",
        pack = 1,
        isCostUp = null,
        cost = 15.0,
        isPriceUp = false,
        price = 34.99,
        stock = 78,
        type = "Split-Pack"
    ),
    Item(
        sku = 564565,
        itemName = "Jameson",
        size = "750 ml",
        pack = null,
        isCostUp = null,
        cost = 75.0,
        isPriceUp = true,
        price = 124.99,
        stock = 78,
        type = "Standard"
    )
)*/

fun generateDynamicItems(count: Int): Array<Item> {
    val items = Array(count) {
        val sku = Random.nextLong(100000, 999999)
        val itemName = generateRandomItemName() // Use the dynamic name function
        val sizes = arrayOf("ml", "kg", "L", "g")
        val sizeValue = Random.nextInt(1, 1000)
        val sizeUnit = sizes[Random.nextInt(sizes.size)]
        val size = "$sizeValue $sizeUnit"
        val pack = if (Random.nextBoolean()) Random.nextInt(1, 10) else null
        val isCostUp = if (Random.nextBoolean()) Random.nextBoolean() else null
        val cost = Random.nextDouble(5.0, 100.0).toFixed(2)
        val isPriceUp = if (Random.nextBoolean()) Random.nextBoolean() else null
        val price = Random.nextDouble(cost * 1.2, cost * 2.5).toFixed(2)
        val stock = Random.nextInt(0, 200)
        val types = arrayOf("Standard", "Split-Pack")
        val type = types[Random.nextInt(types.size)]

        Item(sku, itemName, size, pack, isCostUp, cost, isPriceUp, price, stock, type)
    }
    return items
}

fun generateRandomItemName(): String {
    val prefixes = arrayOf("Premium", "Deluxe", "Ultra", "Super", "Essential", "Basic", "Limited", "Organic", "Natural", "Artisan")
    val nouns = arrayOf("Product", "Item", "Supply", "Component", "Material", "Goods", "Kit", "Set", "Pack", "Bundle")
    val descriptors = arrayOf("A", "B", "C", "X", "Y", "Z", "1", "2", "3")

    val prefix = prefixes[Random.nextInt(prefixes.size)]
    val noun = nouns[Random.nextInt(nouns.size)]
    val descriptor = descriptors[Random.nextInt(descriptors.size)]

    return "$prefix $noun $descriptor"
}

fun Double.toFixed(digits: Int) = "%.${digits}f".format(this).toDouble()

data class Item(
    val sku: Long,
    val itemName: String,
    val size: String?,
    val pack: Int?,
    val isCostUp: Boolean?,
    val cost: Double,
    val isPriceUp: Boolean?,
    val price: Double,
    val stock: Int,
    val type: String
)