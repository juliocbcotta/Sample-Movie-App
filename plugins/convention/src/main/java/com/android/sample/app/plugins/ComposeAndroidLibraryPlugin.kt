package com.android.sample.app.plugins

import com.android.build.api.dsl.LibraryExtension
import com.android.sample.app.plugins.utils.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class ComposeAndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")

            with(extensions.getByType<LibraryExtension>()) {
                buildFeatures {
                    compose = true
                }
                composeOptions {
                    kotlinCompilerExtensionVersion = libs.findVersion("androidxComposeCompiler").get().toString()
                }
            }
        }
    }
}
