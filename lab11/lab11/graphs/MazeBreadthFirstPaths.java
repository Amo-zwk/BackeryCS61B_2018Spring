package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* inherit public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean isFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        //起点和终点的number值
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        //distTo[s]表示存储从起始点s到i的最短路径长度
        distTo[s] = 0;
        //edgeTo[s]表示s的父节点
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        //创建队列
        Queue<Integer> queue = new ArrayDeque<>();
        //入队
        queue.add(s);
        //当队列不为空
        while (!queue.isEmpty()) {
            //队头出队
            int frontier = queue.remove();
            //标记已经走过
            marked[frontier] = true;
            //Specifically, whenever you call announce,
            //it will draw the contents of your MazeExplorer’s marked, distTo and edgeTo arrays
            //Make sure to call the announce method any time you want the drawing to be updated.
            //此时mark和edgeTo和distTo都已经改了,所以要用announce
            announce();
            //如果找到答案了,就退出循环
            if (frontier == t) {
                isFound = true;
                break;
            }
            //遍历所有邻接结点
            for (int w : maze.adj(frontier)) {
                if (!marked[w]) { //如果有邻接结点没有访问过
                    distTo[w] = distTo[frontier] + 1; //那么到当前这个结点的距离就是到上一个结点的距离+1
                    edgeTo[w] = frontier; //更改没被访问过的邻接结点的父节点为当前结点
                    marked[w] = true;
                    announce();
                    queue.add(w);
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

