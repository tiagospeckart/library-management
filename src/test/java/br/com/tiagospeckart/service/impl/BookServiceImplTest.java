package br.com.tiagospeckart.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.tiagospeckart.dto.mapper.BookMapper;
import br.com.tiagospeckart.models.Book;
import br.com.tiagospeckart.models.User;
import br.com.tiagospeckart.repository.BookRepository;
import br.com.tiagospeckart.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class BookServiceImplTest {

	@RunWith(MockitoJUnitRunner.class)
	public static class CheckBookingPossibilityTests {
		@Mock
		static BookRepository bookRepository;

		@Mock
		static BookMapper bookMapper;

		@Mock
		static UserRepository userRepository;

		@InjectMocks
		static BookServiceImpl service;
		User user;
		Book book;

		@Before
		public void setup() {
			user = new User();
			book = new Book();
		}

		@Test
		public void shouldBePossible() {
			// Arrange
			user.setIsPunished(false);
			book.setIsBorrowed(false);
			// Act
			boolean result = service.checkBookingPossibility(user, book);
			// Assert
			assertTrue(result);
		}

		@Test
		public void shouldThrowExceptionForNullBookAttribute() {
			// Arrange
			user.setIsPunished(false);
			book.setIsBorrowed(null);
			// Act
			IllegalArgumentException thrown = assertThrows(
					IllegalArgumentException.class,
					() -> service.checkBookingPossibility(user, book)
			);
			// Assert
			assertEquals("O livro não possui o atributo necessário", thrown.getMessage());
		}
	}

	@RunWith(MockitoJUnitRunner.class)
	public static class CalculateDiscountBasedOnPercentageTests {
		@Mock
		static BookRepository bookRepository;

		@Mock
		static BookMapper bookMapper;

		@Mock
		static UserRepository userRepository;

		@InjectMocks
		static BookServiceImpl service;
		Book book;
		Double percentage;

		@Before
		public void setup() {
			book = new Book();
		}

		@Test
		public void shouldCalculateCorrectDiscount() {
			// Arrange
			book.setCost(100.0F);
			Double percentage = 50.0;
			// Act
			Double calculatedDiscount = service.calculateDiscountBasedOnPercentage(book, percentage);
			// Assert
			assertEquals(50.0, calculatedDiscount, 0.01);
		}

		@Test
		public void shouldReturnZeroForZeroCost() {
			// Arrange
			book.setCost(0.0F);
			// Act & Assert
			assertEquals(0.0, service.calculateDiscountBasedOnPercentage(book, 10.0), 0.01);
		}
	}

	@RunWith(MockitoJUnitRunner.class)
	public static class GetUsersResponsibleForBorrowedTests {
		@Mock
		static BookRepository bookRepository;

		@Mock
		static BookMapper bookMapper;

		@Mock
		static UserRepository userRepository;

		@InjectMocks
		static BookServiceImpl service;
		List<Book> books;
		List<User> users;

		@Before
		public void setup() {
			books = new ArrayList<>();
			users = new ArrayList<>();
		}

		@Test
		public void shouldReturnEmptyListWhenNoBooks() {
			// Arrange
			// books list is empty

			// Act
			List<User> result = service.getUsersResponsibleForBorrowed(books);

			// Assert
			assertTrue(result.isEmpty());

		}

		@Test
		public void shouldReturnEmptyListWhenNoBorrowedBooks() {
			// Arrange
			Book book = new Book();
			book.setIsBorrowed(false);
			books.add(book);
			// Act
			List<User> result = service.getUsersResponsibleForBorrowed(books);
			// Assert
			assertTrue(result.isEmpty());

		}

		@Test
		public void shouldReturnEmptyListWhenBorrowedBooksHaveNoUser() {
			// Arrange
			Book book = new Book();
			book.setIsBorrowed(true);
			books.add(book);
			// Act
			List<User> result = service.getUsersResponsibleForBorrowed(books);

			// Assert
			assertTrue(result.isEmpty());

		}

		@Test
		public void shouldReturnCorrectResponsibleUsers() {
			// Arrange
			User user = new User();
			Book book1 = new Book();
			book1.setIsBorrowed(true);
			book1.setUser(user);

			Book book2 = new Book();
			book2.setIsBorrowed(false);

			books.add(book1);
			books.add(book2);

			// Act
			List<User> result = service.getUsersResponsibleForBorrowed(books);

			// Assert
			assertEquals(1, result.size());
			assertTrue(result.contains(user));
		}
	}

	@RunWith(MockitoJUnitRunner.class)
	public static class CountNumberOfBorrowedBooksTests{
		@Mock
		static BookRepository bookRepository;

		@Mock
		static BookMapper bookMapper;

		@Mock
		static UserRepository userRepository;

		@InjectMocks
		static BookServiceImpl service;
		List<Book> books;

		@Before
		public void setup() {
			books = new ArrayList<>();
		}

		@Test
		public void shouldThrowExceptionForNullBooks(){
			books = null;

			// Act
			IllegalArgumentException thrown = assertThrows(
					IllegalArgumentException.class,
					() -> service.countNumberOfBorrowedBooks(books)
			);
			// Assert
			assertEquals("Nenhum livro foi encontrado", thrown.getMessage());
		};

		@Test
		public void shouldThrowExceptionForEmptyBooks(){
			IllegalArgumentException thrown = assertThrows(
					IllegalArgumentException.class,
					() -> service.countNumberOfBorrowedBooks(books)
			);
			// Assert
			assertEquals("Nenhum livro foi encontrado", thrown.getMessage());
		};

		@Test
		public void shouldReturnCorrectNumberOfBooks(){
			// Preparação
			List<Book> books = new ArrayList<>(); // assegura que a lista está em um estado conhecido
			Book book1 = new Book();
			book1.setIsBorrowed(true);
			books.add(book1);

			Book book2 = new Book();
			book2.setIsBorrowed(false);
			books.add(book2);

			// Ação
			Long bookCount = service.countNumberOfBorrowedBooks(books);

			// Verificação
			assertEquals(1L, bookCount.longValue());
		}

	}
}
