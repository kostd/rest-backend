package org.kostd.restaurant.ports.h2db

import org.kostd.restaurant.domain.entities.Category
import org.kostd.restaurant.domain.entities.Dish
import org.kostd.restaurant.domain.repositories.CategoryRepository
import org.kostd.restaurant.domain.repositories.DishRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import javax.inject.Inject
import javax.transaction.Transactional

/**
 * Поскольку на данном этапе используем in-memory базу H2, начальное заведение данных
 * приходится делать каждый раз при запуске сервера. Ловим подходящий момент с помощью
 * ApplicationRunner.
 * #TODO: H2 хотелось бы сохранить в отладочном режиме и после перехода на PG в release
 */
@Component
class OnStartupMenuInitializer : ApplicationRunner {

    @Inject
    lateinit var categoryRepository: CategoryRepository

    @Inject
    lateinit var dishRepository: DishRepository

    var logger: Logger = LoggerFactory.getLogger(OnStartupMenuInitializer::class.java)

    // перед каждым персистом CRUDRepository делает find.
    // @Transactional здесь нужен, чтобы find попадал в контекст персистенции
    // и не провоцировал select-запрос(т.к. и создание объектов и их find в одной транзакции).
    @Transactional()
    override fun run(args: ApplicationArguments?) {
        logger.debug("creating initial menu!")
        // #TODO: наверное, в идеологии Котлина надо здесь написать какой-то опирающийся на константы код
        var dishes: Set<Dish> = mutableSetOf()
        var dish = Dish(name = "Лапша", desc = "Лапша на ушах", imagePath = "", cost = 0)

        dish = dishRepository.save(dish);
        dishes = dishes.plus(dish)

        val category: Category = Category(name = "Горячие блюда", imagePath = "", dishes = dishes)
        categoryRepository.save(category)
    }

}