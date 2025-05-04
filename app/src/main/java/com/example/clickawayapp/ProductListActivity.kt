package com.example.clickawayapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.clickawayapp.ui.theme.ClickAwayAppTheme
import kotlinx.coroutines.launch

class ProductListActivity : ComponentActivity() {

    private lateinit var cartViewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cartViewModel = ViewModelProvider(
            this,
            CartViewModelFactory(application)
        )[CartViewModel::class.java]

        setContent {
            ClickAwayAppTheme {
                ProductListScreen("Product List", cartViewModel)
            }
        }
    }
}

data class Product(val name: String, val price: Double, val inStock: Boolean)

val sampleProducts = listOf(
    Product("Wireless Headphones", 59.99, true),
    Product("Smart Watch", 89.99, false),
    Product("Laptop Stand", 34.50, true),
    Product("Bluetooth Speaker", 42.00, true),
    Product("Phone Case", 12.99, true)
)

@Composable
fun ProductListScreen(category: String, cartViewModel: CartViewModel) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = category,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            LazyColumn {
                items(sampleProducts) { product ->
                    ProductItem(product) {
                        if (product.inStock) {
                            cartViewModel.addItem(CartItem(product.name, product.price, 1))
                            scope.launch {
                                snackbarHostState.showSnackbar("${product.name} added to cart")
                            }
                        }
                    }
                    HorizontalDivider(thickness = 1.dp)
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, onAddToCart: () -> Unit) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = product.name, fontSize = 18.sp)
        Text(text = "€${product.price}", fontSize = 14.sp)
        Text(
            text = if (product.inStock) "In Stock" else "Out of Stock",
            color = if (product.inStock) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
        )
        if (product.inStock) {
            Button(
                onClick = onAddToCart,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Add to Cart")
            }
        }
    }
}
