/**
 * 
 */
package com.uniovi.controllers;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.entities.Mark;

/**
 * @author Pablo Rodríguez Valdés
 *
 *         SDI - 2019
 */

@RestController
public class MarksControllers {

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/mark/list")
	public String getList() {
		return "Get List";
	}

	/**
	 * 
	 * @param description
	 * @param score
	 * @return
	 */
	@RequestMapping(value = "/mark/add", method = RequestMethod.POST)
	public String setMark(@ModelAttribute Mark mark) {
		return "added: " + mark.getDescription() 
			+ " with score : " 
			+ mark.getScore() 
			+ " id: " + mark.getId();
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/mark/details")
	public String getDetail(@RequestParam Long id) {
		return "Getting Detail: " + id;
	}
}
