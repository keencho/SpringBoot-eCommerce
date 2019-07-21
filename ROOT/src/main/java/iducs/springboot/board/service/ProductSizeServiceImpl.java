package iducs.springboot.board.service;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import iducs.springboot.board.domain.Product;
import iducs.springboot.board.domain.ProductSize;
import iducs.springboot.board.entity.ProductEntity;
import iducs.springboot.board.entity.ProductSizeEntity;
import iducs.springboot.board.repository.ProductSizeRepository;

@Service("productSizeService")
public class ProductSizeServiceImpl implements ProductSizeService {
	@Autowired 
	private ProductSizeRepository repository;

	@Override
	public ProductSize getProductSizeByNo(long no) {
		ProductSizeEntity entity = repository.findById(no).get();
		ProductSize size = entity.buildDomain();
		return size;
	}

	@Override
	public List<ProductSize> findAll() {
		List<ProductSize> productsize = new ArrayList<ProductSize>();
		List<ProductSizeEntity> entities = repository.findAll();
		for(ProductSizeEntity entity : entities) {
			ProductSize pro = entity.buildDomain();
			productsize.add(pro);
		}
		return productsize;
	}

	@Override
	public List<ProductSize> getProductSizeByProductNo(long no) {
		return null;
	}

	@Override
	public void saveProductSize(ProductSize productsize) {
		ProductSizeEntity entity = new ProductSizeEntity();
		entity.buildEntity(productsize);
		repository.save(entity);
	}

	@Override
	public void updateProductSize(ProductSize productsize) {
		ProductSizeEntity entity = new ProductSizeEntity();
		entity.buildEntity(productsize);
		repository.save(entity);
	}

	@Override
	public void deleteProductSize(ProductSize productsize) {
		ProductSizeEntity entity = new ProductSizeEntity();
		entity.buildEntity(productsize);
		repository.delete(entity);
	}

	@Override
	public List<ProductSize> findDistinctSizeNo() {
		// TODO Auto-generated method stub
		return null;
	}


}
