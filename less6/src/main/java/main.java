import java.util.Random;

public class main {
    public static void main(String[] args) {
//        testTree();
        analysed(10000,10,3,4);
        analysed(10000,10,3,40);
        analysed(10000,10,15,40);
        analysed(10000,100,3,40);
        analysed(10000,10,3,100);
        analysed(10000,10,3,200);
        analysed(10000,100,3,200);

        analysed(10000,0,3,200);

    }
    static void analysed(int countTree, int countValueInTree,int depth, int key){
        System.out.println("\n====================================================="); //нужно обработать вывод в строку, когда неизвестно кол-во выводимиых значений
        System.out.println("При количестве деревьев " + countTree+" в каждом из которых "+countValueInTree+" значений с максимальной глубиной "+depth+" и разбросом значений "+ -key/2 + " до " +key/2+" сбалансированныъ деревьев:");
        Random random = new Random();
        boolean analysis[] = new boolean[countTree];
        TreeImpl<Integer>treeForAnalysis[] = new TreeImpl[countTree];
        for (int i = 0;i<countTree;i++){
            if(countValueInTree==0)
                countValueInTree = random.nextInt(100);
            treeForAnalysis[i]= new TreeImpl<>(depth);
            for (int j =0;j<countValueInTree;j++){
                int randomValue = random.nextInt(key)-key/2;
                treeForAnalysis[i].add(randomValue);
            }
            analysis[i]=treeForAnalysis[i].isBalanced();
        }
        int trueTest = 0;
        for (int i = 0;i<countTree;i++)
            if(analysis[i])
                trueTest++;
        System.out.println(((float)(trueTest*100))/countTree+"% сбалансированных");
    }
}
