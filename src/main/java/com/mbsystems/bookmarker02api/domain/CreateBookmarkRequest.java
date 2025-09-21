package com.mbsystems.bookmarker02api.domain;

import jakarta.validation.constraints.NotBlank;

public record CreateBookmarkRequest(
        @NotBlank(message = "Title should not be empty") String title,
        @NotBlank(message = "Url should not be empty") String url) {
}
