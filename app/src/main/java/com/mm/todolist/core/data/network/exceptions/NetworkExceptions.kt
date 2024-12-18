package com.mm.todolist.core.data.network.exceptions

class FailResponseException(message: String?) : Exception(message ?: "Failed to call network")

class EmptyResponseException : Exception("Empty response!")

class GeneralException(message: String?) : RuntimeException(message ?: "Something went wrong!")

class NoNetworkException(message: String?) : Exception(message ?: "Something went wrong!")
