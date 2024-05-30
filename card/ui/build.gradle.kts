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

    implementation("io.coil-kt:coil-compose:2.6.0")

    api("com.veepee.vpcore.link-router:link-router:0.5.3")

    val composeBom = platform("androidx.compose:compose-bom:2024.05.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose")
    debugImplementation("androidx.compose.ui:ui-tooling")
}
