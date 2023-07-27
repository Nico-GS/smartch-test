package com.app.data

import kotlinx.serialization.Serializable

/**
 * Created by Nicolas Gros - 27/07/2023
 *
 * This data class represents a response from the RemoveDuplicatedService
 * It contains the output list of integers, which are the integers from the input list that appear 'n' times or less
 *
 * @property output The list of integers that appear 'n' times or less in the input list
 */
@Serializable
class RemoveDuplicatedResponse(
    private val output: List<Int>
)