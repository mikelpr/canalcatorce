package tv.canalcatorce

import android.net.Uri
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.leanback.app.VideoSupportFragment
import androidx.leanback.app.VideoSupportFragmentGlueHost
import androidx.leanback.media.MediaPlayerAdapter
import androidx.leanback.media.PlaybackTransportControlGlue
import androidx.leanback.widget.PlaybackControlsRow

/** Handles video playback with media controls. */
class PlaybackVideoFragment : VideoSupportFragment() {

    companion object {
        val streamUrl14: Uri = Uri.parse("https://60ed5b6d0df78.streamlock.net:1936/segundo/segundo/chunklist_w1744854074.m3u8")
    }
    private lateinit var mTransportControlGlue: PlaybackTransportControlGlue<MediaPlayerAdapter>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        val (_, title, description, _, _, videoUrl) =
                activity?.intent?.getSerializableExtra(DetailsActivity.MOVIE) as Movie

        val meta = Movie(
            14,
            "Canal Catorce",
        )
         */

        val glueHost = VideoSupportFragmentGlueHost(this@PlaybackVideoFragment)
        val playerAdapter = MediaPlayerAdapter(activity)
        playerAdapter.setRepeatAction(PlaybackControlsRow.RepeatAction.INDEX_NONE)

        mTransportControlGlue = PlaybackTransportControlGlue(activity, playerAdapter)
        mTransportControlGlue.isControlsOverlayAutoHideEnabled = true
        mTransportControlGlue.host = glueHost
        //mTransportControlGlue.title = meta.title
        //mTransportControlGlue.subtitle = meta.description
        mTransportControlGlue.art = ResourcesCompat.getDrawable(resources, R.drawable.ic_14, null)
        mTransportControlGlue.playWhenPrepared()
        //playerAdapter.setProgressUpdatingEnabled(false)
        playerAdapter.setDataSource(streamUrl14)
    }

    override fun onPause() {
        super.onPause()
        mTransportControlGlue.pause()
    }
}