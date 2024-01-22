plugins {
    id("com.sample.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.android.sample.list.domain"

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":list:abstraction"))
}
