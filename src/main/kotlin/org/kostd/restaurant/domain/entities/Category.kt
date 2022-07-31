package org.kostd.restaurant.domain.entities

import javax.persistence.*


/**
 * Категтрия блюд. Одно блюдо может входить в несколько категорий
 */
@Entity
data class Category (

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        val id: Long = -1L,

        /**
         * название блюда
         */
        val name: String = "",

        /**
         * картинка
         */
        val imagePath: String = "",

        /**
         * блюда в данной категории
         */
        @ManyToMany()
        @JoinTable(name = "CATEGORY_DISH")
        val dishes: Set<Dish> = mutableSetOf<Dish>()
        ) {
}