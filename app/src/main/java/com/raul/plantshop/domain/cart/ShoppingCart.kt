package com.raul.plantshop.domain.cart

import com.raul.plantshop.domain.plants.Plant
import java.math.BigDecimal
import java.math.RoundingMode

data class PlantItem(val plant: Plant, val qty: Int = 0)

class ShoppingCart(val items: MutableMap<String, PlantItem>) {


    fun values(): List<PlantItem> {
        return items.values.toList()
    }

    fun isEmpty(): Boolean = items.keys.isEmpty()

    fun addItem(item: Plant) {
        val containsKey = items.contains(item.id)
        if (containsKey) {
            val value = items[item.id]!!
            val qty = value.qty + 1

            items[item.id] = value.copy(qty = qty)
        } else {
            items[item.id] = PlantItem(plant = item, qty = 1)
        }

    }

    fun removeItem(item: Plant) {
        val containsKey = items.contains(item.id)
        if (!containsKey) {
            return
        }
        val value = items[item.id]!!
        val qty = value.qty
        if (qty == 1) {
            items.remove(item.id)
        } else {
            items[item.id] = value.copy(qty = qty - 1)
        }
    }

    fun getTotal(): BigDecimal {

        val items = values()

        val total =  items.fold(0.0) { acc, item ->
            acc + (item.qty * item.plant.price)
        }

        return BigDecimal(total).setScale(2,RoundingMode.HALF_EVEN)
    }


}