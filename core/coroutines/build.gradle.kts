plugins {
    id("com.sample.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.android.sample.core.coroutines"
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("androidx.core:core-ktx:1.12.0")
}
