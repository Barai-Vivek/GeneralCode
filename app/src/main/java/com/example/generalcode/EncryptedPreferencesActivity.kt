package com.example.generalcode

import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.generalcode.databinding.EncryptedSharedPreferenceActivityBinding

class EncryptedPreferencesActivity : AppCompatActivity() {

    private var _encryptedSharedPreferenceActivityBinding : EncryptedSharedPreferenceActivityBinding ?= null
    private val encryptedSharedPreferenceActivityBinding
        get() = _encryptedSharedPreferenceActivityBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _encryptedSharedPreferenceActivityBinding = EncryptedSharedPreferenceActivityBinding.inflate(layoutInflater)
        setContentView(encryptedSharedPreferenceActivityBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Encrypted Preferences"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _encryptedSharedPreferenceActivityBinding = null
    }


    private fun sharedPreferenceInstance(): SharedPreferences {
        val keyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        return EncryptedSharedPreferences.create(
            "preferences",
            keyAlias,
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun clear(view: View) {
        encryptedSharedPreferenceActivityBinding.edtName.text = null
        encryptedSharedPreferenceActivityBinding.edtAge.text = null
        encryptedSharedPreferenceActivityBinding.txtEncrypted.text = ""
    }
    fun decrypt(view: View) {
        val preference= sharedPreferenceInstance()
        val display = "Name = ${preference.getString("name", "")} \nAge = ${preference.getInt("age", 0)}"
        encryptedSharedPreferenceActivityBinding.txtEncrypted.text = display
    }
    fun encrypt(view: View) {
        val prefEditor = sharedPreferenceInstance().edit()
        prefEditor.putString("name", encryptedSharedPreferenceActivityBinding.edtName.text.toString())
        prefEditor.putInt("age", encryptedSharedPreferenceActivityBinding.edtAge.text.toString().toInt())
        prefEditor.apply()
        encryptedSharedPreferenceActivityBinding.txtEncrypted.text = ""
    }
}