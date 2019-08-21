package iducs.springboot.board.service;

import java.util.List;

import iducs.springboot.board.domain.UserAddress;

public interface UserAddressService {
	UserAddress getAddressByNo(long no);
	UserAddress getAddressByUserNoOrderByDesc(long no);
	UserAddress getAddressByUserNoAndBasic(long no, int basic);
	List<UserAddress> getAddressByUserNo(long no);
	
	void saveUserAddress(UserAddress useraddress); // 생성
	void updateUserAddress(UserAddress useraddress); // 수정
	void deleteUserAddress(UserAddress useraddress); // 삭제
}
