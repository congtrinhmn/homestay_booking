package com.ctr.homestaybooking.ui.home.rooms

import com.ctr.homestaybooking.base.BaseViewModel
import com.ctr.homestaybooking.data.source.PlaceRepository
import com.ctr.homestaybooking.data.source.response.RoomResponse
import com.ctr.homestaybooking.data.source.response.RoomTypeResponse
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

class RoomViewModel(
    private val hotelRepository: PlaceRepository
) : RoomVMContract, BaseViewModel() {

    private val rooms = mutableListOf<RoomResponse.Room>()

    private var rawRoomTypes = listOf<RoomTypeResponse.RoomTypeStatus>()

    private val roomTypes = mutableListOf<RoomTypeResponse.RoomTypeStatus>()

    override fun getRooms(): MutableList<RoomResponse.Room> = rooms

    override fun getRoomTypes(): MutableList<RoomTypeResponse.RoomTypeStatus> = roomTypes

    override fun filterRoomStatus(
        numOfGuest: Int,
        numOfRoom: Int
    ) {
        getRoomTypes().apply {
            clear()
            addAll(rawRoomTypes.filter { it.totalRoomAvailable >= numOfRoom && it.roomType.capacity >= numOfGuest })
        }
    }

    override fun getAllRoomStatus(
        brandId: Int,
        startDate: String,
        endDate: String,
        numOfGuest: Int,
        numOfRoom: Int
    ): Single<RoomTypeResponse> {
        return hotelRepository.getAllRoomStatus(brandId, startDate, endDate)
            .addProgressLoading()
            .doOnSuccess { response ->
                rawRoomTypes = response.roomTypeStatusList
                getRoomTypes().apply {
                    clear()
                    addAll(rawRoomTypes.filter { it.totalRoomAvailable >= numOfRoom && it.roomType.capacity >= numOfGuest })
                }
            }
    }

    override fun getProgressObservable(): BehaviorSubject<Boolean> =
        progressBarDialogObservable
}
