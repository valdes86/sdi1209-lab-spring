/**
 * 
 */
package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.entities.Mark;
import com.uniovi.services.MarksService;

/**
 * @author Pablo Rodríguez Valdés
 *
 *         SDI - 2019
 */

@RestController
public class MarksControllers {

	@Autowired
	private MarksService marksService;

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/mark/list")
	public String getList() {
		return marksService.getMarks().toString();
	}

	/**
	 * 
	 * @param description
	 * @param score
	 * @return
	 */
	@RequestMapping(value = "/mark/add", method = RequestMethod.POST)
	public String setMark(@ModelAttribute Mark mark) {
		marksService.addMark(mark);
		return "Ok";
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/mark/details/{id}")
	public String getDetail(@PathVariable Long id) {
		return marksService.getMark(id).toString();
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/mark/delete/{id}")
	public String deleteMark(@PathVariable Long id) {
		marksService.deleteMark(id);
		return "Ok";
	}

}
