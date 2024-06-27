package com.android.sample.app.plugins

import kotlinx.kover.gradle.plugin.dsl.KoverProjectExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class KoverProjectPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            configureKoverInRootProject()
            subprojects {
                configureKoverInProject()
            }
        }
    }
}

private fun Project.configureKoverInRootProject() {
    pluginManager.apply("org.jetbrains.kotlinx.kover")
    configure<KoverProjectExtension> {
        merge.subprojects()
        configureReportsExclusions()
        currentProject {
            createVariant("Sample") {

            }
        }
    }
}

private fun Project.configureKoverInProject() {
    pluginManager.withPlugin("org.jetbrains.kotlinx.kover") {
        configure<KoverProjectExtension> {
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