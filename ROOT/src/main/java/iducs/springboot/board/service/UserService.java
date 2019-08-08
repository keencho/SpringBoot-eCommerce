package iducs.springboot.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import iducs.springboot.board.domain.User;
import iducs.springboot.board.entity.UserEntity;

public interface UserService {
	User getUserByNo(long no); // primary key에 해당하는 id로  조회
	User getUserById(String id); // unique key에 해당하는 userId로 조회
	
	List<User> getUsers(Long pageNo); // 모든 사용자 조회
	List<User> getUsers(); // 모든 사용자 조회
	List<User> getUsersByName(String name); // name으로 조회
	List<User> getUsersByCompany(String company); // company으로 조회
	List<User> getUsersByPage(Pageable pageable); // 페이지로 조회
	
	Page<UserEntity> getUserPage(Pageable pageable); // 페이징
	
	void saveUser(User user); // 생성
	void updateUser(User user); // 수정
	void deleteUser(User user); // 삭제
}
