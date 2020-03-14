package com.azhara.noteapproom.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.azhara.noteapproom.R
import com.azhara.noteapproom.database.Note
import com.azhara.noteapproom.helper.NoteDiffCallBack
import com.azhara.noteapproom.ui.insert.NoteAddUpdateActivity
import kotlinx.android.synthetic.main.note_item.view.*

class MainAdapter internal constructor(private val activity: MainActivity): RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    private val listNote = ArrayList<Note>()

    fun setListNote(note: List<Note>){
        val diffCallBack = NoteDiffCallBack(this.listNote, note)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        this.listNote.clear()
        this.listNote.addAll(note)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false))
    }

    override fun getItemCount(): Int = listNote.size

    override fun onBindViewHolder(holder: MainAdapter.MainViewHolder, position: Int) {
        holder.bind(listNote[position])
    }

    inner class MainViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(note: Note){
            with(itemView){
                tv_item_title.text = note.title
                tv_item_description.text = note.description
                tv_item_date.text = note.date
                setOnClickListener {
                    val intent = Intent(activity, NoteAddUpdateActivity::class.java).apply {
                        putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note)
                        putExtra(NoteAddUpdateActivity.EXTRA_POSITION, adapterPosition)
                    }
                    activity.startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_UPDATE)
                }
            }
        }
    }
}