package iducs.springboot.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import iducs.springboot.board.domain.Category;
import iducs.springboot.board.domain.Division;
import iducs.springboot.board.domain.Product;
import iducs.springboot.board.domain.ProductSize;
import iducs.springboot.board.domain.Question;
import iducs.springboot.board.domain.Section;
import iducs.springboot.board.entity.ProductEntity;
import iducs.springboot.board.service.CategoryService;
import iducs.springboot.board.service.ClothesSizeService;
import iducs.springboot.board.service.DivisionService;
import iducs.springboot.board.service.ProductService;
import iducs.springboot.board.service.SectionService;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {
	@Autowired ClothesSizeService clothessizeService;
	@Autowired ProductService productService;
	@Autowired CategoryService categoryService;
	@Autowired DivisionService divisionService;
	@Autowired SectionService sectionService;
	
	
	String osName = System.getProperty("os.name");
	static File cwd = new File("src/main/resources/static/uploads/");	// 윈도우 업로드 경로
	static File cwd2 = new File("webapps/ROOT/WEB-INF/classes/static/uploads/");	// 리눅스 실제 웹서버 업로드 경로
	static File path = cwd.getAbsoluteFile();
	static File path2 = cwd2.getAbsoluteFile();
	static String autoFolderStatic = path.toString();
	static String autoFolderStatic2 = path2.toString();
	
	Date d = new Date();
	SimpleDateFormat t1 = new SimpleDateFormat("yyyy-MM-dd");
	
	String newname = null, newname2 = null, newname3 = null, newname4 = null, newname5 = null, explainname= null;
	
	@GetMapping("")
	public String productHome(Model model) throws Exception{
		List <Product> product = productService.getProduct();
		model.addAttribute("product", product);

		return "admin/product/main";
	}
	
	@GetMapping("/add")
	public String productAddForm() {
		File autoFolder = new File(autoFolderStatic + "/" +  t1.format(d));
		File autoFolder2 = new File(autoFolderStatic2 + "/" +  t1.format(d));
		if(osName.matches(".*Windows.*")) {
			if(!autoFolder.exists()) {
				autoFolder.mkdirs();
			}
		} else {
			if(!autoFolder2.exists()) {
				autoFolder2.mkdirs();
			}
		}
		return "admin/product/add";
	}
	
	@GetMapping("/findCategory")
	public String productFindCategory(Model model) {
		List<Section> section= sectionService.getSection();
		model.addAttribute("section", section);
		return "admin/product/findCategory";
	}
	
	@PostMapping(value="/add", consumes = "multipart/form-data")
	public String productAdd(Long category_no, Long section_no, Long division_no, String name, String modelname,
			String price, String discount, String listprice, String size, String color, String material, String madeby,
			String madein, String caution, String date,@RequestParam("pic1") MultipartFile pic1,@RequestParam("pic2") MultipartFile pic2,@RequestParam("pic3") MultipartFile pic3,@RequestParam("pic4") MultipartFile pic4,@RequestParam("pic5") MultipartFile pic5, @RequestParam("explainpic") MultipartFile explainpic,
			String regdate, Model model, MultipartHttpServletRequest request, ModelAndView mav) throws Exception{
		
		if (!pic1.isEmpty()) {
			int idx1 = pic1.getContentType().indexOf("/");
			int idxO1 = pic1.getOriginalFilename().indexOf(".");
			String pic1Ex = pic1.getContentType().substring(idx1+1);
			String pic1Bf = pic1.getOriginalFilename().substring(0, idxO1);

			newname = pic1Bf + System.currentTimeMillis() + "." + pic1Ex;
			if(osName.matches(".*Windows.*")) {
				File file = new File(path + "/" + t1.format(d), newname);
				FileCopyUtils.copy(pic1.getBytes(), file);
			} else {
				File file = new File(path2 + "/" + t1.format(d), newname);
				FileCopyUtils.copy(pic1.getBytes(), file);
			}
		} else {
			newname = null;
		}
		
		if (!pic2.isEmpty()) {
			int idx2 = pic2.getContentType().indexOf("/");
			int idxO2 = pic2.getOriginalFilename().indexOf(".");
			String pic2Ex = pic2.getContentType().substring(idx2+1);
			String pic2Bf = pic2.getOriginalFilename().substring(0, idxO2);
			
			newname2 = pic2Bf + System.currentTimeMillis() + "." + pic2Ex;
			if(osName.matches(".*Windows.*")) {
				File file = new File(path + "/" + t1.format(d), newname2);
				FileCopyUtils.copy(pic2.getBytes(), file);
			} else {
				File file = new File(path2 + "/" + t1.format(d), newname2);
				FileCopyUtils.copy(pic2.getBytes(), file);
			}
		} else {
			newname2 = null;
		}
		
		if (!pic3.isEmpty()) {
			int idx3 = pic3.getContentType().indexOf("/");
			int idxO3 = pic3.getOriginalFilename().indexOf(".");
			String pic3Ex = pic3.getContentType().substring(idx3+1);
			String pic3Bf = pic3.getOriginalFilename().substring(0, idxO3);
			
			newname3 = pic3Bf + System.currentTimeMillis() + "." + pic3Ex;
			if(osName.matches(".*Windows.*")) {
				File file = new File(path + "/" + t1.format(d), newname3);
				FileCopyUtils.copy(pic3.getBytes(), file);
			} else {
				File file = new File(path2 + "/" + t1.format(d), newname3);
				FileCopyUtils.copy(pic3.getBytes(), file);
			}
		} else {
			newname3 = null;
		}
		
		if (!pic4.isEmpty()) {
			int idx4 = pic4.getContentType().indexOf("/");
			int idxO4 = pic4.getOriginalFilename().indexOf(".");
			String pic4Ex = pic4.getContentType().substring(idx4+1);
			String pic4Bf = pic4.getOriginalFilename().substring(0, idxO4);
			
			newname4 = pic4Bf + System.currentTimeMillis() + "." + pic4Ex;
			if(osName.matches(".*Windows.*")) {
				File file = new File(path + "/" + t1.format(d), newname4);
				FileCopyUtils.copy(pic4.getBytes(), file);
			} else {
				File file = new File(path2 + "/" + t1.format(d), newname4);
				FileCopyUtils.copy(pic4.getBytes(), file);
			}
		} else {
			newname4 = null;
		}
		
		if (!pic5.isEmpty()) {
			int idx5 = pic5.getContentType().indexOf("/");
			int idxO5 = pic5.getOriginalFilename().indexOf(".");
			String pic5Ex = pic5.getContentType().substring(idx5+1);
			String pic5Bf = pic5.getOriginalFilename().substring(0, idxO5);
			
			newname5 = pic5Bf + System.currentTimeMillis() + "." + pic5Ex;
			if(osName.matches(".*Windows.*")) {
				File file = new File(path + "/" + t1.format(d), newname5);
				FileCopyUtils.copy(pic5.getBytes(), file);
			} else {
				File file = new File(path2 + "/" + t1.format(d), newname5);
				FileCopyUtils.copy(pic5.getBytes(), file);
			}
		} else {
			newname5 = null;
		}
		
		if (!explainpic.isEmpty()) {
			int idxEx = explainpic.getContentType().indexOf("/");
			int idxOEx = explainpic.getOriginalFilename().indexOf(".");
			String picExEx = explainpic.getContentType().substring(idxEx+1);
			String picExBf = explainpic.getOriginalFilename().substring(0, idxOEx);
			
			explainname = picExBf + System.currentTimeMillis() + "." + picExEx;
			if(osName.matches(".*Windows.*")) {
				File file = new File(path + "/" + t1.format(d), explainname);
				FileCopyUtils.copy(explainpic.getBytes(), file);
			} else {
				File file = new File(path2 + "/" + t1.format(d), explainname);
				FileCopyUtils.copy(explainpic.getBytes(), file);
			}
		} else {
			explainname = "sample";
		}
		 

		Category category = categoryService.getCategoryByNo(category_no);
		Division division = divisionService.getDivisionByNo(division_no);
		Section section = sectionService.getSectionByNo(section_no);

		Product product = new Product(category, division, section, name, modelname, price, discount, listprice, size, color, material, madeby, madein, caution, date, newname, newname2, newname3, newname4, newname5, explainname, regdate);
		productService.saveProduct(product);
		
		System.out.println(newname);
		System.out.println(newname2);
		System.out.println(newname3);
		System.out.println(newname4);
		System.out.println(newname5);

		return "redirect:/admin/product";
	}
	
	@GetMapping("/view/{no}")
	public String viewProduct(@PathVariable(value = "no") Long no, Model model) {
		Product product = productService.getProductById(no);
		model.addAttribute("product", product);
		return "/admin/product/view";
	}
	
	@GetMapping("/del/{no}")
	public String productSizeDel(@PathVariable(value = "no") Long no, Model model) {
		Product product = productService.getProductById(no);
		productService.deleteProduct(product);
		return "redirect:/admin/product";
	}

}
