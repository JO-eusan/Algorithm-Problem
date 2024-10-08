import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* DP table 만들기 */
        int[][][] w = new int[21][21][21];

        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                for (int k = 0; k < 21; k++) {
                    if (i == 0 || j == 0 || k == 0) {
                        w[i][j][k] = 1;
                    } else if (i < j && j < k) {
                        w[i][j][k] = w[i][j][k - 1] + w[i][j - 1][k - 1] - w[i][j - 1][k];
                    } else {
                        w[i][j][k] = w[i - 1][j][k] + w[i - 1][j - 1][k] + w[i - 1][j][k - 1] - w[i - 1][j - 1][k - 1];
                    }
                }
            }
        }

        /* 호출 시작 */
        int a, b, c;
        while (true) {
            String[] line = br.readLine().split(" ");
            a = Integer.parseInt(line[0]);
            b = Integer.parseInt(line[1]);
            c = Integer.parseInt(line[2]);

            if (a == -1 && b == -1 && c == -1) break;

            // 음수일 경우 w(0, 0, 0) 반환
            if (a <= 0 || b <= 0 || c <= 0) {
                bw.write("w(" + a + ", " + b + ", " + c + ") = 1\n");
            } 
            // a, b, c가 20을 초과할 경우 w(20, 20, 20) 사용
            else if (a > 20 || b > 20 || c > 20) {
                bw.write("w(" + a + ", " + b + ", " + c + ") = " + w[20][20][20] + "\n");
            } 
            // 0 ~ 20 범위에서 DP 테이블 값 반환
            else {
                bw.write("w(" + a + ", " + b + ", " + c + ") = " + w[a][b][c] + "\n");
            }
        }

        bw.flush();
        bw.close();
    }
}