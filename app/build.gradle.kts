plugins {
    id("com.android.application") // Le plugin Android doit être appliqué en premier
    id("org.jetbrains.kotlin.android") // Kotlin Android plugin
    id("org.jetbrains.kotlin.kapt")
    id("androidx.navigation.safeargs.kotlin") // SafeArgs plugin
}

android {
    namespace = "com.example.laboratoire02"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.laboratoire02"
        minSdk = 29
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

    viewBinding {
        enable = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // Dépendances principales
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.runtime.ktx)

    // Test unitaires et Android
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.10.0")  // Mise à jour à la version 2.10.0
    implementation("com.squareup.retrofit2:converter-gson:2.10.0")  // Mise à jour à la version 2.10.0
    implementation("com.squareup.okhttp3:okhttp:4.10.0")  // Mise à jour à la version OkHttp 4.10.0
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")  // Mise à jour à la version OkHttp 4.10.0

    // Glide
    implementation("com.github.bumptech.glide:glide:4.15.0")  // Mise à jour à la version Glide 4.15.0
    kapt("com.github.bumptech.glide:compiler:4.15.0")  // Mise à jour à la version Glide 4.15.0

    // Autres dépendances
    // Vous pouvez ajouter d'autres bibliothèques selon vos besoins
}
