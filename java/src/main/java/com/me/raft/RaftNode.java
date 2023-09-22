package com.me.raft;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RaftNode {
    private int nodeId;
    private List<Integer> peers;
    private int currentTerm;
    private Integer votedFor;
    private List<Object> log;
    private int commitIndex;
    private int lastApplied;
    private String state;
    private double electionTimeout;

    public RaftNode(int nodeId, List<Integer> peers) {
        this.nodeId = nodeId;
        this.peers = peers;
        this.currentTerm = 0;
        this.votedFor = null;
        this.log = new ArrayList<>();
        this.commitIndex = 0;
        this.lastApplied = 0;
        this.state = "follower";
        this.electionTimeout = generateElectionTimeout();
    }

    private double generateElectionTimeout() {
        // 生成随机选举超时时间
        Random random = new Random();
        return random.nextDouble() * (0.3 - 0.15) + 0.15;
    }

    public void start() {
        while (true) {
            if (state.equals("follower")) {
                follower();
            } else if (state.equals("candidate")) {
                candidate();
            } else if (state.equals("leader")) {
                leader();
            }
        }
    }

    private void follower() {
        System.out.println("Node " + nodeId + " is in follower state.");
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() < startTime + electionTimeout * 1000) {
            // 处理接收到的请求
        }

        // 超时后转变为候选者
        state = "candidate";
    }

    private void candidate() {
        System.out.println("Node " + nodeId + " is in candidate state.");
        currentTerm++;
        votedFor = nodeId;

        // 向其他节点发送选举请求
        for (int peer : peers) {
            sendRequestVote(peer);
        }

        int votesReceived = 1;

        while (true) {
            if (votesReceived > peers.size() / 2) {
                state = "leader";
                return;
            }

            // 处理接收到的投票结果
        }
    }

    private void leader() {
        System.out.println("Node " + nodeId + " is in leader state.");

        while (true) {
            // 向其他节点发送心跳消息
            for (int peer : peers) {
                sendAppendEntries(peer);
            }

            // 处理接收到的追加日志回复

            try {
                Thread.sleep(100); // 休眠一段时间模拟心跳间隔
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendRequestVote(int peer) {
        // 发送请求选举消息至peer节点
    }

    private void sendAppendEntries(int peer) {
        // 发送追加日志消息至peer节点
    }

    public static void main(String[] args) {
        List<Integer> peers = new ArrayList<>();
        peers.add(2);
        peers.add(3);

        RaftNode node1 = new RaftNode(1, peers);
        RaftNode node2 = new RaftNode(2, peers);
        RaftNode node3 = new RaftNode(3, peers);

        // 启动所有节点
        node1.start();
        node2.start();
        node3.start();
    }
}
