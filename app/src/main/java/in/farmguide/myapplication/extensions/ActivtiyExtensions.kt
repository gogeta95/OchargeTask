package `in`.farmguide.myapplication.extensions

import android.app.Activity
import android.widget.Toast

fun Activity.longToast(message: String?) {
    if (!message.isNullOrBlank())
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}