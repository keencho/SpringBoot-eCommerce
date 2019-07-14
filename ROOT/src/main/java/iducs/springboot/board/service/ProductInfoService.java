package iducs.springboot.board.service;

import java.util.List;

import iducs.springboot.board.domain.ProductInfo;

public interface ProductInfoService {
	ProductInfo getProductInfoByNo(long no);
	
	List<ProductInfo> getProductInfo();
	
	void saveProductInfo(ProductInfo productinfo);
	void updateProductInfo(ProductInfo productinfo);
	void deleteProductIno(ProductInfo productinfo);

}
