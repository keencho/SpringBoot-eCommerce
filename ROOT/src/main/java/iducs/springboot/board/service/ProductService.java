package iducs.springboot.board.service;

import java.util.List;

import iducs.springboot.board.domain.Product;

public interface ProductService {
	Product getProductById(long no);
	
	List<Product> getProduct();
	
	void saveProduct(Product product);
	void updateProduct(Product product);
	void deleteProduct(Product product);

}
