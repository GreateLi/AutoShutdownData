
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.os.IBinder;

import android.telephony.TelephonyManager;


public class BackgroundService extends Service
{
	private MyTimerCheck mycheck;
	private Context mContext;
	public TelephonyManager mtelephonyManager =null;
	@Override
	public void onCreate()
	{
		// TODO Auto-generated method stub
		mContext = getBaseContext();
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		mycheck = new MyTimerCheck()
		{
			@Override
			public void doTimerCheckWork()
			{
				// TODO Auto-generated method stubappsocket
				closeConnectAp();
			}

		};
		mycheck.start(1000);
		//return super.onStartCommand(intent, flags, startId);
		return START_STICKY;
	}

	@Override
	public void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	@Override
	public IBinder onBind(Intent intent)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	 public boolean isWifiConnect() 
	 {
	       ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
	       
	       NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	       return mWifi.isConnected();
	  }
	 
//	public static void closeWifiAp( )
//	{
//		ConnectivityManager ConnectManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//		closeConnectAp(ConnectManager);
//		// ConnectManager.
//	}
	
	private  void closeConnectAp()//mContext
	{
		try
		{

			TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

			if (telephonyManager.DATA_DISCONNECTED == telephonyManager.getDataState())
			{
				return;
			}
			// 开启数据连接
			// Class telephonyManagerClz=TelephonyManager.class;
			// Method
			// mGetITelephony=telephonyManagerClz.getDeclaredMethod("getITelephony");
			// mGetITelephony.setAccessible(true);
			// Object iTelephony = mGetITelephony.invoke(tm);
			Method mEnableDataConn = telephonyManager.getClass().getDeclaredMethod("enableDataConnectivity");
			// 关闭是 disableDataConnectivity
			mEnableDataConn.setAccessible(false);
			mEnableDataConn.invoke(telephonyManager);

			// ConnectManager.setMobileDataEnabled()
			// Method method =
			// telephonyManager.getClass().getMethod("getITelephony");
			// method.setAccessible(true);
			//
			// WifiConfiguration config = (WifiConfiguration)
			// method.invoke(telephonyManager);//TelephonyConfiguration
			// TelephonyConfiguration;
			//
			// Method method2 =
			// ConnectManager.getClass().getMethod("setWifiApEnabled",
			// WifiConfiguration.class, boolean.class);
			// method2.invoke(ConnectManager, config, false);
		} catch (NoSuchMethodException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public boolean GetDataState()
	{
		mtelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

		if (mtelephonyManager.DATA_DISCONNECTED == mtelephonyManager.getDataState())
		{
			return false;
		}
		
		return true;
	}
	public static boolean isMobileConnected(ConnectivityManager ConnectManager)
	{
//	       ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
//	       NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//		if (context != null) {
//		ConnectivityManager mConnectivityManager = (ConnectivityManager) context
//		.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mMobileNetworkInfo = ConnectManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		
		if (mMobileNetworkInfo != null)
		{
			return mMobileNetworkInfo.isAvailable();
		}		
		return false;
	} 
}
