package iducs.springboot.board.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iducs.springboot.board.domain.Category;
import iducs.springboot.board.domain.Division;
import iducs.springboot.board.entity.CategoryEntity;
import iducs.springboot.board.entity.DivisionEntity;
import iducs.springboot.board.repository.CategoryRepository;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryRepository repository;

	@Override
	public Category getCategoryByNo(long no) {
		CategoryEntity categoryEntity = repository.findById(no).get();
		if(categoryEntity == null)
			return null;
		return categoryEntity.buildDomain();
	}
	
	@Override
	public Category getCategoryById(long no) {
		CategoryEntity entity = repository.findById(no).get();
		
		Category category = entity.buildDomain();
		
		List<Division> divisionList = new ArrayList<Division>();
		for(DivisionEntity divisionEntity : entity.getDivision())
			divisionList.add(divisionEntity.buildDomain());
		category.setDivision(divisionList);
		
		return category;
	}

	@Override
	public Category getCategoryByNmae(String name) {
		CategoryEntity categoryEntity = repository.findByName(name);
		if(categoryEntity == null)
			return null;
		return categoryEntity.buildDomain();
	}

	@Override
	public List<Category> getCategory() {
		List<Category> categories = new ArrayList<Category>();
		List<CategoryEntity> entities = repository.findAll();
		for(CategoryEntity entity : entities) {
			Category category = entity.buildDomain();
			categories.add(category);
		}
		return categories;
	}

	@Override
	public List<Category> getCategoryByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCategory(Category category) {
		CategoryEntity entity = new CategoryEntity();
		entity.buildEntity(category);
		repository.save(entity);
	}

	@Override
	public void updateCategory(Category category) {
		CategoryEntity entity = new CategoryEntity();
		entity.buildEntity(category);
		repository.save(entity);
	}

	@Override
	public void deleteCategory(Category category) {
		CategoryEntity entity = new CategoryEntity();
		entity.buildEntity(category);
		repository.delete(entity);
	}
	
}
