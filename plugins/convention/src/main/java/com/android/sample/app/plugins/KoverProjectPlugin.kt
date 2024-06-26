package com.android.sample.app.plugins

import com.android.sample.app.plugins.utils.libs
import kotlinx.kover.gradle.plugin.dsl.KoverProjectExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.provideDelegate

class KoverProjectPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            configureKoverInRootProject()
            val coverageScope: String? by project
            val scope = (coverageScope ?: ":")
            subprojects {
                configureKoverInProject(scope)
            }
        }
    }
}

private fun Project.configureKoverInRootProject() {
    pluginManager.apply("org.jetbrains.kotlinx.kover")
    configure<KoverProjectExtension> {
        useJacoco(libs.findVersion("jacoco").get().toString())
        merge.subprojects()
        configureReportsExclusions()
        currentProject {
            createVariant("Sample") {

            }
        }
    }
}

private fun Project.configureKoverInProject(scope: String) {
    pluginManager.withPlugin("org.jetbrains.kotlinx.kover") {
        configure<KoverProjectExtension> {
            val shouldRunCoverage = path.contains(scope)
            if (!shouldRunCoverage) {
                disable()
            }
            currentProject {
                createVariant("Sample") {
                    add("debug", optional = true)
                }
            }
            configureReportsExclusions()
        }
    }
}

private fun KoverProjectExtension.configureReportsExclusions() {
    reports {
        filters {
            excludes {
                // Android
                androidGeneratedClasses()
                classes("*Dialog")
                classes("*View")
                classes("*ViewHolder")
                classes("*DiffCallback")
                classes("*AdapterItemType")
                classes("*\$inlined\$*")
                inheritedFrom("androidx.recyclerview.widget.*")
                inheritedFrom("androidx.fragment.app.DialogFragment")

                // Dagger
                classes("Dagger*Component")
                classes("Dagger*Component$*")
                classes("*Module")
                classes("*Module_*")
                classes("*Component")
                classes("*Component$*Builder")
                classes("*Component$*Factory")
                classes("*_Factory*")
                classes("*Factory_Impl*")
                classes("*_MembersInjector")

                // Parcelize
                annotatedBy("kotlinx.parcelize.Parcelize")
                classes("*\$Creator")

                // Link Router
                classes("*ActivityName")
                classes("*FragmentName")
                classes("*ComposableName")
                classes("*ActivityLink")
                classes("*FragmentLink")
                classes("*ComposableLink")
                classes("*ActivityNameMapper")
                classes("*FragmentNameMapper")
                classes("*ComposableNameMapper")

                // Compose
                annotatedBy("androidx.compose.ui.tooling.preview.Preview")
                annotatedBy("androidx.compose.runtime.Composable")

                // Arch
                classes("*Request")
                classes("*Response")
                classes("*Entity")
                classes("*ViewState")
                classes("*ViewState\$*")
                packages("*.abstraction.*")

                classes("*Initializer")
            }
        }
    }
}