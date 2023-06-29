package com.example.roomlivedata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.roomlivedata.databinding.FragmentAddNoteBinding
import com.example.roomlivedata.room.db.NoteEntity
import com.google.android.material.snackbar.Snackbar

class AddNoteFragment : Fragment() {
    private lateinit var binding: FragmentAddNoteBinding
    private lateinit var note:NoteEntity
    override fun onCreateView(inflater: LayoutInflater,container:ViewGroup? ,savedInstanceState: Bundle?):View {
        binding=FragmentAddNoteBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            //Save note
            btnSave.setOnClickListener {
                val title = titleEdt.text.toString()
                val desc = descEdt.text.toString()

                if (title.isNotEmpty() || desc.isNotEmpty()) {
                    //پر کردن و اینیشیالایز کردن نوت
                    note= NoteEntity(0,title, desc)
                    //برای ذخیره کردن به دیتابیس نیاز داریم حالا میخوام توسط فرگمنت ادد به یکی از متغییر هایی که توی اکتیویتیم هست دسترسی داشته باشم
                    (activity as NotesActivity).noteDb.noteDao().saveNote(note)
                    //بستن فرگمنت فعلی و باز کردن فرگمنت قبلب
                    findNavController().popBackStack()
                }
                else{
                    Snackbar.make(it,"فیلد خالی است",Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}
