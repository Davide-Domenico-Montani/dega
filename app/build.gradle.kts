plugins {
    id("com.android.application")
    //alias(libs.plugins.google.gms.google.services)
    id("com.google.gms.google-services")
}


android {
    namespace = "com.example.cinevibes"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cinevibes"
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
    implementation(libs.commons.validator)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.gson)
    implementation(libs.room.runtime)
    implementation(libs.firebase.database)
    annotationProcessor(libs.room.compiler)

    implementation(libs.retrofit)
    implementation(libs.retrofit.v290)
    implementation(libs.converter.gson)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)

    implementation (libs.gms.play.services.auth)
   // implementation(libs.credentials.v130)
   // implementation(libs.credentials.play.services.auth.v130)
   // implementation(libs.googleid)
}