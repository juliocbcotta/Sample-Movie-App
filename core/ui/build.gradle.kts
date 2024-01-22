plugins {
    id("com.sample.android.library")
    id("com.sample.compose.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.android.sample.core.ui"
    kotlinOptions {
        jvmTarget = "1.8"
    }

}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    api("com.veepee.vpcore.link-router:link-router:0.5.3")

}
