package ai.hackaton.backend.dto;

import lombok.Builder;

@Builder
public record JwtAuthenticationResponse(
        String status,
        String token
) {
}
