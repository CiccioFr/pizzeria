package cruscalucio.pizzeria;

import cruscalucio.pizzeria.db.Cliente;
import cruscalucio.pizzeria.db.Ordine;
import cruscalucio.pizzeria.db.Pizza;
import cruscalucio.pizzeria.db.PizzaOrdinata;
import cruscalucio.pizzeria.db.connessione.Crud;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

// Creare una applicazione con interfaccia utente da terminale (java.util.Scanner) 
// che permetta di inserire e visualizzare i contenuti delle seguenti tabelle di DataBase:
// - Pizze (o menù) con campi id, nome, ingredienti, prezzo
// - Clienti con i campi id, nome, cognome, indirizzo
// - Ordini con i campi id, data ora id_cliente, ora_consegna
// - PizzeOrdinate coni campi id, id_ordine, id_pizza
/**
 * @author Francesco
 */
public class Pizzeria {

    public static Crud<Pizza> pizza_Crud = new Crud<>(Pizza.class);
    public static Crud<Cliente> cliente_Crud = new Crud<>(Cliente.class);
    public static Crud<Ordine> ordine_Crud = new Crud<>(Ordine.class);
    public static Crud<PizzaOrdinata> pizzaOrdinata_Crud = new Crud<>(PizzaOrdinata.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        List<Pizza> tuttePizze = pizza_Crud.leggiTutti();
//        for (Pizza pizza : tuttePizze) {
//            System.out.println(pizza.getNome());
//        }
        System.out.println("inserisci scelta");
        int nr = scanner.nextInt();
        scanner.nextLine();

        System.out.println("inserisci:"
                + "\n 1 - per Pizze"
                + "\n 2 - per Cliente"
                + "\n 3 - per Ordine"
                + "\n 4 - per Pizze Ordinate");

        switch (nr) {
            case 1:
                System.out.println("Scegli Pizza");
                Pizza pl = pizza_Crud.leggi(nr);
                System.out.println(pl.getNome());
                break;
            case 3:
                SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat ora = new SimpleDateFormat("HH:mm:ss");
                System.out.println(data.format(new Date()));
                System.out.println(ora.format(new Date()));
//                Date data = new Date();
//                data.getTime();
//                System.out.println(data);
//                Ordine ord = new Ordine();
//                ord.setData(data);
                break;
            case 4:
                //ordine
                Crud<Ordine> oCrud = new Crud<>(Ordine.class);
                Ordine o = oCrud.leggi(4);
                System.out.println(o.getData());
                for (PizzaOrdinata po : o.getPizzaOrdinataCollection()) {
                    System.out.println(po.getIdPizza().getNome());
                }
            default:
                throw new AssertionError();
        }
    }
}

//SinpleDateFormat sdf = SinpleDateFormat("YYY-MM-DD HH:mm");
//Date d = sdf.parse(scanner.next());

