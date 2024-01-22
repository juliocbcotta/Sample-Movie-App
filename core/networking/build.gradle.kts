import java.util.Properties

plugins {
    id("com.sample.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.android.sample.core.networking"
        kotlinOptions {
        jvmTarget = "1.8"
    }
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

    implementation("com.google.dagger:dagger:2.50")
    ksp("com.google.dagger:dagger-compiler:2.50")
}
