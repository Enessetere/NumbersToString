import java.util.Scanner;

public class StartUp {
    private static String zero = "zero";
    private static String[] jednosci = {"", " jeden", " dwa", " trzy",
            " cztery", " pięć", " sześć", " siedem", " osiem", " dziewięć"};
    private static String[] dziesiatki = {"", " dziesięć", " dwadzieścia",
            " trzydzieści", " czterdzieści", " pięćdziesiąt",
            " sześćdziesiąt", " siedemdziesiąt", " osiemdziesiąt",
            " dziewięćdziesiąt"};
    private static String[] nascie = {"dziesięć", " jedenaście", " dwanaście",
            " trzynaście", " czternaście", " piętnaście", " szesnaście",
            " siedemnaście", " osiemnaście", " dziewiętnaście"};
    private static String[] setki = {"", " sto", " dwieście", " trzysta",
            " czterysta", " pięćset", " sześćset",
            " siedemset", " osiemset", " dziewięćset"};
    private static String[][] rzedy = {{" tysiąc ", " tysiące ", " tysięcy "},
            {" milion ", " miliony ", " milionów "},
            {" miliard ", " miliardy ", " miliardów "},
            {" bilion ", " biliony ", " bilionów "},
            {" biliard ", " biliardy ", " biliardów "},
            {" trylion ", " tryliony ", " trylionów "}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line;
        do {
            System.out.println("Enter a valid long number to change:");
            line = scanner.nextLine();
            try {
                Long.parseLong(line);
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Enter a valid number up to " + Long.MAX_VALUE + "!");
            }
        } while (true);
        StringBuilder stringBuilder = new StringBuilder(line);
        if(stringBuilder.length() == 1 && stringBuilder.charAt(0) == '0') {
            stringBuilder.delete(0, 1);
            stringBuilder.insert(0, zero);
            System.out.println(stringBuilder);
            return;
        }
        while (stringBuilder.length() % 3 != 0) {
            stringBuilder.insert(0, " ");
        }
        int stringLength = stringBuilder.length();
        for (int i = stringLength - 1; stringBuilder.charAt(i) != ' '; i--) {
            int r = (stringLength - i) / 3;
            int p = i % 3;
            int index = stringBuilder.charAt(i) - 48;
            if(index < 0) {
                break;
            }
            if(r > 0 && p == 2) {
                if(index == 1 && (stringBuilder.charAt(i - 1) == '0' && stringBuilder.charAt(i - 2) == '0' || stringBuilder.charAt(i - 1) == ' ')) {
                    stringBuilder.insert(i + 1, rzedy[r - 1][0]);
                } else if (index > 4) {
                    stringBuilder.insert(i + 1, rzedy[r - 1][2]);
                } else {
                    if(stringBuilder.charAt(i - 1) == '1' || stringBuilder.charAt(i) == '1') {
                        stringBuilder.insert(i + 1, rzedy[r - 1][2]);
                    } else {
                        stringBuilder.insert(i + 1, rzedy[r - 1][1]);
                    }
                }
            }
            if (p == 2 && stringBuilder.charAt(i - 1) != '1') {
                stringBuilder.delete(i, i + 1);
                stringBuilder.insert(i, jednosci[index]);
            } else if (p == 1) {
                if(index == 1) {
                    index = stringBuilder.charAt(i + 1) - 48;
                    stringBuilder.delete(i, i + 2);
                    stringBuilder.insert(i, nascie[index]);
                } else {
                    stringBuilder.delete(i, i + 1);
                    stringBuilder.insert(i, dziesiatki[index]);
                }
            } else if (p == 0) {
                stringBuilder.delete(i, i + 1);
                stringBuilder.insert(i, setki[index]);
            }
        }
        System.out.println(stringBuilder.toString().trim());
    }
}
