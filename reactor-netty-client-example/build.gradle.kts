plugins {
    java
}

group = "com.github.shoothzj"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":http-common"))
    implementation("io.projectreactor.netty:reactor-netty-core:1.0.14")
    implementation("io.projectreactor.netty:reactor-netty-http:1.0.14")
    testImplementation("org.mock-server:mockserver-netty:5.11.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}