plugins {
    id("com.sample.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.android.sample.list.router"

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("com.veepee.vpcore.link-router:link-router:0.5.3")
}
