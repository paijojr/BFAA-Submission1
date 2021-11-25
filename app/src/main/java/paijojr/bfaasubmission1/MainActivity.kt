package paijojr.bfaasubmission1

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import paijojr.bfaasubmission1.adapter.UserAdapter
import paijojr.bfaasubmission1.model.UserModel

class MainActivity : AppCompatActivity() {


    private lateinit var recycler: RecyclerView
    private val list = ArrayList<UserModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        list.addAll(listUser)
        showRecyclerList()
    }

    private fun initView() {
        recycler = findViewById(R.id.main_recycler)
        recycler.setHasFixedSize(true)
    }


    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recycler.layoutManager = GridLayoutManager(this, 2)
        } else {
            recycler.layoutManager = LinearLayoutManager(this)
        }
        recycler.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
        val listUserAdapter = UserAdapter(list)
        recycler.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UserModel) {
                showSelectedUser(data)
            }
        })
    }

    private fun showSelectedUser(user: UserModel) {
        Log.e("MainActivity", user.name)
    }

    private val listUser: ArrayList<UserModel>
        get() {
            val objectData = JSONObject(applicationContext.assets.open(getString(R.string.main_json)).bufferedReader().use { it.readText() })
            val arrayUsers =  objectData.getJSONArray(getString(R.string.key_users))

            val listUsers = ArrayList<UserModel>()

            for (i in 0 until arrayUsers.length()) {
                val username = arrayUsers.getJSONObject(i).getString(getString(R.string.key_username))
                val name = arrayUsers.getJSONObject(i).getString(getString(R.string.key_name))
                val avatar = arrayUsers.getJSONObject(i).getString(getString(R.string.key_avatar))
                val company = arrayUsers.getJSONObject(i).getString(getString(R.string.key_company))
                val location = arrayUsers.getJSONObject(i).getString(getString(R.string.key_location))
                val repository = arrayUsers.getJSONObject(i).getInt(getString(R.string.key_repository))
                val follower = arrayUsers.getJSONObject(i).getInt(getString(R.string.key_follower))
                val following = arrayUsers.getJSONObject(i).getInt(getString(R.string.key_following))

                val dataList = UserModel(username, name, avatar, company, location, repository, follower, following)
                listUsers.add(dataList)
            }
            return listUsers
        }
}