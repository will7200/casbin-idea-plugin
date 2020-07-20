import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

val psiViewerVersion: String by project
val env: String? = System.getenv("CASBIN_ENV")

plugins {
    java
    idea
    id("org.jetbrains.intellij") version "0.4.21"
    kotlin("jvm") version "1.3.72"
}

group = "io.github.will7200.plugins.casbin"
version = "0.1.0-Alpha"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.casbin", "jcasbin", "1.6.0")
    testCompile("junit", "junit", "4.12")
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version = "2020.1.2"
    pluginName = rootProject.name
    updateSinceUntilBuild = true
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
//sourceSets {
//    main {
//        java {
//            srcDirs 'src/java', 'gen'
//        }
//        kotlin {
//            srcDirs 'src/kotlin'
//        }
//        resources {
//            srcDir 'res'
//        }
//    }
//    test {
//        java {
//            srcDir 'test'
//        }
//        resources {
//            srcDir 'test-data'
//        }
//    }
//}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
tasks.getByName<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
    changeNotes(
        """
      Add change notes here.<br>
      <em>most HTML tags may be used</em>"""
    )
}
