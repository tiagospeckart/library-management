package br.com.tiagospeckart.service;

import java.util.List;
import java.util.UUID;

import br.com.tiagospeckart.dto.LibraryDto;

public interface LibraryService {

	LibraryDto insertLibrary(LibraryDto libraryDto);
	List<LibraryDto> getLibraries();
	LibraryDto getLibraryById(UUID id);
	LibraryDto updateLibrary(LibraryDto newLibraryDto, UUID id);
	void deletLibrary(UUID id);
}
