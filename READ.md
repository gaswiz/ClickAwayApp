# ClickAwayApp

**ClickAwayApp** is a mobile prototype designed for Android using Jetpack Compose. It simulates a modern "Click Away" shopping experience, allowing users to browse products, add items to a cart, and schedule pickup using geolocation services. This application was developed as part of the CN6008 coursework in Advanced Topics in Computer Science.

## Project Overview

The goal of ClickAwayApp is to address the limitations of physical shopping during restricted access scenarios, such as the COVID-19 pandemic, by offering a simple, digital-first ordering and pickup process.

This is a prototype-level application focused on UI and basic logic. It does **not include backend integration** or persistent storage. All data is hardcoded and simulated within the app.

## Features

- Browse product categories (e.g., Electronics, Clothing, Books)
- View product lists and product details
- Add and remove items from a shopping cart
- Navigate to cart and see totals
- Simulate pickup location selection using a basic UI (mock geolocation)
- Modern UI with Jetpack Compose

## Screens

- **MainActivity**: Displays product categories
- **ProductListActivity**: Shows a list of products based on category
- **CartActivity**: Displays items in the cart and total price
- **PickupActivity**: Simulates location-based pickup using Compose map placeholder

## Technologies Used

- Kotlin
- Android Studio (Electric Eel or higher)
- Jetpack Compose
- MVVM Pattern with ViewModel
- Android SDK (API 21+)

## Folder Structure

```

ClickAwayApp/
│
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/clickawayapp/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── ProductListActivity.kt
│   │   │   │   ├── CartActivity.kt
│   │   │   │   ├── PickupActivity.kt
│   │   │   │   ├── data/Product.kt
│   │   │   │   ├── data/SampleData.kt
│   │   │   │   ├── viewmodel/CartViewModel.kt
│   │   │   │   └── viewmodel/CartViewModelFactory.kt
│   │   │   └── AndroidManifest.xml
│   │   └── res/ (default Compose theming resources)

````

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone git@github.com:gaswiz/ClickAwayApp.git
````

2. Open in Android Studio:

   * File > Open > Navigate to `ClickAwayApp` root folder

3. Build and run:

   * Use an Android emulator (API 30 or higher recommended)
   * The launcher activity is `MainActivity`

## Notes

* This is a front-end prototype only.
* Product data and cart management are done in-memory with no persistence.
* Pickup functionality is simulated for design purposes.

## License

This project is for academic use only as part of university coursework. No commercial use intended.


