package com.mbsystems.bookmarker02api.domain;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BookmarkMapper implements Function<Bookmark, BookmarkDTO> {

    @Override
    public BookmarkDTO apply(Bookmark bookmark) {
        return new BookmarkDTO(
                bookmark.getId(),
                bookmark.getTitle(),
                bookmark.getUrl(),
                bookmark.getCreatedAt()
        );
    }
}
