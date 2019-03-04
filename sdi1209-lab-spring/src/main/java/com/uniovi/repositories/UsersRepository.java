/**
 * 
 */
package com.uniovi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.User;

/**
 * @author Pablo Rodríguez Valdés
 *
 *         SDI - 2019
 */
public interface UsersRepository extends CrudRepository<User, Long> {

}
