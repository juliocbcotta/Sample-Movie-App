import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.sample.android.app.plugins"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_21.toString()
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    implementation(libs.android.gradlePlugin)
    implementation(libs.ksp.gradlePlugin)
    implementation(libs.kover.gradlePlugin)
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
            id = "com.sample.kotlin.android.library"
            implementationClass = "com.android.sample.app.plugins.KotlinAndroidLibraryPlugin"
        }

        register("ComposeAndroidLibraryPlugin") {
            id = "com.sample.compose.android.library"
            implementationClass = "com.android.sample.app.plugins.ComposeAndroidLibraryPlugin"
        }

        register("KotlinJVMLibraryPlugin") {
            id = "com.sample.kotlin.jvm.library"
            implementationClass = "com.android.sample.app.plugins.KotlinJvmLibrary"
        }

        register("KoverProjectPlugin") {
            id = "com.sample.kover"
            implementationClass = "com.android.sample.app.plugins.KoverProjectPlugin"
        }
    }
}
