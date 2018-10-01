import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.locks.Lock;

public class BatchBackupTask extends DistributedScheduleRunnable {
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
	protected void execute() {
		logger.info("Starting a caculate task at {}!", new Date());
		batchCalculator.calculate();
		logger.info("End a caculate task at {}!", new Date());
    }
    
    protected void backup(){
        // khong thay a =
    }

}