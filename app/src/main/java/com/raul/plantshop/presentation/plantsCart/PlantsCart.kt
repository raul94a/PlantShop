package com.raul.plantshop.presentation.plantsCart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.common.internal.Constants
import com.google.android.gms.wallet.Wallet
import com.google.pay.button.ButtonTheme
import com.google.pay.button.ButtonType
import com.google.pay.button.PayButton
import com.raul.plantshop.R
import com.raul.plantshop.domain.cart.PlantItem
import com.raul.plantshop.domain.payments.PaymentsUtil
import com.raul.plantshop.domain.plants.Plant
import com.raul.plantshop.presentation.plants.PlantState
import com.raul.plantshop.presentation.plants.PlantsViewModel
import com.raul.plantshop.ui.theme.Typography
import com.raul.plantshop.ui.theme.buttonColor
import org.json.JSONObject
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun PlantsCart(
    modifier: Modifier = Modifier, viewModel: PlantsViewModel, navController: NavController,

    paymentUiState: PaymentUiState,
    onClickPayButton: () -> Unit
) {
    val shoppingCart =
        viewModel.homeStateFlow.collectAsState(initial = PlantState()).value.shoppingCart
    println("Shopping Cart: ${shoppingCart.items}")
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp)

    ) {


        if (shoppingCart.isEmpty()) {

            Text(
                "Empty!", textAlign = TextAlign.Center, modifier = Modifier

                    .fillMaxSize(1F)
                    .fillMaxHeight(1F)
            )


        } else {
            val items = shoppingCart.values()
            LazyColumn(
                modifier = Modifier.weight(0.7f)
            ) {
                items(count = items.size) { index ->
                    val item = items[index]
                    key(item.plant.id) {
                        CartPlantItem(modifier = Modifier
                            .height(110.dp),
                            item = item,
                            onRemoveQuantity = { plant ->
                                viewModel.removeItemFromCart(plant)
                            },
                            onAddQuantity = { plant ->
                                viewModel.addItemToCart(plant)

                            })
                        HorizontalDivider(modifier = Modifier.padding(horizontal = 15.dp))
                    }
                }

            }


            Spacer(modifier = Modifier.height(25.dp))
            Column(


            ) {


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            buttonColor.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(15.dp), verticalAlignment =
                    Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Total",
                        style = Typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        "$${shoppingCart.getTotal()}",
                        style = Typography.bodyLarge.copy(
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }



            PayButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onClickPayButton,
                allowedPaymentMethods = PaymentsUtil.allowedPaymentMethods.toString(),
                radius = 8.dp,
                theme = ButtonTheme.Light
            )

        }
    }
}


@Composable
fun CartPlantItem(
    modifier: Modifier = Modifier,
    item: PlantItem,
    onRemoveQuantity: (Plant) -> Unit,
    onAddQuantity: (Plant) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(1f)
            .padding(start = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {


            Image(
                painter = painterResource(item.plant.getDrawable()),
                contentDescription = item.plant.name,
                alpha = 0.8f,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .border(
                        BorderStroke(2.5.dp, buttonColor), CircleShape
                    )
                    .padding(2.5.dp)
                    .clip(CircleShape)


            )
            Column(
                modifier = Modifier.padding(start = 15.dp)
            ) {
                Text(
                    item.plant.name,
                    style = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    "${item.qty} ${
                        if (item.qty > 1) stringResource(R.string.pieces) else stringResource(
                            R.string.piece
                        )
                    }",
                    style = Typography.bodyMedium.copy(color = buttonColor)
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center, modifier = Modifier.padding(end = 5.dp)
        ) {


            Text(
                "$${BigDecimal(item.qty * item.plant.price).setScale(2, RoundingMode.HALF_EVEN)}",
                color = buttonColor,
                modifier = Modifier,
                style = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold)


            )


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "",
                    modifier = Modifier
                        .clickable {
                            onAddQuantity(item.plant)
                        }
                        .padding(horizontal = 5.dp)
                        .size(30.dp)

                )



                Icon(
                    painter = painterResource(R.drawable.remove),
                    contentDescription = "",
                    modifier = Modifier
                        .clickable {
                            onRemoveQuantity(item.plant)
                        }
                        .padding(horizontal = 5.dp)
                        .size(30.dp)
                )

            }
        }


    }

}