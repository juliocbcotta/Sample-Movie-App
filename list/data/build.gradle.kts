plugins {
    id("com.sample.kotlin.android.library")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.android.sample.list.data"
}
android {
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(project(":list:abstraction"))

    implementation("com.google.code.gson:gson:2.10.1")
    api("com.squareup.retrofit2:retrofit:2.11.0")
    api("com.google.dagger:dagger:2.51.1")
    ksp("com.google.dagger:dagger-compiler:2.51.1")

}
