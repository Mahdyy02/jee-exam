package com.ejb;

class SegmentTree {

    float[] a;
    Node[] tree;

    static class Node {
        float sum;
        int cnt;

        Node() {
            sum = 0;
            cnt = 1;
        }

        Node(float s, int c) {
            sum = s;
            cnt = c;
        }
    }

    public SegmentTree(int size) {
        a = new float[size];
        tree = new Node[4 * size];
        for (int i = 0; i < 4 * size; i++) {
            tree[i] = new Node();
        }
    }

    public Node merge(Node x, Node y) {
        int cnt1 = x.cnt, cnt2 = y.cnt;
        float sum1 = x.sum, sum2 = y.sum;
        if (sum1 == -1.0f) {
            sum1 = 0;
            cnt1 = 0;
        }
        if (sum2 == -1.0f) {
            sum2 = 0;
            cnt2 = 0;
        }
        return new Node(sum1 + sum2, cnt1 + cnt2);
    }

    public void build(int id, int ns, int ne) {
        if (ns == ne) {
            tree[id] = new Node(a[ns], 1);
            return;
        }
        int l = 2 * id + 1;
        int r = l + 1;
        int md = ns + (ne - ns) / 2;
        build(l, ns, md);
        build(r, md + 1, ne);
        tree[id] = merge(tree[l], tree[r]);
    }

    public Node query(int qs, int qe, int id, int ns, int ne) {
        if (ns > qe || qs > ne) {
            return new Node(-1.0f, 1);
        }
        if (qs <= ns && qe >= ne) {
            return tree[id];
        }
        int l = 2 * id + 1;
        int r = l + 1;
        int md = ns + (ne - ns) / 2;
        Node ndl = query(qs, qe, l, ns, md);
        Node ndr = query(qs, qe, r, md + 1, ne);
        return merge(ndl, ndr);
    }

    public void update(int pos, float val, int id, int ns, int ne) {
        if (ns > pos || pos > ne) {
            return;
        }
        if (ns == ne) {
            tree[id] = new Node(val, 1);
            return;
        }
        int l = 2 * id + 1;
        int r = l + 1;
        int md = ns + (ne - ns) / 2;
        update(pos, val, l, ns, md);
        update(pos, val, r, md + 1, ne);
        tree[id] = merge(tree[l], tree[r]);
    }
}
