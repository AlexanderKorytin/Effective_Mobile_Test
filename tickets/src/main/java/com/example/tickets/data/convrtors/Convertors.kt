package com.example.tickets.data.convrtors

import android.icu.text.DecimalFormat
import com.example.data.models.main.Offer
import com.example.data.models.search.TicketsOffer
import com.example.data.models.tickets.Ticket
import com.example.tickets.data.models.RecommendationsItemDto
import com.example.tickets.domain.models.Badge
import com.example.tickets.domain.models.OfferData
import com.example.tickets.domain.models.RecommendItem
import com.example.tickets.domain.models.RecommendTicket
import com.example.tickets.domain.models.TicketItem
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

fun map(dto: Offer): OfferData {
    return OfferData(
        id = dto.id,
        title = dto.title,
        town = dto.town,
        price = getPrice(dto.price.value)
    )
}

fun map(dto: RecommendationsItemDto): RecommendItem {
    return RecommendItem(
        icon = dto.icon,
        destinationMane = dto.destinationMane,
        label = dto.label
    )
}

fun map(dto: TicketsOffer): RecommendTicket {
    return RecommendTicket(
        title = dto.title,
        price = getPrice(dto.price.value),
        timeRange = getTimeRange(dto.timeRange)
    )
}

fun map(dto: Ticket): TicketItem {
    return TicketItem(
        badge = getBadge(dto.badge),
        price = getPrice(dto.price.value),
        departureTime = getTime(dto.departure.date),
        departureAirport = getTime(dto.departure.airport),
        arrivalTime = dto.arrival.date,
        arrivalAirport = dto.arrival.airport,
        duration = getDuration(dto.departure.date, dto.arrival.date),
        hasTransfer = dto.hasTransfer
    )
}

private fun getTime(date: String): String {
    val time = date.split(DATE_DELIMITER)[1]
    return time.take(TIME_SIZE)
}
private fun getBadge(badge: String?): Badge {
    return if (badge == null) {
        Badge.EMPTY
    } else {
        Badge.VALUE
    }
}

private fun getDuration(startTime: String, endTime: String): String {
    val arrivalTime = endTime.replace(DATE_DELIMITER, EMPTY)
    val destinationTime = startTime.replace(DATE_DELIMITER, EMPTY)
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val duration = (dateFormat.parse(arrivalTime)?.time ?: EMPTY_TIME) - (dateFormat.parse(
        destinationTime
    )?.time ?: EMPTY_TIME)
    val timeFormat = TimeUnit.MILLISECONDS.toMinutes(duration)
    val result = String.format(Locale.getDefault(), "%.1f", timeFormat.toFloat() / TO_HOURS)
    return result
}

private fun getPrice(value: Int): String {
    val formatingValue = DecimalFormat(PATTERN)
        .format(value)
        .replace(DELIMITER, EMPTY)
    return "$formatingValue ₽"
}

private fun getTimeRange(listTime: List<String>): String {
    val result = StringBuilder()
    listTime.forEach {
        result.append("${it}$EMPTY")
    }
    return result.toString()
}

private const val PATTERN = "#,###"
private const val DELIMITER = ","
private const val EMPTY = " "
private const val DATE_DELIMITER = "T"
private const val TO_HOURS = 60
private const val EMPTY_TIME = 0L
private const val TIME_SIZE = 5

