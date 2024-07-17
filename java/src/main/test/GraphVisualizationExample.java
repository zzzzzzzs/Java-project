import com.google.common.graph.*;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.util.mxConstants;
import org.jgrapht.*;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;
import com.mxgraph.layout.*;
import com.mxgraph.swing.*;
import com.mxgraph.view.*;

import javax.swing.*;

public class GraphVisualizationExample {
    public static void main(String[] args) {
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        // 添加节点
        String v1 = "A";
        String v2 = "B";
        String v3 = "C";
        String v4 = "D";
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);

        // 添加边
        graph.addEdge(v1, v2);
        graph.addEdge(v2, v3);
        graph.addEdge(v3, v4);
        graph.addEdge(v4, v1);

        // 计算最短路径
        DijkstraShortestPath<String, DefaultEdge> dijkstraAlg = new DijkstraShortestPath<>(graph);
        GraphPath<String, DefaultEdge> shortestPath = dijkstraAlg.getPath(v1, v3);

        System.out.println("最短路径为: " + shortestPath.getVertexList());

        // 可视化图形
        mxGraph mxGraph = new mxGraph();
        Object parent = mxGraph.getDefaultParent();

        mxGraph.getModel().beginUpdate();
        try {
            // 设置节点样式
            mxGraph.getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_NOLABEL, "true");
            mxGraph.getStylesheet().getDefaultVertexStyle().put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
            mxGraph.getStylesheet().getDefaultVertexStyle().put(mxConstants.STYLE_FILLCOLOR, "#C3D9FF");

            // 添加节点
            Object v1mx = mxGraph.insertVertex(parent, null, v1, 20, 20, 80, 30);
            Object v2mx = mxGraph.insertVertex(parent, null, v2, 140, 20, 80, 30);
            Object v3mx = mxGraph.insertVertex(parent, null, v3, 140, 140, 80, 30);
            Object v4mx = mxGraph.insertVertex(parent, null, v4, 20, 140, 80, 30);

            // 添加边
            mxGraph.insertEdge(parent, null, "", v1mx, v2mx);
            mxGraph.insertEdge(parent, null, "", v2mx, v3mx);
            mxGraph.insertEdge(parent, null, "", v3mx, v4mx);
            mxGraph.insertEdge(parent, null, "", v4mx, v1mx);

            // 设置布局
            new mxHierarchicalLayout(mxGraph).execute(parent);

        } finally {
            mxGraph.getModel().endUpdate();
        }

        JFrame frame = new JFrame("Simple Graph Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mxGraphComponent graphComponent = new mxGraphComponent(mxGraph);
        frame.getContentPane().add(graphComponent);
        frame.pack();
        frame.setVisible(true);
    }
}
