plugins {
    alias(libs.plugins.kotlin.jvm) apply true

    alias(libs.plugins.ktor) apply true
}

ktor {
    fatJar {
        archiveFileName.set("BServer.jar")
    }
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

dependencies {
    implementation(libs.log4j)
    implementation(libs.log4j.slf4j)

    implementation(project(":backend:databases"))

    implementation(libs.ktor.server.core)
    implementation(libs.ktor.serialization.json)
    implementation(libs.ktor.serialization.cbor)
    implementation(libs.ktor.serialization.xml)
    implementation(libs.ktor.serialization.protobuf)
    implementation(libs.ktor.server.engine.netty)
    implementation(libs.ktor.server.plugin.webSockets)
    implementation(libs.ktor.server.plugin.contentNegotiation)
    implementation(libs.ktor.server.plugin.config.yaml)
    implementation(libs.ktor.server.plugin.logging) {
        exclude(group = "org.apache.logging.log4j")
    }
    implementation(libs.ktor.server.plugin.auth)
    implementation(libs.ktor.server.plugin.autoHead)
    implementation(libs.ktor.server.plugin.partialContent)
    implementation(libs.ktor.server.plugin.methodOverride)

    implementation(libs.kotlinx.datetime)
}
