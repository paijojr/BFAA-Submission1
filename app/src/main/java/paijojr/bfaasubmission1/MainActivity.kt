package paijojr.bfaasubmission1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import paijojr.bfaasubmission1.model.UserModel

class MainActivity : AppCompatActivity() {


    private lateinit var recycler: RecyclerView
    private val list = ArrayList<UserModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}