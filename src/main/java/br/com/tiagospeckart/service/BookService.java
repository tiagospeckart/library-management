package br.com.tiagospeckart.service;

import java.util.List;
import java.util.UUID;

import br.com.tiagospeckart.dto.BookDto;
import br.com.tiagospeckart.models.Book;

public interface BookService {

	BookDto insertBook(BookDto bookDto);
	List<BookDto> getBooks();
	List<BookDto> getBooksSameAuthorAndName(List<Book> books, String name, String author);
	List<BookDto> getBooksSameName(List<Book> books, String name);
	List<BookDto> getBooksSameAuthor(List<Book> books, String author);
	BookDto getBookById(UUID id);
	BookDto updateBook(BookDto newbookDto, UUID id);
	void deleteBook(UUID id);
}
