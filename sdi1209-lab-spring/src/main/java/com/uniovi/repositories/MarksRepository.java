/**
 * 
 */
package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	Page<Mark> findAllByUser(Pageable pageable, User user);

	/**
	 * Retorna todas las notas de la aplicación cuando el texto buscado coincide con
	 * el nombre del usuario o la descripción de la nota.
	 * 
	 * @param seachtext Texto de busqueda
	 * @return Lista de notas 
	 */
	@Query("SELECT r FROM Mark r WHERE (LOWER(r.description) LIKE LOWER(?1) OR LOWER(r.user.name) LIKE LOWER(?1))")
	Page<Mark> searchByDescriptionAndName(Pageable pageable, String seachtext);

	/**
	 * Retorna notas relaccionadas con el usuario enviado como parámetro, cuando el
	 * texto buscado coincide con el nombre del usuario o la descripción de la nota.
	 * 
	 * @param seachtext Texto de busqueda
	 * @param user Usuario
	 * @return Lista de notas
	 */
	@Query("SELECT r FROM Mark r WHERE (LOWER(r.description) LIKE LOWER(?1) OR LOWER(r.user.name) LIKE LOWER(?1)) AND r.user = ?2 ")
	Page<Mark> searchByDescriptionNameAndUser(Pageable pageable, String seachtext, User user);

	Page<Mark> findAll (Pageable pageable);
}
