import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	id("org.springframework.boot") version "3.1.5"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
}
allprojects {
	group = "com.example"
	version = "0.0.1-SNAPSHOT"

	java {
		sourceCompatibility = JavaVersion.VERSION_17
	}

	repositories {
		mavenCentral()
	}

	subprojects {
		apply(plugin = "org.springframework.boot")
		apply(plugin = "io.spring.dependency-management")

		apply(plugin = "org.jetbrains.kotlin.jvm")
		apply(plugin = "org.jetbrains.kotlin.plugin.spring")

		configurations {
			compileOnly {
				extendsFrom(configurations.annotationProcessor.get())
			}
		}

		dependencies {
			implementation("org.springframework.boot:spring-boot-starter-web")
			implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
			implementation("org.jetbrains.kotlin:kotlin-reflect")
			implementation("org.springframework.kafka:spring-kafka")
			implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

			testImplementation("org.springframework.boot:spring-boot-starter-test")
			developmentOnly("org.springframework.boot:spring-boot-devtools")
			testImplementation("org.springframework.boot:spring-boot-starter-test")
		}

		tasks.withType<KotlinCompile> {
			kotlinOptions {
				freeCompilerArgs += "-Xjsr305=strict"
				jvmTarget = "17"
			}
		}

		tasks.getByName<BootJar>("bootJar") {
			enabled = false
		}

		tasks.getByName<Jar>("jar") {
			enabled = true
		}

		tasks.withType<Test> {
			useJUnitPlatform()
		}
	}
}