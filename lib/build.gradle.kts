plugins {
    `java-library`
}

project.version = "0.0.1"
project.group = "jayo.arb.learn-j"

val CHAR_PKG_SPACE = "_";
val REGEX_PKG_SPACE_COMPILE = Regex( "[\\/ *+-]+" );
val REGEX_TRIM_COMPILE = Regex( "(^[ ._-]+)|([ ._-]+$)" );
val PROJECT_GROUP = project.group.toString()
    .replace( REGEX_PKG_SPACE_COMPILE, CHAR_PKG_SPACE )
    .replace( REGEX_TRIM_COMPILE, "" )
    .lowercase();
val ROOT_PROJECT_NAME = project.rootProject.name
    .replace( REGEX_PKG_SPACE_COMPILE, CHAR_PKG_SPACE )
    .replace( REGEX_TRIM_COMPILE, "" )
    .lowercase();
val PROJECT_NAME = project.name
    .replace( REGEX_PKG_SPACE_COMPILE, CHAR_PKG_SPACE )
    .replace( REGEX_TRIM_COMPILE, "" )
    .lowercase();
val PROJECT_COMPOUND_NAME = "${
        ROOT_PROJECT_NAME.replace( Regex( CHAR_PKG_SPACE ), "-")
    }-${
        PROJECT_NAME.replace( Regex( CHAR_PKG_SPACE ), "-" )
    }"
val MODULE_NAME = "${PROJECT_GROUP}.${ROOT_PROJECT_NAME}_${PROJECT_NAME}";

project.dependencies {

}

project.tasks.withType<Zip>() {
    this.archiveBaseName.set( PROJECT_COMPOUND_NAME );
}

project.tasks.withType<Tar>() {
    this.archiveBaseName.set( PROJECT_COMPOUND_NAME );
}

project.tasks.withType<Jar>() {
    this.archiveBaseName.set( PROJECT_COMPOUND_NAME );
}