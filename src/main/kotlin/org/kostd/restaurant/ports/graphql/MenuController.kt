package org.kostd.restaurant.ports.graphql

import org.kostd.restaurant.domain.entities.Category
import org.kostd.restaurant.domain.repositories.CategoryRepository
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Query


@Controller
class MenuController {

    @PersistenceContext
    lateinit var em: EntityManager

    @Inject
    lateinit var categoryRepository: CategoryRepository

    @QueryMapping
    fun menuCategories(): List<Category>{
        return categoryRepository.findAll().toList()
    }

    // #TODO: получать теги jpa-way
    @Transactional
    fun menuCategoriesJPA(): List<Category> {
        // #TODO: переделать в named query, щоб валидировалось
        val query: Query = em.createQuery("from Category")
        val result: List<Category> = query.resultList as List<Category>;
        return result

    }

}