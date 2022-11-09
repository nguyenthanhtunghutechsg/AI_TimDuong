package BFS;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
 
public class MaTranKhongTrongSo {
 
    public static void main(String args[])
    {
    	// So Luong Dinh
        int soDinh = 6;
        boolean voHuong = true;
 
        // Khoi tao ma tran tat ca deu la 0
        int[][] MaTran = new int[soDinh][soDinh];
        for (int i = 0; i < soDinh; i++) {
        	for (int j = 0; j < soDinh; j++) {
				MaTran[i][j]=0;
			}
        }

        // Gan' canh vao ma tran
        GanCanh(MaTran, 0, 1,true);
        GanCanh(MaTran, 0, 2,true);
        GanCanh(MaTran, 1, 3,true);
        GanCanh(MaTran, 1, 2,true);
        GanCanh(MaTran, 2, 4,true);
        GanCanh(MaTran, 3, 4,true);
        GanCanh(MaTran, 3, 5,true);
        GanCanh(MaTran, 4, 5,true);
        int batDau = 0, ketThuc = 5;
        InRaDuongNganNhatBFS(MaTran, batDau, ketThuc, soDinh);
    }
 
    private static void GanCanh(int MaTran[][], int i, int j,boolean voHuong)
    {
    	if (voHuong) {
    		MaTran[j][i]=1;
		}
		MaTran[i][j]=1;
    }
 
    private static void InRaDuongNganNhatBFS(int MaTran[][],
                             int batDau, int ketThuc, int soDinh)
    {
        int TruyVet[] = new int[soDinh];
        int KhoangCach[] = new int[soDinh];
 
        if (!BFS(MaTran, batDau, ketThuc, soDinh, TruyVet, KhoangCach)) {
            System.out.println("khong co duong di tu "+batDau+" den "+ketThuc);
            return;
        }
 
        LinkedList<Integer> path = new LinkedList<Integer>();
        int XetTruyVet = ketThuc;
        path.add(ketThuc);
        while (TruyVet[XetTruyVet] != -1) {
            path.add(TruyVet[XetTruyVet]);
            XetTruyVet = TruyVet[XetTruyVet];
        }
 
        System.out.println("khoang cach ngan nhat la: " + KhoangCach[ketThuc]);

        System.out.println("Duong di ::");
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
    }

    private static boolean BFS(int MaTran[][], int batDau,
                                  int ketThuc, int soLuong, int TruyVet[], 
                                  int KhoangCach[])
    {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        boolean DaTham[] = new boolean[soLuong];

        for (int i = 0; i < soLuong; i++) {
        	DaTham[i] = false;
        	KhoangCach[i] = Integer.MAX_VALUE;
            TruyVet[i] = -1;
        }
        DaTham[batDau] = true;
        KhoangCach[batDau] = 0;
        queue.add(batDau);
        while (!queue.isEmpty()) {
            int dangXet = queue.remove();
            for (int i = 0; i < soLuong; i++) {
                if (DaTham[i] == false&&MaTran[dangXet][i]==1) {
                	DaTham[i] = true;
                	KhoangCach[i]=KhoangCach[dangXet] + 1;
                	TruyVet[i] = dangXet;
                    queue.add(i);
                   
                }
            }
            if (dangXet == ketThuc)
                return true;
        }
        return false;
    }
}