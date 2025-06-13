package com.example.Tomoto.domain.user.dto.request;

import java.util.List;

public record ChallengeUpdateReq(
        List<Boolean> challenges
) {
}
