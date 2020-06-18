package tj.rs.pharmacyonline.utils.event

/**
 * @author Rustam Safarov (RS)
 * created at 13.06.2020
 */
enum class Type {
    EXECUTE_WITHOUT_LIMITS, //Самый распространенный тип события – выполнять столько раз, сколько было вызвано без проверки существует ли хоть один подписчик или нет
    EXECUTE_ONCE, //Выполнить данный тип события один раз
    WAIT_OBSERVER_IF_NEEDED,//Событие должно быть обязательно обработано, поэтому необходимо дождаться первого подписчика-обзёрвера на получение события и выполнить его
    WAIT_OBSERVER_IF_NEEDED_AND_EXECUTE_ONCE //Событие должно быть обязательно обработано, поэтому необходимо дождаться первого подписчика-обзёрвера на получение события и выполнить данный тип события один раз
}