package com.dmm.checkinstudio

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmm.checkinstudio.ViewAdapter.MyRecyclerViewAdapter
import com.dmm.checkinstudio.ui.main.SectionsPagerAdapter
import com.dmm.checkinstudio.databinding.ActivityMainBinding
import com.dmm.checkinstudio.entities.CheckIn
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var checkInViewModel: CheckInViewModel
    private lateinit var adapter: MyRecyclerViewAdapter

    //On Create apenas para activity main
/*    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //binding = DataBindingUtil.setContentView(this, R.layout.fragment_main)

        val dao = CheckInDatabase.getInstance(application).checkInDAO
        val repository = CheckInRepository(dao)
        val factory = CheckInViewModelFactory(repository)
        checkInViewModel = ViewModelProvider(this, factory).get(CheckInViewModel::class.java)
        binding.myViewModel = checkInViewModel
        binding.lifecycleOwner = this

        checkInViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })

        initRecyclerView()
        closeKeyBoard()
    }*/

    //On Create  para activity Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        closeKeyBoard()
    }

    private fun initRecyclerView() {
        binding.checkinlistRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MyRecyclerViewAdapter({ selectedItem: CheckIn -> listItemClicked(selectedItem) })
        binding.checkinlistRecyclerView.adapter = adapter
        displayCheckInList()
    }

    private fun displayCheckInList() {
        checkInViewModel.getSavedCheckIns().observe(this, Observer {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(checkIn: CheckIn){
        //Toast.makeText(this,"selected name is ${checkIn.token}",Toast.LENGTH_LONG).show()
        checkInViewModel.initUpdateAndDelete(checkIn)
        hideKeyboard()
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}