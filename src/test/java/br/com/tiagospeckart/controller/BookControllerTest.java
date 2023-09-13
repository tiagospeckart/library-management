package br.com.tiagospeckart.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.tiagospeckart.dto.BookDto;
import br.com.tiagospeckart.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains unit tests for the BookController.
 * It uses the Mockito framework for mocking dependencies.
 */
@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
    @Mock
    BookService bookService;

    @InjectMocks
    BookController bookController;

    @Autowired
    MockMvc mockMvc;

    /**
     * Sets up the testing environment before each test case.
     * Initializes the MockMvc object with the mock BookController.
     */
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    /**
     * Test case for the scenario where a new book is added.
     *
     * <p>
     * The test initializes a mock BookDto object and mock BookService.
     * It then sends a POST request to the "/books" endpoint with the
     * mock BookDto object as the request body. The test asserts that
     * the HTTP response status is 200 OK and the response body matches
     * the mock BookDto object.
     * </p>
     *
     * @throws Exception if an exception occurs during the test execution
     */
    @Test
    void testAddBook() throws Exception {
        BookDto bookDto = new BookDto();
        when(bookService.insertBook(any())).thenReturn(bookDto);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(bookDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(bookDto)));

        verify(bookService, times(1)).insertBook(any());
    }

    /**
     * Test case for retrieving all books.
     *
     * <p>
     * The test initializes a mock list of BookDto objects and mock BookService.
     * It then sends a GET request to the "/books" endpoint. The test asserts that
     * the HTTP response status is 200 OK and the response body matches the mock
     * list of BookDto objects.
     * </p>
     *
     * @throws Exception if an exception occurs during the test execution
     */
    @Test
    void testAll() throws Exception {
        List<BookDto> books = new ArrayList<>();
        // ... initialize your books list
        when(bookService.getBooks()).thenReturn(books);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(books)));

        verify(bookService, times(1)).getBooks();
    }
}
