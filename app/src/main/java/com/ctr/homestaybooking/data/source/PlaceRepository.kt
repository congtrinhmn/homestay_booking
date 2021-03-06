package com.ctr.homestaybooking.data.source

import com.ctr.homestaybooking.data.model.BookingStatus
import com.ctr.homestaybooking.data.source.datasource.PlaceDataSource
import com.ctr.homestaybooking.data.source.remote.PlaceRemoteDataSource
import com.ctr.homestaybooking.data.source.request.BookingBody
import com.ctr.homestaybooking.data.source.request.PlaceBody

/**
 * Created by at-trinhnguyen2 on 2020/06/19
 */
class PlaceRepository : PlaceDataSource {

    private val placeRemoteDataSource = PlaceRemoteDataSource()

    override fun getPlaces() =
        placeRemoteDataSource.getPlaces()

    override fun getPlacesByHostId(id: Int) =
        placeRemoteDataSource.getPlacesByHostId(id)

    override fun getPlaceDetail(placeId: Int) =
        placeRemoteDataSource.getPlaceDetail(placeId)

    override fun editPlace(placeBody: PlaceBody) =
        placeRemoteDataSource.editPlace(placeBody)

    override fun getPlaceTypes() =
        placeRemoteDataSource.getPlaceTypes()

    override fun getProvinces() =
        placeRemoteDataSource.getProvinces()

    override fun getProvinceById(id: Int) =
        placeRemoteDataSource.getProvinceById(id)

    override fun addBooking(bookingBody: BookingBody) =
        placeRemoteDataSource.addBooking(bookingBody)

    override fun getBookingById(id: Int) =
        placeRemoteDataSource.getBookingById(id)

    override fun getBookingHistory(id: Int) =
        placeRemoteDataSource.getBookingHistory(id)

    override fun getBookingByHostId(id: Int) =
        placeRemoteDataSource.getBookingByHostId(id)

    override fun getAllRoomStatus(brandId: Int, startDate: String, endDate: String) =
        placeRemoteDataSource.getAllRoomStatus(brandId, startDate, endDate)

    override fun changeBookingStatus(bookingId: Int, bookingStatus: BookingStatus) =
        placeRemoteDataSource.changeBookingStatus(bookingId, bookingStatus)

    override fun requestPayment(bookingId: Int) =
        placeRemoteDataSource.requestPayment(bookingId)

}
