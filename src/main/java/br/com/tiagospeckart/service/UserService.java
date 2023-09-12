package br.com.tiagospeckart.service;

import java.util.List;
import java.util.UUID;

import br.com.tiagospeckart.dto.UserDto;

public interface UserService {

	UserDto insertUser(UserDto userDto);
	List<UserDto> getAllUsers();
	UserDto getUserById(UUID id);
	UserDto updateUser(UserDto newUserDto, UUID id);
	void deletUser(UUID id);

    
}
