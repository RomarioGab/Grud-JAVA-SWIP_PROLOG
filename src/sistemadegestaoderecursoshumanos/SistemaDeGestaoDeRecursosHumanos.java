/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadegestaoderecursoshumanos;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import org.jpl7.Query;

/**
 *
 * @author Gabarito
 */
public class SistemaDeGestaoDeRecursosHumanos {
    
    public static int Nif_func,  D,E,F,Nif_dep ,D1,M1,A1,D2,M2,A2 ;
    public static String opcao,Nome,Apelido,Morada,Telefone,Sexo,Email,Nome_Dep,Nome_der,Tipo_li,Venc,Dat_C,d,Nome1;
    
    public static Scanner ler =  new Scanner(System.in);
    
    public static final String BLUE   = "\033[0;34m";  // BLUE
    public static final String GREEN  = "\033[0;32m";  // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String RED    = "\033[0;31m";  // RED

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       conexao();
       
       switchmenuInicial();
       
       salvarNaBaseConheciumento();
       
    }
    
public static void  conexao(){
    
 String caregabaseconhcimento = "consult('RegrasRecursosHumanos')";
        Query conexao = new Query(caregabaseconhcimento);
        System.out.print("Conexao com a Base de Conhecimento "); 
        if(conexao.hasSolution()){
           System.out.println(GREEN+"VERIFICADO");  
           Query consultar = new Query("carregar");
    consultar.hasSolution();
        }else{
            System.out.println(RED+"NAO VEREFICADO");
        }
     }
public static void salvarNaBaseConheciumento(){
  Query salvar = new Query("salvar");
salvar.nextSolution();  
}

public static void cadastrarFuncionario(){
    limparTela();
    cabeclhoCadastroFuncionario();
    do{
    System.out.println(YELLOW+"\t\t\tDepartamento de trabalho do funcionario...\n");
    System.out.println("\t\t\t1.Markiting        4.Venda ");
    System.out.println("\t\t\t2.Adminstrativo    5.Producao ");
    System.out.println("\t\t\t4.Comercial        6.Recursos Humanos \n");  
         escolha();
    switch(opcao){
        case "1":  Nome_Dep = "markiting";        break;
        case "2":  Nome_Dep = "adminstrativo";    break;
        case "3":  Nome_Dep = "comercial";        break;
        case "4":  Nome_Dep = "venda";            break;
        case "5":  Nome_Dep = "producao";         break;
        case "6":  Nome_Dep = "recursos_humanos"; break;       
    default:System.out.println(YELLOW+"Please...Escolha uns dos menus solicitado.");            
    }
    }while(!"1".equals(opcao) && !"2".equals(opcao) && !"3".equals(opcao) && !"4".equals(opcao) && !"5".equals(opcao) && !"6".equals(opcao)); 
    limparTela();
    cabeclhoCadastroFuncionario();
    gravarDadosfunc(Nome_Dep);
    
}
public static void gravarDadosfunc(String Nome_Dep){
    
    System.out.print("\nNº B.I: ");   Nif_func = ler.nextInt();
    System.out.print("Nome: ");     Nome = ler.next().toLowerCase();
    System.out.print("Apelido: ");  Apelido  = ler.next().toLowerCase();
    System.out.print("Data nascimento: ");obterData();
    System.out.print("Morada: ");   Morada = ler.next().toLowerCase();
    System.out.print("Telefone: "); Telefone = ler.next().toLowerCase();
    System.out.print("Sexo: ");     Sexo = ler.next().toLowerCase();
    System.out.print("Email: ");    Email = ler.next().toLowerCase();
    System.out.print("Salario: ");  Venc = ler.next().toLowerCase();
    Nome1=Nome;
    Query registarfuncionario = new Query("addfuncionario("+Nome_Dep+","+Nif_func+","+Nome+","+Apelido+",data_nasc("+D+","+E+","+F+"),"+Morada+","+Telefone+","+Sexo+","+Email+","+Venc+")");
    registarfuncionario.nextSolution();
    
    obterDadosDependente(Nif_func,Nome1);

}
public static void obterDadosDependente(int Nif_func, String Nome1){
    limparTela();
    cabeclhoCadastroFuncionario();
    System.out.print(YELLOW+"Funcionario "+Nome1+" possui dependentes? S(sim)/N(Qualquer outro caratere): ");
   
    d = ler.next();
    while(d.equals("S") || d.equals("s")){
    System.out.print("Nº B.I: ");   Nif_dep = ler.nextInt();
    System.out.print("Nome: ");     Nome = ler.next().toLowerCase();
    System.out.print("Apelido: ");  Apelido  = ler.next().toLowerCase();
    System.out.print("Data nascimento: ");obterData();
    System.out.print("Morada: ");   Morada = ler.next().toLowerCase();
    System.out.print("Telefone: "); Telefone = ler.next().toLowerCase();
    System.out.print("Sexo: ");     Sexo = ler.next().toLowerCase();
    System.out.print("Email: ");    Email = ler.next().toLowerCase();
    
    Query registardependentes = new Query("adddependente("+Nif_func+","+Nif_dep+","+Nome+","+Apelido+",data_nasc("+D+","+E+","+F+"),"+Morada+","+Telefone+","+Sexo+","+Email+")");
    registardependentes.nextSolution(); 
    limparTela();
    cabeclhoCadastroFuncionario();
    System.out.println(YELLOW+"Entrar com outro dependete do  funcionario "+Nome1+"? S(sim)/Nao(Qualquer outro caratere): ");
    d = ler.next();
    
    }
 
}
public static void registarDadosLicenca(int Nif_func){
      do{
    System.out.println("\n"+YELLOW+"TIPO DE LICENÇA...");
    System.out.println("\n1.Licenca de maternidade  5.licença de casamento");
    System.out.println("2.Licenca de paternidade  6.Licenca para o servico militar obrigatorio");
    System.out.println("3.Licenca de obito        7.Licenca nao remunerada");  
    System.out.println("4.Licenca de médica       8.Outros tipos de licencas que nao vem ao caso\n");  
         escolha();
    switch(opcao){
        case "1":  Tipo_li = "de_maternidade";                                  break;
        case "2":  Tipo_li = "de_paternidade";                                  break;
        case "3":  Tipo_li = "de_obito";                                        break;
        case "4":  Tipo_li = "de_medica";                                       break;
        case "5":  Tipo_li = "de_casamento";                                    break;
        case "6":  Tipo_li = "para_comprimento_de_servico_militar obrigatorio";   break; 
        case "7":  Tipo_li = "sem_remunerada";                       break;
        case "8":  Tipo_li = "outros_tipos_de_licencas_que_nao_vem_ao_caso"; break;
    default:System.out.println("Please...Escolha uns dos menus solicitado.");            
    }
    }while(!"1".equals(opcao) && !"2".equals(opcao) && !"3".equals(opcao) && !"4".equals(opcao) && !"5".equals(opcao) && !"6".equals(opcao) && !"7".equals(opcao) && !"8".equals(opcao)); 
    
    System.out.print("Data de inicio: ");   obterData(); D1=D; M1=E; A1=F;
    System.out.print("Data de fim: ");      obterData(); D2=D; M2=E; A2=F;
    
    Query reg_li = new Query("addlicenca("+Nif_func+","+Tipo_li+",data_inicio("+D1+","+M1+","+A1+"),data_fim("+D2+","+M2+","+A2+"))");
    reg_li.nextSolution();
    
}
public static void obterData(){  
    System.out.print("DD(dia): ");    D = ler.nextInt();
    System.out.print("\t\tMM(mês): ");    E = ler.nextInt();
    System.out.print("\t\tAAAA(ano): ");  F = ler.nextInt();   
}

public static void menuIniucial(){
    limparTela();
    System.out.println("=================================================================================================");
    System.out.println(GREEN+"\t\t\t>>>>>>>>>>>>>>>   LOGIN   <<<<<<<<<<<<<<<");
    System.out.println("=================================================================================================\n");
    
    System.out.println("\t\t\t    1 -> Super Administrador");
    System.out.println("\t\t\t    2 -> Usuario normal");
    System.out.println("\t\t\t    0 -> Sair\n");
}
public static void menuAdministrador(){
    limparTela();
    System.out.println("=================================================================================================");
    System.out.println(GREEN+"\t\t\t>>>>>>>>>>>>>>>   SUPER ADMINISTRADOR    <<<<<<<<<<<<<<<");
    System.out.println("=================================================================================================\n");
    
    System.out.println("\t\t\t 1 -> Efetuar operacoes.");
    System.out.println("\t\t\t 2 -> Gerir usuario do sistema.");
    System.out.println("\t\t\t 0 -> Voltar.\n");
}
public static void menuOperacoes(){
    limparTela();
    System.out.println("=================================================================================================");
    System.out.println(GREEN+"\t\t>>>>>>>>>>>>>>>   MENU INICIAL(Operacoes do sistema)    <<<<<<<<<<<<<<<");
    System.out.println("=================================================================================================\n");
    
    System.out.println("\t\t\t\t 1 -> Emitir documentos");
    System.out.println("\t\t\t\t 2 -> Cadastrar Funcionario");
    System.out.println("\t\t\t\t 3 -> Listar funcionario");
    System.out.println("\t\t\t\t 4 -> Listar dependentes");
    System.out.println("\t\t\t\t 5 -> Pesquisar por funcionarios");
    System.out.println("\t\t\t\t 6 -> Eliminar funcionarios");
    System.out.println("\t\t\t\t 0 -> Sair\n");
}
public static void menuGerirusuario(){
    limparTela();
    System.out.println("=================================================================================================");
    System.out.println(GREEN+"\t\t\t>>>>>>>>>>>>>>>   GERIR USUARIOS DO SISTEMA    <<<<<<<<<<<<<<<");
    System.out.println("=================================================================================================\n");
    
    System.out.println("\t\t\t     1 -> Cadastrar usuario");
    System.out.println("\t\t\t     2 -> Listar todos os usuario");
    System.out.println("\t\t\t     3 -> Pesquisar usuario");
    System.out.println("\t\t\t     4 -> Excluir usuario");
    System.out.println("\t\t\t     0 -> Voltar.\n");
}
public static void emissaoDeDocumentos(){ 
    limparTela();
    System.out.println("=================================================================================================");
    System.out.println(GREEN+"\t\t\t>>>>>>>>>>>>>>>   Emitir Documentos    <<<<<<<<<<<<<<<");
    System.out.println("=================================================================================================\n");
    
    System.out.println("\t\t\t 1 -> Emitir declaraçao de vencimento");
    System.out.println("\t\t\t 2 -> Emitir licenca de trabalho");
    System.out.println("\t\t\t 0 -> Sair\n");
 
}

public static void switchmenuInicial(){
    do{
        menuIniucial();
        escolha();
    switch(opcao){
        case "1":
           switchmenuAdministrador();
            break;
            
        case "2":
            switchmenuOperacoes();
            break;
        case "0":
            System.out.println("Saindo...");
            break;
            
        default:System.out.println("Please...Escolha uns dos menus solicitado.");                
    }
    }while(!"0".equals(opcao)); 
}
public static void switchmenuAdministrador(){
        do{
        menuAdministrador();
        escolha();
    switch(opcao){
        case "1":
            switchmenuOperacoes();
            break;
        case "2":
            switchmenuGerirusuario();
            break;
        case "0":
            switchmenuInicial();
            break;
 
        default:System.out.println("Please...Escolha uns dos menus solicitado.");                    
    }
    }while(!"0".equals(opcao)); 
    
}
public static void switchmenuOperacoes(){
        do{
        menuOperacoes();
        escolha();
    switch(opcao){
        case "1":
            switchemitirDocumentos();
            break;
            
        case "2":
             cadastrarFuncionario();
          
            break;
            
        case "3":
             listaFuncionarios();
           
            break;
            
        case "4":
             listarDependentes();
            break;
        case "5":
             pesquisarFuncionarios();
            break;
            
        case "6":
              eliminarFuncionario();
        
            break;

        case "0":
            switchmenuInicial();
            break;
            
        default:System.out.println("Please...Escolha uns dos menus solicitado.");    
                
    }
    }while(!"0".equals(opcao));   
}
public static void switchmenuGerirusuario(){
    do{
        menuGerirusuario();
        escolha();
    switch(opcao){
        case "1":
           
            break;    
        case "2":
             
            break;
         case "3":
             
            break;
        case "4":
             
            break;
        case "0":
           switchmenuAdministrador(); 
            break;
            
        default:mensagem();   
                
    }
    }while(!"0".equals(opcao)); 
    
   }
public static void switchemitirDocumentos(){
    do{
        limparTela(); 
        emissaoDeDocumentos();
        escolha();
    switch(opcao){
        case "1":
             emissaoDlecaracaoVencimento();
            break;    
        case "2":
             emissaoDlecaracaoLiceca();
            break;
         
        case "0":
           switchmenuAdministrador(); 
            break;
            
        default:mensagem();   
                
    }
    }while(!"0".equals(opcao)); 
    
   }

public static void escolha(){ 
    System.out.print("Escolha menu: ");
    opcao = ler.next();
}
public static void mensagem(){
  System.out.println("Please...Escolha uns dos menus solicitado.");   
}
public static void limparTela(){
        try{
            Robot robot = new Robot();
            robot.setAutoDelay(10);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_L);
        }catch(AWTException e){
            System.out.println(e.getMessage());
     }
    }
public static String getDateTime() {
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();
    return dateFormat.format(date);
}

public static void cabeclhoCadastroFuncionario(){
    
System.out.println("=================================================================================================");
System.out.println(YELLOW+"\t\t\t>>>>>>>>>>>>>>>   Cadastrando Funcionario...    <<<<<<<<<<<<<<<");
System.out.println("=================================================================================================\n");    

}
public static void cabeclhoCadastroLivenca(){
System.out.println("=================================================================================================");
System.out.println(YELLOW+"\t\t\t>>>>>>>>>>>>>>>    Registar Licenca   <<<<<<<<<<<<<<<");
System.out.println("=================================================================================================\n");
}

public static void pesquisarFuncionarios(){
    limparTela();
System.out.println("=================================================================================================");
System.out.println(YELLOW+"\t\t\t>>>>>>>>>>>>>>>   Pesquisar Funcionario...    <<<<<<<<<<<<<<<");
System.out.println("=================================================================================================\n");    
    System.out.print("Entre com nif: "); Nif_func=ler.nextInt();
    Query listarFuncionario = new Query("funcionario(Nome_Dep,"+Nif_func+",Nome,Apelido,data_nasc(D,E,F),Morada,Telefone,Sexo,Email,Venc)");
    System.out.println(YELLOW+"Resultado da pesquisa...");
    while(listarFuncionario.hasMoreSolutions()){
    HashMap lF = (java.util.HashMap) listarFuncionario.nextSolution();
   System.out.println(YELLOW+"Nº B.I\tNome\t\tApelido\t      DD/MM/AAA\t  Morada\t   Telefone\t Sexo\t Email\t Deparatamento de trabalho \tVencimento");
   System.out.printf("%s %-15s %-15s %s/%s/%-5s %-15s %-15s %-15s %-15s %-15s %-1s%n",lF.get("Nif_func"),lF.get("Nome"),lF.get("Apelido"),lF.get("D"),lF.get("E"),lF.get("F"),lF.get("Morada"),lF.get("Telefone"),lF.get("Sexo"),lF.get("Email"),lF.get("Nome_Dep"),lF.get("Venc")); 
}
    Query listarDependentes = new Query("dependente("+Nif_func+",Nif_dep,Nome,Apelido,data_nasc(D,E,F),Morada,Telefone,Sexo,Email)");
    System.out.println("\nDependentes referente ao funcionario pesquisado... ");
    System.out.println(YELLOW+"Nº B.I\tNome\t\tApelido\t\tDD/MM/AAA\t Morada\t\tTelefone\t Sexo\t\tEmail");
    while(listarDependentes.hasMoreSolutions()){
    HashMap lD = (java.util.HashMap) listarDependentes.nextSolution();
    
 System.out.printf("%s  %-15s %-15s %s/%s/%-10s %-15s %-15s %-15s %-1s%n",lD.get("Nif_dep"),lD.get("Nome"),lD.get("Apelido"),lD.get("D"),lD.get("E"),lD.get("F"),lD.get("Morada"),lD.get("Telefone"),lD.get("Sexo"),lD.get("Email")); 
    }
      limparBufferTeclado();
} 
public static void listaFuncionarios(){
    limparTela();
    Query listarFuncionario = new Query("funcionario(Nome_Dep,Nif_func,Nome,Apelido,data_nasc(D,E,F),Morada,Telefone,Sexo,Email,Venc)");
    System.out.println(YELLOW+"Nº B.I\tNome\t\tApelido\t      DD/MM/AAA\t  Morada\t   Telefone\t Sexo\t Email\t Deparatamento de trabalho \tVencimento");
   
    while(listarFuncionario.hasMoreSolutions()){
    HashMap lF = (java.util.HashMap) listarFuncionario.nextSolution();   
 System.out.printf("%s %-15s %-15s %s/%s/%-5s %-15s %-15s %-15s %-15s %-15s %-1s%n",lF.get("Nif_func"),lF.get("Nome"),lF.get("Apelido"),lF.get("D"),lF.get("E"),lF.get("F"),lF.get("Morada"),lF.get("Telefone"),lF.get("Sexo"),lF.get("Email"),lF.get("Nome_Dep"),lF.get("Venc"));    
 
    }
   limparBufferTeclado();
}
public static void listarDependentes(){
    limparTela();
    Query listarDependentes = new Query("dependente(_,Nif_dep,Nome,Apelido,data_nasc(D,E,F),Morada,Telefone,Sexo,Email)");
    System.out.println(YELLOW+"Nº B.I\tNome\t\tApelido\t\tDD/MM/AAA\t Morada\t\tTelefone\t Sexo\t\tEmail");
    while(listarDependentes.hasMoreSolutions()){
    HashMap lD = (java.util.HashMap) listarDependentes.nextSolution();
   // System.out.println(lD.get("Nif_dep")+"\t"+lD.get("Nome")+"\t"+lD.get("Apelido")+"\t"+lD.get("D")+"/"+lD.get("E")+"/"+lD.get("F")+"\t"+lD.get("Morada")+"\t"+lD.get("Telefone")+"\t"+lD.get("Sexo")+"\t"+lD.get("Email"));    
      System.out.printf("%s  %-15s %-15s %s/%s/%-10s %-15s %-15s %-15s %-1s%n",lD.get("Nif_dep"),lD.get("Nome"),lD.get("Apelido"),lD.get("D"),lD.get("E"),lD.get("F"),lD.get("Morada"),lD.get("Telefone"),lD.get("Sexo"),lD.get("Email")); 
    }
      limparBufferTeclado();
}
public static void eliminarFuncionario(){
    limparTela();
System.out.println("=================================================================================================");
System.out.println(YELLOW+"\t\t\t>>>>>>>>>>>>>>>   Eliminar Funcionario...    <<<<<<<<<<<<<<<");
System.out.println("=================================================================================================\n");    
    System.out.println("\n"+YELLOW+"Atencao! Ao eliminar funcionario voce estara eliminando todo o os seus registos inclusive dos seus dependentes tambem.");
    System.out.print("Entre com nº b.i do funcionari que pretendes eliminar: "); Nif_func=ler.nextInt();
    Query eliminarFuncionario = new Query("removefuncionario(Nome_Dep,"+Nif_func+",Nome,Apelido,data_nasc(D,E,F),Morada,Telefone,Sexo,Email,Venc)");
     eliminarFuncionario.hasMoreSolutions();
     boolean L;
     do{
    Query eliminarDependentes = new Query("removedependente("+Nif_func+",Nif_dep,Nome,Apelido,data_nasc(D,E,F),Morada,Telefone,Sexo,Email)");
    eliminarDependentes.hasMoreSolutions();
    L= eliminarDependentes.hasMoreSolutions();
     }while(L!= false);
      limparTela();  
     System.out.println("\n\n\n\n\n\n\n"+RED+"\t\t\t\tFUNCIONARIO ELIMINADO COM SUCESSO\n\n\n\n\n");
     limparBufferTeclado();
     
}

public static void emissaoDlecaracaoVencimento(){
    limparTela();
    System.out.println("=================================================================================================");
System.out.println(YELLOW+"\t\t>>>>>>>>>>>>>>>    Emitindo declaraçaõ de Vencimento...   <<<<<<<<<<<<<<<");
System.out.println("=================================================================================================\n");
    System.out.print("Por favor! Entre com o nº B.I do Remetente: "); Nif_func=ler.nextInt(); limparTela();
    Query listarFuncionario = new Query("funcionario(Nome_Dep,"+Nif_func+",Nome,Apelido,data_nasc(D,E,F),Morada,Telefone,Sexo,Email,Venc)");
    while(listarFuncionario.hasMoreSolutions()){
    HashMap lF = (java.util.HashMap) listarFuncionario.nextSolution();
    System.out.println("\n\n\n\n\n\n\t\t\t\t\t    DECLARAÇAÕ DE VENCIMENTO\n");
    System.out.println("     Para os devidos efeitos se declara que o(a) Sr(a). "+lF.get("Nome")+" Portador de B.I: "+Nif_func+"  é trabalhador\n"
                      +"do departamento "+lF.get("Nome_Dep")+" desta Empresa com situaçaõ contratual de Tempo indeterminado.\n\n"
                      +"     Mais informamos, que o referido trabalhador aufere um vencimento mensal iliquido de "+lF.get("Venc")+" Escudos \n\n"
                      +"\n\n\t\t\t\t\tEmitido em: "+getDateTime()+".");

    }
     limparBufferTeclado();
}
public static void emissaoDlecaracaoLiceca(){
    limparTela();
    System.out.println("=================================================================================================");
System.out.println(YELLOW+"\t\t>>>>>>>>>>>>>>>    Emitindo licença de trabalho...   <<<<<<<<<<<<<<<");
System.out.println("=================================================================================================\n");
    
     System.out.print("Por favor! Entre com o Nif do Remetente: "); Nif_func=ler.nextInt();
     registarDadosLicenca(Nif_func); limparTela();
     Query listarFuncionario = new Query("funcionario(Nome_Dep,"+Nif_func+",Nome,Apelido,data_nasc(D,E,F),Morada,Telefone,Sexo,Email,Venc)");
    while(listarFuncionario.hasMoreSolutions()){
    HashMap lF = (java.util.HashMap) listarFuncionario.nextSolution();
    System.out.println("\n\n\n\t\t\t\t\t    NOTA INFORMATIVA\n\n"
            +"\t\t\t\t\tLicenças de "+Tipo_li+".\n\n" 
+"    Com vista à uniformização de entendimentos procede se aos seguintes esclarecimentos sobre\n"
+"a  concessão  de licenças "+Tipo_li+", do trabalhador "+lF.get("Nome")+" do departamento\n" 
 +lF.get("Nome_Dep")+",com contrato de trabalho tempo indeterminado.\n\n"
+"    Nos termos do Despacho n.º XXX/YYYY, de X de MM, publicado no Boletin Oficial  desta Empresa\n"
+"de DD/MM/AAAA foi  subdelega da competência do Senhora  Diretor Geral do deparatmento "+lF.get("Nome_Dep")+".\n\n" 
+"    Os requerimentos  relativos  à  concessão  de  licenças  do trabalhador "+lF.get("Nome")+", são válido \n"
+"apartir dadata "+D1+"/"+M1+"/"+A1+" até "+D2+"/"+M2+"/"+A2+".\n\n"
            + "\n\n\t\t\t\t    Emitido em: "
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
 
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        +".");
    }
    ler.next();
}

public static void limparBufferTeclado(){
     System.out.println("\n\n"+YELLOW+"Entre com qualquer outro caracter seguida da tcleca enter para continuar...");
     ler.next();  
   }
}
