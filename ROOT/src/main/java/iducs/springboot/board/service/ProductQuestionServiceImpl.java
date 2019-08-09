package iducs.springboot.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import iducs.springboot.board.domain.ProductQuestion;
import iducs.springboot.board.entity.ProductQuestionEntity;
import iducs.springboot.board.repository.ProductQuestionRepository;

@Service("productQuestionService")
public class ProductQuestionServiceImpl implements ProductQuestionService{
	
	@Autowired ProductQuestionRepository repository;

	@Override
	public ProductQuestion getProductQuestionByNo(long no) {
		ProductQuestionEntity productquestionEntity = repository.findByNo(no);
		if(productquestionEntity == null)
			return null;
		return productquestionEntity.buildDomain();
	}

	@Override
	public List<ProductQuestion> getProductQuestion(long no, Pageable questionPageable) {
		List<ProductQuestion> productquestion = new ArrayList<ProductQuestion>();
		List<ProductQuestionEntity> entities = repository.findByProductNo(no, questionPageable);
		for(ProductQuestionEntity entity : entities) {
			ProductQuestion product = entity.buildDomain();
			productquestion.add(product);
		}
		return productquestion;
	}

	@Override
	public List<ProductQuestion> findAll() {
		List<ProductQuestion> productquestion = new ArrayList<ProductQuestion>();
		List<ProductQuestionEntity> entities = repository.findAll();
		for(ProductQuestionEntity entity : entities) {
			ProductQuestion product = entity.buildDomain();
			productquestion.add(product);
		}
		return productquestion;
	}
	
	@Override
	public Page<ProductQuestionEntity> getProductQuestionPage(Pageable questionPageable, long no) {
		Page<ProductQuestionEntity> entities = repository.findByProductNo(questionPageable, no);
		return entities;
	}
	
	@Override
	public List<ProductQuestion> getProductQuestionStatus(long no, int status, Pageable questionPageable) {
		List<ProductQuestion> productquestion = new ArrayList<ProductQuestion>();
		List<ProductQuestionEntity> entities = repository.findByProductNoAndStatus(no, questionPageable, status);
		for(ProductQuestionEntity entity : entities) {
			ProductQuestion product = entity.buildDomain();
			productquestion.add(product);
		}
		return productquestion;
	}

	@Override
	public Page<ProductQuestionEntity> getProductQuestionStatusPage(Pageable questionPageable, long no, int status) {
		Page<ProductQuestionEntity> entities = repository.findByProductNoAndStatus(questionPageable, no, status);
		return entities;
	}
	
	@Override
	public List<ProductQuestion> getProductQuestionOriginal(long no) {
		List<ProductQuestion> productquestion = new ArrayList<ProductQuestion>();
		List<ProductQuestionEntity> entities = repository.findByProductNo(no);
		for(ProductQuestionEntity entity : entities) {
			ProductQuestion product = entity.buildDomain();
			productquestion.add(product);
		}
		return productquestion;
	}
	
	@Override
	public void saveProductQuestion(ProductQuestion productquestion) {
		ProductQuestionEntity entitiy = new ProductQuestionEntity();
		entitiy.buildEntity(productquestion);
		repository.save(entitiy);
	}

	@Override
	public void updateProductQuestion(ProductQuestion productquestion) {
		ProductQuestionEntity entitiy = new ProductQuestionEntity();
		entitiy.buildEntity(productquestion);
		repository.save(entitiy);
	}

	@Override
	public void deleteProductQuestion(ProductQuestion productquestion) {
		ProductQuestionEntity entitiy = new ProductQuestionEntity();
		entitiy.buildEntity(productquestion);
		repository.delete(entitiy);
	}
}
