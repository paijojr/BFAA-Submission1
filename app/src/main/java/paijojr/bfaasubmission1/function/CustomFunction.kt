package paijojr.bfaasubmission1.function

import android.content.Context
import android.content.Intent
import paijojr.bfaasubmission1.R
import paijojr.bfaasubmission1.model.UserModel
import java.text.DecimalFormat

class CustomFunction {
    companion object {
        fun formatNumbers(int: Int): String? {
            val dec = DecimalFormat("#,###")
            return dec.format(int)
        }

        fun shareDetail(context: Context, user: UserModel) {
            val shareText: String = "Name : ${user.name}\nUsername : ${user.username}" +
                    "\n\nRepository : ${user.repository}\nCompany : ${user.company}" +
                    "\nLocation : ${user.location}\n\nFollower : ${user.follower}" +
                    "\nFollowing : ${user.following}"

            val intent= Intent()
            intent.action= Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,shareText)
            intent.type=context.getString(R.string.share_type)
            context.startActivity(Intent.createChooser(intent,context.getString(R.string.menu_share)))
        }

    }
}