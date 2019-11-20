#  고급자료구조 Term Project 최종 설명서
---
## 목차
1. 개요
2. 개발 환경
4. 클래스 설명
    * Node
    * Navi
    * Main
5. 알고리즘 구현방법 소개
    * Single Source Shortest Path 알고리즘
    * BFS 알고리즘
6. 사용 자료구조, 알고리즘

---
## 1. 개요

* ### 주제
    네비게이션 프로그램 작성
* ### 구현 조건
    1. Single Source Shortest Path 알고리즘을 이용하여 최소패스와 가중치 합을 출력
    2. BFS 알고리즘을 이용하여 최소패스와 가중치 합을 출력
    3. 패스가 없는 경우 "도달 불가능" 출력
---
## 2. 개발 환경
* ### 사용 OS
    * macOS 10.14 Mojave,
    * macOS 10.15 Catalina,
    * Windows 10 1903
* ### 사용 언어
    Java
* ### 사용 JDK
    JDK 11
* ### 사용 IDE
    Jetbrains intelliJ IDEA 2019.2
---
## 3. 클래스 설명
* ### Node

    * 필드

            - nodeName : int - 노드의 이름을 저장
            - pastName : Node - 해당노드를 호출한 노드를 기록
            - availableNode : ArrayList<Node> - 현재 노드에서 갈 수 있는 노드들
            - weightNode : ArrayList<Float> - 갈 수 있는 노드들의 가중치
            - convertWeight : arrayList<Float> - 1.0 - 가중치
            - visited : boolean - 노드의 방문 여부
            - cost : float - 노드까지 방문하는데 사용된 가중치 들의 합
            - convertCost : float - 노드까지 방문하는데 사용된 (1.0 - 가중치)들의 합

    * 메소드

            + getCoat() : float
            + getNodeName() : int
            + getPastNode() : Node
            + getAvailableNode() : ArrayList<Node>
            + getWeightNode() : ArrayList<Float>
            + getConvertWeight() : ArrayList<Float>
            + getVisited() : boolean
            + getConvertCost() : float
            + setConvertWeight(float) : void
            + setAvailableNode(Node) : void
            + setCost(float) : void
            + setConvertCost(float) :void
            + setPastNode(Node) : void
            + setVisited(boolean) : void
            + setWeightNode(float) : void
            + fininList(int) : boolean - availableNode의 요소 중 인자로 받은 번호를 가진 노드가 있는지 확인


* ### Navi

    - 필드

            - nd : ArrayList<Node> - 이동 가능한 노드들
            - nodeQueue : Queue<Node> - init()과 BFS() 함수의 결과를 저장
    - 메소드

            + getNd() : ArrayList<Node>
            + init(ArrayList<Node>, int) : void - 시작 노드를 nodeQueue에 enqueue
            + DFS(Node) : void - DFS 알고리즘을 수행
            + BFS(int) : void - BFS 알고리즘을 수행
            + SSSP(int) : void - Single Source Shortest Path 알고리즘을 수행
            + printPath(int, int, ArrayList<Node>) : void - 경로와 가중치 합을 수행
---
## 4. 알고리즘 설명
- ### BFS
        1. nodeQueue를 dequeue
        2. dequeue된 노드의 visited의 값을 true로 변경
        3. dequeue된 노드와 직접 연결된 노드 중 visited의 값이 false인 노드들의 cost를 dequeue의 cost + 해당 노드로 이동하는 cost로 변경
        4. 해당 노드의 이름이 목적지 노드였을 경우 알고리즘 종료
        5. 아닐 경우 해당 노드르 nodeQueue에 enqueue
        6. 해당 노드의 이름이 목적지 노드일때까지 1 ~ 5 반복

- ### SSSP
        1. nodeQueue에 dequeue한 값을 model 변수에 대입
        2. movableList에 model을 추가
        3. model을 movableList에서 제거 
        4. model의 visited의 값을 true로 변경
        5. model과 직접 연결된 노드 중 visited가 false인 노드를 탐색
        6. 해당 노드가 movableList에 있을 경우 해당 노드의 convertCost와 (model의 convertCost + 해당 노드로 이동하는 converCost)를 비교하여 후자가 낮을 경우 해당 노드의 pastNode값을 model로 할당하고 cost와 convert노드의 값을 각각 입력
        7. 해당 노드가 movableList에 없을 경우 해당 노드의 pastNode값을 model로 할당하고 cost와 convert노드의 값을 각각 입력
        8. movableList내부의 노드중 convertCost가 가장 낮은 노드를 모델에 입력 
        9. model의 nodeName이 도착지점 노드의 번호와 일치할때까지 3~8 반복
---
## 5. 사용 자료구조, 알고리즘
- BFS

    * 자료구조
        * Queue
        * ArrayList
    * 알고리즘
        * BFS 알고리즘

- Single Source Shortest Path

    * 자료구조
        * Queue
        * ArrayList
    * 알고리즘
        * Dijkstra 알고리즘