import java.util.*;

/**
 * LeetCode : https://leetcode.com/problems/employee-importance/submissions/1816394220/
 *
 * Approaches implemented in this class:
 *
 * 1. BFS with two HashMaps (getImportance):
 *    - Uses one map for employee importance and another for subordinates.
 *    - Traverses the hierarchy using a queue (BFS) to sum up importance values.
 *    - Time Complexity: O(N), where N is the number of employees (each employee is visited once).
 *    - Space Complexity: O(N), for the maps and queue.
 *
 * 2. BFS with one HashMap (getImportance3):
 *    - Uses a map from id to Employee object.
 *    - Traverses using a queue of Employee objects, summing importance.
 *    - Time Complexity: O(N), each employee is visited once.
 *    - Space Complexity: O(N), for the map and queue.
 *
 * 3. DFS with one HashMap (getImportance2):
 *    - Uses a map from id to Employee object.
 *    - Recursively traverses subordinates using DFS to sum importance.
 *    - Time Complexity: O(N), each employee is visited once.
 *    - Space Complexity: O(N), for the map and recursion stack.
 */
public class EmployeeImportance {

    public int getImportance(List<Employee> employees, int id) {
        HashMap<Integer, Integer> importanceMap = new HashMap<>();
        HashMap<Integer, List<Integer>> dependenceMap = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();

        for (Employee employee : employees) {
            importanceMap.put(employee.id, employee.importance);
            dependenceMap.put(employee.id, employee.subordinates);
        }
        int importance = 0;
        queue.add(id);
        while (!queue.isEmpty()) {
            int currentId = queue.poll();
            importance += importanceMap.get(currentId);
            List<Integer> subs = dependenceMap.get(currentId);
            if (!subs.isEmpty()) {
                queue.addAll(subs);
            }
        }
        return importance;
    }

    public int getImportance3(List<Employee> employees, int id) {
        HashMap<Integer, Employee> dependenceMap = new HashMap<>();
        Queue<Employee> queue = new LinkedList<>();

        for (Employee employee : employees) {
            dependenceMap.put(employee.id, employee);
        }
        int importance = 0;
        queue.add(dependenceMap.get(id));
        while (!queue.isEmpty()) {
            Employee current = queue.poll();
            importance += current.importance;
            List<Integer> subs = current.subordinates;
            if (!subs.isEmpty()) {
                for (int e : subs) {
                    queue.add(dependenceMap.get(e));
                }
            }
        }
        return importance;
    }

    public int getImportance2(List<Employee> employees, int id) {
        HashMap<Integer, Employee> dependenceMap = new HashMap<>();

        for (Employee employee : employees) {
            dependenceMap.put(employee.id, employee);
        }
        return dfs(id, dependenceMap);
    }

    private int dfs(int id, HashMap<Integer, Employee> dependenceMap){
        //base
        Employee employee = dependenceMap.get(id);
        int imp =  employee.importance;
        for(int i = 0 ; i<employee.subordinates.size(); i++){
            imp += dfs(employee.subordinates.get(i), dependenceMap);
        }
        return imp;
    }


    // Definition for Employee.
    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

}
