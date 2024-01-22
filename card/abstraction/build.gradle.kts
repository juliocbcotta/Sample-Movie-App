plugins {
    id("com.sample.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.android.sample.card.abstraction"
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    api(project(":core:presentation"))
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
}
