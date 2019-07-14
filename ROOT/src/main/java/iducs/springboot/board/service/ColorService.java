package iducs.springboot.board.service;

import java.util.List;

import iducs.springboot.board.domain.Color;

public interface ColorService {
	Color getColorByNo(long no);
	
	List<Color> getColor();
	
	void saveColor(Color color);
	void updateColor(Color color);
	void deleteColor(Color color);

}
