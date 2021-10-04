package com.lqt.dodgame.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Node {
    private ArrayList<Node> nextNodes;
    private int mapPoint[];
    private int hight;
    private int turn;

    /*
     * Hàm constructor sẽ tự tạo ra các con cho đến độ sâu yêu cầu
     */
    public Node(int[] mapPoint, int hight, int turn) {
        super();
        this.mapPoint = mapPoint;
        this.hight = hight;
        this.turn = turn;

        //Auto create child node
        nextNodes = new ArrayList<>();
        if (this.hight != 0 && !this.isNodeEnd()) {
            for (int i = 0; i < 9; i++) {
                if (this.mapPoint[i] == 1) {
                    if (this.movePoint(IMiniMax.MOVE2LEFT, i).length != 0) {
                        nextNodes.add(new Node(this.movePoint(IMiniMax.MOVE2LEFT, i), this.hight - 1, turn));
                    }
                    if (this.movePoint(IMiniMax.MOVE2UP, i).length != 0) {
                        nextNodes.add(new Node(this.movePoint(IMiniMax.MOVE2UP, i), this.hight - 1, turn));
                    }
                    if (this.movePoint(IMiniMax.MOVE2RIGHT, i).length != 0) {
                        nextNodes.add(new Node(this.movePoint(IMiniMax.MOVE2RIGHT, i), this.hight - 1, turn));
                    }
                    if (this.movePoint(IMiniMax.MOVE2FINAL, i).length != 0) {
                        nextNodes.add(new Node(this.movePoint(IMiniMax.MOVE2FINAL, i), this.hight - 1, turn));
                    }
                } else if (this.mapPoint[i] == -1) {
                    if (this.movePoint(IMiniMax.MOVE2UP, i).length != 0) {
                        nextNodes.add(new Node(this.movePoint(IMiniMax.MOVE2UP, i), this.hight - 1, turn));
                    }
                    if (this.movePoint(IMiniMax.MOVE2RIGHT, i).length != 0) {
                        nextNodes.add(new Node(this.movePoint(IMiniMax.MOVE2RIGHT, i), this.hight - 1, turn));
                    }
                    if (this.movePoint(IMiniMax.MOVE2DOWN, i).length != 0) {
                        nextNodes.add(new Node(this.movePoint(IMiniMax.MOVE2DOWN, i), this.hight - 1, turn));
                    }
                    if (this.movePoint(IMiniMax.MOVE2FINAL, i).length != 0) {
                        nextNodes.add(new Node(this.movePoint(IMiniMax.MOVE2FINAL, i), this.hight - 1, turn));
                    }
                }
            }
        } else {
            nextNodes = null;
        }
    }

    public ArrayList<Node> getNextNodes() {
        return nextNodes;
    }

    public void setNextNodes(ArrayList<Node> nextNodes) {
        this.nextNodes = nextNodes;
    }

    public int[] getMapPoint() {
        return mapPoint;
    }

    public void setMapPoint(int[] mapPoint) {
        this.mapPoint = mapPoint;
    }

    public int getHight() {
        return hight;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    @Override
    public String toString() {
        return "Node [nextNodes=" + nextNodes + ", mapPoint=" + Arrays.toString(mapPoint) + ", hight=" + hight
                + ", turn=" + turn + ", getSumPoint()=" + getSumPoint() + "]";
    }

    /*
     * tính điểm của node đối chiếu với bảng tra trong IMinimax cộng thêm 40 điểm
     * nếu như quân trắng chặn trực tiếp đen và 30 nếu là gián tiếp ngược lại là -40
     * và -30
     */
    public int getSumPoint() {
        int sum = 0;

        int black = 0, white = 0;
        for (int i = 0; i < 9; i++) {
            if (this.mapPoint[i] == 1) {
                sum += IMiniMax.POINTS_OF_WHITE[i] * this.mapPoint[i];
                if (i / 3 == 2 && this.mapPoint[i - 3] == -1) {
                    sum -= 40;
//					System.out.println("-40");
                }
                if (i / 3 == 2 && this.mapPoint[i - 6] == -1) {
                    sum -= 30;
//					System.out.println("-30");
                }
                if (i / 3 == 1 && this.mapPoint[i - 3] == -1) {
                    sum -= 40;
//					System.out.println("-40");
                }
                white++;
            } else if (this.mapPoint[i] == -1) {
                sum += IMiniMax.POINTS_OF_BLACK[i] * this.mapPoint[i];
                if (i % 3 == 0 && this.mapPoint[i + 1] == 1) {
                    sum += 40;

//					System.out.println("+40" + i);
                }
                if (i % 3 == 0 && this.mapPoint[i + 2] == 1) {
                    sum += 30;

//					System.out.println("+30");
                }
                if (i % 3 == 1 && this.mapPoint[i + 1] == 1) {
                    sum += 40;
//					System.out.println("+40");
                }
                black++;
            }
        }
        sum += (2 - white) * 85 - (2 - black) * 85;
        return sum;
    }

    public boolean isLeaf() {
        return this.nextNodes == null;
    }

    /*
     * Nếu trên bàn cờ chỉ có 1 loại quân thì là end
     *
     * TODO tới lượt nhưng không đi được
     */
    public boolean isNodeEnd() {
        int countBlack = 0;
        int countWhite = 0;
        for (int i : mapPoint) {
            if (i == 1) {
                countWhite++;
            } else if (i == -1) {
                countBlack++;
            }
        }

        if (countBlack == 0 || countWhite == 0) {
            return true;
        }

        return false;
    }

    /*
     * kiểm tra xem nếu được phép di chuyển thì sẽ di chuyển
     */
    public int[] movePoint(int direction, int position) {
        int mapPointCopy[] = this.mapPoint.clone();
        int t = mapPointCopy[position];
        switch (direction) {
            case IMiniMax.MOVE2LEFT:
                if (canMove(direction, position, mapPointCopy)) {
                    mapPointCopy[position] = mapPointCopy[position - 1];
                    mapPointCopy[position - 1] = t;
                    return mapPointCopy;
                }
                break;
            case IMiniMax.MOVE2UP:
                if (canMove(direction, position, mapPointCopy)) {
                    mapPointCopy[position] = mapPointCopy[position - 3];
                    mapPointCopy[position - 3] = t;
                    return mapPointCopy;
                }
                break;
            case IMiniMax.MOVE2RIGHT:
                if (canMove(direction, position, mapPointCopy)) {
                    mapPointCopy[position] = mapPointCopy[position + 1];
                    mapPointCopy[position + 1] = t;
                    return mapPointCopy;
                }
                break;
            case IMiniMax.MOVE2DOWN:
                if (canMove(direction, position, mapPointCopy)) {
                    mapPointCopy[position] = mapPointCopy[position + 3];
                    mapPointCopy[position + 3] = t;
                    return mapPointCopy;
                }
                break;
            case IMiniMax.MOVE2FINAL:
                if (canMove(direction, position, mapPointCopy)) {
                    mapPointCopy[position] = 0;
                    return mapPointCopy;
                }
                break;
        }
        int arrayNull[] = {};
        return arrayNull;
    }

    private boolean canMove(int direction, int position, int mapP[]) {
        switch (direction) {
            case IMiniMax.MOVE2LEFT:
                if (mapP[position] == -1)
                    return false;
                if (mapP[position] == 1) {
                    if (position % 3 == 0)
                        return false;
                    else if (position % 3 > 0 && mapP[position - 1] != 0) {
                        return false;
                    }
                }
                break;
            case IMiniMax.MOVE2UP:
                if (position / 3 == 0)
                    return false;
                else if (position / 3 > 0 && mapP[position - 3] != 0) {
                    return false;
                }
                break;
            case IMiniMax.MOVE2RIGHT:
                if (position % 3 == 2)
                    return false;
                else if (position % 3 < 2 && mapP[position + 1] != 0) {
                    return false;
                }
                break;
            case IMiniMax.MOVE2DOWN:
                if (mapP[position] == 1)
                    return false;
                if (mapP[position] == -1) {
                    if (position / 3 == 2)
                        return false;
                    else if (position / 3 < 2 && mapP[position + 3] != 0) {
                        return false;
                    }
                }
                break;
            case IMiniMax.MOVE2FINAL:
                if (mapP[position] == 1) {
                    if (position != 0 && position != 1 && position != 2) {
                        return false;
                    }
                }
                if (mapP[position] == -1) {
                    if (position != 2 && position != 5 && position != 8) {
                        return false;
                    }
                }
                break;
        }
        return true;
    }
}
