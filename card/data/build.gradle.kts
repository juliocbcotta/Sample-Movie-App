plugins {
    id("com.sample.kotlin.android.library")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.android.sample.card.data"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation("com.google.code.gson:gson:2.10.1")
    api("com.squareup.retrofit2:retrofit:2.11.0")
    api("com.google.dagger:dagger:2.51.1")
    ksp("com.google.dagger:dagger-compiler:2.51.1")
    api(project(":card:abstraction"))
}
