import java.util.Properties

pluginManagement {
    includeBuild("plugins")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    val properties = Properties()
    properties.load(file("github.properties").bufferedReader())
    val user = properties["github.user"].toString()
    val token = properties["github.token"].toString()
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://androidx.dev/storage/compose-compiler/repository/")
        }
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/veepee-oss/link-router")
            credentials {
                username = user
                password = token
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
include(":list:data")
include(":list:router")


include(":card:presentation")
include(":card:abstraction")
include(":card:data")
include(":card:router")
include(":card:ui")


include(":search:presentation")
include(":search:router")
