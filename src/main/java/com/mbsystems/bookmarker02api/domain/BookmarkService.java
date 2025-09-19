package com.mbsystems.bookmarker02api.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    @Transactional(readOnly = true)
    public BookmarksDTO getBookmarks(Integer page) {
        int pageNumber = page < 1 ? 0 : page - 1;

        Pageable pageable = PageRequest.of(pageNumber, 5, Sort.Direction.DESC, "createdAt");
        Page<BookmarkDTO> bookmarkPage = this.bookmarkRepository.findBookmarks(pageable);

        return new BookmarksDTO(bookmarkPage);
    }
}
