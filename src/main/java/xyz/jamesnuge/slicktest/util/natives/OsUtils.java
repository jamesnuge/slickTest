package xyz.jamesnuge.slicktest.util.natives;

/**
 * Created by James Nugent on 20/12/2015.
 */
public class OsUtils {

    private OsUtils(){}

    private static String OS_NAME;
    private static String WINDOWS_OS_NAME = "Windows";
    private static String LINUX_OS_NAME = "Linux";

    public static String getOsName() {
        if(OS_NAME == null) {
            OS_NAME = System.getProperty("os.name");
        }
        return OS_NAME;
    }

    public static boolean IS_WINDOWS() {
        return getOsName().contains(WINDOWS_OS_NAME);
    }

    public static boolean IS_LINUX() {
        return getOsName().contains(LINUX_OS_NAME);
    }


}
