package com.example.clickawayapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clickawayapp.ui.theme.ClickAwayAppTheme

class CartActivity : ComponentActivity() {

    private lateinit var cartViewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cartViewModel = ViewModelProvider(
            this,
            CartViewModelFactory(application)
        )[CartViewModel::class.java]

        setContent {
            ClickAwayAppTheme {
                CartScreen(cartViewModel)
            }
        }
    }
}

@Composable
fun CartScreen(viewModel: CartViewModel) {
    val cartItems = viewModel.cartItems
    val context = LocalContext.current
    val total = cartItems.sumOf { it.price * it.quantity }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Your Cart", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(cartItems) { item ->
                CartItemView(item = item, onRemove = {
                    viewModel.removeItem(item.name)
                })
                Divider()
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Total: €%.2f".format(total),
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.End)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val intent = Intent(context, PickupActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Proceed to Pickup")
        }
    }
}

@Composable
fun CartItemView(item: CartItem, onRemove: () -> Unit) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = item.name, fontSize = 18.sp)
        Text(text = "€${item.price}", fontSize = 14.sp)
        Text(text = "Qty: ${item.quantity}", fontSize = 14.sp)

        Button(
            onClick = onRemove,
            modifier = Modifier
                .padding(top = 4.dp)
                .height(36.dp)
        ) {
            Text("Remove")
        }
    }
}