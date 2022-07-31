package org.kostd.restaurant.domain.repositories

import org.kostd.restaurant.domain.entities.Category
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : CrudRepository<Category, Long> {
}