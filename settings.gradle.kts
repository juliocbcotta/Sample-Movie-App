pluginManagement {
    includeBuild("plugins")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/veepee-oss/link-router")
            credentials {
                username = "BugsBunnyBR"
                password = "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"
            }
        }
    }
}

rootProject.name = "MyApplication"
include(":app")

include(":core:networking")
include(":core:ui")
include(":core:di")
include(":core:coroutines")
include(":core:presentation")

include(":list:presentation")
include(":list:abstraction")
include(":list:tracking")
include(":list:domain")
include(":list:data")
include(":list:router")


include(":card:presentation")
include(":card:abstraction")
include(":card:data")
include(":card:router")


include(":search:presentation")
include(":search:router")
