package kr.jm.weddingledger.feature.auth.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.jm.weddingledger.feature.auth.domain.model.AuthResult
import kr.jm.weddingledger.feature.auth.domain.model.AuthUser

interface AuthRepository {
    val currentUser: Flow<AuthUser?>
    
    suspend fun signUp(email: String, password: String): AuthResult<AuthUser>
    suspend fun signIn(email: String, password: String): AuthResult<AuthUser>
    suspend fun signOut(): AuthResult<Unit>
    suspend fun resetPassword(email: String): AuthResult<Unit>
    fun isLoggedIn(): Boolean
}
