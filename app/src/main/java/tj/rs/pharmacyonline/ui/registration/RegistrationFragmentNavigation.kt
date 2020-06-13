package tj.rs.pharmacyonline.ui.registration

import tj.rs.pharmacyonline.utils.event.Event

/**
 * @author Rustam Safarov (RS)
 * created at 13.06.2020
 */
interface RegistrationFragmentNavigation {
    class ShowNetworkError : Event()
    class OpenConfirmFragment : Event()
    class PhoneError : Event()
    class OpenAuthorizedActivity : Event()
    class SMSCodeError : Event()
}