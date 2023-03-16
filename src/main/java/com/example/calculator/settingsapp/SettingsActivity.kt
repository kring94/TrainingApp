package com.example.calculator.settingsapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.calculator.databinding.ActivitySettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsActivity : AppCompatActivity() {

    companion object {
        const val VOLUME_LVL = "volume_lvl"
        const val BLUETOOTH_CK = "bluetooth_ck"
        const val DARK_MODE_CK = "dark_mode_ck"
        const val VIBRATE_CK = "vibrate_ck"
    }

    private lateinit var binding: ActivitySettingsBinding
    private var firstTime:Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        CoroutineScope(Dispatchers.IO).launch{
            getSettings().filter{firstTime}.collect {settingsModel ->
                runOnUiThread {
                    binding.apply {
                        swMute.isChecked = settingsModel.vibrate
                        swBluetooth.isChecked = settingsModel.bluetooth
                        swDarkMode.isChecked = settingsModel.darkMode
                        rsVolume.setValues(settingsModel.volume.toFloat())
                        firstTime = !firstTime
                    }
                }
            }
        }
        initUI()
    }

    private fun initUI() {
        binding.rsVolume.addOnChangeListener { _, value, _ ->
            CoroutineScope(Dispatchers.IO).launch {
                saveVolume(value.toInt())
            }
        }
        binding.swBluetooth.setOnCheckedChangeListener { _, isChecked ->
            CoroutineScope(Dispatchers.IO).launch {
                saveChecksOptions(BLUETOOTH_CK, isChecked)
            }
        }
        binding.swDarkMode.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                enableDarkMode()
            }else{
                disableDarkMode()
            }
            CoroutineScope(Dispatchers.IO).launch {
                saveChecksOptions(DARK_MODE_CK, isChecked)
            }
        }
        binding.swMute.setOnCheckedChangeListener { _, isChecked ->
            CoroutineScope(Dispatchers.IO).launch {
                saveChecksOptions(VIBRATE_CK, isChecked)
            }
        }
    }



    private suspend fun saveVolume(value:Int){
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(VOLUME_LVL)] = value
        }
    }

    private suspend fun saveChecksOptions(key: String, value:Boolean){
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    private fun getSettings(): Flow<SettingsModel> {
        return dataStore.data.map { preferences ->
            SettingsModel(
                volume = preferences[intPreferencesKey(VOLUME_LVL)] ?: 50,
                bluetooth = preferences[booleanPreferencesKey(BLUETOOTH_CK)] ?: true,
                darkMode = preferences[booleanPreferencesKey(DARK_MODE_CK)]?: true,
                vibrate = preferences[booleanPreferencesKey(VIBRATE_CK)] ?: true
            )
        }
    }

    private fun enableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun disableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        delegate.applyDayNight()
    }
}