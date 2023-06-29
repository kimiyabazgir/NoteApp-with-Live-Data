package com.example.roomlivedata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.roomlivedata.databinding.FragmentNotesListBinding
import com.example.roomlivedata.room.NotesAdapter


class NotesListFragment : Fragment() {
    private lateinit var binding:FragmentNotesListBinding
    //Adapter initialize
    private val noteAdapter by lazy { NotesAdapter()}

    override fun onCreateView(inflater: LayoutInflater,container:ViewGroup? ,savedInstanceState: Bundle?):View {
        binding=FragmentNotesListBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            //میخوام کلیک کنم روو اد نوت منو ببره به صفحه ادد نوت
addNoteBtn.setOnClickListener {
    //با findnavcontroller میره صفحه بعد
    findNavController().navigate(R.id.actionListToAdd)
}
    //میخوام دیتای اپدیت شده ام رو بگیرم و توی لیست ریسایکلر ویوم نشون بدم
    //ابزرو رو نوشتیم چون اطلاعات اپدیت شده رو بیاره برای owner اگر اکتیویتی باشه this مینویسیم چون فرگمنتهviewLifecycleOwner
    (activity as NotesActivity).noteDb.noteDao().getAllNotes().observe(viewLifecycleOwner){
        //پر کردن آداپتر
        //ایت mutable list  از NoteEntity بهمون میده
        noteAdapter.differ.submitList(it)
        //پر کردن recycler
       notesRecycler.apply {
           //میتونستیم لینیرلایوت رو بدیم یا گرید لایوت منیجر اما استگر گرید لایوت منیجر فضاهای خالی رو هم در نظر می گیره و لیست مارو مرتب تر پر می کنه
           //گرید لایوت منیجر فضای خالی اگه داشته باشیم رو در نظر نمیگیره و ایتم هارو زیر هم میچینه
           //ایتم اولی که استگر گرید لایوت منیجر ازمون می گیره میگه چند تا چندتا بچینم مثلا میگیم 2 یعنی دو تا دوتا کنار هم بعد برو خط بعد
           //ایتم دومی که استگر گرید لایوت منیجر ازمون می گیره میگه چجوری بچینم ما میگیم افقی یا عمودی
           layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
           adapter=noteAdapter
       }
    }
}
    }

}
