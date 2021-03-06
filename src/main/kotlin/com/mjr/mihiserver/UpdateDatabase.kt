package com.mjr.mihiserver

import java.io.File
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*

object SendToDatabase {
    internal var conn: Connection? = null
    internal var username = "appuser"
    internal var password = "appuserpassword"

    fun getConnection() {
        val connectionProperties = Properties()
        connectionProperties.put("user", username)
        connectionProperties.put("password", password)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance()
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/mihi", connectionProperties
            )
        } catch (ex: SQLException) {
            ex.printStackTrace()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun writeData(organisation:String, data: String) {
        var stmt: Statement? = conn!!.createStatement()
        val sqlText = "insert into sessions (idSessions, organisation, sessionData) value(0, \"$organisation\", \"$data\");"
        stmt!!.execute(sqlText)
    }
}