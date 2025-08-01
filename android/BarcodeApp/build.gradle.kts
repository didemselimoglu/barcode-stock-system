// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

//
buildscript {
    dependencies {
        // Google services plugin
        classpath ("com.google.gms:google-services:4.3.15")
    }
}