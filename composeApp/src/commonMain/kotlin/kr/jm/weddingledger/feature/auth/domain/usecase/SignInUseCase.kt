package kr.jm.weddingledger.feature.auth.domain.usecase

import kr.jm.weddingledger.feature.auth.domain.model.AuthResult
import kr.jm.weddingledger.feature.auth.domain.model.AuthUser
import kr.jm.weddingledger.feature.auth.domain.repository.AuthRepository

class SignInUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): AuthResult<AuthUser> {
        if (email.isBlank()) {
            return AuthResult.Error("이메일을 입력해주세요")
        }
        if (password.isBlank()) {
            return AuthResult.Error("비밀번호를 입력해주세요")
        }
        return authRepository.signIn(email, password)
    }
}
