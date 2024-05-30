plugins {
    id("com.sample.kotlin.android.library")
    id("com.sample.compose.android.library")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.android.sample.core.di"
}

dependencies {

    api(project(":core:presentation"))

    val lifecycle_version = "2.8.1"
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
}
