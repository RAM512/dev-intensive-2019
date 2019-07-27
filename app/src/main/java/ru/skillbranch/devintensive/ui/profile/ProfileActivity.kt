package ru.skillbranch.devintensive.ui.profile

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profile.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.models.Bender

class ProfileActivity : AppCompatActivity() {

    /**
     * Вызывается при первом создании или перезапуске Activity.
     *
     * здесь задается внешний вид активности (UI) через метод setContentView().
     * Инициаизируются представления.
     * Представления связываются с необходимыми данными и ресурсами
     * Связываются данные со списками
     *
     * Этот метод также предоставляет Bundle, содержащий ранее сохраненное
     * состояние Activity, если оно было.
     *
     * Всегда сопровождается вызовом onStart().
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

    }

    /**
     * Этот метод сохраняет состояние представления в Bundle
     * Для API Level < 28 (Android P) этот метод будет выполняться до onStop()
     * и нет никаких гарантий, произойдет ли это до или после onPause().
     * Для API Level >= 28 будет вызван после onStop()
     * Не будет вызван, если Activity будет явно закрыто пользователем при нажатии
     * на системную клавишу back
     */
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

    }

}
