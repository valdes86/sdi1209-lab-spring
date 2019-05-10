/**
 * 
 */
package com.uniovi.controllers;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Mark;
import com.uniovi.entities.User;
import com.uniovi.services.MarksService;
import com.uniovi.services.UsersService;

/**
 * @author Pablo Rodríguez Valdés
 *
 *         SDI - 2019
 */

@Controller
public class MarksControllers {

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private MarksService marksService;
	@Autowired
	private UsersService usersService;

	/**
	 * Método Get que que lista las notas y devuelve a la vista con la lista de
	 * notas
	 * 
	 * @return vista con la lista de notas
	 */
	@RequestMapping("/mark/list")
	public String getList(Model model, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
		String dni = principal.getName(); // DNI es el name de la autenticación
		User user = usersService.getUserByDni(dni);
		
		if (searchText != null && !searchText.isEmpty()) {
			model.addAttribute("markList", marksService.searchMarksByDescriptionAndNameForUser(searchText, user));
		} else {
			model.addAttribute("markList", marksService.getMarksForUser(user));
		}
		
		return "mark/list";
	}

	/**
	 * Método get que dirige a la vista de anadir nota
	 * 
	 * @return vista de anadir nota
	 */
	@RequestMapping(value = "/mark/add")
	public String getMark(Model model) {
		model.addAttribute("usersList", usersService.getUsers());
		return "mark/add";
	}

	/**
	 * Método post que aniade la nota y devuelve la vista a la lista de notas
	 * 
	 * @param mark nota a aniadir
	 * @return vista a la lista de notas
	 */
	@RequestMapping(value = "/mark/add", method = RequestMethod.POST)
	public String setMark(@ModelAttribute Mark mark) {
		marksService.addMark(mark);
		return "redirect:/mark/list";
	}

	/**
	 * Método get que devuelve la vista de detalle de la nota
	 * 
	 * @param model modelo de dato que viaja en la petición
	 * @param id    identificador de la nota
	 * @return vista de detalle de la nota
	 */
	@RequestMapping("/mark/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("mark", marksService.getMark(id));
		return "mark/details";
	}

	/**
	 * Método get que elimina una nota y devuelve la vista de la lista de notas
	 * 
	 * @param id Identificador de la nota
	 * @return vista de la lista de notas
	 */
	@RequestMapping("/mark/delete/{id}")
	public String deleteMark(@PathVariable Long id) {
		marksService.deleteMark(id);
		return "redirect:/mark/list";
	}

	/**
	 * Método get que devuelve la vista de detalle de una nota
	 * 
	 * @param model modelo de dato que viaja en la petición
	 * @param id    identificador de la nota
	 * @return vista de detalle de una nota
	 */
	@RequestMapping(value = "/mark/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id) {
		model.addAttribute("mark", marksService.getMark(id));
		model.addAttribute("usersList", usersService.getUsers());
		return "mark/edit";
	}

	/**
	 * Método post que modifica un detalle de la nota y devuelve a la vista de
	 * detalle de la nota
	 * 
	 * @param model modelo de dato que viaja en la petición
	 * @param id    identificador de la nota
	 * @param mark  nota del detalle que se modificará
	 * @return vista de detalle de la nota
	 */
	@RequestMapping(value = "/mark/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute Mark mark) {
		Mark original = marksService.getMark(id); // modificar solo score y description
		original.setScore(mark.getScore());
		original.setDescription(mark.getDescription());
		marksService.addMark(original);
		return "redirect:/mark/details/" + id;
	}

	/**
	 * Método get que actualiza la tabla de notas unicamente
	 * 
	 * @param model modelo de dato que viaja en la petición
	 * @return actualiza la tabla de notas
	 */
	@RequestMapping("/mark/list/update")
	public String updateList(Model model, Principal principal) {
		String dni = principal.getName(); // DNI es el name de la autenticación
		User user = usersService.getUserByDni(dni);
		model.addAttribute("markList", marksService.getMarksForUser(user));
		return "mark/list :: tableMarks";
	}

	/**
	 * Pone a <code>TRUE</code> el atributo <code>resend</code> de una nota
	 * 
	 * @param model modelo de dato que viaja en la petición
	 * @param id    identificador de la nota
	 * @return redirije a la lista de notas
	 */
	@RequestMapping(value = "/mark/{id}/resend", method = RequestMethod.GET)
	public String setResendTrue(Model model, @PathVariable Long id) {
		marksService.setMarkResend(true, id);
		return "redirect:/mark/list";
	}

	/**
	 * Pone a <code>FALSE</code> el atributo <code>resend</code> de una nota
	 * 
	 * @param model modelo de dato que viaja en la petición
	 * @param id    identificador de la nota
	 * @return redirije a la lista de notas
	 */
	@RequestMapping(value = "/mark/{id}/noresend", method = RequestMethod.GET)
	public String setResendFalse(Model model, @PathVariable Long id) {
		marksService.setMarkResend(false, id);
		return "redirect:/mark/list";
	}

}
