import com.ObfuscatorProcessor;

public class Launcher {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.err.println("Please type file names to process and to output");
            return;
        }

        ObfuscatorProcessor processor = new ObfuscatorProcessor(args[1]);
        if (args.length == 3) {
            if ("-d".equals(args[2])) {
                processor.setDeobfuscation(true);
            }
        }
        processor.process(args[0]);
    }
}
