package com.ctr.homestaybooking.ui.roomdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ctr.homestaybooking.R
import com.ctr.homestaybooking.base.BaseFragment
import com.ctr.homestaybooking.data.model.DateStatus
import com.ctr.homestaybooking.data.source.PlaceRepository
import com.ctr.homestaybooking.extension.onClickDelayAction
import com.ctr.homestaybooking.util.isContain
import com.ctr.homestaybooking.util.isContainAll
import com.ctr.homestaybooking.util.toDate
import com.squareup.timessquare.CalendarCellDecorator
import com.squareup.timessquare.CalendarPickerView
import com.squareup.timessquare.CalendarPickerView.SelectionMode
import com.squareup.timessquare.DefaultDayViewAdapter
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.util.*

/**
 * Created by at-trinhnguyen2 on 2020/11/10
 */
class CalendarFragment : BaseFragment() {
    private lateinit var viewModel: PlaceDetailVMContract
    private var availableDates = listOf<Date>()

    companion object {
        fun newInstance() = CalendarFragment()

        internal const val KEY_BOOKING_SLOTS = "key_booking_slots"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = PlaceDetailViewModel(PlaceRepository())
        initView()
        initListener()
    }

    override fun isNeedPaddingTop() = true

    private fun initView() {

        val current = Calendar.getInstance()
        val nextYear = Calendar.getInstance()
        nextYear.add(Calendar.YEAR, 1)
        (activity as? PlaceDetailActivity)?.bookingSlots
            ?.filter { it.status == DateStatus.AVAILABLE }
            ?.map { it.date.toDate() }
            ?.filter { it.after(current.time) }
            ?.let { availableDates = it }

        calendarPicker.apply {
            setCustomDayView(DefaultDayViewAdapter())
            decorators = emptyList<CalendarCellDecorator>()
            init(current.time, nextYear.time).inMode(SelectionMode.RANGE)
            setOnInvalidDateSelectedListener { null }
            highlightDates(availableDates)
            setDateSelectableFilter { availableDates.isContain(it) }
            setOnDateSelectedListener(object :
                CalendarPickerView.OnDateSelectedListener {
                override fun onDateSelected(date: Date) {
                    if (!availableDates.isContainAll(selectedDates)) {
                        selectDate(date, true)
                    }
                }

                override fun onDateUnselected(date: Date) {
                }
            })
        }
    }

    private fun initListener() {
        imgBack.onClickDelayAction {
            activity?.onBackPressed()
        }

        tvSave.onClickDelayAction {
            calendarPicker.selectedDates.apply { Log.d("--=", "+${this}") }
        }
    }
}
