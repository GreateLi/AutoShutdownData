import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class AutoShutdownDataService extends BroadcastReceiver
{
	private Intent serviceIntent;
	@Override
	public void onReceive(Context context, Intent intent)
	{
		// TODO Auto-generated method stub
		if(intent.getAction().equals("android.intent.action.PHONE_STATE"))//ACTION_AIRPLANE_MODE_CHANGED ACTION_BOOT_COMPLETED
		{
			serviceIntent = new Intent(context,BackgroundService.class);
			context.startService(serviceIntent);
		}
		if(intent.getAction().equals(Intent.ACTION_SHUTDOWN))//ACTION_SHUTDOWN
		{
			//Toast.makeText(context, "shutdown", Toast.LENGTH_LONG).show();
			//context.stopService(serviceIntent);
			context.stopService(serviceIntent);	
			//sensorFunction.disableSensor();
		}
		
	}

}
