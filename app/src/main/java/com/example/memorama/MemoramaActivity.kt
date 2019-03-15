package com.example.memorama

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.example.games.R
import kotlinx.android.synthetic.main.activity_memorama2.*

class MemoramaActivity : AppCompatActivity() {

    var chips:ArrayList<Chip>? = null
    var grid:GridLayoutManager? = null
    var rv:RecyclerView? = null
    var adapter:MemoramaAdapter? = null
    var imgs:ArrayList<Int> = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("Memorama game:","Create Activity")

        cleanTable()

    }

    fun reset(){

        Log.i("Memorama game","Reset!")

        for (i in 0..11){
            chips!![i].turned = false
        }

        Log.i("Memorama game","Chips flipped")

        adapter = MemoramaAdapter(chips!!,this)
        rv!!.adapter = adapter

        Log.i("Memorama game","Adapter reset")
    }

    fun resetAll(){
        Handler().postDelayed(
            {
                Log.i("Memorama game","Reset!")

                imgs = randomImages()

                for (i in 0..11){
                    chips!![i].turned = false
                    chips!![i].found = false
                    chips!![i].idImage = imgs[i]
                }

                Log.i("Memorama game","Chips flipped")

                adapter = MemoramaAdapter(chips!!,this)
                rv!!.adapter = adapter

                Log.i("Memorama game","Adapter reset")

                Toast.makeText(this,"You won",Toast.LENGTH_LONG)
            },
            1000 // value in milliseconds
        )
    }

    fun cleanTable(){

        chips = ArrayList<Chip>()

        setContentView(R.layout.activity_memorama2)
        rv = recyclerView1
        rv!!.setHasFixedSize(true)
        grid = GridLayoutManager(this, 4)
        rv!!.layoutManager = grid

        Log.i("Memorama game","Clean Table")

        chips!!.clear()


        Log.i("Memorama game","Layout setted")

        imgs = randomImages()

        for(i in 0..11){

            chips!!.add(Chip(imgs[i],false,false))

        }

        Log.i("Memorama game","Chips created")

        adapter = MemoramaAdapter(chips!!,this)
        rv!!.adapter = adapter

        Log.i("Memorama game","Adapter setted")


    }

    fun isEnd():Boolean{



        for(i in 0..12){
            if (chips!!.get(i).idImage!=R.mipmap.ic_launcher){
                return false
            }
        }
        return true
    }

    fun clickChip(chip: Chip,index: Int){
        chip.turned == true
        chip.idImage == R.drawable.rocket

        chips!!.set(index,chip)
    }

    fun randomImages():ArrayList<Int>{
        var imgs = ArrayList<Int>()
        imgs.add(R.drawable.baseball)
        imgs.add(R.drawable.baseball)
        imgs.add(R.drawable.rocket)
        imgs.add(R.drawable.rocket)
        imgs.add(R.drawable.f1)
        imgs.add(R.drawable.f1)
        imgs.add(R.drawable.knuckles)
        imgs.add(R.drawable.knuckles)
        imgs.add(R.drawable.space)
        imgs.add(R.drawable.space)
        imgs.add(R.drawable.yoli)
        imgs.add(R.drawable.yoli)

        imgs.shuffle()

        return imgs

    }

}
