package xyz.jamesnuge.slicktest.util.natives;

/**
 * Created by James Nugent on 20/12/2015.
 */
public class NativeLibraryLoader {

    private NativeLibraryLoader(){}

    private static String NATIVES_PATH;

    public static void LOAD_NATIVES() {
        if (NATIVES_PATH == null) {
            throw new IllegalStateException("Cannot load natives without a path specified. Please specify the path of the natives you wish to load");
        } else {

        }
    }
}
