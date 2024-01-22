plugins {
    id("com.sample.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.android.sample.core.presentation"

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    api(project(":core:coroutines"))
    implementation("androidx.core:core-ktx:1.12.0")
}
