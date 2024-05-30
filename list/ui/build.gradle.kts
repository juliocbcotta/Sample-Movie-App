plugins {
    id("com.sample.kotlin.android.library")
    id("com.sample.compose.android.library")
    id("kotlin-parcelize")
}

android {
    namespace = "com.android.sample.list.ui"
}

dependencies {

    implementation(project(":core:di"))

    implementation(project(":card:router"))
    api(project(":list:router"))
    implementation(project(":list:presentation"))
    api(project(":list:abstraction"))


    val composeBom = platform("androidx.compose:compose-bom:2024.05.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose")
    debugImplementation("androidx.compose.ui:ui-tooling")

}
