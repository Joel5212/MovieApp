
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.46")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.28.3-alpha")
        classpath("com.google.gms:google-services:4.4.0")
    }
}



// Top-level build file where you can add configuration options common to all sub-projects/modules.



plugins {

    id("com.android.application") version "8.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.5.0" apply false
}