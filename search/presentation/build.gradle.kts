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

    api(libs.dagger)
    ksp(libs.dagger.compiler)

    api(libs.link.router)

    implementation(libs.androidx.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
