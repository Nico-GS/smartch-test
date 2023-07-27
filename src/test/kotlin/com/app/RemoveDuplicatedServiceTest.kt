package com.app

import com.app.data.RemoveDuplicatedRequest
import com.app.exceptions.NoDuplicatesFoundException
import com.app.services.RemoveDuplicatedService
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.fail

/**
 * Created by Nicolas Gros - 27/07/2023
 */
class RemoveDuplicatedServiceTest
{

    // region Injection

    private val service = RemoveDuplicatedService()

    // endregion

    /**
     * This test verifies that the [RemoveDuplicatedService] correctly removes duplicated integers from the input list
     *
     * Create a new [RemoveDuplicatedRequest] with a specific input list and n value
     * Call the [RemoveDuplicatedService.removeDuplicated] service with this request,
     * and check that the output list is what's expected
     */
    @Test
    fun `Should remove duplicated correctly`()
    {
        val request = RemoveDuplicatedRequest(listOf(1, 3, 8, 8, 1, 1, 1, 3, 5, 2), 1)

        // Expected only 5 & 2 appear once or less
        val expected = listOf(5, 2)

        val result = service.removeDuplicated(request)

        assertEquals(expected, result)
    }

    /**
     * This test verifies that the [RemoveDuplicatedService] correctly handles an input list with no duplicates.
     *
     * Create a new [RemoveDuplicatedRequest] with a specific input list and n value
     * Call the [RemoveDuplicatedService.removeDuplicated] service with this request,
     * and check if the [NoDuplicatesFoundException] is thrown
     */
    @Test
    fun `Should handle input with no duplicates orrectly`()
    {
        val request = RemoveDuplicatedRequest(listOf(1, 2, 3, 4, 5), 2)
        try
        {
            service.removeDuplicated(request)
            fail("Test failed")
        } catch (e: NoDuplicatesFoundException)
        {
            // If the exception is thrown, the test is a success
            assertEquals("No change were made to the input list", e.message)
        }
    }
}