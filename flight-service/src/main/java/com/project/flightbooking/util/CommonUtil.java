
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 
 * Common Util - used for other classese
 */
public class CommonUtil {

    // Check if flight no is digit
	public static final boolean isDigit(String str) {
        boolean tempReturn = false
		if (isEmpty(str)) {
			return false;
        }
                
		try {
			Double.valueOf(str);
			return true;
		} catch (Exception e) {
			return false;
		}
    }
    
    
	public static final boolean isLong(String str) {
		if (isEmpty(str)) {
			return false;
		}
		try {
			Long.valueOf(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static final boolean isInteger(String str) {
		if (isEmpty(str)) {
			return false;
		}
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static final boolean isHexLong(String str) {
		if (isEmpty(str)) {
			return false;
		}
		try {
			Long.parseLong(str, 16);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static final boolean isEmpty(Collection<? extends Object> value) {
		if (value == null || value.isEmpty()) {
			return true;
		}
		return false;
	}

	public static final boolean isAnyEmpty(Collection<? extends Object> value) {
		if (value == null || value.isEmpty()) {
			return true;
		}
		for (Object item : value) {
			if (isEmpty(item)) {
				return true;
			}
		}
		return false;
	}

	public static final boolean isEmpty(Map<? extends Object, ? extends Object> value) {
		if (value == null || value.isEmpty()) {
			return true;
		}
		return false;
	}

	public static final boolean isEmpty(Object value) {
		if (value != null && value instanceof String) {
			return isEmpty((String) value);
		}
		return value == null;
	}

	public static final boolean isEmpty(Object[] value) {
		if (value == null || value.length < 1) {
			return true;
		}
		return false;
	}

	public static final boolean isNotEmpty(Object value) {
		return !isEmpty(value);
	}

	public static final boolean isAllEmpty(Object... value) {
		if (isEmpty(value)) {
			return true;
		}
		for (Object item : value) {
			if (!isEmpty(item)) {
				return false;
			}
		}
		return true;
	}

	public static final boolean isAnyEmpty(Object... value) {
		if (isEmpty(value)) {
			return true;
		}
		for (Object item : value) {
			if (isEmpty(item)) {
				return true;
			}
		}
		return false;
	}

	public static final boolean isEmpty(String value) {
		if (value == null || value.length() == 0) {
			return true;
		}
		return false;
	}

	public static final String toHexString(byte[] data) {
		StringBuilder hexString = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			String hex = Integer.toHexString(data[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			hexString.append(hex.toUpperCase());
		}
		return hexString.toString();
	}

	public static final String toMD5String(byte[] data, int offset, int maxCaculateLength) {
		MessageDigest mdInst = null;
		try {
			mdInst = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Current JVM Not support MD5 Message Digest");
		}
		if (data.length - offset < maxCaculateLength) {
			maxCaculateLength = data.length - offset;
		}
		mdInst.update(data, offset, maxCaculateLength);
		return toHexString(mdInst.digest());
	}

	public static final String toMD5String(String content, Charset charset) {
		byte[] contentBytes = content.getBytes(charset);
		return toMD5String(contentBytes, 0, contentBytes.length);
	}

	public static final String toSha1String(byte[] data, int offset, int maxCaculateLength) {
		MessageDigest mdInst = null;
		try {
			mdInst = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Current JVM Not support SHA1 Message Digest");
		}
		if (data.length - offset < maxCaculateLength) {
			maxCaculateLength = data.length - offset;
		}
		mdInst.update(data, offset, maxCaculateLength);
		return toHexString(mdInst.digest());
	}

	public static final String toSha1String(String content, Charset charset) {
		byte[] contentBytes = content.getBytes(charset);
		return toSha1String(contentBytes, 0, contentBytes.length);
	}

	public static final String toHexString(byte data) {
		String hex = Integer.toHexString(data & 0xFF);
		if (hex.length() == 1) {
			hex = '0' + hex;
		}
		return hex;
	}

	public static final String toHexString(long value) {
		String hex = Long.toHexString(value);
		if (hex.length() % 2 == 1) {
			hex = '0' + hex;
		}
		return hex;
	}

	public static final byte[] hexStr2Bytes(String hexString) {
		if (hexString == null || hexString.length() == 0) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	public static final long hexStr2Long(String hexString) {
		return Long.parseLong(hexString, 16);
	}

	public static final byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	@SafeVarargs
	public static final <T> Set<T> toSet(T... paramm) {
		HashSet<T> set = new HashSet<T>(paramm.length);
		for (T parameter : paramm) {
			set.add(parameter);
		}
		return set;
	}

	public static final <T> List<T> distinct(List<T> list, Comparator<T> comparator) {
		if (isEmpty(list)) {
			return null;
		}
		List<T> targetList = new ArrayList<>();
		list.stream().filter(item -> item != null).forEach(item -> {
			Optional<T> tryFind = targetList.parallelStream().filter(targetItem -> {
				return comparator.compare(item, targetItem) == 0;
			}).findAny();
			if (!tryFind.isPresent()) {
				targetList.add(item);
			}
		});
		return targetList;
	}

	public static final Calendar getNow() {
		return new GregorianCalendar();
	}

}