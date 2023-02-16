plugins {
	java
	id("org.springframework.boot") version "3.0.2"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot", "spring-boot-starter-webflux")
	implementation("org.springframework.boot", "spring-boot-starter-data-jpa")
	implementation("org.axonframework", "axon-spring-boot-starter", "${findProperty("axon.version")}") {
		exclude("org.axonframework", "axon-server-connector")
	}
	implementation("com.mysql", "mysql-connector-j")
	implementation("org.quartz-scheduler", "quartz", "${findProperty("quartz.version")}")
	implementation("com.alibaba.fastjson2", "fastjson2", "${findProperty("fastjson.version")}")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
