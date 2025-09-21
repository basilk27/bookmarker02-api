package com.mbsystems.bookmarker02api.domain;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.function.Function;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final BookmarkMapper bookmarkMapper;
    private static final Function<Integer, Integer> getPageNumber = page -> page < 1 ? 0 : page - 1;

    @Transactional(readOnly = true)
    public BookmarksDTO getBookmarks(Integer page) {
        int pageNumber = getPageNumber.apply(page);

        Pageable pageable = PageRequest.of(pageNumber, 5, Sort.Direction.DESC, "createdAt");
        Page<BookmarkDTO> bookmarkPage = this.bookmarkRepository.findBookmarks(pageable);

        return new BookmarksDTO(bookmarkPage);
    }

    @Transactional(readOnly = true)
    public BookmarksDTO searchBookmarks(Integer page, String query) {
        int pageNumber = getPageNumber.apply(page);

        Pageable pageable = PageRequest.of(pageNumber, 5, Sort.Direction.DESC, "createdAt");

        Page<BookmarkDTO> bookmarkPage = this.bookmarkRepository.searchBookmarks(query, pageable);

        return new BookmarksDTO(bookmarkPage);
    }

    public BookmarkDTO createBookmark(@Valid CreateBookmarkRequest request) {
        Bookmark bookmark = new Bookmark(null, request.title(), request.url(), Instant.now());

        Bookmark savedBookmark = this.bookmarkRepository.save(bookmark);

        return bookmarkMapper.apply(savedBookmark);
    }
}
