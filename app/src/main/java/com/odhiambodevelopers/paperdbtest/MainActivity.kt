package com.odhiambodevelopers.paperdbtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.odhiambodevelopers.paperdbtest.databinding.ActivityMainBinding
import io.paperdb.Paper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var notes:ArrayList<Notes>
    private val notesAdapter by lazy {
        NotesAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this,AddNoteActivity::class.java))
            finish()
        }

        CoroutineScope(Dispatchers.Main).launch {
            //checks if the array list has data
            notes = Paper.book().read("notes",ArrayList<Notes>()) ?: ArrayList<Notes>()
            //submiting data to the adapter
            notesAdapter.submitList(notes)
            //setting data to the recyclerView
            binding.noteRecycler.adapter =notesAdapter

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.deleteAll){

            Paper.book().delete("notes")

            notesAdapter.notifyDataSetChanged()
        }
        return super.onOptionsItemSelected(item)
    }

}