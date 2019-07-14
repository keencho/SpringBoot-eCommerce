package iducs.springboot.board.service;

import java.util.List;

import iducs.springboot.board.domain.ClothesSize;

public interface ClothesSizeService {
	ClothesSize getClothesSizeByNo(long no);
	
	List<ClothesSize> getClothesSize();
	
	void saveClothesSize(ClothesSize clothessize);
	void updateClothesSize(ClothesSize clothessize);
	void deleteClothesSize(ClothesSize clothessize);

}
