package nstv.compositevmchannel.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_list.progressBar
import kotlinx.android.synthetic.main.activity_list.itemsList
import kotlinx.coroutines.launch
import nstv.compositevmchannel.R
import nstv.compositevmchannel.data.model.State
import org.koin.android.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity() {

    private val viewModel: ListCompositeViewModel by viewModel()

    lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        adapter = ListAdapter()
        initView()
        observeViewModel()
    }

    private fun initView() {
        itemsList.adapter = adapter
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            for (state in viewModel.state) {
                when (state) {
                    is State.Loading -> showLoading(true)
                    is State.ShowData -> {
                        showLoading(false)
                        adapter.submitList(state.items)
                    }
                    is State.Error -> {
                        Toast.makeText(baseContext, state.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun showLoading(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}
