plugins {
    id("com.sample.kotlin.android.library")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.android.sample.card.presentation"
}

dependencies {

    implementation(project(":core:ui"))
    implementation(project(":core:di"))
    implementation(project(":core:networking"))
    implementation(project(":core:coroutines"))

    implementation(project(":card:router"))
    implementation(project(":card:data"))
    implementation(project(":card:abstraction"))

    implementation("androidx.lifecycle:lifecycle-viewmodel:2.7.0")

    implementation("com.google.dagger:dagger:2.50")
    ksp("com.google.dagger:dagger-compiler:2.50")
}
