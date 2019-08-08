package iducs.springboot.board.service;

import java.util.List;

import iducs.springboot.board.domain.ProductSize;

public interface ProductSizeService {
	ProductSize getProductSizeByNo(long no);
	ProductSize getProductSizeByNoNativeQuery(long no);
	List<ProductSize> findAll();
	List<ProductSize> getProductSizeByProductNo(long no);
	List<ProductSize> findDistinctSizeNo(long no);
	
	void saveProductSize(ProductSize productsize);
	void updateProductSize(ProductSize productsize);
	void deleteProductSize(ProductSize productsize);
}
