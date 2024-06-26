plugins {
    id("com.sample.kotlin.android.library")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.android.sample.list.presentation"
}

dependencies {

    api(project(":core:di"))
    api(project(":core:networking"))
    implementation(project(":core:coroutines"))

    api(project(":list:data"))
    api(project(":list:abstraction"))


    api("com.google.dagger:dagger:2.51.1")
    ksp("com.google.dagger:dagger-compiler:2.51.1")


    implementation("androidx.lifecycle:lifecycle-viewmodel:2.8.2")

}
