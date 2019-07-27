package iducs.springboot.board.service;

import java.util.List;

import iducs.springboot.board.domain.ProductStock;


public interface ProductStockService {
	ProductStock getProductStockById(long no);
	ProductStock getProductStockByNo(long no);
	ProductStock stockCheck(long no, long color, long size);
	List<ProductStock> findAll();
	List<ProductStock> getProductStockByProductNo(long no);
	
	List<ProductStock> findSizeByProductNo(long no);
	List<ProductStock> findColorByProductNo(long no);
	
	List<ProductStock> findDistinctSizeNo(long no);
	List<ProductStock> findDistinctColorNo(long no);
	
	List<ProductStock> findDivisionDistinctSizeNo(long no);
	List<ProductStock> findDivisionDistinctColorNo(long no);
	
	List<ProductStock> findSectionDistinctSizeNo(long no);
	List<ProductStock> findSectionDistinctColorNo(long no);
	
	void saveProductStock(ProductStock productstock);
	void updateProductStock(ProductStock productstock);
	void deleteProductStock(ProductStock productstock);
}
