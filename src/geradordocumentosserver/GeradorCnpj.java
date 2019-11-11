package geradordocumentosserver;

public class GeradorCnpj implements GeradorDigito{
    @Override
    public String gerar(String doc){
         if(doc.length()!=12)
            throw new RuntimeException("Favor inserir 12 digitos para cnpj");
        
        char primeiroDigito = calcularDigito(doc, 5);
        doc = doc + primeiroDigito;
        char segundoDigito = calcularDigito(doc, 6);
        return doc.concat(String.valueOf(segundoDigito));
    }
    
    private char calcularDigito(String doc,int  peso1){
        Integer total = 0;
        int peso2 = 9;
        char cnpjParte1[] = doc.substring(0, peso1 - 1).toCharArray();
        char cnpjParte2[] = doc.substring(peso1 - 1, doc.length()).toCharArray();
        int valor = 0;
        for (char c : cnpjParte1) {
            valor = c - '0';
            total += valor * peso1;
            peso1--;
        }
        for (char c : cnpjParte2) {
            valor = c - '0';
            total += valor * peso2;
            peso2--;
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
