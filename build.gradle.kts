import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

group = "org.kostd"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	//mavenCentral()
	jcenter()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-graphql")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("com.h2database:h2")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	// в доках Спринга пишут, что  JSR-330 поддерживаются начиная с core версии 3. У нас boot 2.7, который
	// тянет core 5.x, так что должно работать. Поиграемся, хотя жесткого coupling`а со spring избежать
	// не удастся, пока используем @Repository, @Controller и другие специфичные аннотации.
	implementation("javax.inject:javax.inject:1")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework:spring-webflux")
	testImplementation("org.springframework.graphql:spring-graphql-test")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
