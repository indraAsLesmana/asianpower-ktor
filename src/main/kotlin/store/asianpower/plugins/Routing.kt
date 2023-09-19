package store.asianpower.plugins

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.html.*
import store.asianpower.api.Product
import store.asianpower.api.fetchSearchResults

fun Application.configureRouting() {
    routing {
        var fetchData: List<Product> = listOf()
        GlobalScope.launch {
            fetchData = fetchSearchResults(query = "samsung")
            print(fetchData.first().name)
        }

        get("/") {
//            call.let {
//                fetchData = fetchSearchResults(query = "samsung")
//                print(fetchData.first().name)
//            }
            call.respondHtml {
                head {
                    title { +"Home Page" }
                }
                body {
                    h1 {
                        +"Welcome to the Homepage"
                    }
                    // search bar here
                    form(action = "#") {
                        input(type = InputType.text, name = "search") {
                            placeholder = "Search..."
                        }
                        input(type = InputType.submit) {
                            value = "Search"
                        }
                    }

                    // List of products
                    div(classes = "container") {
                        ul(classes = "home__brand") {
                            fetchData.forEach {
                                li {
                                    a(href = it.url) {
                                        img(src = it.img, alt = it.name)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
