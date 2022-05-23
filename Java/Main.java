import java.util.Scanner;
/**
 * Classe que inicializa a execução da aplicacao.
 * @author Isabel H. Manssour
 */

public class Main {
    static Scanner keyboard = new Scanner (System.in); //usar teclado
    public static int nPagina;//declarar variavel p poder puxar
    

    public static void main(String[] args) {
        ListaOrdenadaDePalavras palavras = new ListaOrdenadaDePalavras();
        
        
        
    
  

    ArquivoTexto arquivo = new ArquivoTexto(); // objeto que gerencia o arquivo
    ArquivoTexto arquivoStopWords = new ArquivoTexto(); // objeto que gerencia o arquivo2
    LinhaTexto linha = new LinhaTexto(); // objeto que gerencia uma linha
    String l;

    
do{

    System.out.println("Escolha açao desejada:");por meio de nodo palavra
    System.out.println("1. Exibir todo o índice remissivo(em ordem alfabética);");
    System.out.println("2. Exibir o percentual de stopwords do texto;");
    System.out.println("3. Encontrar a palavra mais frequente, isto é, com maior número de ocorrências;");
    System.out.println("4. Pesquisar palavras (palavra e suas localizações(paginas));");
    System.out.println("5. Listar todo o índice remissivo;");
    System.out.println("6. Encerrar o programa.");

    int maior = 0;
    String campeao="kk";
    int quantia = 0;
    
    int palavraCount = 0;//contagem de palavras
    int StopWordCount = 0;//contagem de palavras StopWord
    int  selecionado = keyboard.nextInt();
    int nLinha = 0;
    nPagina =0;
    String numeros = "";

    arquivo.open("alice.txt"); /////le o texto e faz
    do  // laco que passa em cada linha do arquivo
    {
        l = arquivo.getNextLine();
        if (l==null) // acabou o arquivo?
           break;
        nLinha++; // conta mais uma linha lida do arquivo
        if (nLinha == 40) // chegou ao fim da pagina?
        {
            nLinha = 0;
            nPagina++;
            System.out.println("Pagina " + nPagina + ":");
        }
        // System.out.println("Linha " + nLinha + ":");
                
        linha.setLine(l); // define o texto da linha

       
        
        
        do // laco que passa em cada palavra de uma linha
        {
            String palavra = linha.getNextWord(); // obtem a proxima palavra da linha
            palavraCount++;
            if (palavra == null)// acabou a linha
            {
                break;//////////////fazer metodo para criar lista com a palavra
            }
            else if (palavra!=null)//se aqui não tem a palavra
            {
               
                //adicionar pagina
                arquivoStopWords.open("StopWords-EN.txt"); /////le o texto e faz
                do // laco que passa em cada palavra de uma linha
                {
                    String palavraStop = arquivoStopWords.getNextLine();//salva as palavras que precisam ser apagadas
                    if (palavraStop == null)// acabou a linha
                    {
                    break;
                    }
                    else if  (palavra.equalsIgnoreCase(palavraStop))// obtem a proxima palavra da linha
                    {
                        StopWordCount++;
                        palavra = " ";//removerStopWord da lista
                        break;
                    } 
                } while (true);//pesquisa se é StopWord a palavra maybe
                if (palavra!= ""&palavra!= " ")
                {
                    if (palavras.contains(palavra))
                    {
                            palavras.indexOf(palavra);
                
                    }
                    else
                    {
                        if (selecionado == 1)
                        {
                            palavras.addIncreasingOrder(palavra);
                        }
                        else if (selecionado == 5)
                        {
                            palavras.add(palavra);
                        }
                        else
                        {
                            palavras.add(palavra);
                        }
                   
                    }
                }
                

            }
            quantia = palavras.countOccurrences(palavra);
            if (quantia > maior)
            {
                System.out.println(palavras.indexOf(palavra));
                
                maior = quantia;
                campeao = palavra;
                System.out.println("campeao: "+campeao);
                System.out.println("repetiçoes: "+maior);
            
            }
         } while (true);
        

    } while (true);
    
   
    if (selecionado  == 1)
    {
        System.out.println(palavras);
        //chama getnode(objeto) para conseguir o conteudo de dentro do objeto, ou adicionar, manipular no geral por meio de nodo palavra.
        palavras.clear();
    }
    else if (selecionado  == 2)
    {
        float porcent = StopWordCount*100/palavraCount;
        System.out.println("palavra total: "+palavraCount+"StopWord total: "+StopWordCount+"porcentagem "+porcent+"%");
    }
    else if (selecionado  == 3)
    {   
        System.out.println(campeao);
    }
    else if (selecionado  == 4)
    {
       String pergunta =keyboard.next();
       if (palavras.contains(pergunta)==true)
       {
            int from = palavras.indexOf(pergunta);//pega nos index, onde começa a primeira copia
            int total = palavras.countOccurrences(pergunta);//ve quantas copias tem
            int index = 0;//contagem inicial
            
            do{
                
                System.out.println(palavras.get(from+index)+index+" de "+total);

                if (index==total-1)
                {
                    break;
                }
                index++;

            }while(true);
            
            
       }
        //na lista vai ter repetições das palavra, pode ser usado cada repetição com sua pagina

    }
    else if (selecionado == 5)
    {
        System.out.println(palavras);
        palavras.clear();
    }
    else if (selecionado == 6)
    {
        break;
    }
    

    
} while (true); 
    //System.out.println("====" + palavras + "====");
    arquivo.close();  
         
    }
}

