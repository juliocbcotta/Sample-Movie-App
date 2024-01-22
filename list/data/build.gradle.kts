plugins {
    id("com.sample.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.android.sample.list.data"
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
android {
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":list:abstraction"))
    implementation(project(":list:domain"))

    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.google.dagger:dagger:2.50")
    ksp("com.google.dagger:dagger-compiler:2.50")

}
