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
     */
    fun removeDuplicated(request: RemoveDuplicatedRequest): List<Int>
    {
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

}