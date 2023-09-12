package br.com.tiagospeckart.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.tiagospeckart.dto.mapper.BookMapper;
import br.com.tiagospeckart.repository.BookRepository;
import br.com.tiagospeckart.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import br.com.tiagospeckart.models.Book;
import br.com.tiagospeckart.models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Test class for the {@link BookServiceImpl}.
 */
@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

	@Mock
	BookRepository bookRepository;

	@Mock
	BookMapper bookMapper;

	@Mock
	UserRepository userRepository;

	@InjectMocks
	BookServiceImpl service;

	/**
	 * Tests whether it's possible to book a given book.
	 * <p>
	 * This test asserts that when a user is not punished and the book is available,
	 * the booking should be possible.
	 * </p>
	 */
	@Test
	public void checkBookingPossibility_shouldBePossible() {
		// Arrange
		User user = new User();
		user.setIsPunished(false);

		Book book = new Book();
		book.setIsBorrowed(false);

		// Act
		boolean result = service.checkBookingPossibility(user, book);

		// Assert
        assertTrue(result);
	}

	/**
	 * Tests whether an {@link IllegalArgumentException} is thrown when the 'isBorrowed' attribute of the book is null.
	 * <p>
	 * This test asserts that when the 'isBorrowed' attribute of a book is set to null, the method should throw an
	 * {@link IllegalArgumentException} with a specific error message.
	 * </p>
	 */
	@Test
	public void checkBookingPossibility_shouldThrowExceptionForNullBookAttribute() {
		// Arrange
		User user = new User();
		user.setIsPunished(false);

		Book book = new Book();
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
