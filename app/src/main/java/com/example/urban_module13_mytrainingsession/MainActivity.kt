package com.example.urban_module13_mytrainingsession

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var mainToolbar: Toolbar
    private lateinit var exerciseET: EditText
    private lateinit var startBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainToolbar = findViewById(R.id.mainToolbar)
        exerciseET = findViewById(R.id.exerciseET)
        startBTN = findViewById(R.id.startBTN)

        setSupportActionBar(mainToolbar)
        title = getString(R.string.toolbar_title)
        mainToolbar.overflowIcon?.setTint(Color.WHITE)

        val exercises = Exercises.exercises

        startBTN.setOnClickListener {
            if (exerciseET.text.toString() in exercises) {
                val intent = Intent(this, ExerciseActivity::class.java)
                intent.putExtra("exercise", exerciseET.text.toString())
                startActivity(intent)
            } else {
                val intent = Intent(this, ExerciseListActivity::class.java)
                startActivity(intent)
            }
        }
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