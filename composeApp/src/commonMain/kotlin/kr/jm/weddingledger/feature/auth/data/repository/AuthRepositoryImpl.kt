package kr.jm.weddingledger.feature.auth.data.repository

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.jm.weddingledger.feature.auth.domain.model.AuthResult
import kr.jm.weddingledger.feature.auth.domain.model.AuthUser
import kr.jm.weddingledger.feature.auth.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val supabaseClient: SupabaseClient
) : AuthRepository {

    override val currentUser: Flow<AuthUser?>
        get() = supabaseClient.auth.sessionStatus.map { status ->
            when (status) {
                is SessionStatus.Authenticated -> {
                    status.session.user?.let { user ->
                        AuthUser(
                            id = user.id,
                            email = user.email ?: "",
                            createdAt = user.createdAt?.toString()
                        )
                    }
                }
                else -> null
            }
        }

    override suspend fun signUp(email: String, password: String): AuthResult<AuthUser> {
        return try {
            supabaseClient.auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            val user = supabaseClient.auth.currentUserOrNull()
            if (user != null) {
                AuthResult.Success(
                    AuthUser(
                        id = user.id,
                        email = user.email ?: "",
                        createdAt = user.createdAt?.toString()
                    )
                )
            } else {
                AuthResult.Error("회원가입에 실패했습니다")
            }
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "회원가입 중 오류가 발생했습니다")
        }
    }

    override suspend fun signIn(email: String, password: String): AuthResult<AuthUser> {
        return try {
            supabaseClient.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            val user = supabaseClient.auth.currentUserOrNull()
            if (user != null) {
                AuthResult.Success(
                    AuthUser(
                        id = user.id,
                        email = user.email ?: "",
                        createdAt = user.createdAt?.toString()
                    )
                )
            } else {
                AuthResult.Error("로그인에 실패했습니다")
            }
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "로그인 중 오류가 발생했습니다")
        }
    }

    override suspend fun signOut(): AuthResult<Unit> {
        return try {
            supabaseClient.auth.signOut()
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "로그아웃 중 오류가 발생했습니다")
        }
    }

    override suspend fun resetPassword(email: String): AuthResult<Unit> {
        return try {
            supabaseClient.auth.resetPasswordForEmail(email)
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "비밀번호 재설정 이메일 전송에 실패했습니다")
        }
    }

    override fun isLoggedIn(): Boolean {
        return supabaseClient.auth.currentUserOrNull() != null
    }
}
