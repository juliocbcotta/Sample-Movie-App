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


    implementation(libs.androidx.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    debugImplementation(libs.androidx.compose.ui.tooling)

}
