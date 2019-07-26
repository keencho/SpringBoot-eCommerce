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
	List<Product> getProductByCategoryNoSize(long no, String[] sizeArray, String[] colorArray, long price1, long price2, Pageable pageable);
	
	List<Product> getProductByDivisionNo(long no, Pageable pageable);
	List<Product> getProductByDivisionNoSize(long no, String[] sizeArray, String[] colorArray, long price1, long price2, Pageable pageable);
	
	List<Product> getProductBySectionNo(long no, Pageable pageable);
	List<Product> getProductBySectionNoSize(long no, String[] sizeArray, String[] colorArray, long price1, long price2, Pageable pageable);

	Page<ProductEntity> getProductByCategoryNoPage(Pageable pageable, long no);
	Page<ProductEntity> getProductByCategoryNoPageSize(Pageable pageable, long no, String[] sizeArray, String[] colorArray, long price1, long price2);
	
	Page<ProductEntity> getProductByDivisionNoPage(Pageable pageable, long no);
	Page<ProductEntity> getProductByDivisionNoPageSize(Pageable pageable, long no, String[] sizeArray, String[] colorArray, long price1, long price2);
	
	Page<ProductEntity> getProductBySectionNoPage(Pageable pageable, long no);
	Page<ProductEntity> getProductBySectionNoPageSize(Pageable pageable, long no, String[] sizeArray, String[] colorArray, long price1, long price2);
	
	void saveProduct(Product product);
	void updateProduct(Product product);
	void deleteProduct(Product product);

}
