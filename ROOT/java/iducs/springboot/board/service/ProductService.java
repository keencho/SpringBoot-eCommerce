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
	List<Product> getProductBySectionNo(long no);
	
	List<Product> getRelatedProductByCategoryNo(long no);
	
	List<Product> get6ProductByCategoryNo(long no);
	List<Product> get3ProductByDivisionNo(long no);
	List<Product> get8ProductOrderByNoDesc();
	List<Product> getRand5Product();
	List<Product> getRand3Product();
	List<Product> getNewRand5Product(String date);
	List<Product> getSaleRand5Product(int discount);

	Page<ProductEntity> getProductByCategoryNoPage(Pageable pageable, long no);
	Page<ProductEntity> getProductByCategoryNoPageSize(Pageable pageable, long no, String[] sizeArray, String[] colorArray, long price1, long price2);
	
	Page<ProductEntity> getProductByDivisionNoPage(Pageable pageable, long no);
	Page<ProductEntity> getProductByDivisionNoPageSize(Pageable pageable, long no, String[] sizeArray, String[] colorArray, long price1, long price2);
	
	Page<ProductEntity> getProductBySectionNoPage(Pageable pageable, long no);
	Page<ProductEntity> getProductBySectionNoPageSize(Pageable pageable, long no, String[] sizeArray, String[] colorArray, long price1, long price2);
	
	List<Product> getProductByNameContaining(Pageable pageable, String name);
	Page<ProductEntity> getProductByNameContaining(String name, Pageable pageable);
	
	List<Product> getProductByCategoryNoAndNameContaining(Pageable pageable, long no, String name);
	Page<ProductEntity> getProductByCategoryNoAndNameContaining(long no, String name, Pageable pageable);
	
	List<Product> getProductByDivisionNoAndNameContaining(Pageable pageable, long no, String name);
	Page<ProductEntity> getProductByDivisionNoAndNameContaining(long no, String name, Pageable pageable);
	
	void saveProduct(Product product);
	void updateProduct(Product product);
	void deleteProduct(Product product);

}
