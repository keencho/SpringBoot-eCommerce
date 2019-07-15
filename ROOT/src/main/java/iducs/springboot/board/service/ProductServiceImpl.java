package iducs.springboot.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iducs.springboot.board.domain.Product;
import iducs.springboot.board.entity.ProductEntity;
import iducs.springboot.board.repository.ProductRepository;

@Service("ProductSize")
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository repository;
	
	@Override
	public Product getProductByNo(long no) {
		ProductEntity productEntity = repository.findById(no).get();
		if(productEntity == null)
			return null;
		return productEntity.buildDomain();
	}

	@Override
	public List<Product> getProduct() {
		List<Product> product = new ArrayList<Product>();
		List<ProductEntity> entities = repository.findAll();
		for(ProductEntity entity : entities) {
			Product products = entity.buildDomain();
			product.add(products);
		}
		return product;
	}

	@Override
	public void saveProduct(Product product) {
		
		ProductEntity entity = new ProductEntity();
		entity.buildEntity(product);
		repository.save(entity);
		
	}

	@Override
	public void updateProduct(Product product) {
		ProductEntity entity = new ProductEntity();
		entity.buildEntity(product);
		repository.save(entity);
	}

	@Override
	public void deleteProduct(Product product) {
		ProductEntity entity = new ProductEntity();
		entity.buildEntity(product);
		repository.delete(entity);
	}

}
