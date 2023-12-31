package com.codewithdurgesh.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.CategoryDto;
import com.codewithdurgesh.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){
		this.categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Updated Successfully",true), HttpStatus.OK);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory( @PathVariable Integer categoryId){
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully",true), HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> allCategories = this.categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(allCategories,HttpStatus.FOUND);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId ){
		CategoryDto category = this.categoryService.getCategoryById(categoryId);
		return new ResponseEntity<CategoryDto>(category,HttpStatus.FOUND);
	}
}
