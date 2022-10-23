@file:Suppress("NonAsciiCharacters")

package scripts

import model.dsl.*

fun main() = model("cabinet1", "Шкаф непонятный") {

    val основной_материал = Material("mlMain", "Основной", "ДСП", 48, 15, "/Img/dsp.jpg") { 84 }
    val белый_материал = Material("mlWhite", "Декоративный", "ДСП", 48, 15, "/Img/dsp.jpg") { 74 }

    val ширина = Property("sh", "Ширина", 2000, 3000) { 2800 }

    val minШиринаНижнегоЯщика = 500
    val maxШиринаНижнегоЯщика = 1200
    val maxШиринаБоковогоЯщика = minШиринаНижнегоЯщика
    val minШиринаБоковогоЯщика = maxШиринаБоковогоЯщика / 2

    val ширина_нижнего_ящика = Property("downYaSh", "Ширина нижнего ящика",
        minШиринаНижнегоЯщика, maxШиринаНижнегоЯщика) { maxШиринаНижнегоЯщика - 200 }
    val ширина_бокового_ящика = Property("leftYaSh", "Ширина бокового ящика",
        minШиринаБоковогоЯщика, maxШиринаБоковогоЯщика) { minШиринаБоковогоЯщика + 100 }

    val ширина_раздела = Property("upPolPartSh", "Ширина раздела", 100, 300) { 150 }

    val половина_ширины = ширина / 2
    val половина_ширины_нижнего_ящика = ширина_нижнего_ящика / 2
    val половина_ширины_бокового_ящика = ширина_бокового_ящика / 2

    val высота = Property("vs", "Высота", 2000, 3000) { 2400 }
    val высота_стола = Property("tableVs", "Высота стола", 1000, 1500) { 1200 }
    val высота_нижнего_ящика = Property("downYaVs", "Высота нижнего ящика", 200, 600) { 400 }
    val высота_верхних_ящиков = Property("upYaVs", "Высота верхних ящиков", 100, 500) { 300 }
    val высота_верхней_полки = Property("upPolVs", "Высота верхней полки", 100, 500) { 270 }

    val половина_высоты = высота / 2
    val половина_высоты_стола = высота_стола / 2
    val половина_высоты_нижнего_ящика = высота_нижнего_ящика / 2

    val глубина_нижней_части = Property("downGl", "Глубина нижней части", 700, 1500) { 1200 }
    val глубина_верхней_части = Property("upGl", "Глубина верхней части", 500, 700) { 600 }

    val угол_верхнего_ящика1 = Property("upYa1Angle", "Верхняя дверца 1", 0, 87) { 10 }
    val угол_верхнего_ящика2 = Property("upYa2Angle", "Верхняя дверца 2", 0, 87) { 5 }

    val угол_бокового_ящика = Property("leftYaAngle", "Боковая дверца", 0, 100) { 5 }

    val нижний_ящик = Property("downYaAngle", "Нижний ящик", 0, 650) { 13 }

    val половина_глубины_нижней_части = глубина_нижней_части / 2
    val половина_глубины_верхней_части = глубина_верхней_части / 2

    val ширина_материала = 18.toProperty()
    val половина_ширины_материала = ширина_материала / 2
    val ширина_материала_дверцы = ширина_материала

    val высота_нижнего_ящика_над_полом = 20.toProperty()
    val половина_высоты_нижнего_ящика_над_полом = высота_нижнего_ящика_над_полом / 2

    ////////////// Нижний ящик ///////////////

    // Левая стенка
    element(основной_материал) {
        x = -половина_ширины - половина_ширины_материала
        y = половина_высоты_нижнего_ящика + половина_высоты_нижнего_ящика_над_полом - половина_ширины_материала
        z = половина_глубины_нижней_части
        dx = ширина_материала
        dy = высота_нижнего_ящика + высота_нижнего_ящика_над_полом - ширина_материала
        dz = глубина_нижней_части
    }
    // Верхняя стенка
    element(основной_материал) {
        x = -половина_ширины + половина_ширины_нижнего_ящика - половина_ширины_материала
        y = высота_нижнего_ящика + высота_нижнего_ящика_над_полом - половина_ширины_материала
        z = половина_глубины_нижней_части
        dx = ширина_нижнего_ящика + ширина_материала
        dy = ширина_материала
        dz = глубина_нижней_части
    }
    // Нижняя
    element(основной_материал) {
        x = -половина_ширины + половина_ширины_нижнего_ящика
        y = высота_нижнего_ящика_над_полом + половина_ширины_материала
        z = половина_глубины_нижней_части
        dx = ширина_нижнего_ящика
        dy = ширина_материала
        dz = глубина_нижней_части
    }
    // Боковая стенка (левая ножка стола)
    element(основной_материал) {
        x = -половина_ширины + ширина_нижнего_ящика + половина_ширины_материала
        y = половина_высоты_стола
        z = половина_глубины_нижней_части
        dx = ширина_материала
        dy = высота_стола
        dz = глубина_нижней_части
    }

    ////////////// Стол ///////////////

    // Поверхность
    element(основной_материал) {
        val ширина_стола = ширина - ширина_бокового_ящика

        x = половина_ширины - ширина_стола / 2
        y = высота_стола + половина_ширины_материала
        z = половина_глубины_нижней_части
        dx = ширина_стола
        dy = ширина_материала
        dz = глубина_нижней_части
    }
    // Правая ножка
    element(основной_материал) {
        x = половина_ширины + половина_ширины_материала
        y = половина_высоты
        z = половина_глубины_верхней_части
        dx = ширина_материала
        dy = высота
        dz = глубина_верхней_части
    }
    // Распорка
    element(основной_материал) {
        val ширина_распорки = ширина - ширина_нижнего_ящика - ширина_материала
        val высота_распорки = 300.toProperty()
        val глубина_распорки = 200.toProperty()

        x = половина_ширины - ширина_распорки / 2
        y = высота_стола - высота_распорки / 2
        z = глубина_распорки + половина_ширины_материала
        dx = ширина_распорки
        dy = высота_распорки
        dz = ширина_материала
    }

    ////////////// Верхняя часть ////////////////

    // Левая стенка
    val высота_верхней_части_стенки = высота - (высота_нижнего_ящика + высота_нижнего_ящика_над_полом)
    element(основной_материал) {
        x = -половина_ширины - половина_ширины_материала
        y = высота_нижнего_ящика_над_полом + высота_нижнего_ящика + высота_верхней_части_стенки / 2
        z = половина_глубины_верхней_части
        dx = ширина_материала
        dy = высота_верхней_части_стенки
        dz = глубина_верхней_части
    }
    // Верхняя стенка (потолок)
    element(основной_материал) {
        x = 0.toProperty()
        y = высота - половина_ширины_материала
        z = половина_глубины_верхней_части
        dx = ширина
        dy = ширина_материала
        dz = глубина_верхней_части
    }

    val внутренняя_глубина_верхней_части = глубина_верхней_части - ширина_материала_дверцы
    val половина_внутренней_глубины_верхней_части = внутренняя_глубина_верхней_части / 2
    val y_нижней_грани_верхних_шкафов = высота - ширина_материала - высота_верхних_ящиков

    // Полка для ящиков
    element(основной_материал) {
        x = 0.toProperty()
        y = y_нижней_грани_верхних_шкафов + половина_ширины_материала
        z = половина_внутренней_глубины_верхней_части
        dx = ширина
        dy = ширина_материала
        dz = внутренняя_глубина_верхней_части
    }
    // Средняя доска в полке для ящиков
    val высота_доски = высота_верхних_ящиков - ширина_материала
    element(основной_материал) {
        val глубина_доски = глубина_верхней_части - ширина_материала - ширина_материала_дверцы

        x = 0.toProperty()
        y = y_нижней_грани_верхних_шкафов + ширина_материала + высота_доски / 2
        z = глубина_доски / 2 + ширина_материала
        dx = ширина_материала
        dy = высота_доски
        dz = глубина_доски
    }
    // Верхняя полка с разделами
    val внутренняя_высота_полки = высота_верхней_полки - ширина_материала
    val ширина_полки = ширина - ширина_бокового_ящика
    element(основной_материал) {
        x = половина_ширины_бокового_ящика
        y = y_нижней_грани_верхних_шкафов - внутренняя_высота_полки - половина_ширины_материала
        z = половина_внутренней_глубины_верхней_части
        dx = ширина_полки
        dy = ширина_материала
        dz = внутренняя_глубина_верхней_части
    }

    //// Разделы для полки ////
    val высота_раздела = внутренняя_высота_полки
    val глубина_раздела = внутренняя_глубина_верхней_части - ширина_материала
    val x_левой_стенки_левого_раздела = -половина_ширины + ширина_бокового_ящика
    for (i in 0..2) {
        element(белый_материал) {
            x = x_левой_стенки_левого_раздела + i * ширина_материала_дверцы + (i + 1) * ширина_раздела
            y = y_нижней_грани_верхних_шкафов - высота_раздела / 2
            z = ширина_материала + глубина_раздела / 2
            dx = ширина_материала_дверцы
            dy = высота_раздела
            dz = глубина_раздела
        }
    }
    // Правая стенка левого ящика
    val высота_правой_стенки_левого_ящика = высота_верхней_части_стенки - ширина_материала - высота_верхних_ящиков
    val y_правой_стенки_левого_ящика = высота - ширина_материала - высота_верхних_ящиков - высота_правой_стенки_левого_ящика / 2
    element(основной_материал) {
        x = -половина_ширины + ширина_бокового_ящика - половина_ширины_материала
        y = y_правой_стенки_левого_ящика
        z = половина_внутренней_глубины_верхней_части
        dx = ширина_материала
        dy = высота_правой_стенки_левого_ящика
        dz = внутренняя_глубина_верхней_части
    }

    /////////// Задние стенки ////////////

    // Верхние ящики
    element(основной_материал) {
        x = 0.toProperty()
        y = y_нижней_грани_верхних_шкафов + ширина_материала + высота_доски / 2
        z = половина_ширины_материала
        dx = ширина
        dy = высота_доски
        dz = ширина_материала
    }
    // Левый ящик
    element(основной_материал) {
        val внутренняя_ширина_бокового_ящика = ширина_бокового_ящика - ширина_материала
        x = -половина_ширины + внутренняя_ширина_бокового_ящика / 2
        y = y_правой_стенки_левого_ящика
        z = половина_ширины_материала
        dx = внутренняя_ширина_бокового_ящика
        dy = высота_правой_стенки_левого_ящика
        dz = ширина_материала
    }
    // Нижний ящик
    element(основной_материал) {
        x = -половина_ширины + половина_ширины_нижнего_ящика
        y = высота_нижнего_ящика_над_полом + половина_высоты_нижнего_ящика
        z = половина_ширины_материала
        dx = ширина_нижнего_ящика
        dy = высота_нижнего_ящика - 2 * ширина_материала
        dz = ширина_материала
    }
    // Задняя стенка полки
    element(основной_материал) {
        x = половина_ширины_бокового_ящика
        y = y_нижней_грани_верхних_шкафов - внутренняя_высота_полки / 2
        z = половина_ширины_материала
        dx = ширина_полки
        dy = внутренняя_высота_полки
        dz = ширина_материала
    }

    ///////// Дверцы ////////
    val зазор = 1.toProperty()
    // Верхние ящики
    element(белый_материал) {
        val y_без_вращения = высота - ширина_материала - высота_верхних_ящиков / 2
        val z_без_вращения = глубина_верхней_части - ширина_материала_дверцы / 2
        val dy_без_вращения = высота_верхних_ящиков
        val a = угол_верхнего_ящика1.toRadians()
        x = половина_ширины / 2
        y = y_без_вращения + (cos(a) - 1) * dy_без_вращения / 2
        z = z_без_вращения + dy_без_вращения / 2 * sin(a)
        dx = половина_ширины - зазор
        dy = dy_без_вращения - зазор
        dz = ширина_материала_дверцы
        px = a
    }
    element(белый_материал) {
        val y_без_вращения = высота - ширина_материала - высота_верхних_ящиков / 2
        val z_без_вращения = глубина_верхней_части - ширина_материала_дверцы / 2
        val dy_без_вращения = высота_верхних_ящиков
        val a = угол_верхнего_ящика2.toRadians()
        x = -половина_ширины / 2
        y = y_без_вращения + (cos(a) - 1) * dy_без_вращения / 2
        z = z_без_вращения + dy_без_вращения / 2 * sin(a)
        dx = половина_ширины - зазор
        dy = dy_без_вращения - зазор
        dz = ширина_материала_дверцы
        px = a
    }
    // Боковой ящик
    element(белый_материал) {
        val x_без_вращения = -половина_ширины + половина_ширины_бокового_ящика
        val z_без_вращения = глубина_верхней_части - ширина_материала_дверцы / 2
        val dx_без_вращения = ширина_бокового_ящика
        val a = угол_бокового_ящика.toRadians()
        x = x_без_вращения + (cos(a) - 1) * dx_без_вращения
        y = высота_нижнего_ящика_над_полом + высота_нижнего_ящика + высота_правой_стенки_левого_ящика / 2
        z = z_без_вращения + dx_без_вращения / 2 * sin(a)
        dx = ширина_бокового_ящика
        dy = высота_правой_стенки_левого_ящика
        dz = ширина_материала_дверцы
        pz = a
    }
    ///// Выдвижной ящик /////
    // TODO
}