package br.com.tiagospeckart.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tiagospeckart.dto.UserDto;
import br.com.tiagospeckart.dto.mapper.UserMapper;
import br.com.tiagospeckart.exceptions.NotFoundException;
import br.com.tiagospeckart.models.User;
import br.com.tiagospeckart.repository.UserRepository;
import br.com.tiagospeckart.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	public UserDto insertUser(UserDto userDto) {
		final User userEntity = userMapper.toEntity(userDto);
		final User userSaved = userRepository.save(userEntity);
		UserDto dto = userMapper.toDto(userSaved);
		
		return dto;
	}
	
	public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDto(users);
    }
	
	public UserDto getUserById(UUID id) {
		Optional<User> userOpt = userRepository.findById(id);
		
		if(userOpt.isPresent()) {
			UserDto dto = userMapper.toDto(userOpt.get());
			return dto;
		}
		
		throw new NotFoundException();
	}
	
	public UserDto updateUser(UserDto newUserDto, UUID id) {
		Optional<User> userOpt = userRepository.findById(id);
		User user = userOpt.orElseThrow();
		
		user.setName(newUserDto.name);
		user.setUsername(newUserDto.username);
		user.setPassword(newUserDto.password);
		user.setIsPunished(newUserDto.isPunished);
		
		User userSaved = userRepository.save(user);
		UserDto dto = userMapper.toDto(userSaved);
		
		return dto;
	}
	
	public void deletUser(UUID id) {
		Optional<User> userOpt = userRepository.findById(id);
		User user = userOpt.orElseThrow();
		
		userRepository.delete(user);
	}
   
}
