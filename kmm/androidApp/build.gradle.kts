plugins {
    id("com.android.application")
    kotlin("android")
}

val compose_version = "1.2.0-alpha01"

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    // corotine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0-native-mt")

    implementation("androidx.multidex:multidex:2.0.1")

    compileOnly("io.realm.kotlin:library:0.4.1")
}

android {
    compileSdkVersion(31)
    defaultConfig {
        applicationId = "com.demo.kmm.android"
        minSdkVersion(16)
        targetSdkVersion(31)
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    ndkVersion = "23.0.7599858"

}