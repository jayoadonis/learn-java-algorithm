
plugins {
    java
    application
}

project.version = "0.0.1"
project.group = "jayo.arb.learn_j"

val ROOT_PROJECT_NAME = project.rootProject.name
    .replace( Regex("[ .-]+"), "_" );
val MODULE_NAME = "${project.group}.${ROOT_PROJECT_NAME}.${project.name}";
project.application {
    this.mainClass.set(
        "${MODULE_NAME}.MainExe"
    )
    this.mainModule.set(
        MODULE_NAME
    )
}

project.dependencies {
    this.implementation( project( ":lib" ) );
}