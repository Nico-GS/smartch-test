package com.app.services

import com.app.data.RemoveDuplicatedRequest
import com.app.exceptions.NoDuplicatesFoundException
import org.koin.core.annotation.Single

/**
 * Created by Nicolas Gros - 27/07/2023
 */
@Single
class RemoveDuplicatedService()
{

    /**
     * This function takes a [RemoveDuplicatedRequest] containing a list of integers and a number n
     * It returns a list of integers that appear n times or less in the input list
     *
     * @param request The request containing the input list of integers and the number n
     * @return A list of integers that appear n times or less in the input list
     * @throws IllegalArgumentException If the input list is empty or the maximum occurrence count is negative.
     * @throws NoDuplicatesFoundException If no duplicates are found in the input list.
     */
    fun removeDuplicated(request: RemoveDuplicatedRequest): List<Int>
    {

        /**
         * Validate the request before processing it
         */
        this.validatePayload(request)

        val frequencyMap = request.input.groupingBy { it }.eachCount()
        val result = frequencyMap.filterValues { it <= request.n }.keys.toList()

        /**
         * Throw [NoDuplicatesFoundException] if no changes
         */
        if (result == request.input)
        {
            throw NoDuplicatesFoundException("No change were made to the input list")
        }
        return result
    }

    // region Private methods

    /**
     * Validates the request payload
     *
     * @param request The request to validate
     * @throws IllegalArgumentException If the input list is empty or the maximum occurrence count is negative
     */
    private fun validatePayload(request: RemoveDuplicatedRequest)
    {
        require(request.input.isNotEmpty()) { "Input can't be empty" }
        require(request.n >= 0) { "N must be positive" }
    }

    // endregion

}