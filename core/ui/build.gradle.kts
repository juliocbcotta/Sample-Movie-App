plugins {
    id("com.sample.kotlin.android.library")
    id("com.sample.compose.android.library")
}

android {
    namespace = "com.android.sample.core.ui"
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.link.router)

}
