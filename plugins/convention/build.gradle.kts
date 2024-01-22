import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.android.sample.app.plugins"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("AndroidLibraryPlugin") {
            id = "com.sample.android.library"
            implementationClass = "com.android.sample.app.plugins.AndroidLibraryPlugin"
        }

        register("ComposeAndroidLibraryPlugin") {
            id = "com.sample.compose.android.library"
            implementationClass = "com.android.sample.app.plugins.ComposeAndroidLibraryPlugin"
        }


    }
}
