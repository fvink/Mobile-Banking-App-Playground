plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "com.fvink.mobilebanking"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }

    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Compose.compose
    }
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
    implementation(Deps.AndroidX.appcompat)
    implementation(Deps.AndroidX.coreKtx)
    implementation(Deps.androidMaterial)
    implementation(Deps.timber)
    implementation(Deps.Coroutines.common)
    implementation(Deps.Coroutines.android)
    implementation(Deps.AndroidX.lifecycleRuntime)
    implementation(Deps.AndroidX.lifecycleViewModel)
    implementation(Deps.AndroidX.lifecycleViewModelExtensions)
    implementation(Deps.AndroidX.lifecycleProcess)
    implementation(Deps.Koin.core)
    implementation(Deps.Koin.android)
    implementation(Deps.Koin.compose)
    implementation(Deps.Compose.activityCompose)
    implementation(Deps.Compose.runtime)
    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.uiTooling)
    implementation(Deps.Compose.foundation)
    implementation(Deps.Compose.material)
    implementation(Deps.Compose.util)
    implementation(Deps.Compose.navigation)
    implementation(Deps.Compose.Accompanist.systemUiController)
    implementation(Deps.Compose.Accompanist.navigationAnimation)
    implementation(Deps.Compose.Accompanist.swipeRefresh)
    implementation(Deps.Compose.Accompanist.pager)
    implementation(Deps.Compose.Accompanist.pagerIndicators)
}

object Versions {
    const val minSdk = 23
    const val compileSdk = 31
    const val targetSdk = 31

    const val coroutines = "1.6.0"
    const val androidMaterial = "1.3.0"
    const val koin = "3.1.4"
    const val landscapist = "1.4.8"
    const val timber = "5.0.1"

    object AndroidX {
        const val appcompat = "1.3.1"
        const val core = "1.6.0"
        const val lifecycle = "2.4.0"
    }

    object Compose {
        const val compose = "1.2.0-alpha05"
        const val activity = "1.3.0"
        const val accompanist = "0.24.6-alpha"
        const val navigation = "2.4.1"
    }
}

object Deps {
    const val androidMaterial = "com.google.android.material:material:${Versions.androidMaterial}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appcompat}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.AndroidX.core}"

        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.lifecycle}"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.AndroidX.lifecycle}"
        const val lifecycleViewModelExtensions = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.lifecycle}"
        const val lifecycleProcess = "androidx.lifecycle:lifecycle-process:${Versions.AndroidX.lifecycle}"
    }

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.Compose.compose}"
        const val runtime = "androidx.compose.runtime:runtime:${Versions.Compose.compose}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.Compose.compose}"
        const val foundation = "androidx.compose.foundation:foundation:${Versions.Compose.compose}"
        const val material = "androidx.compose.material:material:${Versions.Compose.compose}"
        const val activityCompose = "androidx.activity:activity-compose:${Versions.Compose.activity}"
        const val util = "androidx.compose.ui:ui-util:${Versions.Compose.compose}"
        const val navigation = "androidx.navigation:navigation-compose:${Versions.Compose.navigation}"

        object Accompanist {
            const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.Compose.accompanist}"
            const val navigationAnimation = "com.google.accompanist:accompanist-navigation-animation:${Versions.Compose.accompanist}"
            const val swipeRefresh = "com.google.accompanist:accompanist-swiperefresh:${Versions.Compose.accompanist}"
            const val flowLayout = "com.google.accompanist:accompanist-flowlayout:${Versions.Compose.accompanist}"
            const val pager = "com.google.accompanist:accompanist-pager:${Versions.Compose.accompanist}"
            const val pagerIndicators = "com.google.accompanist:accompanist-pager-indicators:${Versions.Compose.accompanist}"
        }
    }

    object Coroutines {
        const val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object Koin {
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    }
}