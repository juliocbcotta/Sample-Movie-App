plugins {
    id("com.sample.kotlin.android.library")
    id("com.sample.compose.android.library")
    id("kotlin-parcelize")
}

android {
    namespace = "com.android.sample.list.ui"
}

dependencies {

    implementation(project(":core:ui"))
    implementation(project(":core:di"))

    implementation(project(":card:router"))
    implementation(project(":list:router"))
    implementation(project(":list:presentation"))
    implementation(project(":list:abstraction"))


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
