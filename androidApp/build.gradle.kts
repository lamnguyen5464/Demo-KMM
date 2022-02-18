plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")


    // corotine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0-native-mt")
}

android {
    compileSdkVersion(31)
    defaultConfig {
        applicationId = "com.demo.kmm.android"
        minSdkVersion(16)
        targetSdkVersion(31)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}