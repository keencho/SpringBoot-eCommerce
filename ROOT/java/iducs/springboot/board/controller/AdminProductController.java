package iducs.springboot.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import iducs.springboot.board.domain.Category;
import iducs.springboot.board.domain.Division;
import iducs.springboot.board.domain.Product;
import iducs.springboot.board.domain.Section;
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
	String attachName1 = null, attachName2 = null, attachName3 = null, attachName4 = null, attachName5 = null, attachName6 = null;
	String attachNameOriginal1 = null, attachNameOriginal2 = null, attachNameOriginal3 = null, attachNameOriginal4 = null, attachNameOriginal5 = null, attachNameOriginal6 = null;
	
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
			String madein, String caution, String date, @RequestParam("pic1") MultipartFile pic1,
			@RequestParam("pic2") MultipartFile pic2, @RequestParam("pic3") MultipartFile pic3,
			@RequestParam("pic4") MultipartFile pic4, @RequestParam("pic5") MultipartFile pic5,
			@RequestParam("explainpic") MultipartFile explainpic, String regdate, Model model,
			MultipartHttpServletRequest request, ModelAndView mav) throws Exception{
		
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

		return "redirect:/admin/product";
	}
	
	@GetMapping("/view/{no}")
	public String viewProduct(@PathVariable(value = "no") Long no, Model model) {
		Product product = productService.getProductById(no);
		model.addAttribute("product", product);
		return "/admin/product/view";
	}
	
	@GetMapping("/update/{no}")
	public String adminProductUpdateForm(
			@PathVariable(value="no") Long no,
			Model model) {
		Product product = productService.getProductById(no);
		model.addAttribute("product", product);
		
		return "/admin/product/update";
		
	}
	
	@GetMapping("/del/{no}")
	public String productSizeDel(@PathVariable(value = "no") Long no, Model model) {
		Product product = productService.getProductById(no);
		if(product.getPic1() != null) {
			File file;
			if(osName.matches(".*Windows.*")) {
				file = new File(cwd + "/" + product.getRegdate(), product.getPic1());
			} else {
				file = new File(cwd2 + "/" + product.getRegdate(), product.getPic1());
			}
			file.delete();
		}
		
		if(product.getPic2() != null) {
			File file;
			if(osName.matches(".*Windows.*")) {
				file = new File(cwd + "/" + product.getRegdate(), product.getPic2());
			} else {
				file = new File(cwd2 + "/" + product.getRegdate(), product.getPic2());
			}
			file.delete();
		}
		
		if(product.getPic3() != null) {
			File file;
			if(osName.matches(".*Windows.*")) {
				file = new File(cwd + "/" + product.getRegdate(), product.getPic3());
			} else {
				file = new File(cwd2 + "/" + product.getRegdate(), product.getPic3());
			}
			file.delete();
		}
		
		if(product.getPic4() != null) {
			File file;
			if(osName.matches(".*Windows.*")) {
				file = new File(cwd + "/" + product.getRegdate(), product.getPic4());
			} else {
				file = new File(cwd2 + "/" + product.getRegdate(), product.getPic4());
			}
			file.delete();
		}
		
		if(product.getPic5() != null) {
			File file;
			if(osName.matches(".*Windows.*")) {
				file = new File(cwd + "/" + product.getRegdate(), product.getPic5());
			} else {
				file = new File(cwd2 + "/" + product.getRegdate(), product.getPic5());
			}
			file.delete();
		}
		
		if(product.getExplainpic() != null) {
			File file;
			if(osName.matches(".*Windows.*")) {
				file = new File(cwd + "/" + product.getRegdate(), product.getExplainpic());
			} else {
				file = new File(cwd2 + "/" + product.getRegdate(), product.getExplainpic());
			}
			file.delete();
		}
		productService.deleteProduct(product);
		return "redirect:/admin/product";
	}
	
	@PutMapping("/update/{no}")
	public String productUpdate(
			@PathVariable(value="no") Long no,
			@RequestParam("name") String name,
			@RequestParam("modelname") String modelname,
			@RequestParam("price") String price,
			@RequestParam("discount") String discount,
			@RequestParam("listprice") String listprice,
			@RequestParam("color") String color,
			@RequestParam("size") String size,
			@RequestParam("material") String material,
			@RequestParam("madeby") String madeby,
			@RequestParam("madein") String madein,
			@RequestParam("caution") String caution,
			@RequestParam("date") String date,
			@RequestParam("pic1") MultipartFile pic1,
			@RequestParam("pic2") MultipartFile pic2, 
			@RequestParam("pic3") MultipartFile pic3,
			@RequestParam("pic4") MultipartFile pic4, 
			@RequestParam("pic5") MultipartFile pic5,
			@RequestParam("explainpic") MultipartFile explainpic,
			@RequestParam("attach_status1") int status1,
			@RequestParam("attach_status2") int status2,
			@RequestParam("attach_status3") int status3,
			@RequestParam("attach_status4") int status4,
			@RequestParam("attach_status5") int status5,
			@RequestParam("attach_status6") int status6,
			Model model
			) throws IOException {
		Product product = productService.getProductById(no);
		
		if(status1 == 0) {								// 첨부파일이 기존에 없었거나 삭제되지 않았을 경우
			if(!pic1.isEmpty()) {						// 첨부파일을 새로 등록했을때
				int idx1 = pic1.getContentType().indexOf("/");
				int idx2 = pic1.getOriginalFilename().indexOf(".");
				String attach1 = pic1.getContentType().substring(idx1 + 1);
				String attach2 = pic1.getOriginalFilename().substring(0, idx2);
				
				attachName1 = attach2 + System.currentTimeMillis() + "." + attach1;
				try {
					if(!product.getPic1().isEmpty()) {	// 기존의 첨부파일이 있다면
						File file;
						if(osName.matches(".*Windows.*")) {
							file = new File(cwd + "/" + product.getRegdate(), product.getPic1());
						} else {
							file = new File(cwd2 + "/" + product.getRegdate(), product.getPic1());
						}
						file.delete(); 					// 기존의 첨부파일은 삭제
					}
				} catch (Exception e) {}
				
				if(osName.matches(".*Windows.*")) {
					File newFile = new File(path + "/" + product.getRegdate(), attachName1);
					FileCopyUtils.copy(pic1.getBytes(), newFile);
				} else {
					File newFile = new File(path2 + "/" + product.getRegdate(), attachName1);
					FileCopyUtils.copy(pic1.getBytes(), newFile);
				}
				product.setPic1(attachName1);
			}
		} else if (status1 == 1) {	// 기존의 첨부파일을 삭제하고자 할 경우
			File file;
			if(osName.matches(".*Windows.*")) {
				file = new File(cwd + "/" + product.getRegdate(), product.getPic1());
			} else {
				file = new File(cwd2 + "/" + product.getRegdate(), product.getPic1());
			}
			file.delete();
			attachName1 = null;
			if(!pic1.isEmpty()) {
				int idx1 = pic1.getContentType().indexOf("/");
				int idx2 = pic1.getOriginalFilename().indexOf(".");
				String attach1 = pic1.getContentType().substring(idx1 + 1);
				String attach2 = pic1.getOriginalFilename().substring(0, idx2);
				
				attachName1 = attach2 + System.currentTimeMillis() + "." + attach1;
				
				if(osName.matches(".*Windows.*")) {
					File newFile = new File(path + "/" + product.getRegdate(), attachName1);
					FileCopyUtils.copy(pic1.getBytes(), newFile);
				} else {
					File newFile = new File(path2 + "/" + product.getRegdate(), attachName1);
					FileCopyUtils.copy(pic1.getBytes(), newFile);
				}
			}
			product.setPic1(attachName1);
		}
		/* ---------------------------------------------------------------------------------------------- */
		if(status2 == 0) {
			if(!pic2.isEmpty()) {
				int idx1 = pic2.getContentType().indexOf("/");
				int idx2 = pic2.getOriginalFilename().indexOf(".");
				String attach1 = pic2.getContentType().substring(idx1 + 1);
				String attach2 = pic2.getOriginalFilename().substring(0, idx2);
				
				attachName2 = attach2 + System.currentTimeMillis() + "." + attach1;
				try {
					if(!product.getPic2().isEmpty()) {	// 기존의 첨부파일이 있다면
						File file;
						if(osName.matches(".*Windows.*")) {
							file = new File(cwd + "/" + product.getRegdate(), product.getPic2());
						} else {
							file = new File(cwd2 + "/" + product.getRegdate(), product.getPic2());
						}
						file.delete(); 					// 기존의 첨부파일은 삭제
					}
				} catch (Exception e) {}
				
				if(osName.matches(".*Windows.*")) {
					File newFile = new File(path + "/" + product.getRegdate(), attachName2);
					FileCopyUtils.copy(pic2.getBytes(), newFile);
				} else {
					File newFile = new File(path2 + "/" + product.getRegdate(), attachName2);
					FileCopyUtils.copy(pic2.getBytes(), newFile);
				}
				product.setPic2(attachName2);
			}
		} else if (status2 == 1) {
			File file;
			if(osName.matches(".*Windows.*")) {
				file = new File(cwd + "/" + product.getRegdate(), product.getPic2());
			} else {
				file = new File(cwd2 + "/" + product.getRegdate(), product.getPic2());
			}
			file.delete();
			attachName2 = null;
			if(!pic2.isEmpty()) {
				int idx1 = pic2.getContentType().indexOf("/");
				int idx2 = pic2.getOriginalFilename().indexOf(".");
				String attach1 = pic2.getContentType().substring(idx1 + 1);
				String attach2 = pic2.getOriginalFilename().substring(0, idx2);
				
				attachName2 = attach2 + System.currentTimeMillis() + "." + attach1;
				
				if(osName.matches(".*Windows.*")) {
					File newFile = new File(path + "/" + product.getRegdate(), attachName2);
					FileCopyUtils.copy(pic2.getBytes(), newFile);
				} else {
					File newFile = new File(path2 + "/" + product.getRegdate(), attachName2);
					FileCopyUtils.copy(pic2.getBytes(), newFile);
				}
			}
			product.setPic2(attachName2);
		}
		/* ---------------------------------------------------------------------------------------------- */
		if(status3 == 0) {
			if(!pic3.isEmpty()) {
				int idx1 = pic3.getContentType().indexOf("/");
				int idx2 = pic3.getOriginalFilename().indexOf(".");
				String attach1 = pic3.getContentType().substring(idx1 + 1);
				String attach2 = pic3.getOriginalFilename().substring(0, idx2);
				
				attachName3 = attach2 + System.currentTimeMillis() + "." + attach1;
				try {
					if(!product.getPic3().isEmpty()) {	// 기존의 첨부파일이 있다면
						File file;
						if(osName.matches(".*Windows.*")) {
							file = new File(cwd + "/" + product.getRegdate(), product.getPic3());
						} else {
							file = new File(cwd2 + "/" + product.getRegdate(), product.getPic3());
						}
						file.delete(); 					// 기존의 첨부파일은 삭제
					}
				} catch (Exception e) {}
				
				if(osName.matches(".*Windows.*")) {
					File newFile = new File(path + "/" + product.getRegdate(), attachName3);
					FileCopyUtils.copy(pic3.getBytes(), newFile);
				} else {
					File newFile = new File(path2 + "/" + product.getRegdate(), attachName3);
					FileCopyUtils.copy(pic3.getBytes(), newFile);
				}
				product.setPic3(attachName3);
			}
		} else if (status3 == 1) {
			File file;
			if(osName.matches(".*Windows.*")) {
				file = new File(cwd + "/" + product.getRegdate(), product.getPic3());
			} else {
				file = new File(cwd2 + "/" + product.getRegdate(), product.getPic3());
			}
			file.delete();
			attachName3 = null;
			if(!pic3.isEmpty()) {
				int idx1 = pic3.getContentType().indexOf("/");
				int idx2 = pic3.getOriginalFilename().indexOf(".");
				String attach1 = pic3.getContentType().substring(idx1 + 1);
				String attach2 = pic3.getOriginalFilename().substring(0, idx2);
				
				attachName3 = attach2 + System.currentTimeMillis() + "." + attach1;
				
				if(osName.matches(".*Windows.*")) {
					File newFile = new File(path + "/" + product.getRegdate(), attachName3);
					FileCopyUtils.copy(pic3.getBytes(), newFile);
				} else {
					File newFile = new File(path2 + "/" + product.getRegdate(), attachName3);
					FileCopyUtils.copy(pic3.getBytes(), newFile);
				}
			}
			product.setPic3(attachName3);
		}
		/* ---------------------------------------------------------------------------------------------- */
		if(status4 == 0) {
			if(!pic4.isEmpty()) {
				int idx1 = pic4.getContentType().indexOf("/");
				int idx2 = pic4.getOriginalFilename().indexOf(".");
				String attach1 = pic4.getContentType().substring(idx1 + 1);
				String attach2 = pic4.getOriginalFilename().substring(0, idx2);
				
				attachName4 = attach2 + System.currentTimeMillis() + "." + attach1;
				try {
					if(!product.getPic4().isEmpty()) {	// 기존의 첨부파일이 있다면
						File file;
						if(osName.matches(".*Windows.*")) {
							file = new File(cwd + "/" + product.getRegdate(), product.getPic4());
						} else {
							file = new File(cwd2 + "/" + product.getRegdate(), product.getPic4());
						}
						file.delete(); 					// 기존의 첨부파일은 삭제
					}
				} catch (Exception e) {}
				
				if(osName.matches(".*Windows.*")) {
					File newFile = new File(path + "/" + product.getRegdate(), attachName4);
					FileCopyUtils.copy(pic4.getBytes(), newFile);
				} else {
					File newFile = new File(path2 + "/" + product.getRegdate(), attachName4);
					FileCopyUtils.copy(pic4.getBytes(), newFile);
				}
				product.setPic4(attachName4);
			}
		} else if (status4 == 1) {
			File file;
			if(osName.matches(".*Windows.*")) {
				file = new File(cwd + "/" + product.getRegdate(), product.getPic4());
			} else {
				file = new File(cwd2 + "/" + product.getRegdate(), product.getPic4());
			}
			file.delete();
			attachName4 = null;
			if(!pic4.isEmpty()) {
				int idx1 = pic4.getContentType().indexOf("/");
				int idx2 = pic4.getOriginalFilename().indexOf(".");
				String attach1 = pic4.getContentType().substring(idx1 + 1);
				String attach2 = pic4.getOriginalFilename().substring(0, idx2);
				
				attachName4 = attach2 + System.currentTimeMillis() + "." + attach1;
				
				if(osName.matches(".*Windows.*")) {
					File newFile = new File(path + "/" + product.getRegdate(), attachName4);
					FileCopyUtils.copy(pic4.getBytes(), newFile);
				} else {
					File newFile = new File(path2 + "/" + product.getRegdate(), attachName4);
					FileCopyUtils.copy(pic4.getBytes(), newFile);
				}
			}
			product.setPic4(attachName4);
		}
		/* ---------------------------------------------------------------------------------------------- */
		if(status5 == 0) {
			if(!pic5.isEmpty()) {
				int idx1 = pic5.getContentType().indexOf("/");
				int idx2 = pic5.getOriginalFilename().indexOf(".");
				String attach1 = pic5.getContentType().substring(idx1 + 1);
				String attach2 = pic5.getOriginalFilename().substring(0, idx2);
				
				attachName5 = attach2 + System.currentTimeMillis() + "." + attach1;
				try {
					if(!product.getPic5().isEmpty()) {	// 기존의 첨부파일이 있다면
						File file;
						if(osName.matches(".*Windows.*")) {
							file = new File(cwd + "/" + product.getRegdate(), product.getPic5());
						} else {
							file = new File(cwd2 + "/" + product.getRegdate(), product.getPic5());
						}
						file.delete(); 					// 기존의 첨부파일은 삭제
					}
				} catch (Exception e) {}
				
				if(osName.matches(".*Windows.*")) {
					File newFile = new File(path + "/" + product.getRegdate(), attachName5);
					FileCopyUtils.copy(pic5.getBytes(), newFile);
				} else {
					File newFile = new File(path2 + "/" + product.getRegdate(), attachName5);
					FileCopyUtils.copy(pic5.getBytes(), newFile);
				}
				product.setPic5(attachName5);
			}
		} else if (status5 == 1) {
			File file;
			if(osName.matches(".*Windows.*")) {
				file = new File(cwd + "/" + product.getRegdate(), product.getPic5());
			} else {
				file = new File(cwd2 + "/" + product.getRegdate(), product.getPic5());
			}
			file.delete();
			attachName5 = null;
			if(!pic5.isEmpty()) {
				int idx1 = pic5.getContentType().indexOf("/");
				int idx2 = pic5.getOriginalFilename().indexOf(".");
				String attach1 = pic5.getContentType().substring(idx1 + 1);
				String attach2 = pic5.getOriginalFilename().substring(0, idx2);
				
				attachName5 = attach2 + System.currentTimeMillis() + "." + attach1;
				
				if(osName.matches(".*Windows.*")) {
					File newFile = new File(path + "/" + product.getRegdate(), attachName5);
					FileCopyUtils.copy(pic5.getBytes(), newFile);
				} else {
					File newFile = new File(path2 + "/" + product.getRegdate(), attachName5);
					FileCopyUtils.copy(pic5.getBytes(), newFile);
				}
			}
			product.setPic5(attachName5);
		}
		/* ---------------------------------------------------------------------------------------------- */
		if(status6 == 0) {
			if(!explainpic.isEmpty()) {
				int idx1 = explainpic.getContentType().indexOf("/");
				int idx2 = explainpic.getOriginalFilename().indexOf(".");
				String attach1 = explainpic.getContentType().substring(idx1 + 1);
				String attach2 = explainpic.getOriginalFilename().substring(0, idx2);
				
				attachName6 = attach2 + System.currentTimeMillis() + "." + attach1;
				try {
					if(!product.getExplainpic().isEmpty()) {	// 기존의 첨부파일이 있다면
						File file;
						if(osName.matches(".*Windows.*")) {
							file = new File(cwd + "/" + product.getRegdate(), product.getExplainpic());
						} else {
							file = new File(cwd2 + "/" + product.getRegdate(), product.getExplainpic());
						}
						file.delete(); 					// 기존의 첨부파일은 삭제
					}
				} catch (Exception e) {}
				
				if(osName.matches(".*Windows.*")) {
					File newFile = new File(path + "/" + product.getRegdate(), attachName6);
					FileCopyUtils.copy(explainpic.getBytes(), newFile);
				} else {
					File newFile = new File(path2 + "/" + product.getRegdate(), attachName6);
					FileCopyUtils.copy(explainpic.getBytes(), newFile);
				}
				product.setExplainpic(attachName6);
			}
		} else if (status6 == 1) {
			File file;
			if(osName.matches(".*Windows.*")) {
				file = new File(cwd + "/" + product.getRegdate(), product.getExplainpic());
			} else {
				file = new File(cwd2 + "/" + product.getRegdate(), product.getExplainpic());
			}
			file.delete();
			attachName6 = null;
			if(!explainpic.isEmpty()) {
				int idx1 = explainpic.getContentType().indexOf("/");
				int idx2 = explainpic.getOriginalFilename().indexOf(".");
				String attach1 = explainpic.getContentType().substring(idx1 + 1);
				String attach2 = explainpic.getOriginalFilename().substring(0, idx2);
				
				attachName6 = attach2 + System.currentTimeMillis() + "." + attach1;
				
				if(osName.matches(".*Windows.*")) {
					File newFile = new File(path + "/" + product.getRegdate(), attachName6);
					FileCopyUtils.copy(explainpic.getBytes(), newFile);
				} else {
					File newFile = new File(path2 + "/" + product.getRegdate(), attachName6);
					FileCopyUtils.copy(explainpic.getBytes(), newFile);
				}
			}
			product.setExplainpic(attachName6);
		}
		
		product.setName(name);
		product.setModelname(modelname);
		product.setPrice(price);
		product.setDiscount(discount);
		product.setListprice(listprice);
		product.setColor(color);
		product.setSize(size);
		product.setMaterial(material);
		product.setMadeby(madeby);
		product.setMadein(madein);
		product.setCaution(caution);
		product.setDate(date);
		productService.updateProduct(product);
		
		return "redirect:/admin/product/view/" + no;
	}

}
