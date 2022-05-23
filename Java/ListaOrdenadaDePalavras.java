/**
 * Esta classe guarda as palavra do indice remissivo em ordem alfabetica.
 * @author Isabel H. Manssour
 */

public class ListaOrdenadaDePalavras {

    // Classe interna 
    private class Palavra {
        public String element;
        public Palavra next;
        public int pagina = Main.nPagina; //traz o numero da pagina para a classe
        public int quantidade;//contagem de cada palavra
        public ListaDeOcorrencias listaOcorrencias;   
        public Palavra(String element) {
            this.element = element;
            next = null;
            listaOcorrencias = new ListaDeOcorrencias();//cria uma lista p cada palavra(elemento)

            if (contains(element) == true)//se tem na lista
            {
                Palavra aux = getRef(element); //pega referencia para o nodo da palavra
                aux.quantidade++;//usa o nodo da palavra para add quantidade total
                if (aux.listaOcorrencias.contains(pagina)== false)//caso a pagina nao foi adicionada, adiciona
                {
                    aux.listaOcorrencias.add(pagina);
                }
           }
           else
           {
                quantidade++;//se nao existir na lista, o nodo atual é usado para cópias futuras
                if (listaOcorrencias.contains(pagina)== false)
                {
                    listaOcorrencias.add(pagina);
                }
           }
            
            
            
            
            // System.out.println(listaOcorrencias.size());

           
        }
    }

        
        // Atributos
    private Palavra head;
    private Palavra tail;
    private int count;
        
        // Construtores
        public ListaOrdenadaDePalavras() {//mudei
            head = null;
            tail = null;
            count = 0;
        }
	// metodo add para adicionar uma palavra na lista
    public void add (String element)  {
         // O(1)
         if (contains(element))//so p nn faze nada caso ja tiver elemento
        {
            Palavra n = new Palavra(element);
       }
       else{
        Palavra n = new Palavra(element);
        if (head == null) {
            head = n;
        } else {
            tail.next = n;
        }
        tail = n;
        count++;
        }
    } 
     
        // metodo toString
        public String toString() { // O(n)
            StringBuilder s = new StringBuilder();
    
            Palavra aux = head;
    
            while (aux != null) {
                s.append(aux.element.toString());
                s.append(aux.listaOcorrencias.toString());
                s.append("\n");
                aux = aux.next;
            }
    
            return s.toString();
        }

        
        // demais metodos necessarios
        public void topCount()
    {
        int top =0;
        String campeao = "k";
        
        Palavra aux = head;
        for(int i=0; i<count; i++) {
            if (aux.element.equalsIgnoreCase(""))
            //caso for "" ignora, devido a substituição dos StopWord
            {

            }
            else if (aux.quantidade>top)
            //ve qual é a maior quantidade e atualiza campeao, e sua quantidade
            {
                campeao = aux.element;
                top = aux.quantidade;
            }
            aux = aux.next;
        }
        System.out.println("Palavra: "+campeao+"; Repetiçoes: "+top);

    }
        /**
     * Retorna true se a lista nao contem elementos.
     * @return true se a lista nao contem elementos
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Retorna o numero de elementos da lista.
     * @return o numero de elementos da lista
     */
    public int size() {
        return count;
    }

    /**
     * Esvazia a lista
     */
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }    

    /**
     * Retorna o elemento de uma determinada posicao da lista.
     * @param index a posição da lista
     * @return o elemento da posicao especificada
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public String get(int index) { // O(n)
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        if (index == count-1)
            return tail.element;
        
            Palavra aux = head;
        int c = 0;
        while (c < index) {
            aux = aux.next;
            c++;
        }
        return (aux.element);
        
    }
    
    ////////////////////////////////////////////////////////////////
    
    /**
     * Insere um elemento em uma determinada posicao da lista.
     * @param index a posicao da lista onde o elemento sera inserido
     * @param element elemento a ser inserido
     * @throws IndexOutOfBoundsException se (index < 0 || index > size())
     */
    public void add(int index, String element) { // O(n)
        // Primeiro verifica se index eh valido
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException(); // erro
        
        // Depois cria o nodo e incrementa count
        Palavra n = new Palavra(element);
        count++;
        
        // "Encadear" o nodo criado na lista
        if (index == 0) { // Insercao no inicio
            if (head == null) { // se a lista estava vazia
                tail = n;
            }
            else {
                n.next = head;
            }
            head = n;
        }
        else if (index == count) { // Insercao no fim
            tail.next = n;
            tail = n;
        }
        else { // Insercao no meio
            Palavra ant = head;
            for (int i=0; i<index-1; i++) 
                ant = ant.next;
            n.next = ant.next;
            ant.next = n;
        }
    }

    /**
     * Substitui o elemento armanzenado em uma determinada posicao da lista pelo
     * elemento indicado.
     * @param index a posicao da lista
     * @param element o elemento a ser armazenado na lista
     * @return o elemento armazenado anteriormente na posicao da lista
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public String set(int index, String element) { // O(n)
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        if (index == count-1) { //se for a ultima posicao
            String num = tail.element; // guarda o elemento da posicao index
            tail.element = element; // troca o element da posicao index
            return num; // retorna o elemento que foi substituido
        }
        Palavra aux = head;
        for (int i=0; i<index; i++) 
            aux = aux.next;
        String num = aux.element;
        aux.element = element;
        return num;
    }

    /**
     * Remove a primeira ocorrencia do elemento na lista, se estiver presente.
     * @param element o elemento a ser removido
     * @return true se a lista contem o elemento especificado
     */
    public boolean remove(String element) { // O(n)
        if (count == 0) // se a lista estiver vazia
            return false;
        
        if (head.element.equals(element)) { // se remocao do primeiro
            head = head.next;
            if (count==1) { // se tinha apenas 1 elemento na lista
                tail = null;
            }
            count--; // atualiza o count
            return true;
        }
        
        Palavra aux = head.next;
        Palavra ant = head;
        while (aux != null) {
            if (aux.element.equals(element)) { // se achou o elemento a ser removido
                if (aux == tail) { // se remocao do ultimo
                    tail = ant;
                    tail.next = null;
                }
                else { // se remocao do meio
                    ant.next = aux.next;
                }
                count--;
                return true;
            }
            aux = aux.next;
            ant = ant.next;
        }
        return false;
    }

    /**
     * Remove o elemento de uma determinada posicao da lista.
     * @param index a posicao da lista
     * @return o elemento que foi removido da lista
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public String removeByIndex(int index) {
        // Primeiro verifica se index eh valido
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException(); //erro
        
        // Se remocao do primeiro elemento da lista
        if (index == 0) {
            String elem = head.element;
            head = head.next;
            if (count==1) // se tinha apenas 1 elemento na lista
                tail = null;
            count--; // atualiza o contador
            return elem;
        }
        
        // Se remocao do ultimo ou do meio
        Palavra aux = head;
        Palavra ant = null;
        for (int i=0; i<index; i++) {
            ant = aux;
            aux = aux.next;
        }
        if (aux==tail) { // se remocao do ultimo
            tail = ant;
            tail.next = null;
        }
        else { // remocao do meio
            ant.next = aux.next;
        }
        count--;
        return aux.element;
    }

    /**
     * Retorna o indice da primeira ocorrencia do elemento na lista, ou -1 se a
     * lista nao contem o elemento.
     * @param element o elemento a ser buscado
     * @return o indice da primeira ocorrencia do elemento na lista, ou -1 se a
     * lista nao contem o elemento
     */
    public int indexOf(String element) {
        Palavra aux = head;
        for(int i=0; i<count; i++) {
            if (aux.element.equals(element))
                return i;
            aux = aux.next;
        }
        return -1;
    }

    /**
     * Retorna true se a lista contem o elemento especificado.
     * @param element o elemento a ser testado
     * @return true se a lista contem o elemento especificado
     */
    public boolean contains(String element) {
        Palavra aux = head;
        for (int i = 0; i < count; i++) {
            if (aux.element.equals(element)) {
                return true;
            }
            aux = aux.next;
        }
        return false;
    }

    public void addIncreasingOrder(String element)
    {
        if (contains(element))//so p nn faze nada caso ja tiver elemento
        {
            Palavra n = new Palavra(element);
       }
       else{
        int i;
        Palavra aux = head;
        for(i=0; i<count; i++) 
        {
            if (aux.element.compareTo(element) > 0)
                break;
            aux = aux.next;
        }
           add(i,element);
       }
    }

    public int countOccurrences(String element)
    {
        int nOccurences=0;
        
        Palavra aux = head;
        for(int i=0; i<count; i++) {
            if (aux.element.equals(element))
                nOccurences++;
            aux = aux.next;
        }
        return nOccurences;
    }
   
      public Palavra EncontraAnterior(Palavra ptr) {
        if (ptr == head) // o 1o. nao tem anterior
          return null;
          Palavra aux;
        aux = head;
        while (aux != null) {
          if (aux.next == ptr)
            break;
          aux = aux.next;
        }
        return aux;
      }
      // Codigo em Java
      public Palavra getRef(String element) {//busca o node
        Palavra aux = head;
              for (int i = 0; i < count; i++) {
                  if (aux.element.equals(element)) {
                      return aux;
                  }
                  aux = aux.next;
              }
              return null;
          }
      
    
    
}
