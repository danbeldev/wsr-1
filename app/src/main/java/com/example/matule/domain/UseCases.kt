package com.example.matule.domain

import com.example.network.Network

class UseCases(
    private val network: Network
) {

    suspend fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onIsLoading: (Boolean) -> Unit,
        onFailed: () -> Unit,
        onNetworkFailed: () -> Unit
    ) {
        try {
            onIsLoading(true)
            val isSuc = network.login(
                email = email,
                password = password
            )
            if (isSuc) onSuccess() else onFailed()
        }catch (e: Exception) {
            onNetworkFailed()
        }finally {
            onIsLoading(false)
        }
    }
}