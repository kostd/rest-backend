package org.kostd.restaurant.domain.repositories

import org.kostd.restaurant.domain.entities.Dish
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Магия: создаем только интерфейс, Спринг сам берет где-то реализацию.
 */
@Repository
interface DishRepository : CrudRepository<Dish, Long> {
}