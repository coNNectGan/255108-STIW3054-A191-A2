

import java.io.IOException;
import java.util.ArrayList;

public class main {

    private static int followerNum = 0, reader = 0;

    public static void main(String[] args) throws IOException {

        System.out.println("\n*****************************************************************************************************************************\n");
        System.out.println("                                                 STIW3054-A191-A2"                                                           );
        System.out.println("\n*****************************************************************************************************************************\n");

        System.out.println("\nGetting Zhamri's GitHub followers...");
        String[] userData = zhamriFollowerGetter.getInfo("https://api.github.com/users/zhamri");

        System.out.println("\nCalculating Zhamri's GitHub followers...");
        ArrayList<String> zhamriFollower = zhamriFollowerGetter.followersInfo(userData[5]);
        followerNum = zhamriFollower.size();
        System.out.println("\nThe number of Zhamri's GitHub followers = " +  followerNum );

        System.out.println("\n*****************************************************************************************************************************\n");
        excelCreater.createExcel();


        System.out.println("Collecting the userInfo. . .\n");
        System.out.println("\nPlease wait. . . . . .\n");


        int count = 1;
        for (String user : zhamriFollower) {

            int finalCount = count;
            Thread thread = new Thread(() -> {

                String[] userInfo = zhamriFollowerGetter.getInfo(user);

                String[] Info = {String.valueOf(finalCount), userInfo[0], userInfo[1], userInfo[2], userInfo[3], userInfo[4]};

                synchronized (main.class) {
                    excelData.addData(Info);

                    reader++;

                }
            });
            thread.start();
            count++;
        }

        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(reader==followerNum){
                excelCreater.readData();
              excelCreater.excelfile();
                break;
            }
        }
        System.out.println("Excel file is created.");


    }

}