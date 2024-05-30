plugins {
    id("com.sample.kotlin.android.library")
    id("com.sample.compose.android.library")
}

android {
    namespace = "com.android.sample.core.ui"
}

dependencies {
    implementation("androidx.core:core:1.13.1")
    implementation(platform("androidx.compose:compose-bom:2024.05.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("com.veepee.vpcore.link-router:link-router:0.5.3")

}
