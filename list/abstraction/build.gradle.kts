plugins {
    id("com.sample.kotlin.android.library")

}

android {
    namespace = "com.android.sample.list.abstraction"
}

dependencies {
    api(project(":core:presentation"))
    implementation("androidx.core:core-ktx:1.12.0")
}
