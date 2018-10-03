
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class PatternUtils.
 */
public class PatternUtils {

    private final static Logger logger = LoggerFactory.getLogger(PatternUtils.class);

    private static final long MEGABYTE = 1024L * 1024L;
    
        private static final long GIGABYTE = 1024L;
    
        public static final String svcTagPattern = "^[A-Za-z0-9&&[^AEIOUaeiou]]{7}$";
    
        public static final Pattern NFS_PATTERN = Pattern.compile(".*[:]{1}[/]{1}[^/].*$");
    
        public static final Pattern CIFS_PATTERN = Pattern.compile("[\\\\][\\\\][^\\\\].{0,255}[\\\\]{1}[^\\\\]*.*");
    
        public static final Pattern NFS_FILE_PATTERN = Pattern.compile(".*[:]{1}[/]{1}[^/].*[.]{1}.*$");
    
        public static final Pattern CIFS_FILE_PATTERN = Pattern.compile("[\\\\][\\\\][^\\\\].{0,255}[\\\\]{1}[^\\\\]*.*[.]{1}.*");
    
        public static final Pattern STRING_PATTERN = Pattern.compile("[!@#$%*()_0-9A-Za-z]*");
    
        public static final Pattern IPADDRESS_PATTERN = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

        

        


}
