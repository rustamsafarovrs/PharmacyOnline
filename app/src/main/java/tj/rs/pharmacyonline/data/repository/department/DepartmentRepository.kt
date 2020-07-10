package tj.rs.pharmacyonline.data.repository.department

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.rs.pharmacyonline.data.model.Department
import tj.rs.pharmacyonline.data.repository.profile.ProfileRepository
import tj.rs.pharmacyonline.modules.NetworkService

/**
 * Created by Rustam Safarov (RS) on 10.07.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */

class DepartmentRepository private constructor(val authRepository: ProfileRepository) {
    val departmentList = arrayListOf<Department>()

    companion object {
        fun instance(authRepository: ProfileRepository) = DepartmentRepository(authRepository)
    }

    fun getDepartment(id: Int, onDepartmentReadyCallback: OnDepartmentReadyCallback) {
        var isContains = false
        for (item in departmentList) {
            if (item.id == id) {
                onDepartmentReadyCallback.onReady(item)
                isContains = true
                break
            }
        }
        if (!isContains) {
            load(id, onDepartmentReadyCallback)
        }
    }

    private fun load(
        id: Int,
        onDepartmentReadyCallback: OnDepartmentReadyCallback
    ) {
        NetworkService.instance()
            .getDepartment(id, authRepository.getPhoneNumber(), authRepository.getCode())
            .enqueue(object : Callback<Department> {
                override fun onFailure(call: Call<Department>, t: Throwable) {
                    onDepartmentReadyCallback.onReady(null)
                }

                override fun onResponse(call: Call<Department>, response: Response<Department>) {
                    response.body()?.let {

                    }
                    onDepartmentReadyCallback.onReady(response.body())
                }
            })
    }

    interface OnDepartmentReadyCallback {
        fun onReady(data: Department?)
    }
}