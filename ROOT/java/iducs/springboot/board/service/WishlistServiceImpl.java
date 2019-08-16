package iducs.springboot.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import iducs.springboot.board.domain.Wishlist;
import iducs.springboot.board.entity.WishlistEntity;
import iducs.springboot.board.repository.WishlistRepository;

@Transactional
@Service("wishlistService")
public class WishlistServiceImpl implements WishlistService{
	
	@Autowired WishlistRepository repository;

	@Override
	public List<Wishlist> getWishlistByUserNo(long no) {
		List<Wishlist> wishlist = new ArrayList<Wishlist>();
		List<WishlistEntity> entities = repository.findByUserNo(no);
		for(WishlistEntity entity : entities) {
			Wishlist wis = entity.buildDomain();
			wishlist.add(wis);
		}
		return wishlist;
	}
	
	@Override
	public Wishlist getWishlistDuplicateCheck(long productno, long userno) {
		WishlistEntity wishlistEntity = repository.findDuplicate(productno, userno);
		if(wishlistEntity == null)
			return null;
		return wishlistEntity.buildDomain();
	}
	
	@Override
	public Wishlist getWishlistByNo(long no) {
		WishlistEntity wishlistEntity = repository.findByNo(no);
		if(wishlistEntity == null)
			return null;
		return wishlistEntity.buildDomain();
	}

	@Override
	public void saveWishlist(Wishlist wishlist) {
		WishlistEntity entity = new WishlistEntity();
		entity.buildEntity(wishlist);
		repository.save(entity);
	}

	@Override
	public void deleteWishlist(Wishlist wishlist) {
		WishlistEntity entity = new WishlistEntity();
		entity.buildEntity(wishlist);
		repository.delete(entity);
	}

	@Override
	public List<Wishlist> deleteByIdWishList(long no) {
		List<Wishlist> wishlist = new ArrayList<Wishlist>();
		List<WishlistEntity> entities = repository.deleteAllByUserNo(no);
		for(WishlistEntity entity : entities) {
			Wishlist wis = entity.buildDomain();
			wishlist.add(wis);
		}
		return wishlist;
	}

	
}
