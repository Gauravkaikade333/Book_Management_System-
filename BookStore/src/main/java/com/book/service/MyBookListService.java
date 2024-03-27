package com.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.entity.MyBookList;
import com.book.repository.MyBookRepository;

@Service
public class MyBookListService {

@Autowired
private MyBookRepository myBookRepo;

public void saveMyBooks(MyBookList mbook) {
	myBookRepo.save(mbook);
}
public List<MyBookList> getAllMyBooks(){
	return myBookRepo.findAll();
}
public void deletById(int id) {
	myBookRepo.deleteById(id);
}
}
