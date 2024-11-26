package com.mm.todolist.core.data.helper

import com.mm.todolist.core.data.network.exceptions.GeneralException
import com.mm.todolist.core.data.network.exceptions.NoNetworkException
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> getResultOrThrow(
    caller: suspend () -> T,
): T {
    try {
        return caller()
    } catch (e: HttpException) {
        throw GeneralException(e.message ?: "Unknown Http error")
    } catch (e: IOException) {
        throw NoNetworkException("No internet connection")
    } catch (e: Exception) {
        throw GeneralException(e.message ?: "Something went wrong")
    }
}
