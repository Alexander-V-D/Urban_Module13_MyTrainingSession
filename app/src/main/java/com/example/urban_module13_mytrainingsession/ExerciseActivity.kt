package com.example.urban_module13_mytrainingsession

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ExerciseActivity : AppCompatActivity() {

    private lateinit var exerciseToolbar: Toolbar
    private lateinit var exerciseNameTV: TextView
    private lateinit var exerciseDescriptionTV: TextView
    private lateinit var exercisePictureIV: ImageView
    private lateinit var timerTV: TextView
    private lateinit var nextBTN: Button
    private lateinit var beginBTN: Button
    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        exerciseToolbar = findViewById(R.id.exerciseToolbar)
        exerciseNameTV = findViewById(R.id.exerciseNameTV)
        exercisePictureIV = findViewById(R.id.exercisePictureIV)
        exerciseDescriptionTV = findViewById(R.id.exerciseDescriptionTV)
        timerTV = findViewById(R.id.timerTV)
        nextBTN = findViewById(R.id.nextBTN)
        beginBTN = findViewById(R.id.beginBTN)


        setSupportActionBar(exerciseToolbar)
        title = getString(R.string.toolbar_title)
        exerciseToolbar.overflowIcon?.setTint(Color.WHITE)

        nextBTN.isEnabled = false

        val exercise = intent.getStringExtra("exercise")
        exerciseNameTV.text = exercise
        var duration = 0
        var index = 0
        when(exercise) {
            "Планка" -> {
                index = 0
                duration = 60
                exerciseDescriptionTV.text =
                    "Выпрямите ноги. Упритесь логтями в пол. Тело выпрямите."
                exercisePictureIV.setImageResource(R.drawable.bar)
            }
            "Альпинист" -> {
                index = 1
                duration = 30
                exerciseDescriptionTV.text =
                    "Выпрямите ноги. Упритесь выпрямленными руками в пол." +
                            " Сгинайте ноги по-очереди в колене к груди."
                exercisePictureIV.setImageResource(R.drawable.climber)
            }
            "Прыжки с ноги на ногу" -> {
                index = 2
                duration = 30
                exerciseDescriptionTV.text =
                    "Ноги на ширине плеч. В прыжке по-очереди переносите вес с ноги на ногу"
                exercisePictureIV.setImageResource(R.drawable.jumps_leg_to_leg)
            }
            "Тяга гантелей стоя" -> {
                index = 3
                duration = 30
                exerciseDescriptionTV.text =
                    "Стоя поднимайте гантели над головой"
                exercisePictureIV.setImageResource(R.drawable.lifting_dumbbells)
            }
            "Выпады вперед" -> {
                index = 4
                duration = 45
                exerciseDescriptionTV.text =
                    "Делайте выпады вперед на колено каждой ногой по-очереди"
                exercisePictureIV.setImageResource(R.drawable.lunges_forward)
            }
            "Отжимания" -> {
                index = 5
                duration = 45
                exerciseDescriptionTV.text =
                    "Упритесь руками в пол. Ноги и тело выпрямите." +
                            " Опускайте корпус почти касаясь пола." +
                            " Возвращайтесь в исходное положение."
                exercisePictureIV.setImageResource(R.drawable.pushups)
            }
            "Отжимания с разворотом корпуса" -> {
                index = 6
                duration = 30
                exerciseDescriptionTV.text = "Упритесь руками в пол. Ноги и тело выпрямите." +
                        " Опускайте корпус почти касаясь пола." + "Распрямляйте руки полностью" +
                        " Возвращайтесь в исходное положение." +
                        " Поднимайте одну руку с разворотом корпуса." +
                        " Возвращайтесь в исходное положение."
                exercisePictureIV.setImageResource(R.drawable.pushups_with_turn)
            }
            "Бег на месте" -> {
                index = 7
                duration = 60
                exerciseDescriptionTV.text = "Бегите на месте"
                exercisePictureIV.setImageResource(R.drawable.running_in_place)
            }
            "Приседания" -> {
                index = 8
                duration = 60
                exerciseDescriptionTV.text = "Выпрямите руки перед собой. Ноги на ширине плеч." +
                        " Сгибайте ноги в коленях почти касаясь пола." +
                        " Возвращайтесь в исходное положение."
                exercisePictureIV.setImageResource(R.drawable.squats)
            }
            "Скручивания корпуса" -> {
                index = 9
                duration = 30
                exerciseDescriptionTV.text = "Лежа на спине выпрямите руки за головой." +
                        " Не сгибая рук и ног коснитесь ими над корпусом. " +
                        "Вернитесь в исходное положение."
                exercisePictureIV.setImageResource(R.drawable.twisting)
            }
        }
        timerTV.text = formatTime(duration * 1000L)
        timer = object : CountDownTimer(
            (duration * 1000).toLong(), 1000L
        ) {
            override fun onTick(millisUntilFinished: Long) {
                timerTV.text = formatTime(millisUntilFinished)
            }

            override fun onFinish() {
                timerTV.text = "Упражение завершено"
                exercisePictureIV.visibility = View.VISIBLE
                nextBTN.isEnabled = true
                exercisePictureIV.setImageResource(0)
            }

        }

        beginBTN.setOnClickListener {
            beginBTN.isEnabled = false
            beginBTN.text = "Упражение выполняется"
            timer.start()
        }

        nextBTN.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            index++
            if (index > 9) index = 0
            intent.putExtra("exercise", Exercises.exercises[index])
            startActivity(intent)
            finish()
        }
    }

    fun formatTime(millis: Long): String {
        val minutes = millis / 60_000
        val seconds = (millis / 1000) - (minutes * 60)
        return if (seconds > 10) "$minutes:$seconds" else "$minutes:0$seconds"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menuExit -> finishAffinity()
        }
        return true
    }
}