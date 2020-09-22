package com.lb.lahorebroast.utilities

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Patterns
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lb.lahorebroast.R
import com.wang.avi.AVLoadingIndicatorView
import java.text.SimpleDateFormat
import java.util.*


class Utilities {

    companion object {
        val imageBaseURL = "https://pos.lahorebroast.com/uploads/img/"
        val countryCode = "+92"
        private var indicatorView: AVLoadingIndicatorView? = null
        private var dialog: Dialog? = null

        fun validateUserName(userName: EditText): Boolean {
            if (userName.text.isNotEmpty()) {
                return true
            }
            userName.error = "UserName cannot be empty"
            return false
        }
        fun showAlertDialog(context: Context, message: String)
        {
            MaterialAlertDialogBuilder(context,R.style.AlertDialogTheme)
                .setTitle("")
                .setMessage(message)
                .setPositiveButton("ok") { dialog, which ->
                    dialog.dismiss()
                }
                .show()
        }
        fun isUserLogin(context: Context):Boolean
        {
            val user = TinyDB(context).getObject("user")
            if(user?.mobile != null) {
                return true
            }
            return false
        }

//        fun showCustomToast(context: Context, message : String)
//        {
//            val customToast = CustomToast(context)
//            customToast.showToast(message)
//        }
        fun validateEmail(email: EditText): Boolean {
            if (email.text.isNotEmpty()) {
                if (Patterns.EMAIL_ADDRESS.matcher(email.text).matches()) {
                    return true
                }
                email.error = "Email not formatted"
                return false
            }
            email.error = "Email cannot be empty"
            return false
        }

        fun isPasswordEmpty(password: EditText): Boolean {
            if (password.text.isNotEmpty()) {
                return true
            }
            password.error = "Password cannot be empty"
            return false
        }

        fun validatePassword(password1: EditText, password2: EditText): Boolean {
            if (isPasswordEmpty(password1) && isPasswordEmpty(password2)) {
                if (password1.text.toString() == password2.text.toString()) {
                    return true
                }
                password1.error = "Password Mismatched"
                return false
            }
            return false
        }

        fun progressdialog(context: Context) {
            dialog = Dialog(context)
            dialog?.setContentView(R.layout.myloader)
            dialog?.setCanceledOnTouchOutside(false)
            dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            indicatorView = dialog?.findViewById(R.id.avi)
            startAnim()
            dialog?.show()
        }

        fun finishprogress() {
            stopAnim()
            dialog?.dismiss()
        }

        private fun startAnim() {
            indicatorView?.show()
            // or avi.smoothToShow();
        }

        private fun stopAnim() {
            indicatorView?.hide()
            // or avi.smoothToHide();
        }

        fun datePicker(editText: EditText, context: Context) {
            val c = Calendar.getInstance()
            val mYear = c.get(Calendar.YEAR)
            val mMonth = c.get(Calendar.MONTH)
            val mDay = c.get(Calendar.DAY_OF_MONTH)
            val days = arrayOf("")
            val month = arrayOf("")


            val datePickerDialog = DatePickerDialog(
                context,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    if (dayOfMonth <= 10) {
                        days[0] = "0$dayOfMonth"
                    } else {
                        days[0] = "" + dayOfMonth
                    }

                    if (monthOfYear <= 10) {
                        month[0] = "0" + (monthOfYear + 1)
                    } else {
                        month[0] = "" + (monthOfYear + 1)
                    }
                    editText.setText(days[0] + "/" + month[0] + "/" + year)
                }, mYear, mMonth, mDay
            )
            datePickerDialog.show()
        }

        fun timePicker(editText: EditText, context: Context) {
            val c = Calendar.getInstance()
            val mHour = c.get(Calendar.HOUR_OF_DAY)
            val mMinute = c.get(Calendar.MINUTE)

            // Launch Time Picker Dialog
            val timePickerDialog = TimePickerDialog(
                context,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    editText.setText(
                        "$hourOfDay:$minute"
                    )
                }, mHour, mMinute, false
            )
            timePickerDialog.show()
        }
        fun createVerticallayoutManager(context: Context): LinearLayoutManager {
            return LinearLayoutManager(context)
        }

        fun createHorizontalLayoutManager(context: Context): LinearLayoutManager {
            return LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        fun getCurrentDate():String
        {
            val calendar = Calendar.getInstance()
            val today = calendar.time
            val dateFormat =  SimpleDateFormat("yyyy-MMM-dd")
            return dateFormat.format(today)
        }
        fun getTomorrowsDate(): String
        {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, 1)
            val tomorrow = calendar.time
            val dateFormat =  SimpleDateFormat("dd-MMM-yyyy")
            return dateFormat.format(tomorrow)
        }
    }


}