package com.example.tickets.data.convrtors

import android.icu.text.DecimalFormat
import com.example.data.models.main.Offer
import com.example.data.models.search.TicketsOffer
import com.example.data.models.tickets.Ticket
import com.example.tickets.domain.models.Badge
import com.example.tickets.domain.models.MainData
import com.example.tickets.domain.models.RecommendTicket
import com.example.tickets.domain.models.TicketItem

fun map(dto: Offer): MainData {
    return MainData(
        id = dto.id,
        title = dto.title,
        town = dto.town,
        price = getPrice(dto.price.value)
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
        departureDate = dto.departure.date,
        departureAirport = dto.departure.airport,
        arrivalDate = dto.arrival.date,
        arrivalAirport = dto.arrival.airport,
        duration = getDuration(dto.departure.date, dto.arrival.date),
        hasTransfer = dto.hasTransfer
    )
}

private fun getBadge(badge: String?): Badge {
    return if (badge == null) {
        Badge.EMPTY
    } else {
        Badge.VALUE
    }
}

private fun getDuration(begin: String, end: String): String {
    TODO()
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