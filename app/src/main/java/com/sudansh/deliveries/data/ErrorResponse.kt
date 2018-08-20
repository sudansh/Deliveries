package com.sudansh.deliveries.data

class ErrorResponse(
        val status: String = "",
        var statusCode: Int = 0,
        var message: String = "",
        val desc: String = ""
)