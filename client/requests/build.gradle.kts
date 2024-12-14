import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform) apply true
    alias(libs.plugins.android.library) apply true
    alias(libs.plugins.kotlin.serialization) apply true

    `maven-publish`
}

group = "gc.libs"
version = "0.1"

kotlin {
    compilerOptions.freeCompilerArgs.apply {
        add("-Xconsistent-data-class-copy-visibility")
    }

    jvm()
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }
    androidTarget {
        publishLibraryVariants("release")
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }
    linuxX64()
    linuxArm64()
    mingwX64()

    sourceSets {
        commonMain {
            dependencies {
                // Entities
                api(project(":entities"))

                // KTor
                api(libs.ktor.client.core)
                implementation(libs.ktor.serialization.json)
                implementation(libs.ktor.serialization.cbor)
                implementation(libs.ktor.serialization.xml)
                implementation(libs.ktor.serialization.protobuf)
                implementation(libs.ktor.client.plugin.webSockets)
                implementation(libs.ktor.client.plugin.auth)
                implementation(libs.ktor.client.plugin.contentNegotiation)
                implementation(libs.ktor.client.plugin.encoding)
            }
        }

        jvmMain {
            dependencies {
                // KTor engine
                api(libs.ktor.client.engine.java)
            }
        }

        wasmJsMain {
            dependencies {
                // KTor engine
                api(libs.ktor.client.engine.js)
            }
        }

        androidMain {
            dependencies {
                // KTor engine
                api(libs.ktor.client.engine.java)
            }
        }

        linuxMain {
            dependencies {
                // KTor engine
                api(libs.ktor.client.engine.curl)
            }
        }

        mingwMain {
            dependencies {
                // KTor engine
                api(libs.ktor.client.engine.winhttp)
            }
        }
    }
}

android {
    namespace = "gc.libs"
    compileSdk = libs.versions.android.compileSDK.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minimumSDK.get().toInt()
    }
}
