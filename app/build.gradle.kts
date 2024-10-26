plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.midtermandroid_yerkazy"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.midtermandroid_yerkazy"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17 // Updated to Java 17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17" // Updated to Java 17
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // Jetpack Compose UI dependencies
    implementation("androidx.compose.ui:ui:1.5.2") // Latest Compose UI version
    implementation("androidx.compose.material3:material3:1.2.1") // Latest Material3 version
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.2")
    implementation("androidx.compose.foundation:foundation:1.5.2")

    // Jetpack Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.8.0") // Latest Navigation Compose version

    // Additional UI tooling and testing dependencies
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.2")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.2")

    // Kotlin coroutine dependencies
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3") // Latest coroutines version

    // ViewModel and Jetpack Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.1") // Latest ViewModel Compose version

    // Testing dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.2")
}
