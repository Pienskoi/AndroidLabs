package com.pienskoi.lab3

data class OrderModel(val name: String, val color: String, val price: String) {
    override fun toString(): String {
        return """
            Назва: $name
            Колір: $color
            Діапазон цін: $price
        """.trimIndent()
    }
}
