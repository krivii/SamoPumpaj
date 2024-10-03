plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.samopumpaj"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.samopumpaj"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.gridlayout)

    // Room dependencies
    implementation(libs.room.common)
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)

    // Lifecycle dependencies - Removed LiveData as it's not used in your code
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
