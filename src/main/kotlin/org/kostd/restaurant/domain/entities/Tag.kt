package org.kostd.restaurant.domain.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * тег, которым можно помечать блюда. В перспективе может создаваться пользователями
 */
@Entity
data class Tag(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        val id: Long = -1L,

        /**
         * ключевое слово, латинское название
         */
        val keyword: String,

        /**
         * название
         */
        val name: String) {
}