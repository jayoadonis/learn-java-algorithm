plugins {
    `java-library`
}

project.version = "0.0.1"
project.group = "jayo.arb.learn_j"

val ROOT_PROJECT_NAME = project.rootProject.name
    .replace( Regex("[ .-]+"), "_" );