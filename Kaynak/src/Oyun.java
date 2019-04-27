
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

    //Oluşturduğumuz listelerde veri tutmak için kullanıyoruz.
    class Node
    {
	//x ve y koordinatları temsil eder
	//dist mümkün olan en kısa yolu elinde tutar.
	int x, y,x2,y2,dist;

	Node(int x, int y, int dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
        //Burada x ve y değişkenlerini engel ile karşılaşmadan önceki konumumuzu
        //tutuyoruz x2 ve y2 değişkenlerinide çarptığımız engelin konumu tutmak için
        //kullanıyoruz x ve y biz daha sonra path2 içindeki veriler ile path3 
        //karşılaştırırken lazım olucak bu sayede en kısa yolda ilerken çarptığımız
        //engelleri bulmuş olucağız.
        Node(int x, int y, int x2,int y2) {
		this.x = x;
		this.y = y;
		this.x2=x2;
                this.y2=y2;
	}
    };

public class Oyun {
        //Linkteki kod parçacığı üzerinde eklemeler yapılarak sonuca ulaşılmıştır.
        //https://www.techiedelight.com/lee-algorithm-shortest-path-in-a-maze/
    
        //Başlangıç noktasından hedefe ulaşana kadar olan adım sayısını tutmak için kullanıyoruz.
        int dist,min_dist2;
        static int yaz=0;
        //En az bir engele uğramak için kullanıyoruz.
        //Uzaklığın dokuz olmasının sebebi en uzak noktaya olan uzaklığı yaklaşık olarak dokuz olmasıdır.
        static int e_x=6,e_y=7,uzaklik=14;
        
        //İlk path değişkenimiz tüm adımlarımızı depolar.
         ArrayList<Node>path= new ArrayList<Node>();
        //İkinci path değişkenimiz en kısa yolu depolar.
        static List<Integer>path2= new ArrayList<Integer>();
         //Üçüncü path değişkenimiz tüm karşılaştığımız engelleri depolar.
        static ArrayList<Node>path3= new ArrayList<Node>();
         //sayac değişkeni bize attığımız adım tüm adımların sayısını verir.
         //Daha sonra tüm adımların içinden kısa yolu çekerken kullanırız.
        static int sayac=0;
	// M x N matrix
	private static final int M = 8;
	private static final int N = 8;

	//Olabilecek dört haraket bu arraylerin içindeki değerlerle sağlanır.
        //Bu arrayler sayesinde çapraz ilerleme engellenmiş olur
	private  final int row[] = { -1, 0, 0, 1 };
	private  final int col[] = { 0, -1, 1, 0 };

        //Gidilmek istenen konumun geçerliliğini kontrol ediyor.
        //Aynı zamanda daha önce ziyaret edilip edilmediğine bakar
        //Bu sayede aynı yerde dönüp durmaz
	private  boolean isValid(int mat[][], boolean visited[][],int row, int col)
	{
		return (row >= 0) && (row < M) && (col >= 0) && (col < N)&& mat[row][col] == 1 && !visited[row][col];
	}

	// Hedef hücreye en kısa yolu bulur
	// Başlangıç (i, j) hedef (x, y)
        public int BFS(int mat[][], int i, int j, int x, int y)
	{        
		//Ziyaret edilen hücreleri işaretlemek için bir matris oluşturuyoruz.
		boolean[][] visited = new boolean[M][N];
                int min_dist = Integer.MAX_VALUE;
	
		Queue<Node> q = new ArrayDeque<>();
                Node node;
		//Başlangıç noktasını işaretliyoruz.Bu sayede tekrar buraya dönmemiz engelleniyor.
		visited[i][j] = true;
		q.add(new Node(i, j, 0));
                path.add(new Node(i, j,0));
		
		

		//Queue boş olana kadar dönememizi sağlar.
		while (!q.isEmpty())
		{   
                    /*if(path2.size()!=0){
                     path2.remove(path2.size());
                    path2.r*emove(path2.size()-1);
                    }*/
			//Veri çıkartılır
			node = q.poll();

			//i ve j değikenlerinde bulunduğumuz konu tutuyoruz.
                        //bu değişkenleri hedefe ulaşıp ulaşmadığımız kontrol etmek için kullanıyoruz.
			i = node.x;
			j = node.y;
                        //Hedefe ulaşana kadar dist değerni güncel tutuyoruz.
			dist = node.dist;

			//Sonuca ulaşıldıysa while döngüsü kırılır.
			if (i == x && j == y)
			{
                            path.add(new Node(i,j, dist+1));
				min_dist = dist;
				break;
			}

			//Olası haraket kontrol edilir.
			//Kontrol edilen haraketlerden geçerli olanlar eklenir
			for (int k = 0; k < 4; k++)
			{
				//Atıcağımız adımın uygunluğunu kontrol ediyoruz
				if (isValid(mat, visited, i + row[k], j + col[k]))
				{
					//Ziyaret edilen hücre olarak işaretlenir
					visited[i + row[k]][j + col[k]] = true;
                                        path.add(new Node(i + row[k], j + col[k], dist + 1));
                                        sayac++;
                                       //System.out.println("x:"+(i + row[k])+" y:"+(j + col[k]));
					q.add(new Node(i + row[k], j + col[k], dist + 1));
				}
                                else if((i + row[k])>=0&&(j + col[k])>=0&&(j + col[k])<8&&(i + row[k])<8&&!visited[i + row[k]][j + col[k]]){
                                    path3.add(new Node(i ,j ,i + row[k], j + col[k]));
                                   
                                }
			}
                        
		}
                min_dist2=min_dist;
		if (min_dist != Integer.MAX_VALUE) { 
                    if(yaz==0){
                        System.out.println("x:"+x+" y:"+y+"");
                        yaz++;
                    }
                   //Bu tüm adımların içinden en kısa yolu çekiyoruz.Bu kısımda
                   //kullandığım mantık tüm adımları tersten incelemek oldu.
                   //Bunun sebebi kulladığım kod parçacığı hedefe gidene kadar
                   //tüm mümkün yolları denemesi ile hedefe ulaşmasıdır
                   //Üst kısımda bulunan kod parçacıkları hedef konuma vardığı 
                   //noktadan itibaren tüm verileri tersten inceleyerek bir önceki
                   //adımı buluyoruz.
                   for(int k=sayac;k>=0;k--){
                       
                    if((min_dist-path.get(k).dist)==1){
                       
                        int sonuc = (Math.abs(x-(path.get(k).x)))+Math.abs((y-(path.get(k).y)));
                        if(sonuc==1){
                         //x ve y değişkenleri geri giderken güncelliyoruz.
                         x=path.get(k).x;
                         y=path.get(k).y;
                         //Uygun değerkeri path ikiye ekliyoruz.
                         path2.add(path.get(k).x);
                         path2.add(path.get(k).y);
                         
                         System.out.println("x:"+x+" y:"+y);
                         //min_dist değişkeni azaltılarak uygun değerlerle karışaştırlmasınını sağlıyoruz.
                         min_dist--;
                         
                        }
                    }
                   }    
                   return min_dist2;
                }
		else {
                    System.out.print("Sonuca ulaşılamadı");
                    path2.clear();
                    path3.clear();
                    sayac=0;
                    yaz=0;
                    return 99;
		}
                 
                
    
    
        }
       public void engelleri_bas(){
           System.out.println("");
             //Yolda karşılaştığımız engelleri depolarken hangi konumdayken karşılatığımızın
                   //bilgisini elimizde tutuğumuz için bunu en kısa yoldaki koordinatları karşılaştırarak
                   //çarptığımız siyah pulları ekrana basabiliriz.
                   if(path3.size()!=0){
                    System.out.println("Aynı koordinat tekrar ediyorsa tekrar oraya gidilmeye çalışılmıştır");
                    System.out.println("Karşılaşılan Engeller:");
                    for(int t=0;t<path2.size();t+=2){
                        
                        for(int c=0;c<(path3.size());c++){
                         if(path3.get(c).x==path2.get(t)&&path3.get(c).y==path2.get(t+1)){
                            System.out.println("x:"+path3.get(c).x2+" y:"+path3.get(c).y2);
                         }
                        }
                     }
                   }
                   
       }
    //Tahtayı ekrana çiziyoruz.
    public void Tahta_ciz(int[][] mat){
        
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                System.out.print(mat[i][j]+"  ");
            }
            System.out.println("");
        }
    }
    //Engelleri atıyoruz.
    public int[][] Engel_ekle(int[][] mat){
        
        Random rdm = new Random();
        int engel_sayisi,x,y;
        
        engel_sayisi=(rdm.nextInt(8)+1);
       // System.out.println("Engel_sayisi:"+engel_sayisi);
        for(int i=0;i<engel_sayisi;i++){
            x=rdm.nextInt(8);
            y=rdm.nextInt(8);
            //Başlangıç noktasının ve bitiş noktasının erişilmez olmasını
            //engellemek için kullanıyoruz.
            if(!(x==0&&y==0)&&!(x==7&&y==7)){
                //Sıfır atanan nokta haraket edilemicek olan hücredir.
                mat[x][y]=0;
            }
            
        }
        mat=yol_ac(mat);
        mesafe_bul(mat);
        mat=engel_kaldı_mi(mat);
            
        return mat;
    }
    public int[][] yenile(int[][] mat){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                mat[i][j]=1;
            }
        }
        return mat;
    }
    public int[][] yol_ac(int[][] mat){
        if(mat[0][1]==0&&mat[0][1]==0)
            mat[0][1]=1;
        if(mat[6][7]==0&&mat[7][6]==0)
            mat[7][6]=1;
        
        return mat;
    }
    public void mesafe_bul(int[][] mat){
        for(int x=0;x<8;x++){
            for(int y=0;y<8;y++){
            if(mat[x][y]==0){
                int mesafe=x+y;
                if(mesafe < uzaklik){
                    uzaklik=mesafe;
                    e_x=x;
                    e_y=y;
                }
            }
            }
        }
    }
    public int[][] engel_kaldı_mi(int[][] mat){
        int k=0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(mat[i][j]==0){
                    k=1;
                }
            }
        }
        if(k==0){
           uzaklik=14;
           mat=yenile(mat);
           mat= Engel_ekle(mat);
           mat=engel_kaldı_mi(mat);
        }
        
        return mat;
    }
}
