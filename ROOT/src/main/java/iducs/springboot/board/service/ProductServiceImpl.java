package iducs.springboot.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import iducs.springboot.board.domain.ClothesSize;
import iducs.springboot.board.domain.Product;
import iducs.springboot.board.domain.ProductSize;
import iducs.springboot.board.domain.ProductStock;
import iducs.springboot.board.entity.ProductEntity;
import iducs.springboot.board.entity.ProductSizeEntity;
import iducs.springboot.board.entity.ProductStockEntity;
import iducs.springboot.board.repository.ProductRepository;
import iducs.springboot.board.repository.ProductSizeRepository;

@Service("ProductSize")
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository repository;
	
	@Override
	public Product getProductById(long no) {
		ProductEntity entity = repository.findById(no).get();
		Product product = entity.buildDomain();
		
		List<ProductSize> productsize = new ArrayList<ProductSize>();
		List<ProductStock> productstock = new ArrayList<ProductStock>();
		for(ProductSizeEntity productsizeEntity : entity.getProductsize())
			productsize.add(productsizeEntity.buildDomain());
		for(ProductStockEntity productstockEntity : entity.getProductstock())
			productstock.add(productstockEntity.buildDomain());
		product.setProductsize(productsize);
		product.setProductstock(productstock);
			
		return product;
	}
	
	
	@Override
	public Page<ProductEntity> getProductByCategoryNoPage(Pageable pageable, long no) {
		Page<ProductEntity> entities = repository.findByCategoryNo(no, pageable);
		return entities;
	}
	

	@Override
	public Page<ProductEntity> getProductByCategoryNoPageSize(Pageable pageable, long no, String[] sizeArray, String[] colorArray, long price1, long price2) {
		Page<ProductEntity> entities = repository.findByCategoryNoPage(no, pageable, sizeArray, colorArray, price1, price2);
		return entities;
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
	public List<Product> getProductByCategoryNoSize(long categoryno, String[] sizeArray, String[] colorArray, long price1, long price2, Pageable pageable) {
		List<Product> product = new ArrayList<Product>();
		List<ProductEntity> entities = repository.findByCategoryNo(categoryno, sizeArray, colorArray, price1, price2, pageable);
		for(ProductEntity entity : entities) {
			Product products = entity.buildDomain();
			product.add(products);
		}
		return product;
	}
	
	@Override
	public List<Product> getProductByCategoryNo(long no, Pageable pageable) {
		List<Product> product = new ArrayList<Product>();
		List<ProductEntity> entities = repository.findByCategoryNo(pageable, no);
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
