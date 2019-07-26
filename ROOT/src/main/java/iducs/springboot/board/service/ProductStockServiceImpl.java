package iducs.springboot.board.service;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import iducs.springboot.board.domain.Product;
import iducs.springboot.board.domain.ProductStock;
import iducs.springboot.board.domain.ProductStock;
import iducs.springboot.board.entity.ProductEntity;
import iducs.springboot.board.entity.ProductStockEntity;
import iducs.springboot.board.entity.ProductStockEntity;
import iducs.springboot.board.repository.ProductStockRepository;
import iducs.springboot.board.repository.ProductStockRepository;

@Service("productStockService")
public class ProductStockServiceImpl implements ProductStockService {
	@Autowired 
	private ProductStockRepository repository;

	@Override
	public ProductStock getProductStockById(long no) {
		ProductStockEntity entity = repository.findById(no).get();
		ProductStock stock= entity.buildDomain();
		return stock;
	}

	@Override
	public List<ProductStock> findAll() {
		List<ProductStock> productstock = new ArrayList<ProductStock>();
		List<ProductStockEntity> entities = repository.findAll();
		for(ProductStockEntity entity : entities) {
			ProductStock pro = entity.buildDomain();
			productstock.add(pro);
		}
		return productstock;
	}
	
	@Override
	public List<ProductStock> findDistinctSizeNo(long no) {
		List<ProductStock> productstock = new ArrayList<ProductStock>();
		List<ProductStockEntity> entities = repository.findByCategoryNo(no);
		for(ProductStockEntity entity : entities) {
			ProductStock pro = entity.buildDomain();
			productstock.add(pro);
		}
		return productstock;
	}
	
	@Override
	public List<ProductStock> findDistinctColorNo(long no) {
		List<ProductStock> productstock = new ArrayList<ProductStock>();
		List<ProductStockEntity> entities = repository.findByCategoryNoColor(no);
		for(ProductStockEntity entity : entities) {
			ProductStock pro = entity.buildDomain();
			productstock.add(pro);
		}
		return productstock;
	}
	
	@Override
	public List<ProductStock> findDivisionDistinctSizeNo(long no) {
		List<ProductStock> productstock = new ArrayList<ProductStock>();
		List<ProductStockEntity> entities = repository.findByDivisionNo(no);
		for(ProductStockEntity entity : entities) {
			ProductStock pro = entity.buildDomain();
			productstock.add(pro);
		}
		return productstock;
	}

	@Override
	public List<ProductStock> findDivisionDistinctColorNo(long no) {
		List<ProductStock> productstock = new ArrayList<ProductStock>();
		List<ProductStockEntity> entities = repository.findByDivisionNoColor(no);
		for(ProductStockEntity entity : entities) {
			ProductStock pro = entity.buildDomain();
			productstock.add(pro);
		}
		return productstock;
	}


	@Override
	public List<ProductStock> getProductStockByProductNo(long no) {
		return null;
	}

	@Override
	public void saveProductStock(ProductStock productstock) {
		ProductStockEntity entity = new ProductStockEntity();
		entity.buildEntity(productstock);
		repository.save(entity);
	}

	@Override
	public void updateProductStock(ProductStock productstock) {
		ProductStockEntity entity = new ProductStockEntity();
		entity.buildEntity(productstock);
		repository.save(entity);
	}

	@Override
	public void deleteProductStock(ProductStock productstock) {
		ProductStockEntity entity = new ProductStockEntity();
		entity.buildEntity(productstock);
		repository.delete(entity);
	}

	@Override
	public List<ProductStock> findSectionDistinctSizeNo(long no) {
		List<ProductStock> productstock = new ArrayList<ProductStock>();
		List<ProductStockEntity> entities = repository.findBySectionNo(no);
		for(ProductStockEntity entity : entities) {
			ProductStock pro = entity.buildDomain();
			productstock.add(pro);
		}
		return productstock;
	}

	@Override
	public List<ProductStock> findSectionDistinctColorNo(long no) {
		List<ProductStock> productstock = new ArrayList<ProductStock>();
		List<ProductStockEntity> entities = repository.findBySectionNoColor(no);
		for(ProductStockEntity entity : entities) {
			ProductStock pro = entity.buildDomain();
			productstock.add(pro);
		}
		return productstock;
	}


}
