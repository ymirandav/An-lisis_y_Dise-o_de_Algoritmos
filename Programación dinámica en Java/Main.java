import java.util.*;

public class Main {
    static final int MAX = 10005;      // maximo numero de vértices
    static final int INF = 1<<30;     // definimos un valor grande que represente la distancia infinita inicial
    
    static class Node implements Comparable <Node>{
        int first, second;
        
        Node(int origen, int destino) {
            this.first = origen;
            this.second = destino;
        }
        public int compareTo(Node other){
            if (second > other.second) return 1;
            if (second == other.second) return 0;
            return -1;
        }
    };

    static List<List<Node>> ady = new ArrayList<List<Node>>(); // Lista de adyacencia
    static int distancia[] = new int[MAX];          //distancia[ u ] distancia de vértice inicial a vértice con ID = u
    static boolean visitado[] = new boolean[MAX];   //para vértices visitados
    static PriorityQueue<Node> Q = new PriorityQueue<Node>(); //priority queue propia de Java, usamos el comparador definido para que el de menor valor este en el tope
    static int V;                                   //numero de vertices
    static int previo[] = new int[MAX];             //para la impresion de caminos
    

    static void init() {
        for (int i = 0; i <= V; ++i) {
            distancia[i] = INF;  //inicializamos todas las distancias con valor infinito
            visitado[i] = false; //inicializamos todos los vértices como no visitados
            previo[i] = -1;      //inicializamos el previo del vertice i con -1
        }
    }

    // Paso de relajacion
    static void relajacion( int actual , int adyacente , int peso ){
        /*Si la distancia del origen al vertice actual + peso de su arista es menor a la distancia 
        del origen al vertice adyacente */
        if( distancia[ actual ] + peso < distancia[ adyacente ] ){
            distancia[ adyacente ] = distancia[ actual ] + peso;  //relajamos el vertice actualizando la distancia
            previo[ adyacente ] = actual;                         //a su vez actualizamos el vertice previo
            Q.add( new Node( adyacente , distancia[ adyacente ] ) ); //agregamos adyacente a la cola de prioridad
        }
    }

    //Impresion del camino mas corto desde el vertice inicial y final ingresados
    static void print( int destino ){
        if( previo[ destino ] != -1 )    //si aun poseo un vertice previo
            print( previo[ destino ] );  //recursivamente sigo explorando
        System.out.printf("%d " , destino );        //terminada la recursion imprimo los vertices recorridos
    }

    static void dijkstra( int inicial ){
        init(); //inicializamos nuestros arreglos
        Q.add( new Node( inicial , 0 ) ); //Insertamos el vértice inicial en la Cola de Prioridad
        distancia[ inicial ] = 0;      //Este paso es importante, inicializamos la distancia del inicial como 0
        int actual , adyacente , peso;
        while( !Q.isEmpty() ){                   //Mientras cola no este vacia
            actual = Q.element().first;            //Obtengo de la cola el nodo con menor peso, en un comienzo será el inicial
            Q.remove();                           //Sacamos el elemento de la cola
            if( visitado[ actual ] ) continue; //Si el vértice actual ya fue visitado entonces sigo sacando elementos de la cola
            visitado[ actual ] = true;         //Marco como visitado el vértice actual

            for( int i = 0 ; i < ady.get( actual ).size() ; ++i ){ //reviso sus adyacentes del vertice actual
                adyacente = ady.get( actual ).get( i ).first;   //id del vertice adyacente
                peso = ady.get( actual ).get( i ).second;        //peso de la arista que une actual con adyacente ( actual , adyacente )
                if( !visitado[ adyacente ] ){        //si el vertice adyacente no fue visitado
                    relajacion( actual , adyacente , peso ); //realizamos el paso de relajacion
                }
            }
        }


        System.out.printf( "Distancias mas cortas iniciando en vertice %d\n" , inicial );
        for( int i = 1 ; i <= V ; ++i ){
            System.out.printf("Vertice %d , distancia mas corta = %d\n" , i , distancia[ i ] );
        }

        // System.out.println("\n**************Impresion de camino mas corto**************");
        // System.out.printf("Ingrese vertice destino: ");
        // int destino;
        // destino = sc.nextInt();
        // print( destino );

        System.out.println("\n**************Impresion de camino mas corto**************");
        System.out.println("\nDel vertice 1 al 7");
        print(7);
        System.out.printf("\n");
    }

    public static void main(String[] args) {
        // int E , origen, destino , peso , inicial;
        
        V = 7;
        // E = 10;
        for( int i = 0 ; i <= V ; ++i ) ady.add(new ArrayList<Node>()) ; //inicializamos lista de adyacencia
        
        /* Origen - Destino - Peso
        O D P
        1 2 1
        1 3 3
        1 4 6 
        2 5 9
        3 5 3
        4 6 3
        4 5 2
        5 6 4
        5 7 2
        6 7 2
        */
        //     Origen            Destino,Peso
        ady.get( 1 ).add( new Node( 2 , 1 ) );    //grafo diridigo
        ady.get( 1 ).add( new Node( 3 , 3 ) );    //grafo diridigo
        ady.get( 1 ).add( new Node( 4 , 6 ) );    //grafo diridigo
        ady.get( 2 ).add( new Node( 5 , 9 ) );    //grafo diridigo
        ady.get( 3 ).add( new Node( 5 , 3 ) );    //grafo diridigo
        ady.get( 4 ).add( new Node( 6 , 3 ) );    //grafo diridigo
        ady.get( 4 ).add( new Node( 5 , 2 ) );    //grafo diridigo
        ady.get( 5 ).add( new Node( 6 , 4 ) );    //grafo diridigo
        ady.get( 5 ).add( new Node( 7 , 2 ) );    //grafo diridigo
        ady.get( 6 ).add( new Node( 7 , 2 ) );    //grafo diridigo

        System.out.print("Ingrese el vertice inicial: 1 \n");
        // inicial = sc.nextInt();
        dijkstra( 1 );
    }
}















