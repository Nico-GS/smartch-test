package com.app

import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.ktor.util.*
import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * Created by Nicolas Gros - 27/07/2023
 */
class RemoveDuplicatedRouteTest
{

    /**
     * This test checks that the remove-duplicated endpoint behaves correctly.
     * It sends a POST request to the endpoint with a specific input and 'n' value in the request body.
     * The endpoint is expected to return a response with HTTP status 200 (OK),
     * and a body containing the expected output list.
     *
     * @OptIn(InternalAPI::class) is used to allow the use of Ktor's internal API
     */
    @OptIn(InternalAPI::class)
    @Test
    fun `Should return correct result for remove duplicated endpoint`() = testApplication {

        val response = client.post("/api/remove-duplicated") {
            contentType(ContentType.Application.Json)
            body = """{"input": [1, 3, 8, 8, 1, 1, 1, 3, 5, 2],"n": 1}"""
        }

        assertEquals(HttpStatusCode.OK, response.status)

        val expected = ObjectMapper().readTree("""{"output":[5,2]}""")
        val actual = ObjectMapper().readTree(response.bodyAsText())

        assertEquals(expected, actual)
    }

}