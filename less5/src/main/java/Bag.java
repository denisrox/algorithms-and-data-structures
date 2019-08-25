import java.util.ArrayList;

public class Bag {
    private int capacity;
    private Item[] allItems;
    private Item[] itemsInBag;
    private ArrayList<ArrayList<Item>> items;
    public Bag(int capacity){
        this.capacity=capacity;
    }
    public void put(Item[] allItems){
        this.allItems=allItems;
        items=new ArrayList<ArrayList<Item>>();
        items.add(new ArrayList<Item>());
        recursion(0);
        int indexMaxPrice=0;
        int maxPrice=0;
        for(int i = 0;i<items.size();i++)
        {
            if(getPriceItems(items.get(i))>maxPrice) {
                maxPrice=getPriceItems(items.get(i));
                indexMaxPrice = i;
            }
        }
        for (Item item:items.get(indexMaxPrice)) {
            System.out.println("======================\n"+item.name+"\n"+item.price+"\n"+item.weight+"\n======================");
        }
    }
    private void recursion(int begin){
        for(int i=begin;i<allItems.length-1;i++){
            if(getWeightItems(items.get(items.size()-1))+allItems[begin].weight>capacity || begin==allItems.length){
                return;
            }
            items.get(items.size()-1).add(allItems[begin]);
            recursion(begin+1);
            rotate(begin);
            this.items.add(new ArrayList<Item>());
            for(int j=0;j<begin;j++)
                items.get(items.size()-1).add(items.get(items.size()-2).get(j));
        }

    }

    private void rotate(int capacityItem) {
        Item tempItem = allItems[capacityItem];

        for(int i = capacityItem;i<(allItems.length-1);i++){
            allItems[i]=allItems[i+1];
        }
        allItems[allItems.length-1]=tempItem;
    }
    private int getWeightItems(ArrayList<Item> items){
        int weight=0;
        for (Item item:items){
            weight+=item.weight;
        }
        return weight;
    }
    private int getPriceItems(ArrayList<Item> items){
        int price=0;
        for (Item item:items){
            price+=item.price;
        }
        return price;
    }


}
