package com.ctr.homestaybooking.ui.roomdetail

import com.ctr.homestaybooking.base.BaseViewModel
import com.ctr.homestaybooking.data.source.PlaceRepository
import com.ctr.homestaybooking.data.source.response.PromoResponse
import com.ctr.homestaybooking.util.DateUtil
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import java.util.*

/**
 * Created by at-trinhnguyen2 on 2020/06/24
 */
class RoomDetailViewModel(
    private val hotelRepository: PlaceRepository
) : RoomDetailVMContract, BaseViewModel() {

    private val promos = mutableListOf<PromoResponse.Promo>()

    override fun getPromos(): MutableList<PromoResponse.Promo> = promos

    override fun getMaxPromo(): PromoResponse.Promo? = promos.maxBy { it.percentDiscount }

    override fun getPromoByRoomType(roomTypeId: Int, startDate: Calendar): Single<PromoResponse> {
        return hotelRepository.getAllPromoStillActive()
            .addProgressLoading()
            .doOnSuccess { response ->
                getPromos().apply {
                    clear()
                    addAll(response.promos.filter { it.roomType?.id == roomTypeId }.filter {
                        val start = DateUtil.parse(it.startDate, DateUtil.FORMAT_DATE_PROMO_SERVER)
                        val end = DateUtil.parse(it.endDate, DateUtil.FORMAT_DATE_PROMO_SERVER)
                        end.add(Calendar.DATE, 1)
                        startDate.timeInMillis < end.timeInMillis && startDate.timeInMillis > start.timeInMillis
                    })
                }
            }
    }

    override fun getProgressObservable(): BehaviorSubject<Boolean> =
        progressBarDialogStateObservable
}