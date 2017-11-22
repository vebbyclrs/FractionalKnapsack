/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TubesDAA;

/**
 *
 * @author VebbyClrs
 */
public class fungsi {
    public double[] getOptimalValue(int capacity, int[] values, int[] weights) {
        double totalValue = 0; //menginisialisasi total value/profit sementara dengan 0
        double totalWeight = 0; //menginisialiasi total berat sementara dengan 0
        //int i = 0;
        double[] Hasil = new double[values.length]; //membuat aray baru untuk menampung hasil
        for (int i = 0; i < Hasil.length; i++) { //baris 17-18 mengisi array hasil dengan 0
            Hasil[i]=0;
        }
        while (totalWeight < capacity) { //looping selama totalWeight lebih kecil dari kapasitas knapsack
            int maxDex = bestValueByWeight(values, weights); /*memanggil fungsi untuk mengisi variable maxDex 
                                                             dengan item yang memiliki density tertinggi*/
            
            if (totalWeight + weights[maxDex] <= capacity) { /*Jika totalweight + weight dari item yang baru yang ingin ditambahkan
                                                                lebih kecil dari kapasitas maka kita ambil seluruh item*/
                totalWeight += weights[maxDex]; //menambahkan total weight dengan weight dari item yang baru ditambahkan
                //totalValue += values[maxDex];
                Hasil[maxDex]=1; //mengisi array hasil menandakan kita mengambil seluruh bagian dari item tersebut
            }
            else { //jika kita hanya dapat mengambil sebagian dari item yang ingin ditambahkan
                double difference = capacity - totalWeight; /*membuat variable baru berisi selisih 
                                                            antara kapasitas dan total berat sementara*/
                //totalValue += difference * (values[maxDex]/(double)weights[maxDex]);
                Hasil[maxDex]=(difference/weights[maxDex]); //hasil diisi dengan seberapa banyak bagian dari item baru yang bisa dimasukan
                break;//memberhentikan looping karena kapasitas pasti sudah penuh
            }
            values[maxDex] = 0;//mengisi profit yang baru ditambahkan dengan 0 agar ketika mencari density  item tersebut tidak muncul kembali
        }
        return Hasil;//mereturn array hasil
    }
    
    private int bestValueByWeight(int[] values, int[] weights) {
        double currVal = 0; //Inisiasi current profit di awal dengan 0
        double maxVal = 0; //Inisiasi profit terbaik sementara dengan 0
        int maxDex = 0; //Inisiasi index sementara dengan 0
        
        for (int i=0; i < values.length; ++i) {//looping sebanyak jumlah array untuk mencari density terbesar
            currVal = values[i]/(double)weights[i];//mengisi variable currVal dengan density dari item yang sedang di cek
            if (currVal > maxVal) { //mengecek jika density lebih besar dari density sebelum nya maka variable currvall di isi dengan density yang baru
                maxVal = currVal;
                maxDex = i;
            }
        }
        return maxDex; //mengembalikan index dengan density terbesar
    }
    
    public double[] getBestByWeight(int capacity,int[] values, int[] weights){
            int maxWeight=999;//menginisiai max weight sementara dengan 999
            int maxDex=0; //menginisiasi index sementara dengan 0
            int totalWeight=0; //mengeinisiasi totalweight dengan 0
            double[] Hasil=new double[values.length]; //membuat array hasil dengan panjang sebanyak array values
            
            while(totalWeight<capacity){ //looping selama total weight lebih kecil dari kapasitas knapsack
                for (int i = 0; i < values.length; i++) { //looping sebanyak jumlah dari item
                    if (maxWeight>weights[i]){ //mengecek jika ada weight item yang lebih kecil dari maxWight
                        maxWeight=weights[i]; //maxWight diisi dengan weight dari item yang memiliki weight lebih kecil
                        maxDex=i; //maxDex di isi oleh idex dari item tersebut
                    }
                }
                if((totalWeight+maxWeight)<capacity){ //mengecek jika sisa dari kapasitas mencukupi untuk mengambil seluruh item
                    totalWeight+=maxWeight; //menambahkan total weight dengan berat item yang ditambahkan
                    weights[maxDex]=999; //mengisi weight dari item yang telah ditambakan agar tidak terpilih ketika pengecekan selanjutnya
                    Hasil[maxDex]=1; //mngisi array hasil index yang sama dengan item dengan 1 untuk menandakan bahwa item tersebut diambil seluruhnya
                    maxWeight=999;//mengisi maxWight dengan 999 untuk mencari weight terbaik di iterasi selanjutnya
                }
                else{ //jika sisa dari kapasitas tidak mencukupi untuk mengambil seluruh item
                    Hasil[maxDex]=((double)capacity-(double)totalWeight)/(double)weights[maxDex]; /*mengisi array hasil dengan berapa banyak 
                                                                                                    bagian yang bisa di ambil dari item tersebut*/
                    break;//memberhentikan looping karena wight barang sudah seimbang dengan kapasitas
                }
            }
            
        return Hasil;//mereturn array hasil
    }
    
    public double[] getBestByValues(int capacity,int[] values, int[] weights){
            int maxValues=0;//menginisiasi maxValues sementara dengan 0
            int maxDex=0; //mengisiniasi index sementara dengan 0
            int totalWeight=0; //menginisiasi totalweight sementara dengan 0
            double[] Hasil=new double[values.length]; //membuat array hasil dengan panjang yang sesuai dengan panjang array values
            
            while(totalWeight<capacity){//looping selama totalweight lebih kecil dari kapasitas
                for (int i = 0; i < values.length; i++) {//looping sebanyak panjang array values untuk mencari profit terbesar
                    if (values[i]>maxValues){//jika values/profit lebih besar dari max values
                        maxValues=values[i]; //max values di isi dari values item tersebut
                        maxDex=i; //maxdex diisi dengan values dari item tersebut
                    }                    
                }
                if((totalWeight+weights[maxDex])<capacity){//mengecek jika sisa dari kapasitas mencukupi untuk mengambil seluruh item
                    totalWeight+=weights[maxDex]; //menambahkan totalweight dengan berat item yang baru ditambahkan
                    values[maxDex]=0; //mngubah values item tersebut menjadi 0 agar pada iterasi selanjutnya tidak terpilih
                    Hasil[maxDex]=1; //mengisi array hasil index yang sama dengan item dengan 1 menandakan bahwa seluruh item di masukan
                    maxValues=0;//mengisi maxvalues dengan 0
                }
                else{//jika sisa dari kapasitas tidak mencukupi untuk mengambil seluruh item
                    Hasil[maxDex]=((double)capacity-(double)totalWeight)/(double)weights[maxDex]; /*mengisi array hasil dengan berapa banyak 
                                                                                                    bagian yang bisa di ambil dari item tersebut*/
                    break;//memberhentikan looping karena wight barang sudah seimbang dengan kapasitas
                }
            }
            
        return Hasil;//mengembalikan array hasil
    }
}
