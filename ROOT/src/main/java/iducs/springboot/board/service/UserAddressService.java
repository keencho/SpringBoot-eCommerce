package iducs.springboot.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import iducs.springboot.board.domain.User;
import iducs.springboot.board.domain.UserAddress;
import iducs.springboot.board.entity.UserEntity;

public interface UserAddressService {
	UserAddress getAddressByNo(long no);
	UserAddress getAddressByUserNoOrderByDesc(long no);
	List<UserAddress> getAddressByUserNo(long no);
	
	void saveUserAddress(UserAddress useraddress); // 생성
	void updateUserAddress(UserAddress useraddress); // 수정
	void deleteUserAddress(UserAddress useraddress); // 삭제
}
