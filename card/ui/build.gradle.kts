plugins {
    id("com.sample.kotlin.android.library")
    id("com.sample.compose.android.library")
    id("kotlin-parcelize")
}

android {
    namespace = "com.android.sample.card.ui"
}

dependencies {

    implementation(project(":core:ui"))
    implementation(project(":core:di"))
    implementation(project(":core:coroutines"))

    implementation(project(":card:router"))
    implementation(project(":card:presentation"))
    implementation(project(":card:abstraction"))

    implementation("io.coil-kt:coil-compose:2.5.0")

    implementation("com.veepee.vpcore.link-router:link-router:0.5.3")

    val composeBom = platform("androidx.compose:compose-bom:2023.10.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.lifecycle:lifecycle-runtime-compose")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose")
    implementation("androidx.compose.material:material-icons-extended")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
}
