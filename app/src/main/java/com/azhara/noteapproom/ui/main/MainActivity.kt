package com.azhara.noteapproom.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.azhara.noteapproom.R
import com.azhara.noteapproom.database.Note
import com.azhara.noteapproom.ui.insert.NoteAddUpdateActivity
import com.azhara.noteapproom.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainAdapter: MainAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = obtainViewModel(this@MainActivity)
        mainViewModel.getAllNote().observe(this, noteObserver)

        mainAdapter = MainAdapter(this)
        with(rv_note){
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = mainAdapter
        }

        fab_note.setOnClickListener { view ->
            if (view.id == R.id.fab_note){
                val intent = Intent(this@MainActivity, NoteAddUpdateActivity::class.java)
                startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_ADD)
            }
        }
    }

    private val noteObserver = Observer<List<Note>>{ note ->
        if (note != null){
            mainAdapter.setListNote(note)
        }
    }

    private fun obtainViewModel(activity: MainActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MainViewModel::class.java]
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null){
            if (requestCode == NoteAddUpdateActivity.REQUEST_ADD){
                if (resultCode == NoteAddUpdateActivity.RESULT_ADD){
                    showSnackBarMessage(getString(R.string.added))
                }
            }else if (requestCode == NoteAddUpdateActivity.REQUEST_UPDATE){
                if (resultCode == NoteAddUpdateActivity.RESULT_UPDATE){
                    showSnackBarMessage(getString(R.string.update))
                }else if (resultCode == NoteAddUpdateActivity.RESULT_DELETE){
                    showSnackBarMessage(getString(R.string.delete))
                }
            }
        }
    }

    private fun showSnackBarMessage(snackbar: String) {
        Snackbar.make(rv_note, snackbar,Snackbar.LENGTH_SHORT).show()
    }
}
