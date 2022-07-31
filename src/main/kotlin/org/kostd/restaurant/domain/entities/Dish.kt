package org.kostd.restaurant.domain.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Блюдо, позиция в меню.
 */
@Entity
data class Dish(

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        val id: Long = -1L,

        /**
         * название
         */
        val name: String = "",

        /**
         * фотка
         */
        val imagePath: String = "",

        /**
         * цена
         */
        val cost: Int = 0,

        /**
         * состав
         * <p>
         * вообще напрасно объявлять nullable-поля в nullsafe-фреймворке не стоит. Если только
         * ты не ставишь своей целю прочуствовать разницу между dart и kt
         */
        val consist: String? = null,

        /**
         * масса
         */
        val weight: String? = null,

        /**
         * описание
         */
        val desc: String? = null,
) {
}