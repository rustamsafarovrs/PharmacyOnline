package tj.rs.pharmacyonline.data.lastmedicine

import android.os.Handler
import tj.rs.pharmacyonline.data.model.Medicine

/**
 * Created by Rustam Safarov (RS) on 06.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class MedicineLocalDataSource private constructor() {

    companion object {
        val instance = MedicineLocalDataSource()
    }

    var arrayList = ArrayList<Medicine>()
    fun getLastMedicine(onLastMedicineLocalDataReadyCallback: OnLastMedicineLocalDataReadyCallback) {
        arrayList.clear()
        arrayList.add(
            Medicine(
                1,
                "Дюфалак сироп 667мг/мл 200мл Local",
                278,
                "Внутрь, при печеночной коме, прекоме, энцефалопатии и гипераммониемии - по 30-50 мл 3 раза в сутки, суточная доза может составлять 90-190 мл; затем в индивидуально подобранной поддерживающей дозе (обеспечивающей pH кала 5-5.5) 2-3 раза в сутки. При запорах - в первые 3 дня по 15-45 мл, затем - 10-30 мл. Детям до 14 лет - в первые 3 дня 15 мл, затем - 10 мл/сут, до 6 лет - 5-10 мл/сут, грудным детям - 5 мл/сут. При сальмонеллезном энтерите - в первые 10-12 дней по 15 мл 3 раза в день.",
                "photo_es_076E2946-5C51-43FD-B25E-0FF47E01A688.jpg"
            )
        )
        arrayList.add(
            Medicine(
                2,
                "Бактистатин капсулы 500 мг 20 шт. Local",
                345,
                "БАД, способствующие нормализации и поддержанию нормальной микрофлоры кишечника.Комплексный препарат природного происхождения,соединяет свойства энтеросорбента, пробиотика и пребиотика.",
                "photo_es_58A63864-2BC9-495A-ABF4-CDFA1C771421.jpg",
                true
            )
        )

        Handler().postDelayed(
            { onLastMedicineLocalDataReadyCallback.onLocalDataReadyCallback(arrayList) },
            1000
        )
    }

    fun saveLastMedicines(data: ArrayList<Medicine>) {

    }

    fun getMedicine(id: Int, onMedicineLocalDataReadyCallback: OnMedicineLocalDataReadyCallback) {
        var data: Medicine? = null
        for (medicine in arrayList) {
            if (medicine.id == id) {
                data = medicine
            }
        }
        Handler().postDelayed(
            { onMedicineLocalDataReadyCallback.onLocalMedicineReadyCallback(data) },
            600
        )
    }
}

interface OnLastMedicineLocalDataReadyCallback {
    fun onLocalDataReadyCallback(data: ArrayList<Medicine>)
}

interface OnMedicineLocalDataReadyCallback {
    fun onLocalMedicineReadyCallback(data: Medicine?)
}