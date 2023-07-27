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

/**
 * Created by Nicolas Gros - 27/07/2023
 *
 * This class is the route definition for the [RemoveDuplicatedService]
 */
fun Route.removeDuplicatedInt(service: RemoveDuplicatedService)
{
    route("/api/remove-duplicated")
    {
        post {

            // Serialize the request
            val request = call.receive<RemoveDuplicatedRequest>()

            val json = kotlinx.serialization.json.Json { prettyPrint = true }

            try
            {
                // Call the service and get the result
                val result = service.removeDuplicated(request)
                LOGGER.info("Result : $result")
                call.respondText(json.encodeToString(RemoveDuplicatedResponse(result)), ContentType.Application.Json)

            } catch (e: NoDuplicatesFoundException)
            {
                call.respond(HttpStatusCode.BadRequest, e.message ?: "No change were made to the input list")
            }
        }
    }
}

internal val LOGGER = KtorSimpleLogger("com.app.routes.RemoveDuplicatedRoute")
