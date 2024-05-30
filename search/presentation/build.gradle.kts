plugins {
    id("com.sample.kotlin.android.library")
    id("com.sample.compose.android.library")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.android.sample.search.presentation"
}

dependencies {

    implementation(project(":core:di"))
    implementation(project(":core:networking"))

    api(project(":search:router"))

    implementation(project(":list:router"))

    api("com.google.dagger:dagger:2.51.1")
    ksp("com.google.dagger:dagger-compiler:2.51.1")

    api("com.veepee.vpcore.link-router:link-router:0.5.3")

    val composeBom = platform("androidx.compose:compose-bom:2024.05.00")
    implementation(composeBom)

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
