package iducs.springboot.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iducs.springboot.board.domain.ClothesSize;
import iducs.springboot.board.domain.Color;
import iducs.springboot.board.entity.ClothesSizeEntity;
import iducs.springboot.board.entity.ColorEntity;
import iducs.springboot.board.repository.ClothesSizeRepository;
import iducs.springboot.board.repository.ColorRepository;

@Service("colorService")
public class ColorServiceImpl implements ColorService{
	@Autowired
	private ColorRepository repository;

	@Override
	public Color getColorByNo(long no) {
		ColorEntity colorEntity = repository.findById(no).get();
		if(colorEntity == null)
			return null;
		return colorEntity.buildDomain();
	}

	@Override
	public List<Color> getColor() {
		List<Color> color = new ArrayList<Color>();
		List<ColorEntity> entities = repository.findAll();
		for(ColorEntity entity : entities) {
			Color colors = entity.buildDomain();
			color.add(colors);
		}
		return color;
	}

	@Override
	public void saveColor(Color color) {
		ColorEntity entity = new ColorEntity();
		entity.buildEntity(color);
		repository.save(entity);
	}

	@Override
	public void updateColor(Color color) {
		ColorEntity entity = new ColorEntity();
		entity.buildEntity(color);
		repository.save(entity);
	}

	@Override
	public void deleteColor(Color color) {
		ColorEntity entity = new ColorEntity();
		entity.buildEntity(color);
		repository.delete(entity);
	}
}
