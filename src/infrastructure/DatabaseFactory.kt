package com.maha.infrastructure

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        Database.connect(hikari())
        transaction {
            create(Watches)
            Watches.insert {
                it[id] = 1
                it[name] = "Roles"
                it[price] = 10000
                it[discountPrice] = 20000
                it[discountUnits] = 3
            }
            Watches.insert {
                it[id] = 2
                it[name] = "Michael Kors"
                it[price] = 8000
                it[discountPrice] = 12000
                it[discountUnits] = 2
            }
            Watches.insert {
                it[id] = 3
                it[name] = "Swatch"
                it[price] = 5000
            }
            Watches.insert {
                it[id] = 3
                it[name] = "Casio"
                it[price] = 3000
            }
        }
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "org.h2.Driver"
        config.jdbcUrl = "jdbc:h2:mem:test"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }
}