plugins {
    id("com.sample.kotlin.android.library")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.android.sample.card.presentation"
}

dependencies {
    api(project(":core:di"))
    api(project(":core:networking"))
    implementation(project(":core:coroutines"))

    api(project(":card:data"))
    api(project(":card:abstraction"))

    api("androidx.lifecycle:lifecycle-viewmodel:2.8.1")

    api("com.google.dagger:dagger:2.51.1")
    ksp("com.google.dagger:dagger-compiler:2.51.1")
}
