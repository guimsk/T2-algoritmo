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

    System.out.println("Escolha açao desejada:");
    System.out.println("1. Exibir todo o índice remissivo(em ordem alfabética);");
    System.out.println("2. Exibir o percentual de stopwords do texto;");
    System.out.println("3. Encontrar a palavra mais frequente, isto é, com maior número de ocorrências;");
    System.out.println("4. Pesquisar palavras (palavra e suas localizaçoes(paginas));");
    System.out.println("5. Listar todo o índice remissivo;");
    System.out.println("6. Encerrar o programa.");

    String  busca = "";
    boolean encontrado = false;
    
    int palavraCount = 0;//contagem de palavras
    int StopWordCount = 0;//contagem de palavras StopWord
    String  selecionado = keyboard.next();//selecionar qual é a escolha do usuario, o resto depende disso
    if (selecionado.equalsIgnoreCase("4"))
    //caso queira somente uma palavra, especificar qual buscar
    {
        System.out.println("Favor, digite a palavra desejada:");
        busca = keyboard.next();
    }
    int nLinha = 0;
    nPagina =0;


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
            //System.out.println("Pagina " + nPagina + ":");
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
                        palavra = "";//removerStopWord da lista
                        break;
                    } 

                } while (true);//pesquisa se é StopWord a palavra maybe
                    
                if (selecionado.equalsIgnoreCase("1")) 
                //caso queira que as palavras sejam adicionadas ordenadamente
                {
                    palavras.addIncreasingOrder(palavra);
                }
                else if (selecionado.equalsIgnoreCase("5"))
                //caso queira que as palavras mantenham suas posiçoes originais
                {
                    palavras.add(palavra);
                }
                if (selecionado.equalsIgnoreCase("4"))
                //caso queira somente uma palavra
                {
                    if (busca.equalsIgnoreCase(palavra))
                    {
                        encontrado = true;
                        palavras.add(palavra);
                    }
                         
                }
                else
                //caso nao importa a ordem, apenas carrega os dados
                {
                    palavras.add(palavra);
                }

         } while (true);
        

    } while (true);
    
   
    if (selecionado.equalsIgnoreCase("1"))
    {
        System.out.println(palavras.toString());
        //chama getnode(objeto) para conseguir o conteudo de dentro do objeto, ou adicionar, manipular no geral por meio de nodo palavra.
        palavras.clear();
    }
    else if (selecionado.equalsIgnoreCase("2"))
    {
        float porcent = StopWordCount*100/palavraCount;
        System.out.println("Total de palavras: "+palavraCount+"; StopWord total: "+StopWordCount+"; Porcentagem de StopWord: "+porcent+"%");
        palavras.clear();
    }
    else if (selecionado.equalsIgnoreCase("3"))
    {   
        palavras.topCount();
        palavras.clear();
    }
    else if (selecionado.equalsIgnoreCase("4"))
    {
        if (encontrado == false)//checar se foi encontrado
        {
            System.out.println("Erro durante busca, palavra nao encontrada.");
        }
        else
        {
            System.out.println(palavras.toString());
            palavras.clear();
        }
        
    }
    else if (selecionado.equalsIgnoreCase("5"))
    {
        //chama getnode(objeto) para conseguir o conteudo de dentro do objeto, ou adicionar, manipular no geral por meio de nodo palavra.
        System.out.println(palavras.toString());
        palavras.clear();
    }
    else if (selecionado.equalsIgnoreCase("6"))
    {
        break;
    }
    else
    {
        System.out.println("Erro na seleçao de escolha, tente novamente com uma escolha valida.");
    }
    

    
} while (true); 
    //System.out.println("====" + palavras + "====");
    arquivo.close();  
         
    }
}

