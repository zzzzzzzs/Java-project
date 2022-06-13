![Alt text](https://g.gravizo.com/g?
digraph G {
    rankdir = TB;
    size = "8,5"
    node [shape = box];
    INIT [label="INITIAL"];
    S1 [label="S1"];
    S2 [label="S2"];
    FAIL [label="FAIL"];
    SUCC [label="SUCCESS"];
    INIT -> S1 [ label = "fail"];
    S1 -> S2 [ label = "fail"];
    S2 -> FAIL [ label = "fail"];
    FAIL -> INIT [ label = "reset" ,headport="sw",tailport="nw"];
    S1 -> SUCC [ label = "success" , constraint=false];
    S2 -> SUCC [ label = "success" , constraint=false];
    INIT -> SUCC [ label = "success", constraint=false];
	SUCC -> INIT[ label = "reset" , constraint=false,tailport="nw"];
}
)