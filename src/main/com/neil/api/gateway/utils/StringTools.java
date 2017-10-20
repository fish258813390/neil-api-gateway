package neil.api.gateway.utils;

public class StringTools {
    public StringTools() {
    }

    public static Long[] parseLong(String[] args) {
        Long[] array = new Long[args.length];

        for(int i = 0; i < args.length; ++i) {
            array[i] = Long.valueOf(Long.parseLong(args[i]));
        }

        return array;
    }

    public static Double[] parseDouble(String[] args) {
        Double[] array = new Double[args.length];

        for(int i = 0; i < args.length; ++i) {
            array[i] = Double.valueOf(Double.parseDouble(args[i]));
        }

        return array;
    }

    public static Integer[] parseInteger(String[] args) {
        Integer[] array = new Integer[args.length];

        for(int i = 0; i < args.length; ++i) {
            array[i] = Integer.valueOf(Integer.parseInt(args[i]));
        }

        return array;
    }

    public static String arrayToStr(String[] args) {
        StringBuffer sb = new StringBuffer();

        for(int str = 0; str < args.length; ++str) {
            sb.append(args[str]).append(",");
        }

        String var3 = sb.toString();
        return var3.substring(0, var3.length() - 1);
    }

    public static String increaseSpaceToRight(String str, int total) {
        int lenth = str.getBytes().length;
        int number = total - lenth;
        if(number < 0) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer(str);

            for(int i = 0; i < number; ++i) {
                sb.append(" ");
            }

            return sb.toString();
        }
    }
}
