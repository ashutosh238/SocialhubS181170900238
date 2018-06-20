package com.ashutosh.socialhub.dao;

import java.util.List;

import com.ashutosh.socialhub.domain.Blog;

public interface BlogDAO {
	
	
	public boolean save(Blog blog);
	public boolean update(Blog blog);
	public boolean delete(Blog blog);
	public Blog get(int id);
	public List<Blog> list();
	
	
}