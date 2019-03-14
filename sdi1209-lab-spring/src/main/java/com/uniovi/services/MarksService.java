/**
 * 
 */
package com.uniovi.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

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
	private HttpSession httpSession;

	@Autowired
	private MarksRepository marksRepository;

	/**
	 * Devuelve una lista con todas las notas almacenadas en base de datos
	 * 
	 * @return lista con las notas
	 */
	public List<Mark> getMarks() {
		List<Mark> marks = new ArrayList<>();
		marksRepository.findAll().forEach(marks::add);
		return marks;
	}

	/**
	 * Devuelve una nota concreta dod su identificador
	 * 
	 * @param id identificador de la nota a buscar
	 * @return nota cuyo identificador es id
	 */
	@SuppressWarnings("unchecked")
	public Mark getMark(Long id) {
		Set<Mark> consultedList = (Set<Mark>) httpSession.getAttribute("consultedList");
		if (consultedList == null) {
			consultedList = new HashSet<Mark>();
		}
		Mark markObtained = marksRepository.findById(id).get();
		consultedList.add(markObtained);
		httpSession.setAttribute("consultedList", consultedList);
		return markObtained;
	}

	/**
	 * Aniade una nota a la base de datos
	 * 
	 * @param mark nota a aniadir
	 */
	public void addMark(Mark mark) {
		marksRepository.save(mark);
	}

	/**
	 * Borra una nota de base de datos dado su identificador
	 * 
	 * @param id identifcador de la nota a borrar
	 */
	public void deleteMark(Long id) {
		marksRepository.deleteById(id);
	}

}
