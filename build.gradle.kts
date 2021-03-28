import org.jetbrains.changelog.closure
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

// Import variables from gradle.properties file
val pluginGroup: String by project
val pluginName: String by project
val pluginVersion: String by project
val pluginSinceBuild: String by project
val pluginUntilBuild: String by project
val platformType: String by project
val platformVersion: String by project
val platformDownloadSources: String by project
val pluginVerifierIdeVersions: String by project

val psiViewerVersion: String by project
val env: String? = System.getenv("CASBIN_ENV")

plugins {
    idea
    // Java support
    id("java")
    // Kotlin support
    id("org.jetbrains.kotlin.jvm") version "1.4.31"
    // gradle-intellij-plugin - read more: https://github.com/JetBrains/gradle-intellij-plugin
    id("org.jetbrains.intellij") version "0.7.2"
    // gradle-changelog-plugin - read more: https://github.com/JetBrains/gradle-changelog-plugin
    id("org.jetbrains.changelog") version "1.1.2"
}


group = pluginGroup
version = pluginVersion

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.casbin", "jcasbin", "1.7.4") {
        exclude("org.slf4j")
    }
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
    implementation("io.github.java-diff-utils:java-diff-utils:4.9")
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version = platformVersion
    pluginName = rootProject.name
    updateSinceUntilBuild = false
    downloadSources = platformDownloadSources.toBoolean()
    sandboxDirectory = "${project.projectDir}/sandbox/idea-sandbox"
    val devPlugins = arrayOf("PsiViewer:$psiViewerVersion")
    setPlugins(*if (env == "DEV") devPlugins else emptyArray())
}

sourceSets {
    main {
        resources.srcDirs("resources")
        java.srcDirs("src/java", "gen")

        withConvention(KotlinSourceSet::class) {
            // Configure Kotlin SourceDirectorySet
            kotlin.srcDirs("src/kotlin")
        }
    }
    test {
        resources.srcDirs("test/resources")
        java.srcDirs("test/java")

        withConvention(KotlinSourceSet::class) {
            // Configure Kotlin SourceDirectorySet
            kotlin.srcDirs("test/kotlin")
        }
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    patchPluginXml {
        version(pluginVersion)
        sinceBuild(pluginSinceBuild)
        untilBuild(pluginUntilBuild)

        // Get the latest available change notes from the changelog file
        changeNotes(
            closure {
                changelog.getLatest().toHTML()
            }
        )
    }

    runPluginVerifier {
        ideVersions(pluginVerifierIdeVersions)
    }

    publishPlugin {
        dependsOn("patchChangelog")
        token(System.getenv("PUBLISH_TOKEN"))
        channels(pluginVersion.split('-').getOrElse(1) { "default" }.split('.').first())
    }
}
