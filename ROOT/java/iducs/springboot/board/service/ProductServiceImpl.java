package iducs.springboot.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import iducs.springboot.board.domain.Product;
import iducs.springboot.board.domain.ProductSize;
import iducs.springboot.board.domain.ProductStock;
import iducs.springboot.board.entity.ProductEntity;
import iducs.springboot.board.entity.ProductSizeEntity;
import iducs.springboot.board.entity.ProductStockEntity;
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
	public Page<ProductEntity> getProductByDivisionNoPage(Pageable pageable, long no) {
		Page<ProductEntity> entities = repository.findByDivisionNo(no, pageable);
		return entities;
	}
	
	@Override
	public Page<ProductEntity> getProductBySectionNoPage(Pageable pageable, long no) {
		Page<ProductEntity> entities = repository.findBySectionNo(no, pageable);
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
	public List<Product> getProductByDivisionNo(long no, Pageable pageable) {
		List<Product> product = new ArrayList<Product>();
		List<ProductEntity> entities = repository.findByDivisionNo(pageable, no);
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


	@Override
	public List<Product> getProductByDivisionNoSize(long no, String[] sizeArray, String[] colorArray, long price1,
			long price2, Pageable pageable) {
		List<Product> product = new ArrayList<Product>();
		List<ProductEntity> entities = repository.findByDivisionNo(no, sizeArray, colorArray, price1, price2, pageable);
		for(ProductEntity entity : entities) {
			Product products = entity.buildDomain();
			product.add(products);
		}
		return product;
	}


	@Override
	public List<Product> getProductBySectionNo(long no, Pageable pageable) {
		List<Product> product = new ArrayList<Product>();
		List<ProductEntity> entities = repository.findBySectionNo(pageable, no);
		for(ProductEntity entity : entities) {
			Product products = entity.buildDomain();
			product.add(products);
		}
		return product;
	}


	@Override
	public List<Product> getProductBySectionNoSize(long no, String[] sizeArray, String[] colorArray, long price1,
			long price2, Pageable pageable) {
		List<Product> product = new ArrayList<Product>();
		List<ProductEntity> entities = repository.findBySectionNo(no, sizeArray, colorArray, price1, price2, pageable);
		for(ProductEntity entity : entities) {
			Product products = entity.buildDomain();
			product.add(products);
		}
		return product;
	}


	@Override
	public Page<ProductEntity> getProductByDivisionNoPageSize(Pageable pageable, long no, String[] sizeArray,
			String[] colorArray, long price1, long price2) {
		Page<ProductEntity> entities = repository.findByDivisionNoPage(no, pageable, sizeArray, colorArray, price1, price2);
		return entities;
	}


	@Override
	public Page<ProductEntity> getProductBySectionNoPageSize(Pageable pageable, long no, String[] sizeArray,
			String[] colorArray, long price1, long price2) {
		Page<ProductEntity> entities = repository.findBySectionNoPage(no, pageable, sizeArray, colorArray, price1, price2);
		return entities;
	}


	@Override
	public List<Product> getRelatedProductByCategoryNo(long no) {
		List<Product> product = new ArrayList<Product>();
		List<ProductEntity> entities = repository.findTop5ByCategoryNo(no);
		for(ProductEntity entity : entities) {
			Product products = entity.buildDomain();
			product.add(products);
		}
		return product;
	}


	@Override
	public List<Product> getProductBySectionNo(long no) {
		List<Product> product = new ArrayList<Product>();
		List<ProductEntity> entities = repository.findBySectionNo(no);
		for(ProductEntity entity : entities) {
			Product products = entity.buildDomain();
			product.add(products);
		}
		return product;
	}


	@Override
	public List<Product> get6ProductByCategoryNo(long no) {
		List<Product> product = new ArrayList<Product>();
		List<ProductEntity> entities = repository.findTop6ByCategoryNo(no);
		for(ProductEntity entity : entities) {
			Product products = entity.buildDomain();
			product.add(products);
		}
		return product;
	}


	@Override
	public List<Product> get3ProductByDivisionNo(long no) {
		List<Product> product = new ArrayList<Product>();
		List<ProductEntity> entities = repository.findTop3ByDivisionNo(no);
		for(ProductEntity entity : entities) {
			Product products = entity.buildDomain();
			product.add(products);
		}
		return product;
	}


	@Override
	public List<Product> get8ProductOrderByNoDesc() {
		List<Product> product = new ArrayList<Product>();
		List<ProductEntity> entities = repository.findTop8OrderByNoDesc();
		for(ProductEntity entity : entities) {
			Product products = entity.buildDomain();
			product.add(products);
		}
		return product;
	}


	@Override
	public List<Product> getRand5Product() {
		List<Product> product = new ArrayList<Product>();
		List<ProductEntity> entities = repository.findRand5();
		for(ProductEntity entity : entities) {
			Product products = entity.buildDomain();
			product.add(products);
		}
		return product;
	}
	
	@Override
	public List<Product> getRand3Product() {
		List<Product> product = new ArrayList<Product>();
		List<ProductEntity> entities = repository.findRand3();
		for(ProductEntity entity : entities) {
			Product products = entity.buildDomain();
			product.add(products);
		}
		return product;
	}


	@Override
	public List<Product> getNewRand5Product(String date) {
		List<Product> product = new ArrayList<Product>();
		List<ProductEntity> entities = repository.findNewRand5(date);
		for(ProductEntity entity : entities) {
			Product products = entity.buildDomain();
			product.add(products);
		}
		return product;
	}


	@Override
	public List<Product> getSaleRand5Product(int discount) {
		List<Product> product = new ArrayList<Product>();
		List<ProductEntity> entities = repository.findSaleRand5(discount);
		for(ProductEntity entity : entities) {
			Product products = entity.buildDomain();
			product.add(products);
		}
		return product;
	}


	@Override
	public List<Product> getProductByNameContaining(Pageable pageable, String name) {
		List<Product> product = new ArrayList<Product>();
		List<ProductEntity> entities = repository.findByNameContaining(pageable, name);
		for(ProductEntity entity : entities) {
			Product products = entity.buildDomain();
			product.add(products);
		}
		return product;
	}


	@Override
	public Page<ProductEntity> getProductByNameContaining(String name, Pageable pageable) {
		Page<ProductEntity> entities = repository.findByNameContaining(name, pageable);
		return entities;
	}


	@Override
	public List<Product> getProductByCategoryNoAndNameContaining(Pageable pageable, long no, String name) {
		List<Product> product = new ArrayList<Product>();
		List<ProductEntity> entities = repository.findByCategoryNoAndNameContaining(pageable, no, name);
		for(ProductEntity entity : entities) {
			Product products = entity.buildDomain();
			product.add(products);
		}
		return product;
	}


	@Override
	public Page<ProductEntity> getProductByCategoryNoAndNameContaining(long no, String name, Pageable pageable) {
		Page<ProductEntity> entities = repository.findByCategoryNoAndNameContaining(no, name, pageable);
		return entities;
	}


	@Override
	public List<Product> getProductByDivisionNoAndNameContaining(Pageable pageable, long no, String name) {
		List<Product> product = new ArrayList<Product>();
		List<ProductEntity> entities = repository.findByDivisionNoAndNameContaining(pageable, no, name);
		for(ProductEntity entity : entities) {
			Product products = entity.buildDomain();
			product.add(products);
		}
		return product;
	}


	@Override
	public Page<ProductEntity> getProductByDivisionNoAndNameContaining(long no, String name, Pageable pageable) {
		Page<ProductEntity> entities = repository.findByDivisionNoAndNameContaining(no, name, pageable);
		return entities;
	}


	@Override
	public List<Product> findProductByNameContaining(String search) {
		List<Product> product = new ArrayList<Product>();
		List<ProductEntity> entities = repository.findByNameContainingOrderByNameAsc(search);
		for(ProductEntity entity : entities) {
			Product products = entity.buildDomain();
			product.add(products);
		}
		return product;
	}

}
