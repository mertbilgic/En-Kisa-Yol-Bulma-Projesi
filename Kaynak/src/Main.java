
import java.util.Arrays;

public class Main {
    
    //Eğer ulaşılmazsa adımlardan return 99  döndürmesi sebebiyle 99 eşit olucak
    //Bu da hedefe varamadığımız anlamına gelicek.
    public static final int HEDEFE_ULASILAMADI_KODU = 99;
    
      public static void main(String[] args) {
        //Bir olan hücrelere haraket edilebileceğini gösteriyor
        //Daha sonra engel olan kısımlar sıfır yapılır.
        int[][] oyun_tahtasi =
		{
			{ 1, 1, 1, 1, 1, 1, 1, 1},
			{ 1, 1, 1, 1, 1, 1, 1, 1},
			{ 1, 1, 1, 1, 1, 1, 1, 1},
			{ 1, 1, 1, 1, 1, 1, 1, 1},
			{ 1, 1, 1, 1, 1, 1, 1, 1},
			{ 1, 1, 1, 1, 1, 1, 1, 1},
			{ 1, 1, 1, 1, 1, 1, 1, 1},
			{ 1, 1, 1, 1, 1, 1, 1, 1},

		};
        
        int min_adım=0,k=0;
        int min_adım2=0;
        Oyun oyun= new Oyun();
        
        //Projenin isterlerinden biri olan engelle karılaşmanın gerçekleşmesi içim
        //Beyaz bul öncelikle en yakın engelin yanına uğrar.İkinci adımda hedef
        //konuma doğru ilerlemeye başlar.
        
        while(k==0){
            
       

            oyun_tahtasi=oyun.Engel_ekle(oyun_tahtasi);
        
            //Bu kısmı kullanmamızın sebebi eğer engelin üstünü hedeflersek kulladığım
            //algoritma buraya ulaşamiyacak.Bu yüzden bir önceki adıma gelirsem
            //o kısımda 4 hareketide denerken engele çarpıcağım için mutlaka bir
            //engelle ile karşılaşmış olacağım.
            if(oyun.e_x!=0&&oyun.uzaklik>0) oyun.e_x-=1;
            else if(oyun.e_y!=0){oyun.e_y-=1;}
        
            //Projenin isterlerinden biri olan engelle karılaşmanın gerçekleşmesi içim
            //Beyaz bul öncelikle en yakın engelin yanına uğrar.İkinci adımda hedef
            //konuma doğru ilerlemeye başlar.
            System.out.println("En kısa yolun koordinatları:");
            min_adım=oyun.BFS(oyun_tahtasi, oyun.e_x, oyun.e_y, 7, 7);
            min_adım2=oyun.BFS(oyun_tahtasi, 0, 0, oyun.e_x, oyun.e_y);
        
        
            //Eğer ikiside 99 eşit değilse hedefe ulaşılmıştır.
            if(min_adım!=HEDEFE_ULASILAMADI_KODU&&min_adım2!=HEDEFE_ULASILAMADI_KODU){
                oyun.engelleri_bas();
                System.out.println("\nMümkün olan en kısa yol "+(min_adım+min_adım2)+"adım\n");        
                k=1;
            }
            
            System.out.println("Engel eklenmiş oyun tahtası:");
            oyun.Tahta_ciz(oyun_tahtasi);
            //Eğer ulaşmadıysak tahtadaki sıfırların temizlenmesi gerekiyor mu fonksiyonda
            //tahtayı tekrar başlangıçtaki haline getirmemizi sağlıyor.
            oyun_tahtasi=oyun.yenile(oyun_tahtasi);
        }

        
        
        
        
    
        
    }
    
    
}
