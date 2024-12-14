rootProject.name = "GigaChat"


pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        maven("https://repo1.maven.org/maven2/")
        mavenCentral()
        google()
    }
}

buildscript {
    repositories {
        mavenCentral()
        google()
    }
}

include(":entities")
include(":backend")
include(":backend:databases")
findProject(":backend:databases")?.name = "databases"
include(":backend:BServer")
findProject(":backend:BServer")?.name = "BServer"
