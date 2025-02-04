import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("java")
//    id("java-library") // api("org.springframework:spring-web")
    kotlin("jvm") version "1.9.22" // id("org.jetbrains.kotlin.jvm") version "1.9.22"

    id("maven-publish")
}

group = "com.example"
version = "0.1.0-rc"

repositories {
    mavenCentral()
}

dependencies {
    // // Choose: api("org.springframework:spring-webmvc") vs compileOnly("org.springframework:spring-webmvc")
    // compileOnly("org.springframework:spring-webmvc:6.2.2")
    // compileOnly("org.springframework.boot:spring-boot-autoconfigure:3.4.2")

    // test
    // testImplementation("org.springframework.boot:spring-boot-starter-test:3.4.2")
    testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
    testImplementation("io.mockk:mockk:1.13.12")
    testImplementation(kotlin("script-runtime"))
    // testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")

    // test application
    // testImplementation("org.springframework:spring-web:6.2.2")
    // testImplementation("org.springframework.boot:spring-boot-starter-web:3.4.2")
}

tasks.test {
    useJUnitPlatform()
}

kotlin{
    sourceSets {
        test {
            kotlin.srcDirs(listOf("src/test/kotlin"))
        }
    }
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}

// [2] Add task script to publish
publishing {
    publications {
        //
        create<MavenPublication>("capitalizing-util") {
            from(components["java"]) // One of java, web, javaPlatform 컴포넌트
            groupId = "com.github.kimdobbang" // github username required
            artifactId = "practice-capitalizing-util" // 아래와 똑같다.
//            artifactId = project.name
            version = project.version.toString()
        }
    }
}

tasks.named("publishToMavenLocal").configure {
    // assemble 작업 후 publishToMavenLocal 실행
    //  assemble: 프로젝트의 아티팩트를 생성합니다. (jar 파일 등)
    //  publishToMavenLocal: 빌드된 아티팩트를 로컬 저장소에 저장합니다. (.m2/repository)
    dependsOn("assemble")
}


// 이부분은 당장 필요하지는 않음
//tasks.named<Jar>("jar") {
//    enabled = true
//    archiveClassifier.set("") // remove suffix "-plain"
//    // 다운로드 하려는 파일 이름: capitalizing-util-0.1.0.jar
//    // 위 명령 누락 때 파일 이름(스프링부트 플러그인 있을 때): capitalizing-util-0.1.0-plain.jar
//}