package iducs.springboot.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import iducs.springboot.board.domain.Product;
import iducs.springboot.board.entity.ProductEntity;

public interface ProductService {
	Product getProductById(long no);
	
	List<Product> getProduct();
	List<Product> getProductByCategoryNo(long no, Pageable pageable);
	Page<ProductEntity> getProductByCategoryNoPage(long no, Pageable pageable);
	
	void saveProduct(Product product);
	void updateProduct(Product product);
	void deleteProduct(Product product);

}
