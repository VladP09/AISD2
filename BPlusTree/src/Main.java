import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        BStarTree bStarTree = new BStarTree();
        ArrayList<Result> result = new ArrayList<>();
        int[] array = generateRandomArray(10000);
        
        for (int i = 0; i < array.length; i++) {
            int key = array[i];
            long startTime = System.nanoTime();
            bStarTree.insert(key);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            int operationCount = bStarTree.getOperationCount();
            result.add(new Result(key, duration, operationCount));
        }
        ArrayList<Result> searchResults = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int index = random.nextInt(array.length);
            int key = array[index];
            long startTime = System.nanoTime();
            boolean found = bStarTree.search(key);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            int operationCount = bStarTree.getOperationCount();
            searchResults.add(new Result(key, duration, operationCount, found));
        }
        ArrayList<Result> deleteResults = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int index = random.nextInt(array.length);
            int key = array[index];
            long startTime = System.nanoTime();
            bStarTree.delete(key);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            int operationCount = bStarTree.getOperationCount();
            deleteResults.add(new Result(key, duration, operationCount));
        }
        double averageInsertTime = calculateTime(result);
        double averageDeleteTime = calculateTime(deleteResults);
        double averageSearchTime = calculateTime(searchResults);

        double averageInsertOperations = calculateOperations(result);
        double averageDeleteOperations = calculateOperations(deleteResults);
        double averageSearchOperations = calculateOperations(searchResults);

        System.out.println("Вставка: " + averageInsertTime);
        System.out.println("Удаление: " + averageDeleteTime);
        System.out.println("Поиск: " + averageSearchTime);
        System.out.println("Среднее количество операций вставки:  " + averageInsertOperations);
        System.out.println("Среднее количество операций удаления: " + averageDeleteOperations);
        System.out.println("Среднее количество поисковых операций: " + averageSearchOperations);
    }

    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }

    private static double calculateTime(ArrayList<Result> results) {
        long totalDuration = 0;
        for (Result result : results) {
            totalDuration += result.getDuration();
        }
        return (double) totalDuration / results.size();
    }

    private static double calculateOperations(ArrayList<Result> results) {
        int totalOperations = 0;
        for (Result result : results) {
            totalOperations += result.getOperationCount();
        }
        return (double) totalOperations / results.size();
    }
}