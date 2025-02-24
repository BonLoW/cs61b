package bomb;

import common.IntList;

public class BombMain {
    public static void main(String[] args) {
        int phase = 2;
        if (args.length > 0) {
            phase = Integer.parseInt(args[0]);
        }
        // TODO: Find the correct passwords to each phase using debugging techniques
        Bomb b = new Bomb();
        if (phase >= 0) {
            b.phase0("39291226");
        }
        if (phase >= 1) {
            b.phase1(IntList.of(0,9,3,0,8)); // Figure this out too
        }
        if (phase >= 2) {
            String password = "d" + "d";
            for (int i = 1; i < 1338; i++) {
                if (i == 1337) {
                    password = password + " -81201430";
                    continue;
                }
                password = password + " d";
            }
            b.phase2(password);
        }
    }
}
