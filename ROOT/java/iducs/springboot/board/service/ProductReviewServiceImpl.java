package iducs.springboot.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import iducs.springboot.board.domain.ProductReview;
import iducs.springboot.board.entity.ProductReviewEntity;
import iducs.springboot.board.repository.ProductReviewRepository;

@Service("productReviewService")
public class ProductReviewServiceImpl implements ProductReviewService{
	
	@Autowired ProductReviewRepository repository;

	@Override
	public ProductReview findByNo(long no) {
		ProductReviewEntity entity = repository.findByNo(no);
		if(entity == null)
			return null;
		return entity.buildDomain();
	}

	@Override
	public List<ProductReview> findAll() {
		List<ProductReview> review = new ArrayList<ProductReview>();
		List<ProductReviewEntity> entities = repository.findAll();
		for(ProductReviewEntity entity : entities) {
			ProductReview product = entity.buildDomain();
			review.add(product);
		}
		return review;
	}

	@Override
	public List<ProductReview> findByUserNo(long no) {
		List<ProductReview> review = new ArrayList<ProductReview>();
		List<ProductReviewEntity> entities = repository.findByUserNo(no);
		for(ProductReviewEntity entity : entities) {
			ProductReview product = entity.buildDomain();
			review.add(product);
		}
		return review;
	}

	@Override
	public List<ProductReview> findByProductNo(long no) {
		List<ProductReview> review = new ArrayList<ProductReview>();
		List<ProductReviewEntity> entities = repository.findByProductNoOrderByNoDesc(no);
		for(ProductReviewEntity entity : entities) {
			ProductReview product = entity.buildDomain();
			review.add(product);
		}
		return review;
	}

	@Override
	public void saveProductReview(ProductReview productreview) {
		ProductReviewEntity entity = new ProductReviewEntity();
		entity.buildEntity(productreview);
		repository.save(entity);
	}

	@Override
	public void updateProductReview(ProductReview productreview) {
		ProductReviewEntity entity = new ProductReviewEntity();
		entity.buildEntity(productreview);
		repository.save(entity);
	}

	@Override
	public void deleteProductReview(ProductReview productreview) {
		ProductReviewEntity entity = new ProductReviewEntity();
		entity.buildEntity(productreview);
		repository.delete(entity);
	}

	@Override
	public List<ProductReview> findByInfoNo(long no){
		List<ProductReview> review = new ArrayList<ProductReview>();
		List<ProductReviewEntity> entities = repository.findByInfoNo(no);
		for(ProductReviewEntity entity : entities) {
			ProductReview product = entity.buildDomain();
			review.add(product);
		}
		return review;
	}

	@Override
	public ProductReview getByProductNo(long no) {
		ProductReviewEntity entity = repository.findByProductNo(no);
		if(entity == null)
			return null;
		return entity.buildDomain();
	}

	@Override
	public Page<ProductReviewEntity> findByProductNoPage(long no, Pageable pageable) {
		Page<ProductReviewEntity> entities = repository.findByProductNo(pageable, no);
		return entities;
	}

	@Override
	public List<ProductReview> findByProductNo(long no, Pageable pageable) {
		List<ProductReview> review = new ArrayList<ProductReview>();
		List<ProductReviewEntity> entities = repository.findByProductNoOrderByNoDesc(no, pageable);
		for(ProductReviewEntity entity : entities) {
			ProductReview product = entity.buildDomain();
			review.add(product);
		}
		return review;
	}

}
