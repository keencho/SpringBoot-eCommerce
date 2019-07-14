package iducs.springboot.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iducs.springboot.board.domain.ProductInfo;
import iducs.springboot.board.entity.ProductInfoEntity;
import iducs.springboot.board.repository.ProductInfoRepository;

@Service("ProductInfoSize")
public class ProductInfoServiceImpl implements ProductInfoService{
	@Autowired
	private ProductInfoRepository repository;
	
	@Override
	public ProductInfo getProductInfoByNo(long no) {
		ProductInfoEntity productinfoEntity = repository.findById(no).get();
		if(productinfoEntity == null)
			return null;
		return productinfoEntity.buildDomain();
	}

	@Override
	public List<ProductInfo> getProductInfo() {
		List<ProductInfo> productinfo = new ArrayList<ProductInfo>();
		List<ProductInfoEntity> entities = repository.findAll();
		for(ProductInfoEntity entity : entities) {
			ProductInfo product = entity.buildDomain();
			productinfo.add(product);
		}
		return productinfo;
	}

	@Override
	public void saveProductInfo(ProductInfo productinfo) {
		ProductInfoEntity entity = new ProductInfoEntity();
		entity.buildEntity(productinfo);
		repository.save(entity);
	}

	@Override
	public void updateProductInfo(ProductInfo productinfo) {
		ProductInfoEntity entity = new ProductInfoEntity();
		entity.buildEntity(productinfo);
		repository.save(entity);
	}

	@Override
	public void deleteProductIno(ProductInfo productinfo) {
		ProductInfoEntity entity = new ProductInfoEntity();
		entity.buildEntity(productinfo);
		repository.delete(entity);
	}

}
