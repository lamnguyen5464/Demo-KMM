buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()

    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.android.tools.build:gradle:4.2.2")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.6.10")

    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
    version = "0.9.0"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}