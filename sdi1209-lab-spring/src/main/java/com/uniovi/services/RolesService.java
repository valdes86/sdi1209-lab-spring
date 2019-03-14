/**
 * 
 */
package com.uniovi.services;

import org.springframework.stereotype.Service;

/**
 * @author Pablo Rodríguez Valdés
 *
 *         SDI - 2019
 */
@Service
public class RolesService {

	String[] roles = { "ROLE_STUDENT", "ROLE_PROFESSOR", "ROLE_ADMIN" };

	/**
	 * Devuelve el array de roles del sistema
	 * @return roles del sistema
	 */
	public String[] getRoles() {
		return roles;
	}
}