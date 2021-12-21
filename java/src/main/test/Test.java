import javax.annotation.Nullable;

/**
 * @author zs
 *@date 2021/9/7.

 */
public class Test {
    public static void main(String[] args) {
        final String OS_NAME = System.getProperty("os.name");
        /** The processor bit. */
        final String PROCESSOR_BIT = System.getProperty("os.arch");
        /** The java vendor name used in this platform. */
        final String JAVA_VENDOR_NAME = System.getProperty("java.vendor");
        /** Indicates the current java vendor is IBM java or not. */
        final boolean IBM_JAVA = JAVA_VENDOR_NAME.contains("IBM");
        System.out.println(OS_NAME);
        System.out.println(PROCESSOR_BIT);
        System.out.println(JAVA_VENDOR_NAME);
        System.out.println(IBM_JAVA);
    }
}
