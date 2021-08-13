package ge.ajikuridze.messengerapp.avatarfetcher

import com.google.firebase.storage.FirebaseStorage
import java.io.File

class AvatarFetcher(val listener: AvatarListener) {

    private val imageStore = FirebaseStorage.getInstance().getReference("images")

    fun fetchAvatarById(id: String) {
        val localFile = File.createTempFile(id, "jgp")

        imageStore.child(id).getFile(localFile).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                listener.avatarFetched(localFile, id)
            } else {
                listener.avatarFetched(null, id)
            }
        }
    }

}