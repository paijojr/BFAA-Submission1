package paijojr.bfaasubmission1.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel (
    var username: String,
    var name: String,
    var avatar: String,
    var company: String,
    var location: String,
    var repository: Int,
    var follower: Int,
    var following: Int
) : Parcelable