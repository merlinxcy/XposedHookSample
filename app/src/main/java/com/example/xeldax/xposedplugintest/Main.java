package com.example.xeldax.xposedplugintest;

import android.widget.TextView;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

/**
 * Created by xeldax on 2018/7/7.
 */

public class Main implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable{
        XposedBridge.log("handle execute success");
        if(loadPackageParam.packageName.equals("com.example.xeldax.xposed_target")){
            XposedBridge.log("hook start");
            findAndHookMethod(TextView.class, "setText", CharSequence.class,
                    new XC_MethodHook(){
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable{
                            XposedBridge.log("start hook settext before");
                            param.args[0] = "l am xposed. You are hooked";
                        }
                        @Override
                        protected  void afterHookedMethod(MethodHookParam param) throws Throwable{
                            XposedBridge.log("start text");
                        }
                    });
        }
    }
}
