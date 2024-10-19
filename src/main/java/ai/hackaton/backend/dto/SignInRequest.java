package ai.hackaton.backend.dto;

public record SignInRequest(
        String email,
        String password
) {
}
