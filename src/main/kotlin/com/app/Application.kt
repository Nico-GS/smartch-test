package com.app

import com.app.routes.RemoveDuplicatedRoute
import com.app.services.RemoveDuplicatedService
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import org.koin.dsl.module
import org.koin.ktor.ext.get
import org.koin.ktor.plugin.Koin


/**
 * Start the server on port 8080, see application.conf
 */
fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module()
{
    install(Koin)
    {
        modules(appModule)
    }

    install(ContentNegotiation)
    {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }


    install(Routing)
    {
        // Route to duplicated service
        get<RemoveDuplicatedRoute>().removeDuplicated(this)
    }

}


val appModule = module {
    single<RemoveDuplicatedService> { RemoveDuplicatedService() }
    single<RemoveDuplicatedRoute> { RemoveDuplicatedRoute() }
}
