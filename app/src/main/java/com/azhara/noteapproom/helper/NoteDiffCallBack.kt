package com.azhara.noteapproom.helper

import androidx.recyclerview.widget.DiffUtil
import com.azhara.noteapproom.database.Note

class NoteDiffCallBack(private val mOldNoteList: List<Note>, private val mNewNoteList: List<Note>): DiffUtil.Callback() {


    override fun getOldListSize(): Int = mOldNoteList.size

    override fun getNewListSize(): Int = mNewNoteList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldNoteList[oldItemPosition].id == mNewNoteList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldNote = mOldNoteList[oldItemPosition]
        val newNote = mNewNoteList[newItemPosition]

        return oldNote.title == newNote.title && oldNote.description == newNote.description
    }
}