/**
 * 
 */
package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.Mark;
import com.uniovi.entities.User;

/**
 * @author Pablo Rodríguez Valdés
 *
 *         SDI - 2019
 */
public interface MarksRepository extends CrudRepository<Mark, Long> {

	@Modifying
	@Transactional
	@Query("UPDATE Mark SET resend = ?1 WHERE id = ?2")
	void updateResend(Boolean resend, Long id);
	
	/**
	 * Devuelve las notas dado un usuario
	 * 
	 * @param user Usuario
	 * @return Notas del usuario
	 */
	@Query("SELECT r FROM Mark r WHERE r.user = ?1 ORDER BY r.id ASC ")
	List<Mark> findAllByUser(User user);
}
