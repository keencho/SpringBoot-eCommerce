package iducs.springboot.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import iducs.springboot.board.domain.ProductSize;
import iducs.springboot.board.domain.Question;
import iducs.springboot.board.entity.QuestionEntity;

public interface ProductSizeService {
	ProductSize getProductSizeByNo(long no);
	List<ProductSize> findAll();
	List<ProductSize> getProductSizeByProductNo(long no);
	List<ProductSize> findDistinctSizeNo();
	
	void saveProductSize(ProductSize productsize);
	void updateProductSize(ProductSize productsize);
	void deleteProductSize(ProductSize productsize);
}
