package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;

public class Solver {

    //SearchNode类
    //每个SearchNode代表一个“移动序列”
    public class Node implements Comparable<Node> {

        private int priority; //优先级
        private WorldState worldState; //WorldState对象
        private int moves; // initial state到 this word的移动次数
        private Node previous; //指向前一个搜索节点的

        public int getPriority() {
            return priority;
        }

        public WorldState getWorldState() {
            return worldState;
        }

        public int getMoves() {
            return moves;
        }

        public Node getPrevious() {
            return previous;
        }

        //构造方法
        public Node(WorldState w, int moves, Node previous) {
            this.worldState = w;
            this.moves = moves;
            this.previous = previous;
            this.priority = moves + w.estimatedDistanceToGoal();
        }

        //重写优先级比较方法
        @Override
        public int compareTo(Node o) {
            return this.priority - o.priority;
        }
    }

    private int walks; //最终距离
    private ArrayList<WorldState> arrayList; //最终答案的路径

    //第一步是创建一个SearchNode的priority queue
    MinPQ<Node> priorityQueue = new MinPQ<Node>();

    private void getAnswer(Node goal) {
        walks = goal.moves; //最终步数就是goal.moves
        arrayList = new ArrayList<>();
        Node p = goal;
        while (p != null) {
            arrayList.add(p.worldState);
            p = p.previous;
        }
    }

    public Solver(WorldState initial) {
        //从PQ里面插入initial search node
        //先把initial的结点入队
        priorityQueue.insert(new Node(initial, 0, null));
        // Three Steps for Adding the first Search Node
        // Step 1 : the initial world state;
        // Step 2 : 0 ( since no moves have been made yet )
        // Step 3 : null ( since there is no previous search node )

        //同样，最后求出来的具体路径，应该是从终点开始，沿着每一个点的父节点向上溯源，直到起始点。

        while (!priorityQueue.isEmpty()) { //当队列不为空
            Node current = priorityQueue.delMin();
            //从队列中找优先级最小的结点移除并得到优先级最小的Search Node
            //如果找到了goal
            if (current.worldState.isGoal()) {
                getAnswer(current);
                return;
            } else {
                //否则没找到goal
                Iterable<WorldState> neighbors = current.worldState.neighbors();
                //获得被移除的Search Node的neighbor的可迭代对象
                //把所有neighbor加入到优先队列里面
                for (WorldState neighbor : neighbors) {
                    //如果当前探测的neighbor和自己的父节点不相同，就可以加入队列
                    if (current.previous == null || !neighbor.equals(current.previous.worldState)) {
                        // 第一个是为了保证第一次入队,第二个是为了保证邻居和父节点不相等
                        priorityQueue.insert(new Node(neighbor, current.moves + 1, current));
                    }
                }
            }
        }

        /**
         * The algorithm then proceeds as follows:
         * 1.Remove the search node with minimum priority.
         * Let’s call this node X. If it is the goal node, then we’re done.
         */
        //传递一个WorldState对象进来
        //Step 1 : 找出它的neighbors
        //Step 2 : 找出最小的编辑距离
    }

    public int moves() {
        //返回从初始世界状态开始求解谜题的最小移动次数
        return walks;
    }

    public Iterable<WorldState> solution() {
        //返回一个可迭代对象,是一个WorldState的neighbor
        //返回从初始WorldState到解决方案的WorldState序列
        ArrayList<WorldState> res = new ArrayList<>();
        for (int i = walks; i >= 0; i--) {
            res.add(arrayList.get(i));
        }
        return res;
    }
}
