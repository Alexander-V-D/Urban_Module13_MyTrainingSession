package com.example.urban_module13_mytrainingsession

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ExerciseListActivity : AppCompatActivity() {

    private lateinit var exerciseListToolbar: Toolbar
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_list)

        exerciseListToolbar = findViewById(R.id.exerciseListToolbar)
        listView = findViewById(R.id.listView)

        setSupportActionBar(exerciseListToolbar)
        title = getString(R.string.toolbar_title)
        exerciseListToolbar.overflowIcon?.setTint(Color.WHITE)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            Exercises.exercises
        )
        listView.adapter = adapter

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val exercise = adapter.getItem(position)
                val intent = Intent(this, ExerciseActivity::class.java)
                intent.putExtra("exercise", exercise)
                startActivity(intent)
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