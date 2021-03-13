package com.maha.infrastructure

import org.jetbrains.exposed.sql.Table

object Watches : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val name = varchar("name", 255)
    val price = integer("price")
    val discountUnits = integer("discountUnits")
    var discountPrice = integer("discountPrice")
}