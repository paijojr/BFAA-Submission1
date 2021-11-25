package paijojr.bfaasubmission1

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import paijojr.bfaasubmission1.function.CustomFunction.Companion.formatNumbers
import paijojr.bfaasubmission1.function.CustomFunction.Companion.shareDetail
import paijojr.bfaasubmission1.model.UserModel

class DetailActivity : AppCompatActivity() {
    private lateinit var imageAvatar: ImageView
    private lateinit var textName: TextView
    private lateinit var textUsername: TextView
    private lateinit var textRepo: TextView
    private lateinit var textCompany: TextView
    private lateinit var textLocation: TextView
    private lateinit var textFollower: TextView
    private lateinit var textFollowing: TextView

    companion object {
        const val DETAIL_USER = "detailUser"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initToolbar()
        initView()
        initFun()
    }

    private fun initToolbar() {
        supportActionBar?.title = getString(R.string.title_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun initView() {
        imageAvatar = findViewById(R.id.detail_avatar)
        textName = findViewById(R.id.detail_name)
        textUsername = findViewById(R.id.detail_username)
        textRepo = findViewById(R.id.detail_repo)
        textCompany = findViewById(R.id.detail_company)
        textLocation = findViewById(R.id.detail_location)
        textFollower = findViewById(R.id.detail_follower)
        textFollowing = findViewById(R.id.detail_following)
    }

    private fun initFun() {
        val user = intent.getParcelableExtra<UserModel>(DETAIL_USER) as UserModel
        val imageResource: Int = resources.getIdentifier(user.avatar, null, packageName)
        val res: Drawable? = ResourcesCompat.getDrawable(resources, imageResource, null)
        val username:String = getString(R.string.detail_at) + user.username
        val repo: String = formatNumbers(user.repository) + getString(R.string.detail_title_repo)

        textName.text = user.name
        textUsername.text = username
        textRepo.text =  repo
        textCompany.text = user.company
        textLocation.text = user.location
        textFollower.text = formatNumbers(user.follower)
        textFollowing.text = formatNumbers(user.following)

        Glide.with(this)
            .load(res)
            .circleCrop()
            .into(imageAvatar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_share) {
            val user = intent.getParcelableExtra<UserModel>(DETAIL_USER) as UserModel
            shareDetail(this, user)
            Toast.makeText(this, getString(R.string.menu_share), Toast.LENGTH_LONG).show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}