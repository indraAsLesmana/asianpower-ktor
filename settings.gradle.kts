rootProject.name = "asian power store"
fun module(group: String, name: String) {
    include(name)
    project(":$name").projectDir = file("$group/$name")
}

//module("snippets", "auth-basic")
//module("snippets", "e2e")