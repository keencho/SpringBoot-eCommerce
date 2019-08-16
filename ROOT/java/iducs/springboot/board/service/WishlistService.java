package iducs.springboot.board.service;

import java.util.List;

import iducs.springboot.board.domain.Wishlist;

public interface WishlistService {
	List<Wishlist> getWishlistByUserNo(long no);
	Wishlist getWishlistDuplicateCheck(long productno, long userno);
	Wishlist getWishlistByNo(long no);
	List<Wishlist> deleteByIdWishList(long no);
	
	void saveWishlist(Wishlist wishlist);
	void deleteWishlist(Wishlist wishlist);
}
