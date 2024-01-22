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
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.google.dagger:dagger:2.50")
    ksp("com.google.dagger:dagger-compiler:2.50")
    implementation(project(":card:abstraction"))
}
