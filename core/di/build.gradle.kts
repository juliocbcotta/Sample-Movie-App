plugins {
    id("com.sample.kotlin.android.library")
    id("com.sample.compose.android.library")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.android.sample.core.di"
}

dependencies {

    api(project(":core:coroutines"))
    api(project(":core:presentation"))
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")

    val composeBom = platform("androidx.compose:compose-bom:2023.10.01")
    implementation(composeBom)

    implementation("androidx.compose.ui:ui")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose")

    implementation("com.google.dagger:dagger:2.50")
    ksp("com.google.dagger:dagger-compiler:2.50")
}
