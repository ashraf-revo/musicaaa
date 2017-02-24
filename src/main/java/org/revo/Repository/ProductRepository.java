package org.revo.Repository;

import org.revo.Domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ashraf on 22/01/17.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}