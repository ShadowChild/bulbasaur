plugins {
    kotlin("multiplatform") version "1.8.10"
    application
}

fun kotlinw(target: String): String =
    "org.jetbrains.kotlin-wrappers:kotlin-$target"

val kWrapperVer = "1.0.0-pre.515"
val ktorVersion = "2.2.3"
val jcefVersion = "110.0.25"
val cybernizeVersion = "1.1.6"
val logbackVersion = "1.4.6"

group = "co.uk.innoxium"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
    maven("https://repo.repsy.io/mvn/innoxium/innoxium")
}

kotlin {
    jvm {
        jvmToolchain(19)
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
        }
    }
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                // My Common Library
                implementation("uk.co.innoxium.cybernize:cybernize:${cybernizeVersion}")

                // JCeF for desktop client
                implementation("me.friwi:jcefmaven:$jcefVersion")

                // Ktor and other server related packages
                implementation("io.ktor:ktor-server-netty:$ktorVersion")
                implementation("io.ktor:ktor-server-html-builder-jvm:$ktorVersion")
                implementation("io.ktor:ktor-server-status-pages:$ktorVersion")
                implementation("io.ktor:ktor-server-auth:$ktorVersion")
                implementation("ch.qos.logback:logback-classic:$logbackVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.2")
            }
        }
        val jvmTest by getting
        val jsMain by getting {
            dependencies {
                implementation(enforcedPlatform(kotlinw("wrappers-bom:$kWrapperVer")))
                implementation(kotlinw("react"))
                implementation(kotlinw("react-dom"))
                implementation(kotlinw("emotion"))
                implementation(kotlinw("react-router-dom"))
                implementation(kotlinw("redux"))
                implementation(kotlinw("react-redux"))
                implementation(kotlinw("mui"))
                implementation(kotlinw("mui-icons"))
            }
        }
        val jsTest by getting
    }
}

application {
    mainClass.set("co.uk.innoxium.bulbasaur.DesktopServerKt")
}

tasks.named<Copy>("jvmProcessResources") {
    val jsBrowserDistribution = tasks.named("jsBrowserDistribution")
    from(jsBrowserDistribution)
}

tasks.named<JavaExec>("run") {
    dependsOn(tasks.named<Jar>("jvmJar"))
    classpath(tasks.named<Jar>("jvmJar"))
}