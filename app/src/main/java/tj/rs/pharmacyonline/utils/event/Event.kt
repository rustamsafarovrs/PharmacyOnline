package tj.rs.pharmacyonline.utils.event

/**
 * @author Rustam Safarov (RS)
 * created at 13.06.2020
 */
open class Event(
    var isHandled: Boolean = false,
    var type: Type = Type.EXECUTE_WITHOUT_LIMITS
) {


}