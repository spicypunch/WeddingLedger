package kr.jm.weddingledger.feature.auth.domain.usecase

import kr.jm.weddingledger.feature.auth.domain.model.AuthResult
import kr.jm.weddingledger.feature.auth.domain.repository.AuthRepository

class ResetPasswordUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String): AuthResult<Unit> {
        if (email.isBlank()) {
            return AuthResult.Error("이메일을 입력해주세요")
        }
        if (!email.contains("@")) {
            return AuthResult.Error("올바른 이메일 형식이 아닙니다")
        }
        return authRepository.resetPassword(email)
    }
}
