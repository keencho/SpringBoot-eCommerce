package iducs.springboot.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import iducs.springboot.board.domain.ClothesSize;
import iducs.springboot.board.domain.Product;
import iducs.springboot.board.domain.ProductSize;
import iducs.springboot.board.entity.ProductEntity;
import iducs.springboot.board.entity.ProductSizeEntity;
import iducs.springboot.board.repository.ProductRepository;

@Service("ProductSize")
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository repository;
	
	@Override
	public Product getProductById(long no) {
		ProductEntity entity = repository.findById(no).get();
		Product product = entity.buildDomain();
		
		List<ProductSize> productsize = new ArrayList<ProductSize>();
		for(ProductSizeEntity productsizeEntity : entity.getProductsize())
			productsize.add(productsizeEntity.buildDomain());
		product.setProductsize(productsize);
		
		return product;
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
