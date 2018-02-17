
public abstract class MyTimerCheck
{
	private int mSpaceTimeCount = 1;
	private int mSleepTime = 500; // 500ms
	private boolean mExitFlag = false;
	private Thread mThread = null;

	/**
	 * Do not process UI work in this.
	 */
	public abstract void doTimerCheckWork();

	public MyTimerCheck()
	{
		mThread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				// TODO Auto-generated method stub
				while (!mExitFlag)//!mExitFlag
				{
					doTimerCheckWork();
					for(int i = 0;i<mSpaceTimeCount;i++)
					{
						if(mExitFlag)
						{
							break;
						}
						try
						{
							mThread.sleep(mSleepTime);
						} catch (InterruptedException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
							//exit();
						}						
					}
				}
			}
		});
	}

	/**
	 * start
	 * 
	 * @param sleepTime
	 *            ms, Every  sleep time.
	 */
	public void start(int sleepTime)
	{
		mSpaceTimeCount = sleepTime/500;
		mThread.start();
	}

	public void SetSpace(int sleepTime)
	{
		mSpaceTimeCount = sleepTime/500;
	}
	
	public void exit()
	{
		mExitFlag = true;
	}
}
