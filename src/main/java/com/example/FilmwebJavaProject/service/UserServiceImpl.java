package com.example.FilmwebJavaProject.service;


import com.example.FilmwebJavaProject.dao.RoleRepository;
import com.example.FilmwebJavaProject.dao.UserRepository;
import com.example.FilmwebJavaProject.dto.CustomUserDetails;
import com.example.FilmwebJavaProject.dto.DtoUser;
import com.example.FilmwebJavaProject.entity.Role;
import com.example.FilmwebJavaProject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	private RoleRepository roleRepository;

	private final BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository theUserRepository, RoleRepository theRoleRepository, @Lazy BCryptPasswordEncoder passwordEncoder) {
		userRepository = theUserRepository;
		roleRepository = theRoleRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public List<User> findAll() {
		return userRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public User findById(int theId) {
		Optional<User> result = userRepository.findById(theId);
		
		User theUser;
		
		if (result.isPresent()) {
			theUser = result.get();
		}
		else {
			throw new RuntimeException("Did not find user id - " + theId);
		}
		
		return theUser;
	}

	@Override
	public void save(User theUser) {
		userRepository.save(theUser);
	}

	@Override
	public void deleteById(int theId) {
		userRepository.deleteById(theId);
	}

	@Override
	public List<Role> findRoles(){
		return roleRepository.findAll();
	}

	@Override
	public User findByLogin(String login) {
		return userRepository.findUserByLogin(login);
	}

	@Override
	public void save(DtoUser dtoUser) {

		User user = new User();

		user.setLogin(dtoUser.getLogin());
		user.setPassword(passwordEncoder.encode(dtoUser.getPassword()));
		user.setFirstName(dtoUser.getFirstName());
		user.setLastName(dtoUser.getLastName());

		user.setRoles(Arrays.asList(roleRepository.findRoleByName("ROLE_FULL_USER")));

		userRepository.save(user);
	}

	@Override
	public void update(DtoUser dtoUser, User user) {

		user.setLogin(dtoUser.getLogin());
		user.setPassword(passwordEncoder.encode(dtoUser.getPassword()));
		user.setFirstName(dtoUser.getFirstName());
		user.setLastName(dtoUser.getLastName());

		userRepository.save(user);
	}


	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByLogin(username);

		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new CustomUserDetails(user.getLogin(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}






