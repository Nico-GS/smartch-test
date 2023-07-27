package com.app.routes

import com.app.data.RemoveDuplicatedRequest
import com.app.data.RemoveDuplicatedResponse
import com.app.exceptions.NoDuplicatesFoundException
import com.app.services.RemoveDuplicatedService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.logging.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

/**
 * Created by Nicolas Gros - 27/07/2023
 *
 * This class is the route definition for the [RemoveDuplicatedService]
 */
@Module
@ComponentScan
class RemoveDuplicatedRoute(
    private var service: RemoveDuplicatedService
)
{

    fun removeDuplicated(routing: Routing)
    {
        routing.route("/api/remove-duplicated")
        {
            post {

                // Serialize the request
                val request = call.receive<RemoveDuplicatedRequest>()

                try
                {
                    // Call the service and get the result
                    val result = service.removeDuplicated(request)
                    LOGGER.info("Result : $result")
                    call.respondWithJson(RemoveDuplicatedResponse(result))

                    /**
                     * If no duplicates is found, throw [NoDuplicatesFoundException]
                     */
                } catch (e: NoDuplicatesFoundException)
                {
                    call.respond(HttpStatusCode.BadRequest, e.message ?: "No change were made to the input list")
                }
            }
        }
    }


    // region Private Methods

    /**
     * Responds to the client with the specified object, converted to JSON.
     *
     * This method is necessary as otherwise, the return result in Postman is '{}'.
     */
    private suspend fun ApplicationCall.respondWithJson(response: RemoveDuplicatedResponse)
    {
        val json = Json { prettyPrint = true }
        this.respondText(json.encodeToString(response), ContentType.Application.Json)
    }

    companion object
    {
        private val LOGGER = KtorSimpleLogger("com.app.routes.RemoveDuplicatedRoute")
    }


    // endregion

}

