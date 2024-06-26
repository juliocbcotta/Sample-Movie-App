plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.android.sample.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.android.sample.app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            // https://github.com/Kotlin/kotlinx.coroutines?tab=readme-ov-file#avoiding-including-the-debug-infrastructure-in-the-resulting-apk
            excludes += "DebugProbesKt.bin"
        }
    }
}

dependencies {

    implementation(project(":core:ui"))
    implementation(project(":list:ui"))
    implementation(project(":search:presentation"))
    implementation(project(":card:ui"))
    implementation(project(":search:router"))

    implementation(libs.link.router)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)

    debugImplementation(libs.androidx.compose.ui.tooling)
}
