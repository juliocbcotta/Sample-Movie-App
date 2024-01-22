plugins {
    id("com.sample.kotlin.android.library")
}

android {
    namespace = "com.android.sample.list.domain"
}

dependencies {
    implementation(project(":list:abstraction"))
}
