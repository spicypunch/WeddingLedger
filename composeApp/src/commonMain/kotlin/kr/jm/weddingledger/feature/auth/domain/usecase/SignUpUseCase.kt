package kr.jm.weddingledger.feature.auth.domain.usecase

import kr.jm.weddingledger.feature.auth.domain.model.AuthResult
import kr.jm.weddingledger.feature.auth.domain.model.AuthUser
import kr.jm.weddingledger.feature.auth.domain.repository.AuthRepository

class SignUpUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): AuthResult<AuthUser> {
        if (email.isBlank()) {
            return AuthResult.Error("이메일을 입력해주세요")
        }
        if (!email.contains("@")) {
            return AuthResult.Error("올바른 이메일 형식이 아닙니다")
        }
        if (password.length < 6) {
            return AuthResult.Error("비밀번호는 6자 이상이어야 합니다")
        }
        return authRepository.signUp(email, password)
    }
}
