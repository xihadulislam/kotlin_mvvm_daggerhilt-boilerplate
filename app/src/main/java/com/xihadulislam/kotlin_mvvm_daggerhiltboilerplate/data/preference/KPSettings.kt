package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.preference

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.text.TextUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class KPSettings(mContext: Context) {

    companion object {
        @Provides
        @Singleton
        fun provideKPSettings(@ApplicationContext appContext: Context): KPSettings =
            KPSettings(appContext)
    }

    val preference: SharedPreferences
    private var mEditor: SharedPreferences.Editor? = null
    private var mEditorOpened = false

    val preferenceEditor: SharedPreferences.Editor?
        get() {
            if (mEditorOpened) {
                return mEditor
            }
            mEditorOpened = true
            mEditor = preference.edit()
            return mEditor
        }

    init {
        preference = getSharedPreferenceFile(mContext, "new_mypref")
    }

    /**
     * if a prefFilename is not defined the getDefaultSharedPreferences is used.
     *
     * @param context should be ApplicationContext not Activity
     * @return
     */
    private fun getSharedPreferenceFile(context: Context, prefFilename: String): SharedPreferences {
        return if (TextUtils.isEmpty(prefFilename)) {
            PreferenceManager
                .getDefaultSharedPreferences(context)
        } else {
            context.getSharedPreferences(prefFilename, Context.MODE_PRIVATE)
        }
    }

    fun commitPreferenceEditor() {
        try {
            mEditor!!.commit()
        } catch (e: Exception) {
            // TODO: handle exception
        }

        mEditorOpened = false
    }

    // string
    fun setStringValue(key: String, `val`: String) {
        // TODO Auto-generated method stub
        try {
            mEditor!!.putString(getSecureText(key), getSecureText(`val`))
        } catch (e: Exception) {
            // TODO: handle exception
            val editor = preference.edit()
            editor.putString(getSecureText(key), getSecureText(`val`))
            editor.commit()
        }

    }

    fun getStringValue(key: String): String {
        val defff = preference.getString(getSecureText(key), "")
        return if (defff!!.equals("", ignoreCase = true)) {
            defff
        } else {
            getPlainText(defff)
        }
    }

    fun getStringValue(key: String, defaultstring: String): String {
        // TODO Auto-generated method stub)
        val defff = preference.getString(getSecureText(key), defaultstring)
        return if (defff!!.equals(defaultstring, ignoreCase = true)) {
            defff
        } else {
            getPlainText(defff)
        }
    }

    // int
    fun getIntValue(key: String): Int {
        // TODO Auto-generated method stub
        return preference.getInt(getSecureText(key), 0)
    }

    // int
    fun getIntValue(key: String, defaultvalue: Int): Int {
        // TODO Auto-generated method stub
        return preference.getInt(getSecureText(key), defaultvalue)
    }

    fun setIntValue(key: String, `val`: Int) {
        // TODO Auto-generated method stub
        val editor = preference.edit()
        editor.putInt(getSecureText(key), `val`)
        editor.commit()
    }

    // long
    fun getLongValue(key: String): Long {
        // TODO Auto-generated method stub
        return preference.getLong(getSecureText(key), 0)
    }

    // long
    fun getLongValue(key: String, defaultvalue: Long): Long {
        // TODO Auto-generated method stub
        return preference.getLong(getSecureText(key), defaultvalue)
    }

    fun setLongValue(key: String, `val`: Long) {
        // TODO Auto-generated method stub
        val editor = preference.edit()
        editor.putLong(getSecureText(key), `val`)
        editor.commit()
    }


    // float
    fun getFloatValue(key: String): Float {
        // TODO Auto-generated method stub
        return preference.getFloat(getSecureText(key), -1.0f)
    }

    fun getFloatValue(key: String, `val`: Float): Float {
        // TODO Auto-generated method stub
        return preference.getFloat(getSecureText(key), `val`)
    }

    fun setFloatValue(key: String, `val`: Float) {
        // TODO Auto-generated method stub
        val editor = preference.edit()
        editor.putFloat(getSecureText(key), `val`)
        editor.commit()
    }

    // double
    fun getDoubleValue(key: String): Float {
        // TODO Auto-generated method stub
        return preference.getFloat(getSecureText(key), -1.0f)
    }

    fun setDoubleValue(key: String, `val`: Double) {
        // TODO Auto-generated method stub
        val editor = preference.edit()
        editor.putFloat(getSecureText(key), `val`.toFloat())
        editor.commit()
    }

    // boolean
    fun setBoolValue(key: String, b: Boolean) {
        // TODO Auto-generated method stub
        val editor = preference.edit()
        editor.putBoolean(getSecureText(key), b)
        editor.commit()
    }

    fun removeKeyValue(key: String) {
        try {
            mEditor!!.remove(getSecureText(key))
        } catch (e: Exception) {
            // TODO: handle exception
            val editor = preference.edit()
            editor.remove(getSecureText(key))
            editor.commit()
        }

    }


    fun onResetExcept(data: MutableMap<String, Any>) {
        val editor = preference.edit()
        editor.clear()

        val it = data.entries.iterator()
        while (it.hasNext()) {
            val pair = it.next()

            val itemObject = pair.value
            if (itemObject is Int) {
                this.setIntValue(getSecureText(pair.key), itemObject)
            } else if (itemObject is String) {
                this.setStringValue(getSecureText(pair.key), getSecureText(itemObject))
            } else if (itemObject is Boolean) {
                this.setBoolValue(getSecureText(pair.key), itemObject)
            } else if (itemObject is Long) {
                this.setLongValue(getSecureText(pair.key), itemObject)
            } else if (itemObject is Double) {
                this.setDoubleValue(getSecureText(pair.key), itemObject)
            }

            it.remove() // avoids a ConcurrentModificationException
        }
        editor.commit()
    }

    fun getBoolValue(key: String): Boolean {
        return preference.getBoolean(getSecureText(key), false)
    }

    fun getBoolValue(key: String, defaultvalue: Boolean): Boolean {
        // TODO Auto-generated method stub
        return preference.getBoolean(getSecureText(key), defaultvalue)
    }

    private fun getSecureText(plain_data: String): String {
        return plain_data
    }

    private fun getPlainText(encrypted_data: String): String {
        return encrypted_data
    }


}