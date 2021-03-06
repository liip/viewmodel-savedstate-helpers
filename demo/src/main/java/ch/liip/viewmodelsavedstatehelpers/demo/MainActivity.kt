package ch.liip.viewmodelsavedstatehelpers.demo

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtain the ViewModel with SavedStateViewModelFactory
        viewModel = ViewModelProviders.of(this, SavedStateViewModelFactory(application, this)).get(MainViewModel::class.java)

        // Set the value manually during creation
        manualText.setText(viewModel.manualText)
        manualValue.setText(viewModel.manualText)

        // Observe the livedata
        viewModel.liveDataText.observe(this, Observer {
            liveDataText.setText(it)
            liveDataValue.setText(it)
        })

        // Save the values
        // This is done manually for the demo
        button.setOnClickListener {
            viewModel.manualText = manualText.text.toString()
            viewModel.liveDataText.value = liveDataText.text.toString()
        }
    }
}
