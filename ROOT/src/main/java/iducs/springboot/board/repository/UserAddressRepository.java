package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.UserAddressEntity;

@Repository
public interface UserAddressRepository	extends JpaRepository<UserAddressEntity, Long> {
	UserAddressEntity findByNo(long no);
	
	List<UserAddressEntity> findByUserNo(long no);

}
