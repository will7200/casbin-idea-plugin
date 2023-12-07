import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
    id("org.jetbrains.kotlin.jvm") version "1.6.0"
    // gradle-intellij-plugin - read mo
    // re: https://github.com/JetBrains/gradle-intellij-plugin
    id("org.jetbrains.intellij") version "1.10.2"
    // gradle-changelog-plugin - read more: https://github.com/JetBrains/gradle-changelog-plugin
    id("org.jetbrains.changelog") version "1.3.1"
}


group = pluginGroup
version = pluginVersion

repositories {
    mavenCentral()
    mavenLocal()
    maven(url = "https://www.jetbrains.com/intellij-repository/releases")
    maven(url = "https://www.jetbrains.com/intellij-repository/snapshots")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.casbin", "jcasbin", "1.32.3") {
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
    version.set(platformVersion)
    pluginName.set(rootProject.name)
    updateSinceUntilBuild.set(false)
    downloadSources.set(platformDownloadSources.toBoolean())
    sandboxDir.set("${project.projectDir}/sandbox/idea-sandbox")
    val devPlugins = listOf("PsiViewer:$psiViewerVersion")
    plugins.set(provider {
        if (env == "DEV") devPlugins else emptyList()
    })
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

changelog {
    version.set(pluginVersion)
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }

    patchPluginXml {
        version.set(pluginVersion)
        sinceBuild.set(pluginSinceBuild)
        // untilBuild.set(pluginUntilBuild)

        // Get the latest available change notes from the changelog file
        changeNotes.set(
            provider {
                changelog.getLatest().toHTML()
            }
        )
    }

    runPluginVerifier {
        ideVersions.set(pluginVerifierIdeVersions.split(",").map(String::trim).filter(String::isNotEmpty))
    }

    publishPlugin {
        dependsOn("patchChangelog")
        token.set(System.getenv("PUBLISH_TOKEN"))
        channels.set(listOf(pluginVersion.split('-').getOrElse(1) { "default" }.split('.').first()))
    }
}
