package com.turbosokol.iqmafiaapp.data.core

import kotlinx.serialization.Serializable

/***
 *If this code runs it was created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who created it.
 ***/

class ApiResponse<T>(
 val success: Boolean = false,
 val data: T?,
 val errorResponse: ErrorResponse?
)

@Serializable
data class ApiResponseEmpty(
 val success: Boolean = false,
 val errorResponse: ErrorResponse? = null
)

@Serializable
data class ErrorResponse(
 var message: String,
 val code: Int
)