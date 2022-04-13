package com.geek.libnsfw

//import kotlinx.android.synthetic.main.activity_mainnsfw.*
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import kotlin.random.Random


class NsfwMainActivity : NsfwBaseActivity() {

    private lateinit var mRecyclerView: RecyclerView

    override fun initData() {
        //开启日志输出，可选
        NSFWHelper.openDebugLog()
        //扫描前必须初始化
        NSFWHelper.initHelper(
            context = this
        )
        //
        mRecyclerView = findViewById(R.id.mRecyclerView)
        mRecyclerView.layoutManager = GridLayoutManager(this, 2)
        mRecyclerView.adapter =
            object : BaseQuickAdapter<MyNSFWBean, BaseViewHolder>(R.layout.item_mainnsfw) {
                @SuppressLint("SetTextI18n")
                override fun convert(holder: BaseViewHolder, item: MyNSFWBean) {
                    Glide.with(this@NsfwMainActivity).load(item.bitmap)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.getView(R.id.iv))
                    holder.getView<TextView>(R.id.tv).apply {

                        //异步方式
                        NSFWHelper.getNSFWScore(item.bitmap) {
                            this.text =
                                "nsfw:${it.nsfwScore}\nsfw:${it.sfwScore}\n扫描耗时：${it.timeConsumingToScanData} ms"
                            if (it.nsfwScore > 0.7) {
                                this.setBackgroundColor(Color.parseColor("#C1FF0000"))
                            } else if (it.nsfwScore > 0.5) {
                                this.setBackgroundColor(Color.parseColor("#C1FF9800"))
                            } else {
                                this.setBackgroundColor(Color.parseColor("#803700B3"))
                            }
                        }

//                        //同步方式
//                        NSFWHelper.getNSFWScore(item.bitmap).let {
//                            this.text =
//                                "nsfw:${it.nsfwScore}\nsfw:${it.sfwScore}\n扫描耗时：${it.timeConsumingToScanData} ms"
//                            if (it.nsfwScore > 0.7) {
//                                this.setBackgroundColor(Color.parseColor("#C1FF0000"))
//                            } else if (it.nsfwScore > 0.5) {
//                                this.setBackgroundColor(Color.parseColor("#C1FF9800"))
//                            } else {
//                                this.setBackgroundColor(Color.parseColor("#803700B3"))
//                            }
//                        }

                    }

                }
            }.also { adapter ->
                adapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn)
                adapter.isAnimationFirstOnly = false

                Thread({


                    for (i in 1..10) {

                        BitmapFactory.decodeStream(
                            resources.assets.open(
                                "img/${
                                    resources.assets.list(
                                        "img"
                                    )!![Random.nextInt(
                                        1, resources.assets.list(
                                            "img"
                                        )!!.size
                                    )]
                                }"
                            )
                        ).also { bitmap ->
                            runOnUiThread {
                                adapter.addData(MyNSFWBean(bitmap))
                            }

                        }
                    }

                }).start()

            }
    }


    override fun getView(): Int {
        return R.layout.activity_mainnsfw
    }
}

data class MyNSFWBean(val bitmap: Bitmap)
