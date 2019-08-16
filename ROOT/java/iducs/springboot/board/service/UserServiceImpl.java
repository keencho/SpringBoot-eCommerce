package iducs.springboot.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import iducs.springboot.board.domain.User;
import iducs.springboot.board.entity.UserEntity;
import iducs.springboot.board.repository.UserRepository;

@Service//("userService")
public class UserServiceImpl implements UserService {

	@Autowired UserRepository repository;

	@Override
	public User getUserByNo(long no) {
		UserEntity userEntity = repository.findByNo(no);
		if(userEntity == null)
			return null;
		return userEntity.buildDomain();
	}

	@Override
	public User getUserById(String id) {
		UserEntity userEntity = repository.findById(id);
		if(userEntity == null)
			return null;
		return userEntity.buildDomain();
	}

	public List<User> getUsers(PageRequest pageRequest) {
		List<User> users = new ArrayList<User>();
		Page<UserEntity> entities = repository.findAll(pageRequest);
		for(UserEntity entity : entities) {
			User user = entity.buildDomain();
			users.add(user);
		}
		return users;
	}
	
	public Page<UserEntity> getUserPage(Pageable pageable) {
		Page<UserEntity> entities = repository.findAll(pageable);

		return entities;
	}
	
	@Override
	public List<User> getUsers(Long pageNo) {
		PageRequest pageRequest = PageRequest.of((int) (pageNo - 1), 3, new Sort(Sort.Direction.ASC, "id"));
		Page<UserEntity> entities = repository.findAll(pageRequest);
		List<User> users = new ArrayList<User>();
		for(UserEntity entity : entities) {
			User user = entity.buildDomain();
			users.add(user);
		}
		return users;
	}	
	
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		List<UserEntity> entities = repository.findAll();
		for(UserEntity entity : entities) {
			User user = entity.buildDomain();
			users.add(user);
		}
		return users;
	}
	@Override
	public List<User> getUsersByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsersByCompany(String company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsersByPage(Pageable pageable) {
		List<User> users = new ArrayList<User>();
		Page<UserEntity> entities = repository.findAll(pageable);
		for(UserEntity entity : entities) {
			User user = entity.buildDomain();
			users.add(user);
		}
		return users;
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		UserEntity entity = new UserEntity();
		entity.buildEntity(user);
		repository.save(entity);
	}
	@Override
	public void updateUser(User user) {
		UserEntity entity = new UserEntity();
		entity.buildEntity(user);
		repository.save(entity);
	}

	@Override
	public void deleteUser(User user) {
		UserEntity entity = new UserEntity();
		entity.buildEntity(user);
		repository.delete(entity);
	}
}
