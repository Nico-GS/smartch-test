package com.app.data

import kotlinx.serialization.Serializable

/**
 * Created by Nicolas Gros - 27/07/2023
 *
 * This data class represents a request to the RemoveDuplicatedService
 * It is used to specify the input list of integers and the number 'n'
 *
 * @property input The list of integers to be processed
 * @property n The number that will determine which integers to keep in the output list. An integer will be kept if it appears 'n' times or less in the 'input' list
 */
@Serializable
class RemoveDuplicatedRequest(
    val input: List<Int>,
    val n: Int
)