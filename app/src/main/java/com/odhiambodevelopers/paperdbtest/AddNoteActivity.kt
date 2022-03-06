package com.odhiambodevelopers.paperdbtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.odhiambodevelopers.paperdbtest.databinding.ActivityAddBinding
import io.paperdb.Paper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNoteActivity : AppCompatActivity() {

     private lateinit var binding: ActivityAddBinding
     private lateinit var noteList:ArrayList<Notes>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         *  check if the arraylist has data if not it is initialized to an empty arraylist
         */
        noteList = Paper.book().read("notes",ArrayList()) ?: ArrayList()


        binding.btnSave.setOnClickListener {
            //checks if the inserting field are empty
            if (binding.etTitle.text.isEmpty() && binding.etDescription.text.isEmpty()){
                return@setOnClickListener
            }
           CoroutineScope(Dispatchers.IO).launch {
               //adding data to the array list
               val note = Notes(binding.etTitle.text.toString(),binding.etDescription.text.toString())
               noteList.add(note)
               //inserting data to paper database
               Paper.book().write("notes",noteList)
           }
            //Navigating back
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}