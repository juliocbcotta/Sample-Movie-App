// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kagp) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.compose.compiler) apply false
    id("com.sample.kover")
    id("com.autonomousapps.dependency-analysis") version "1.32.0"
}

subprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
}
tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory.get())
    subprojects { delete(project.layout.buildDirectory.get()) }
}

dependencyAnalysis {
    issues {
        all {
            onAny {
                severity("fail")
            }
            onRuntimeOnly {
                severity("ignore")
            }
            onIncorrectConfiguration {
                exclude("org.jetbrains.kotlin:kotlin-stdlib")
            }
            onUnusedDependencies {
                exclude("org.jetbrains.kotlin:kotlin-stdlib")
            }
            onUsedTransitiveDependencies {
                severity("ignore")
            }
            onModuleStructure {
                exclude(":card:data")
                severity("ignore")
            }
        }
    }
}