import java.util.Scanner;

public class NumberBaseball {
    private int s = 0;
    private int b = 0;
    private int o = 0;
    private int round = 0;

    public static void main(String[] args) {
        NumberBaseball game = new NumberBaseball();
        Boolean isStart = game.askToStart();

        if (!isStart) {
            System.out.println("게임이 종료되었습니다.");
            System.exit(0);
        }

        System.out.println("1회가 시작되었습니다");

    }

    private boolean askToStart() {
        Boolean repeatAsking = true;
        Boolean isStart = false;
        while (repeatAsking) {
            System.out.println("새 게임을 시작하시겠습니까? y/n");
            Scanner scan = new Scanner(System.in);

            String input = scan.next().toLowerCase();
            switch (input) {
                case "y" :
                    isStart = true;
                    repeatAsking = false;
                    break;
                case "n" :
                    repeatAsking = false;
                    break;
                default:
                    System.out.println("y, n중에 입력해주세요");
            }
        }
        return isStart;
    }

}
