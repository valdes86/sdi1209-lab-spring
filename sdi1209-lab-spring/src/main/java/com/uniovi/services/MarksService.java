/**
 * 
 */
package com.uniovi.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.uniovi.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	public Page<Mark> getMarks(Pageable pageable) {
		Page<Mark> marks = marksRepository.findAll(pageable);
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

	/**
	 * Actualiza el valor "reenviado" de una nota
	 * 
	 * @param revised valor booleano a actualizar
	 * @param id      identificador de la nota
	 */
	public void setMarkResend(boolean revised, Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String dni = auth.getName();
		Mark mark = marksRepository.findById(id).get();
		if (mark.getUser().getDni().equals(dni)) {
			marksRepository.updateResend(revised, id);
		}
	}

	/**
	 * Devuelve las notas del usuario legueado si es un alumno o todas las notas si
	 * es un profesor
	 * 
	 * @param user Usuario
	 * @return Lista de notas
	 */
	public Page<Mark> getMarksForUser(Pageable pageable, User user) {

		Page<Mark> marks = new PageImpl<Mark>(new LinkedList<Mark>());
		
		if (user.getRole().equals("ROLE_STUDENT")) {
			marks = marksRepository.findAllByUser(pageable, user);
		}
		if (user.getRole().equals("ROLE_PROFESSOR")) {
			marks = getMarks(pageable);
		}
		return marks;
	}

	/**
	 * Se encarga de realizar una búsqueda en las notas: las notas del propio
	 * usuario si el usuario autenticado es ROLE_STUDENT o las notas de todos los
	 * usuarios si el usuario autenticado es ROLE_PROFESSOR.
	 * 
	 * @param searchText Texto de busqueda
	 * @param user       Usuario
	 * @return Lista de notas
	 */
	public Page<Mark> searchMarksByDescriptionAndNameForUser(Pageable pageable, String searchText, User user) {
		Page<Mark> marks = new PageImpl<Mark>(new LinkedList<Mark>());
		searchText = "%"+searchText+"%";
		
		if (user.getRole().equals("ROLE_STUDENT")) {
			marks = marksRepository.searchByDescriptionNameAndUser(pageable, searchText, user);
		}
		if (user.getRole().equals("ROLE_PROFESSOR")) {
			marks = marksRepository.searchByDescriptionAndName(pageable, searchText);
		}
		return marks;
	}

}
