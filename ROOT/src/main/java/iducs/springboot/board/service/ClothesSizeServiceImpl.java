package iducs.springboot.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iducs.springboot.board.domain.ClothesSize;
import iducs.springboot.board.entity.ClothesSizeEntity;
import iducs.springboot.board.repository.ClothesSizeRepository;

@Service("clothesSizeService")
public class ClothesSizeServiceImpl implements ClothesSizeService{
	@Autowired
	private ClothesSizeRepository repository;

	@Override
	public ClothesSize getClothesSizeByNo(long no) {
		ClothesSizeEntity clothessizeEntity = repository.findById(no).get();
		if(clothessizeEntity == null)
			return null;
		return clothessizeEntity.buildDomain();
	}
	
	@Override
	public List<ClothesSize> getClothesSize() {
		List<ClothesSize> clothessizes = new ArrayList<ClothesSize>();
		List<ClothesSizeEntity> entities = repository.findAll();
		for(ClothesSizeEntity entity : entities) {
			ClothesSize clothessize = entity.buildDomain();
			clothessizes.add(clothessize);
		}
		return clothessizes;
	}

	@Override
	public void saveClothesSize(ClothesSize clothessize) {
		ClothesSizeEntity entity = new ClothesSizeEntity();
		entity.buildEntity(clothessize);
		repository.save(entity);
	}

	@Override
	public void updateClothesSize(ClothesSize clothessize) {
		ClothesSizeEntity entity = new ClothesSizeEntity();
		entity.buildEntity(clothessize);
		repository.save(entity);
	}

	@Override
	public void deleteClothesSize(ClothesSize clothessize) {
		ClothesSizeEntity entity = new ClothesSizeEntity();
		entity.buildEntity(clothessize);
		repository.delete(entity);
	}

}
