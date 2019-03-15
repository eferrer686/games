package com.example.memorama

import android.app.Activity
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.games.R
import kotlinx.android.synthetic.main.renglon.view.*

class MemoramaAdapter(val chips: ArrayList<Chip>,val activity: MemoramaActivity):

    RecyclerView.Adapter<MemoramaAdapter.ChipViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ChipViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.renglon, p0, false)
        return ChipViewHolder(view)

    }

    override fun getItemCount(): Int {
        return  chips.size
    }

    override fun onBindViewHolder(p0: ChipViewHolder, p1: Int) {

        if(chips[p1].found){
            p0.imageView.setImageResource(chips[p1].idImage)
        }
        else {
            p0.imageView.setImageResource(R.mipmap.ic_launcher)
        }
        p0.chip = chips[p1]
        p0.chipIndex= p1
    }


    inner class ChipViewHolder(item : View) : RecyclerView.ViewHolder(item){

        val imageView = item.findViewById<ImageView>(R.id.chip)
        var chip:Chip = Chip(0,false,false)
        var chipIndex:Int = 0;

        init {
            item.setOnClickListener {
                imageView.setImageResource(chip.idImage)
                chip.turned = true

                checkBoard()

                if(isEnd()){
                    activity.resetAll()
                }




            }
        }

        fun isEnd():Boolean{

            for(i in 0..11){
                if (!chips[i].found){
                    Log.i("Memorama is end","i: "+ i +"-"+ chips[i].turned)
                    return false
                }
            }


            return true
        }

        fun checkBoard(){

            Log.i("Memorama game","Check Board")

            var check:Boolean = false
            var odd:Boolean = false
            var cont:Int = 0

            for (i in 0..11){
                Log.i("Memorama game","Check for:"+ i+ " - " + chips[i].turned)
                Log.i("Memorama game","Check for:"+ i+ " - " + chips[i].idImage)
                Log.i("Memorama game","Check for:"+ i+ " - " + chip.idImage)
                if(i!=chipIndex && chips[i].turned && chips[i].idImage==chip.idImage){
                    check = true
                    chips[i].found = true
                    chip.found = true
                }
                if(chips[i].turned){
                    cont++
                }
            }
            Log.i("Memorama game","Cont: " + cont)
            Log.i("Memorama game","Check: " + check)

            if (!check && cont%2!=1){

                Handler().postDelayed(
                    {
                        chip.turned=false
                        Toast.makeText(activity,"You lost",Toast.LENGTH_SHORT)
                        activity.reset()// This method will be executed once the timer is over
                    },
                    1000 // value in milliseconds
                )

            }
        }

    }

}
