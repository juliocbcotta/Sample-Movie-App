plugins {
    id("com.sample.kotlin.android.library")
}

android {
    namespace = "com.android.sample.search.router"
}

dependencies {

    implementation("com.veepee.vpcore.link-router:link-router:0.5.3")
}
