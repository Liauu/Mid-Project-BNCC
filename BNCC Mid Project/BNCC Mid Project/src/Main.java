import java.util.*;

public class Main {
    private static Vector<String> name = new Vector<>();
    private static Vector<String> gender = new Vector<>();
    private static Scanner sc = new Scanner(System.in);
    private static Vector<String> code = new Vector<>();
    private static Vector<String> rank = new Vector<>();
    private static Vector<Integer> wage = new Vector<>();
    private static Random randomizer = new Random();
    private static int jumlahManager = 0;
    private static int jumlahSupervisor = 0;
    private static int jumlahAdmin = 0;
    public static void main(String[] args) {
        int menu = -1;
        do {
            System.out.println("PT.Mentol");
            System.out.println("1. Insert data karyawan ");
            System.out.println("2. View data karyawan ");
            System.out.println("3. Update data karyawan ");
            System.out.println("4. Delete data karyawan ");
            System.out.println("5. Exit ");
            System.out.print(">> ");
            menu = TryCatchInt();
            System.out.println("");
        } while(menu < 1 || menu > 5);
        switch (menu){
            case 1 :
                InsertData(args);
                break;
            case 2 :
                ViewData(args);
                enter(args);
                break;
            case 3 :
                UpdateData(args);
                break;
            case 4 :
                RemoveData(args);
                break;
            case 5 :
                System.out.println("Thank you for using this application :)");
                System.exit(0);
        }
    }
    private static void InsertData (String[]args){
        char [] InputCode = new char[7];
        InputCode [0] = (char) (randomizer.nextInt(26)+65);
        InputCode [1] = (char) (randomizer.nextInt(26)+65);
        InputCode [2] = '-';
        InputCode [3] = (char) (randomizer.nextInt(10)+48);
        InputCode [4] = (char) (randomizer.nextInt(10)+48);
        InputCode [5] = (char) (randomizer.nextInt(10)+48);
        InputCode [6] = (char) (randomizer.nextInt(10)+48);
        String inputName,inputGender,inputJabatan;
        int inputGaji = 0;
        do {
            System.out.println("Input nama karyawan [>= 3]: ");
            inputName = sc.nextLine();
            if (inputName.length()<3){
                System.out.println("Nama karyawan minimal terdiri dari 3 huruf!");
            }
        }while (inputName.length()<3);
        do {
            System.out.println("Jenis Kelamin (Laki-Laki | Perempuan)[Case Sensitive] : ");
            inputGender = sc.nextLine();
            if (!inputGender.equals("Laki-Laki") &&!inputGender.equals("Perempuan")){
                System.out.println("Gender harus Laki-Laki | Perempuan!");
            }
        }while (!inputGender.equals("Laki-Laki") &&!inputGender.equals("Perempuan"));
        do {
            System.out.println("Jabatan (Manager | Supervisor | Admin)[Case Sensitive] : ");
            inputJabatan = sc.nextLine();
            if (!inputJabatan.equals("Manager") && !inputJabatan.equals("Supervisor") && !inputJabatan.equals("Admin")){
                System.out.println("Jabatan harus (Manager | Supervisor | Admin)!");
            }
        }while (!inputJabatan.equals("Manager") && !inputJabatan.equals("Supervisor") && !inputJabatan.equals("Admin"));
        System.out.println("Berhasil menambahkan karyawan dengan id " + String.valueOf(InputCode));
        switch (inputJabatan) {
            case "Manager" :
                inputGaji = 8000000;
                jumlahManager++;
                break;
            case "Supervisor" :
                inputGaji = 6000000;
                jumlahSupervisor++;
                break;
            case "Admin" :
                inputGaji = 4000000;
                jumlahAdmin++;
                break;
        }
        int bonusManager = (jumlahManager/3)* 3;
        int bonusAdmin = (jumlahAdmin/3)* 3;
        int bonusSupervisor = (jumlahSupervisor/3)* 3;
        int manager = 0;
        int admin = 0;
        int supervisor = 0;
        if (jumlahManager > 3 && jumlahManager % 3 == 1){
            System.out.print("Bonus sebesar 10% telah diberikan kepada karyawan dengan id ");
            for (int i = 0 ; i < code.size();i++){
                if (manager < bonusManager){
                    manager++;
                    wage.set(i, (int) (wage.get(i)*1.1));
                    if (manager != 1) {
                        System.out.print(", ");
                    }
                    System.out.print(code.get(i));
                }
            }
            System.out.println("");
        }
        if (jumlahSupervisor > 3 && jumlahSupervisor % 3 == 1){
            System.out.print("Bonus sebesar 7,5% telah diberikan kepada karyawan dengan id ");
            for (int i = 0 ; i < code.size();i++){
                if (supervisor < bonusSupervisor){
                    supervisor++;
                    wage.set(i, (int) (wage.get(i)*1.075));
                    if (supervisor != 1) {
                        System.out.print(", ");
                    }
                    System.out.print(code.get(i));
                }
            }
            System.out.println("");
        }

        if (jumlahAdmin > 3 && jumlahAdmin % 3 == 1){
            System.out.print("Bonus sebesar 5% telah diberikan kepada karyawan dengan id ");
            for (int i = 0 ; i < code.size();i++){
                if (admin < bonusAdmin){
                    admin++;
                    wage.set(i, (int) (wage.get(i)*1.05));
                    if (admin != 1) {
                        System.out.print(", ");
                    }
                    System.out.print(code.get(i));
                }
            }
            System.out.println("");
        }
        code.add(String.valueOf(InputCode));
        name.add(inputName);
        gender.add(inputGender);
        rank.add(inputJabatan);
        wage.add(inputGaji);
        enter(args);
    }
    private static Vector <String> ViewData (String[]args){
        System.out.printf("|%4s|%15s|%27s|%15s|%12s|%15s|\n" ,"----","---------------","---------------------------","---------------","------------","---------------");
        System.out.printf("| %-2s | %-13s | %-25s | %-13s | %-10s | %-13s |\n", "No" , "Kode Karyawan" , "Nama Karyawan" , "Jenis Kelamin" , "Jabatan" , "Gaji Karyawan");
        System.out.printf("|%4s|%15s|%27s|%15s|%12s|%15s|\n" ,"----","---------------","---------------------------","---------------","------------","---------------");
        Vector <String> sortedName = new Vector<>(name);
        BubbleSort(sortedName);
        int index = -1;
        for (int i = 0; i< code.size();i++){
            for (int j = 0; j < sortedName.size();j++){
                if (name.get(j).equals(sortedName.get(i))){
                    index = j;
                }
            }
            System.out.printf("| %-2d | %-13s | %-25s | %-13s | %-10s | %-13d |\n", (i+1) , code.get(index) , name.get(index) , gender.get(index) , rank.get(index) ,
                    wage.get(index));
        }
        System.out.printf("|%4s|%15s|%27s|%15s|%12s|%15s|\n" ,"----","---------------","---------------------------","---------------","------------","---------------");
        return sortedName;
    }
    private static void UpdateData(String[]args){
        Vector <String> sortedName = ViewData(args);
        int inputNomor = 0;
        System.out.println("Input nomor yang ingin di update : ");
        System.out.print(">> ");
        inputNomor = (TryCatchInt()-1);
        int index = -1;
        for (int i = 0; i< code.size();i++){
            if (name.get(i).equals(sortedName.get(inputNomor))){
                index = i;
            }
        }
        AddData(index,args);
    }
    private static void RemoveData (String[]args){
        Vector <String> sortedName = ViewData(args);
        int inputNomor = 0;
        System.out.println("Input nomor yang ingin di delete : ");
        System.out.print(">> ");
        inputNomor = (TryCatchInt()-1);
        int index = -1;
        for (int i = 0; i< code.size();i++){
            if (name.get(i).equals(sortedName.get(inputNomor))){
                index = i;
            }
        }
        code.remove(index);
        name.remove(index);
        gender.remove(index);
        rank.remove(index);
        wage.remove(index);
        System.out.println("Data berhasil dihapus!");
        enter(args);
    }
    private static void AddData (int index ,String[]args){
        char [] InputCode = new char[7];
        InputCode [0] = (char) (randomizer.nextInt(26)+65);
        InputCode [1] = (char) (randomizer.nextInt(26)+65);
        InputCode [2] = '-';
        InputCode [3] = (char) (randomizer.nextInt(10)+48);
        InputCode [4] = (char) (randomizer.nextInt(10)+48);
        InputCode [5] = (char) (randomizer.nextInt(10)+48);
        InputCode [6] = (char) (randomizer.nextInt(10)+48);
        String inputName,inputGender,inputJabatan;
        int inputGaji = 0;
        do {
            System.out.println("Input nama karyawan [>= 3]: ");
            inputName = sc.nextLine();
            if (inputName.length()<3){
                System.out.println("Nama karyawan minimal terdiri dari 3 huruf!");
            }
        }while (inputName.length()<3);
        do {
            System.out.println("Jenis Kelamin (Laki-Laki | Perempuan)[Case Sensitive] : ");
            inputGender = sc.nextLine();
            if (!inputGender.equals("Laki-Laki") &&!inputGender.equals("Perempuan")){
                System.out.println("Gender harus Laki-Laki | Perempuan!");
            }
        }while (!inputGender.equals("Laki-Laki") &&!inputGender.equals("Perempuan"));
        do {
            System.out.println("Jabatan (Manager | Supervisor | Admin)[Case Sensitive] : ");
            inputJabatan = sc.nextLine();
            if (!inputJabatan.equals("Manager") && !inputJabatan.equals("Supervisor") && !inputJabatan.equals("Admin")){
                System.out.println("Jabatan harus (Manager | Supervisor | Admin)!");
            }
        }while (!inputJabatan.equals("Manager") && !inputJabatan.equals("Supervisor") && !inputJabatan.equals("Admin"));
        System.out.println("Berhasil menambahkan karyawan dengan id " + String.valueOf(InputCode));
        switch (inputJabatan) {
            case "Manager" :
                inputGaji = 8000000;
                jumlahManager++;
                break;
            case "Supervisor" :
                inputGaji = 6000000;
                jumlahSupervisor++;
                break;
            case "Admin" :
                inputGaji = 4000000;
                jumlahAdmin++;
                break;
        }
        int bonusManager = (jumlahManager/3)* 3;
        int bonusAdmin = (jumlahAdmin/3)* 3;
        int bonusSupervisor = (jumlahSupervisor/3)* 3;
        int manager = 0;
        int admin = 0;
        int supervisor = 0;
        if (jumlahManager > 3 && jumlahManager % 3 == 1){
            System.out.print("Bonus sebesar 10% telah diberikan kepada karyawan dengan id ");
            for (int i = 0 ; i < code.size();i++){
                if (manager < bonusManager){
                    manager++;
                    wage.set(i, (int) (wage.get(i)*1.1));
                    if (manager != 1) {
                        System.out.print(", ");
                    }
                    System.out.print(code.get(i));
                }
            }
            System.out.println("");
        }
        if (jumlahSupervisor > 3 && jumlahSupervisor % 3 == 1){
            System.out.print("Bonus sebesar 7,5% telah diberikan kepada karyawan dengan id ");
            for (int i = 0 ; i < code.size();i++){
                if (supervisor < bonusSupervisor){
                    supervisor++;
                    wage.set(i, (int) (wage.get(i)*1.075));
                    if (supervisor != 1) {
                        System.out.print(", ");
                    }
                    System.out.print(code.get(i));
                }
            }
            System.out.println("");
        }

        if (jumlahAdmin > 3 && jumlahAdmin % 3 == 1){
            System.out.print("Bonus sebesar 5% telah diberikan kepada karyawan dengan id ");
            for (int i = 0 ; i < code.size();i++){
                if (admin < bonusAdmin){
                    admin++;
                    wage.set(i, (int) (wage.get(i)*1.05));
                    if (admin != 1) {
                        System.out.print(", ");
                    }
                    System.out.print(code.get(i));
                }
            }
            System.out.println("");
        }
        name.set(index,inputName);
        gender.set(index,inputGender);
        rank.set(index,inputJabatan);
        wage.set(index,inputGaji);
        System.out.println("Data berhasil diubah!");
        enter(args);
    }
    private static void BubbleSort (Vector<String> arr){
        String temp;
        for (int i = 0 ; i < arr.size(); i++){
            for (int j = i; j < arr.size(); j++){
                if (arr.get(j).toUpperCase().compareTo(arr.get(i).toUpperCase())< 0){
                    temp = arr.get(i);
                    arr.set(i, arr.get(j));
                    arr.set(j,temp);
                }
            }
        }
    }
    private static int TryCatchInt (){
        int temp = -1;
        try{
            temp = sc.nextInt();
        }catch(Exception e) {
            System.out.println("Input must be numeric!");
        }
        sc.nextLine();
        return temp;
    }
    private static void enter (String[] args){
        System.out.println("ENTER to return");
        sc.nextLine();
        main(args);
    }
}
