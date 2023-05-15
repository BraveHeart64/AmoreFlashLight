package com.example.amoreflashlight

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    private var state: Boolean  = false
    private var camera_avaliable :String = ""
    private var  mycamera : Boolean = false




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SetMyCamera(SetUpCamera())
        setContentView(R.layout.activity_main)
        DeviceCompatibility()
    }



    fun SetState(value: Boolean){
        this.state = value
    }

    fun GetState():Boolean{

        return this.state
    }

    fun SetCameraAvaliable(value : String){

        this.camera_avaliable = value
    }

    fun GetCameraAvaliable():String{

        return this.camera_avaliable
    }

    fun SetMyCamera(my_camera : Boolean){

        this.mycamera = my_camera
    }

    fun GetMyCamera(): Boolean{
        return this.mycamera
    }

    fun SetUpCamera():Boolean{
        return  getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
    }


    fun DeviceCompatibility(){

        if(GetMyCamera() == false){
            val  call = Intent(this,IncompatibleDevice::class.java)
            startActivity(call)
            finish()
        }

    }


    fun StateMachine(mstate:Boolean ): Boolean{

            if(mstate == false){
                return true
            }
            else if(mstate == true){
                return false
            }
            return mstate
    }


    fun PowerOn(){
        val cm = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try
        {

            SetCameraAvaliable(cm.getCameraIdList()[0])
        }
        catch( e:Exception ){
            e.printStackTrace()
        }
        try {
            if (GetState() == true) {
                cm.setTorchMode(GetCameraAvaliable(),true)

               /* cm.turnOnTorchWithStrengthLevel(GetCameraAvaliable(),3)

               Trying to figure this out but program works for now
               I cant change brightness level

                */
                
            } else if (GetState() == false) {

                cm.setTorchMode(GetCameraAvaliable(),false)
            }
        }catch(e:Exception ){
            e.printStackTrace()
        }


    }

    fun Power(view: View) {
        SetState(StateMachine(GetState()))
        PowerOn()

    }

   override fun onResume(){
       super.onResume()
       SetState(true)
       PowerOn()
    }

    override fun onRestart() {
        super.onRestart()
        PowerOn()
    }

}