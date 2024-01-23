package com.android.sample.app.plugins

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class KotlinJvmLibrary : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.jvm")

            tasks.withType<JavaCompile> {
                sourceCompatibility = JavaVersion.VERSION_1_8.toString()
                targetCompatibility = JavaVersion.VERSION_1_8.toString()
            }
            tasks.withType<KotlinCompile>().all {
                kotlinOptions {
                    jvmTarget = "1.8"

                }
            }
        }
    }
}
