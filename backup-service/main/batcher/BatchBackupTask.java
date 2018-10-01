import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.locks.Lock;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;



@Component
public class BatchBackupTask extends DistributedScheduleRunnable {

    public static Random random = new Random();

	private static final Logger logger = LoggerFactory.getLogger(BatchBackupTask.class);

	public static final BatchBackupTask wrap(Lock distributedLock, BatchCalculator batchCalculator) {
		return new BatchCalculatorTask(distributedLock, batchCalculator);
	}

	private final BatchBackup batchBackup;

	private BatchCalculatorTask(Lock distributedLock, BatchCalculator batchCalculator) {
		super(distributedLock);
		this.batchCalculator = batchCalculator;
	}


    
    @Override
    @Async // execute task with async 
    protected void backup() throws Exception{
        logger.info("Starting a caculate task at {}!", new Date());
        batchBackup.calculate();
        Thread.sleep(random.nextInt(10000));
		logger.info("End a caculate task at {}!", new Date());
        
    }

}