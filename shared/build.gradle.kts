plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("com.squareup.sqldelight")
}

version = "1.0"

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = Versions.gradle
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = Versions.iOSDeploymentTarget
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }


    sourceSets {
        val commonMain by getting {
            dependencies {
                //COROUTINES
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}")

                //DI
                api("io.insert-koin:koin-core:${Versions.koin}")

                //NETWORK
                implementation("io.ktor:ktor-client-core:${Versions.ktor}")
                implementation("io.ktor:ktor-client-json:${Versions.ktor}")
                implementation("io.ktor:ktor-client-logging:${Versions.ktor}")
                implementation("io.ktor:ktor-client-serialization:${Versions.ktor}")
                implementation("io.ktor:ktor-client-websockets:${Versions.ktor}")

                //DATABASE
                implementation("com.squareup.sqldelight:runtime:${Versions.sqlDelight}")
//                implementation("org.kodein.db:kodein-db:${Versions.kodeinDb}")
//                implementation("org.kodein.db:kodein-db-serializer-kotlinx:${Versions.kodeinDb}")

                //SERIALIZATION SETTINGS
                implementation("com.russhwolf:multiplatform-settings:${Versions.russhwolf}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")




            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val androidMain by getting {
            dependencies{
                implementation("io.ktor:ktor-client-android:${Versions.ktor}")
                implementation("io.ktor:ktor-network-tls:${Versions.ktor}")
                implementation("com.squareup.okhttp3:okhttp:4.10.0")
                implementation("androidx.core:core:1.10.1")
                implementation("androidx.compose.ui:ui:${Versions.compose}")
                implementation("com.squareup.sqldelight:android-driver:${Versions.sqlDelight}")
            }
        }

        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:${Versions.jUnit}")
//                implementation("org.mockito:mockito-core:${Versions.mockito}")
//                implementation("org.mockito.kotlin:mockito-kotlin:${Versions.mockito}")
//                implementation("io.mockk:mockk:${Versions.mockk}")
//                implementation("io.insert-koin:koin-core:${Versions.koin}")
//                implementation("io.insert-koin:koin-test-junit4:${Versions.koin}")
//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}")
//                implementation("org.kodein.db:kodein-db-inmemory:${Versions.kodein}")
                implementation("com.russhwolf:multiplatform-settings-test:${Versions.russhwolf}")
            }
        }

        val iosX64Main by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:${Versions.ktor}")
            }
        }

        val iosArm64Main by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:${Versions.ktor}")
            }
        }

        val iosSimulatorArm64Main by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:${Versions.ktor}")
            }
        }

        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }

    //Allow code comments visibility in Swift
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        // Для включения документации
        compilations["main"].kotlinOptions.freeCompilerArgs += "-Xexport-kdoc"
    }

}

android {
    compileSdk = Versions.targetSdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
    }
}

sqldelight {
    database("SqlDatabase") {
        packageName = "com.turbosokol.locikmm"
    }
}