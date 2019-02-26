/**
 * 
 */
package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Mark;
import com.uniovi.repositories.MarksRepository;

/**
 * @author Pablo Rodríguez Valdés
 *
 *         SDI - 2019
 */
@Service
public class MarksService {

	@Autowired
	private MarksRepository marksRepository;

	public List<Mark> getMarks() {
		List<Mark> marks = new ArrayList<>();
		marksRepository.findAll().forEach(marks::add);
		return marks;
	}

	public Mark getMark(Long id) {
		return marksRepository.findById(id).get();
	}

	public void addMark(Mark mark) {
		marksRepository.save(mark);
	}

	public void deleteMark(Long id) {
		marksRepository.deleteById(id);
	}

}
