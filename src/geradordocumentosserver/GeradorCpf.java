package geradordocumentosserver;

public class GeradorCpf implements GeradorDigito{
    
    @Override
    public String gerar(String doc){
        if(doc.length()!=9)
            throw new RuntimeException("Favor inserir 9 digitos para cpf");
        
        char primeiroDigito = calcularDigito(doc, 10);
        doc = doc + primeiroDigito;
        //doc.concat(String.valueOf(primeiroDigito));
        char segundoDigito = calcularDigito(doc, 11);
        return doc.concat(String.valueOf(segundoDigito));
    }
    
    private char calcularDigito(String doc, int peso){
        Integer total = 0;
        int valor = 0;
        char cpf[] = doc.toCharArray();
        for (char c : cpf) {
            valor = c - 48;
            total  = total + ( valor * peso);
            peso--;
        }
        int resto = total % 11;
        if(resto < 2)
            return '0';
        else
            resto = 11 - resto;
            if(resto > 9 || resto < 0)
                throw new RuntimeException("erro na execução! resto valor estranho: " + resto);
            return (char) (resto + '0');
    }
}
