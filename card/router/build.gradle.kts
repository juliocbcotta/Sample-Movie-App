plugins {
    id("com.sample.kotlin.android.library")
}

android {
    namespace = "com.android.sample.card.router"
}

dependencies {
    api("com.veepee.vpcore.link-router:link-router:0.5.3")
}
