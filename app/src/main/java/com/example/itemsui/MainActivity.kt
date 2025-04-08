package com.example.itemsui

import android.app.LocaleManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import com.example.itemsui.ui.theme.ItemsUITheme
import com.example.itemsui.ui.theme.LufgaFont
import java.util.Locale
import kotlin.random.Random

val items = generateDynamicItems(100)


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDarkTheme by rememberSaveable { mutableStateOf(false) }
            ItemsUITheme(darkTheme = isDarkTheme) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            val interactionSource = remember { MutableInteractionSource() }
                            val isPressed by interactionSource.collectIsPressedAsState()
                            val scale by animateFloatAsState(targetValue = if (isPressed) 0.9f else 1f )

                            var isEnglishSelected by rememberSaveable { mutableStateOf(LocaleList.getDefault().toLanguageTags().contains("en",true)) }

                            Button(
                                modifier = Modifier.scale(scale),
                                onClick = {
                                    isEnglishSelected = !isEnglishSelected
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                                        getSystemService(LocaleManager::class.java).applicationLocales =
                                            LocaleList.forLanguageTags(if (isEnglishSelected) "en" else "es")
                                    }else{
                                        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(if (isEnglishSelected) "en" else "es")
                                        AppCompatDelegate.setApplicationLocales(appLocale)
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                                ),
                                interactionSource = interactionSource
                            ) {
                                Text(text = stringResource(R.string.language), color = if (isPressed) Color.Red else MaterialTheme.colorScheme.primary)
                            }

                            Spacer(Modifier.width(12.dp))

                            IconButton(colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MaterialTheme.colorScheme.tertiaryContainer
                            ), onClick = {
                                isDarkTheme = !isDarkTheme
                            }) {
                                Icon(
                                    imageVector = Icons.Rounded.Refresh,
                                    tint = MaterialTheme.colorScheme.primary,
                                    contentDescription = null
                                )
                            }
                        }
                        Items()
                    }
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
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .clip(RoundedCornerShape(36.dp))
                .background(
                    MaterialTheme.colorScheme.tertiaryContainer/*Color(0xFFDADADA).copy(0.7f)*/
                )
                .padding(12.dp)
        ) {
            Text(
                modifier = Modifier.weight(0.1f),
                text = stringResource(R.string.sku),
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = LufgaFont,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
                )
            )

            Text(
                modifier = Modifier.weight(0.3f),
                text = stringResource(R.string.item_name),
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = LufgaFont,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
                )
            )

            Text(
                modifier = Modifier.weight(0.1f),
                text = stringResource(R.string.size),
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = LufgaFont,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
                )
            )

            Text(
                modifier = Modifier.weight(0.1f),
                text = stringResource(R.string.pack),
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = LufgaFont,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
                )
            )

            Text(
                modifier = Modifier.weight(0.1f),
                text = stringResource(R.string.cost),
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = LufgaFont,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
                )
            )

            Text(
                modifier = Modifier.weight(0.1f),
                text = stringResource(R.string.price),
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = LufgaFont,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
                )
            )

            Text(
                modifier = Modifier.weight(0.1f),
                text = stringResource(R.string.stock),
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = LufgaFont,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
                )
            )

            Text(
                modifier = Modifier.weight(0.1f),
                text = stringResource(R.string.type),
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = LufgaFont,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
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
        val cost = Random.nextDouble(5.0, 100.0).toLocalFixed(2)//.toFixed(2)
        val isPriceUp = if (Random.nextBoolean()) Random.nextBoolean() else null
        val price = Random.nextDouble(cost * 1.2, cost * 2.5).toLocalFixed(2)//.toFixed(2)
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

fun Double.toLocalFixed(digits: Int) = String.format(Locale.ENGLISH,"%.${digits}f",this).toDouble()

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