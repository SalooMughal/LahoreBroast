package com.lb.lahorebroast.utilities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lb.lahorebroast.cart.Cart
import com.lb.lahorebroast.cart.CartDao

@Database(entities = arrayOf(Cart::class), version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): CartDao
    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDatabase::class.java, "todo-list.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}