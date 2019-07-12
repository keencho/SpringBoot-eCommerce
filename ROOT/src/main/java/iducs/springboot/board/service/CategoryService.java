package iducs.springboot.board.service;

import java.util.List;

import iducs.springboot.board.domain.Category;

public interface CategoryService {
	Category getCategoryById(long no);
	Category getCategoryByNo(long no);
	Category getCategoryByNmae(String name);
	
	List<Category> getCategory();
	List<Category> getCategoryByName();
	
	void saveCategory(Category category);
	void updateCategory(Category category);
	void deleteCategory(Category category);

}
