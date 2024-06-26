plugins {
    id("com.sample.kotlin.android.library")
    id("com.sample.compose.android.library")
    id("kotlin-parcelize")
}

android {
    namespace = "com.android.sample.card.ui"
}

dependencies {

    implementation(project(":core:di"))

    api(project(":card:router"))
    implementation(project(":card:presentation"))
    api(project(":card:abstraction"))

    implementation(libs.coil.kt.compose)

    api(libs.link.router)


    implementation(libs.androidx.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    debugImplementation(libs.androidx.compose.ui.tooling)
}
