import java.util.Properties

plugins {
    id("com.sample.kotlin.android.library")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.android.sample.core.networking"
}
android {
    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        val properties = Properties()
        properties.load(project.rootProject.file("api.properties").bufferedReader())
        val apiKey = properties["apiKey"].toString()
        buildConfigField("String", "API_KEY", """"$apiKey"""".trimIndent())
    }
}
dependencies {

    api("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    api("com.squareup.okhttp3:logging-interceptor:4.12.0")

    api("com.google.dagger:dagger:2.51.1")
    ksp("com.google.dagger:dagger-compiler:2.51.1")
}
