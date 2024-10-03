package com.techtech.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techtech.dto.ProductDTO;
import com.techtech.repo.ProductRepository;
import com.techtech.service.ProductService;

@Controller
public class ProductController {
	
	
	//@Autowired
	//private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;
	
	/*
	 * method for showing all datas from database
	 */
	@GetMapping({"/showProduct","/"})
	public String showProduct(Model model) {
		List<ProductDTO> list= productService.findAll();
		model.addAttribute("list", list);
		return "addProduct";
	}
	
	/*
	 * method for deleting product by id
	 */
	@GetMapping("/deleteProduct")
	public String deleteProduct(@RequestParam String pid, Model model) {
		//String pid = request.getParameter("pid");
		productService.deleteById(Integer.parseInt(pid));
		
		List<ProductDTO> list= productService.findAll();
		model.addAttribute("list", list);
		model.addAttribute("message", "Product deleted successfully.");
		return "addProduct";
	}
	
	/*
	 * method to save datas into database
	 */
	@PostMapping("/addProduct")
	public String createProduct(@ModelAttribute ProductDTO productDTO,Model model) {
		/*
		 * String name= request.getParameter("name"); String price=
		 * request.getParameter("price"); String category=
		 * request.getParameter("category"); String photo=
		 * request.getParameter("photo");
		 * 
		 * ProductEntity productEntity= new ProductEntity();
		 * productEntity.setName(name);
		 * productEntity.setPrice(Double.parseDouble(price));
		 * productEntity.setCategory(category); productEntity.setPhoto(photo);
		 */
		productDTO.setDoe(new Timestamp(new Date().getTime()));
		productService.save(productDTO);
		
		List<ProductDTO> list= productService.findAll();
//		request.setAttribute("list", list);
//		request.setAttribute("message", "product is added into database");
		model.addAttribute("list", list);
		model.addAttribute("message", "product is added into database");
		return "addProduct";

	}
	
	/*
	 * method for searching product 
	 */
	@GetMapping("/searchProduct")
	public String searchProduct(@RequestParam String stext, Model model) {
		List<ProductDTO> list = productService.searchProduct(stext);
		model.addAttribute("list", list);
		return "addProduct";
	}
	
//	@GetMapping("/searchProduct1")
//	public String searchPro(@RequestParam String searchPro, Model model) {
//		List<ProductEntity> list = productService.searchProduct1(searchPro);
//		model.addAttribute("list", list);
//		return "addProduct";
//	}
//	
	
	/*
	 * method for sorting product by name and category
	 */
	@GetMapping("/sorting")
	public String sortingBy(@RequestParam String attribute, @RequestParam String orderBy, Model model) {
		List<ProductDTO> list = productService.findAll();
		if (attribute.equals("name") && orderBy.equals("asc")) {
			model.addAttribute("orderBy", "desc");
			Collections.sort(list, Comparator.comparing(ProductDTO::getName));
		} else if (attribute.equals("name")) {
			model.addAttribute("orderBy", "asc");
			Collections.sort(list, Comparator.comparing(ProductDTO::getName).reversed());
		}

		if (attribute.equals("category") && orderBy.equals("asc")) {
			model.addAttribute("orderBy", "desc");
			Collections.sort(list, Comparator.comparing(ProductDTO::getCategory));
		} else if (attribute.equals("category")) {
			model.addAttribute("orderBy", "asc");
			Collections.sort(list, Comparator.comparing(ProductDTO::getCategory).reversed());
		}

		model.addAttribute("list", list);
		return "addProduct";
	}
	
	
	@GetMapping("/api/chart")
	public ResponseEntity<byte[]> getBarChart() throws IOException {
		
		
		byte[] chartImage = generateBarChart();

		// Set HTTP Headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		headers.setContentLength(chartImage.length);

		return new ResponseEntity<>(chartImage, headers, HttpStatus.OK);
	}

	// Generate the bar chart
	public byte[] generateBarChart() throws IOException {
		// Create dataset with String X-axis and double Y-axis
		CategoryDataset dataset = createDataset();
		// Create a bar chart
		JFreeChart chart = ChartFactory.createBarChart("Sales by Product", // Chart Title
				"Product", // X-axis label (String)
				"Sales (in $)", // Y-axis label (Double)
				dataset);
		
		
		// Step 3: Customize the Bar Renderer
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        // Set different colors for each bar
        renderer.setSeriesPaint(0, Color.ORANGE);
        renderer.setSeriesPaint(1, Color.MAGENTA);

		// Convert the chart to an image (PNG)
		BufferedImage chartImage = chart.createBufferedImage(800, 600);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(chartImage, "png", baos);
		return baos.toByteArray();
	}

	// Create dataset with String as X-axis values and Double as Y-axis values
	private CategoryDataset createDataset() {
		
		
		List<ProductDTO> productList = productService.findAll();
		Map<String,Double> map =new HashMap<>();
		for(ProductDTO pe :productList){
			  String category=pe.getCategory();
			  double currentPrice =pe.getPrice();
			  if(map.containsKey(category)) {
				  double priceInMap=map.get(category);
				  map.put(category, priceInMap+currentPrice);
			  }else {
				  map.put(category, currentPrice);
			  }
		}
	
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		// Add data (Product name as X-axis and Sales value as Y-axis)
		map.forEach((category,price)->{
			dataset.addValue(price, "Sales", category); // X-axis: Product A, Y-axis: 25000.0
		});
		return dataset;
	}

	
	

}
