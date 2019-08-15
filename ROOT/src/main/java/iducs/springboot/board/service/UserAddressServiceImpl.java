package iducs.springboot.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iducs.springboot.board.domain.UserAddress;
import iducs.springboot.board.entity.UserAddressEntity;
import iducs.springboot.board.repository.UserAddressRepository;

@Service("userAddressService")
public class UserAddressServiceImpl implements UserAddressService{
	
	@Autowired UserAddressRepository repository;

	@Override
	public UserAddress getAddressByNo(long no) {
		UserAddressEntity userAddressEntity = repository.findByNo(no);
		if(userAddressEntity == null)
			return null;
		return userAddressEntity.buildDomain();
	}

	@Override
	public List<UserAddress> getAddressByUserNo(long no) {
		List<UserAddress> userAddress = new ArrayList<UserAddress>();
		List<UserAddressEntity> entities = repository.findByUserNo(no);
		for(UserAddressEntity entity : entities) {
			UserAddress address = entity.buildDomain();
			userAddress.add(address);
		}
		return userAddress;
	}

	@Override
	public void saveUserAddress(UserAddress useraddress) {
		UserAddressEntity entity = new UserAddressEntity();
		entity.buildEntity(useraddress);
		repository.save(entity);
	}

	@Override
	public void updateUserAddress(UserAddress useraddress) {
		UserAddressEntity entity = new UserAddressEntity();
		entity.buildEntity(useraddress);
		repository.save(entity);
	}

	@Override
	public void deleteUserAddress(UserAddress useraddress) {
		UserAddressEntity entity = new UserAddressEntity();
		entity.buildEntity(useraddress);
		repository.delete(entity);
	}

	@Override
	public UserAddress getAddressByUserNoOrderByDesc(long no) {
		UserAddressEntity userAddressEntity = repository.findTop1ByUserNoOrderByNoDesc(no);
		if(userAddressEntity == null)
			return null;
		return userAddressEntity.buildDomain();
	}

	
}
