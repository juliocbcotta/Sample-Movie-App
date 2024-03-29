import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.sample.android.app.plugins"

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
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin")
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


    }
}
