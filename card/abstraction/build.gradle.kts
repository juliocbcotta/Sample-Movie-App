plugins {
    id("com.sample.kotlin.jvm.library")
}

dependencies {
    api(project(":core:presentation"))
}
