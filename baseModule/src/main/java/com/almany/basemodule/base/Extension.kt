@file:Suppress("NAME_SHADOWING", "UNREACHABLE_CODE")

package com.almany.basemodule.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


fun Throwable.toErrorBody(): String? {
    when (this) {
        is SocketTimeoutException -> " Check Your Network Connection , Try Again later "
        is ConnectException -> " Check Your Network Connection , Try Again later "
        is UnknownHostException -> message.toString() + " Try again later "
        is HttpException -> message().toString()
    }
    return "this.message.toString()"
}
//
//fun SharedPreferences.Editor.saveUser(result: User) {
//    val name = result.name
//    val token = result.token
//    val email = result.email
//    val mobile = result.mobile
//    val birthday = result.birthday
//    val gender = result.gender
//    val gson = GsonBuilder().create()
//    val json = gson.toJson(User(name, mobile, email, token, birthday,gender))
//    putString(Constants.USER_DATA, json).apply()
//}
//
//fun <T> setList(key: String?, list: List<T>?) {
//    val gson = Gson()
//    val json: String = gson.toJson(list)
//    getSharedPrefrences(HomyApp.getAppContext() as Application).edit().putString(key, json).apply()
//}
//
//fun <T> Context.getList(key: String): MutableList<T>? {
//    var arrayItems: MutableList<T>? = null
//    val serializedObject: String? = getSharedPrefrences(HomyApp.getAppContext() as Application).getString(key, null)
//    if (serializedObject != null) {
//        val gson = Gson()
//        val type = object : TypeToken<MutableList<subServices?>?>() {}.type
//        arrayItems = gson.fromJson(serializedObject, type)
//    }
//    return arrayItems
//}
//
//
//fun <T> Context.getListOfQuestions(key: String): MutableList<T>? {
//    var arrayItems: MutableList<T>? = null
//    val serializedObject: String? = getSharedPrefrences(HomyApp.getAppContext() as Application).getString(key, null)
//    if (serializedObject != null) {
//        val gson = Gson()
//        val type = object : TypeToken<MutableList<Question?>?>() {}.type
//        arrayItems = gson.fromJson(serializedObject, type)
//    }
//    return arrayItems
//}
//
//fun <T> Context.getListOfBarbers(key: String): MutableList<T>? {
//    var arrayItems: MutableList<T>? = null
//    val serializedObject: String? = getSharedPrefrences(HomyApp.getAppContext() as Application).getString(key, null)
//    if (serializedObject != null) {
//        val gson = Gson()
//        val type = object : TypeToken<MutableList<HealthService?>?>() {}.type
//        arrayItems = gson.fromJson(serializedObject, type)
//    }
//    return arrayItems
//}
//
//fun SharedPreferences.getUser(): User? {
//    val gson = GsonBuilder().create()
//    val json = this.getString(Constants.USER_DATA, "")
//    val user = gson.fromJson<User>(json, User::class.java)
//    return user
//
//}
//
//fun Context.makeFirstClickToFirstItemInAdapter(recyclerView: RecyclerView) {
//    Handler().postDelayed(
//            {
//                recyclerView.findViewHolderForAdapterPosition(0)?.itemView?.performClick()
//            },
//            100
//    )
//}
//
//fun Context.makeFirstClickToDatePicker(datePiccker: CalendarView) {
//    Handler().postDelayed(
//            {
//                datePiccker.performClick()
//            },
//            100
//    )
//}
//
//fun Context?.isRTL(): Boolean {
//    val config: Configuration? = this?.resources?.configuration
//    return config?.layoutDirection == View.LAYOUT_DIRECTION_RTL
//
//}

fun ViewModel.launchDataLoad(
    execution: suspend CoroutineScope.() -> Unit,
    errorReturned: suspend CoroutineScope.(Throwable) -> Unit,
    finallyBlock: (suspend CoroutineScope.() -> Unit)? = null
) {

    this.viewModelScope.launch {
        try {
            execution()
        } catch (e: Throwable) {
            errorReturned(e)
        } finally {
            finallyBlock?.invoke(this)
        }
    }
}
//
//
//val Editable.checkIfZeroValueEnterd: Boolean
//    get() {
//        val txtInInteger = this.toString().toInt()
//        if (txtInInteger == 0) {
//            return true
//        }
//        return false
//    }
//
//fun Context.showSnack(view: View?, message: String) {
//    view?.let { viewSnack ->
//        val snack = Snackbar.make(viewSnack, message, Snackbar.LENGTH_SHORT)
//        snack.setActionTextColor(Color.RED)
//        snack.show()
//    }
//
//
//}
//
//@SuppressLint("SimpleDateFormat")
//fun Context.convertToDate(date: String): String {
//    val formatter = SimpleDateFormat("yyyy-MM-dd")
//    return date.format(formatter)
//}
//
//fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
//    val safeClickListener = SafeClickListener {
//        onSafeClick(it)
//    }
//    setOnClickListener(safeClickListener)
//}
//
//fun TextInputLayout.enablePasswordToggle() {
//    this.setPasswordVisibilityToggleTintMode(PorterDuff.Mode.MULTIPLY)
//}
//
//fun TextInputLayout.disablePasswordToggle() {
//    this.setPasswordVisibilityToggleTintMode(PorterDuff.Mode.CLEAR)
//}
//
//fun Context?.showLoginDialog() {
//    val login = this?.let { LoginDialog(it) }
//    login?.show()
//}
//
//fun Context.loadImage(avatarNetwork: String?, avatar: ImageView) {
//    val picasso = Picasso.get()
//    picasso.load(avatarNetwork).placeholder(R.drawable.ic_logo).into(avatar)
//}
//
//fun Context?.showDialogAlert(title: String, fromCancel: Boolean = false, onOkeyButtonClicked: () -> Unit) {
//    val mAlertDialog = AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert) // This needs the activity's context
//    if (fromCancel) mAlertDialog.setPositiveButton("OK") { dialog, id ->
//        dialog.dismiss()
//        onOkeyButtonClicked()
//    }
//    mAlertDialog.show()
//    mAlertDialog.setTitle(this?.resources?.getString(R.string.payment_status))
//    mAlertDialog.setMessage(title)
//    mAlertDialog.show()
//
//}
//
//@SuppressLint("SimpleDateFormat")
//fun Context.isTodayAndTimeWithin30Minutes(date: String): Boolean {
//    val c = Calendar.getInstance().time
//    val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm a")
//    val requiredDate: Date = simpleDateFormat.parse(date)
//    val nowDateAsString: String = simpleDateFormat.format(c)
//    val nowDateAsDate = simpleDateFormat.parse(nowDateAsString)
//
//    var different = requiredDate.time - nowDateAsDate.time
//    val secondsInMilli: Long = 1000
//    val minutesInMilli = secondsInMilli * 60
//    val hoursInMilli = minutesInMilli * 60
//    val daysInMilli = hoursInMilli * 24
//    val elapsedDays = different / daysInMilli
//    different %= daysInMilli
//    val elapsedHours = different / hoursInMilli
//    different %= hoursInMilli
//    val elapsedMinutes = different / minutesInMilli
//    different %= minutesInMilli
//    val elapsedSeconds = different / secondsInMilli
//
//
//    Log.i("date", "required at $requiredDate")
//    Log.i("date", "now date $nowDateAsDate")
//    Log.i("date", "minutes  $elapsedMinutes")
//    Log.i("date", "days  $elapsedDays")
//    return elapsedDays.toInt() == 0 && elapsedMinutes < 30
//
//}
//
//
//fun NotificationManager.sendNotification(messageBody: String, messageTitle: String, applicationContext: Context) {
//    val contentIntent = Intent(applicationContext, OrdersHistoryActivity::class.java)
//    contentIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
//    contentIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//    val pendingIntent = PendingIntent.getActivity(
//            applicationContext, 5, contentIntent,
//            PendingIntent.FLAG_UPDATE_CURRENT
//    )
//
//    val builder = NotificationCompat.Builder(
//            applicationContext,
//            applicationContext.getString(R.string.notification_id)
//    ).setContentTitle((messageTitle))
//            .setContentText(messageBody)
//            .setContentIntent(pendingIntent)
//            .setLargeIcon(BitmapFactory.decodeResource(applicationContext.resources, R.drawable.ic_logo))
//            .setSmallIcon(R.drawable.ic_logo)
//            .setAutoCancel(true)
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//
//    notify(5, builder.build())
//}
//
//fun NotificationManager.cancelAllNotifications() {
//    cancelAll()
//}
//
//fun NotificationManager.cancelSpecificNotifcation(id: Int) {
//    cancel(id)
//}
//
//fun Context.showAlertDialog(title: String, message: String, onYesClicked: () -> Unit) {
//    val builder = AlertDialog.Builder(this)
//    builder.setTitle(title)
//    builder.setMessage(message)
//
//    builder.setPositiveButton(this.resources.getString(R.string.yes)) { dialog, which ->
//        onYesClicked()
//        dialog.dismiss()
//
//    }
//    builder.setNegativeButton(this.resources.getString(R.string.no)) { dialog, which ->
//        dialog.cancel()
//    }
//    val dialog: AlertDialog = builder.create()
//    dialog.setOnShowListener {
//        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setBackgroundColor(resources.getColor(R.color.colorAccent))
//        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setBackgroundColor(resources.getColor(R.color.colorAccent));
//    }
//
//    dialog.show()
//}
//
//fun Context.bitmapToFile(bitmap: Bitmap): File {
//    // Get the context wrapper
//    val wrapper = ContextWrapper(this)
//    val getImage: File? = filesDir
//    val file = File(getImage?.path, "${UUID.randomUUID()}.jpg")
//    try {
//        // Compress the bitmap and save in jpg format
//        val stream: OutputStream = FileOutputStream(file)
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, stream)
//        stream.flush()
//        stream.close()
//    } catch (e: IOException) {
//        e.printStackTrace()
//    }
//    return file
//}
//
//
