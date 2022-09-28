plugins {
    java
    id("io.freefair.lombok") version "6.5.1"
    id( "jacoco")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "au.com.ailo.zombie.apocalypse"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("commons-codec:commons-codec:1.15")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.3")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("org.hamcrest:java-hamcrest:2.0.0.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<Jar> {
    manifest.attributes["Main-Class"] = "au.com.ailo.zombie.apocalypse.ZombieApocalypseApp"
}



tasks.withType<JacocoCoverageVerification> {
    violationRules {
        rule {
            limit {
                minimum = BigDecimal(0.62)
            }
        }
    }

    afterEvaluate {
        classDirectories.setFrom(files(classDirectories.files.map {
            fileTree(it).apply {
                exclude("au/com/ailo/zombie/apocalypse/types/**")
            }
        }))
    }
}

tasks.withType<JacocoReport> {
    afterEvaluate {
        classDirectories.setFrom(files(classDirectories.files.map {
            fileTree(it).apply {
                exclude("au/com/ailo/zombie/apocalypse/types/**")
            }
        }))
    }
}