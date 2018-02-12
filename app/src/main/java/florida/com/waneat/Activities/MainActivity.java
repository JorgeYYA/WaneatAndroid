package florida.com.waneat.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import florida.com.waneat.Fragments.DialogFragment;
import florida.com.waneat.Fragments.ListProductFragment;
import florida.com.waneat.Fragments.OrderList;
import florida.com.waneat.Fragments.ProductFragment;
import florida.com.waneat.Fragments.ShowOrder;
import florida.com.waneat.Fragments.TarjetasFragment;
import florida.com.waneat.Fragments.UsuarioFragment;
import florida.com.waneat.Models.Order;
import florida.com.waneat.Models.Product;
import florida.com.waneat.Models.User;
import florida.com.waneat.R;
import florida.com.waneat.Services.UserService;


public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, DialogFragment.CestaInterface,
        TarjetasFragment.OnFragmentInteractionListener, UsuarioFragment.UserProfileListener,
        ListProductFragment.OnFragmentInteractionListener, ProductFragment.OnFragmentInteractionListener,
        OrderList.interfaceOrder{


    public ArrayList<Product> productosCesta = new ArrayList<Product>();
    public ArrayList<Product> productosLista = new ArrayList<Product>();
    ArrayList<Integer> imagen = new ArrayList<>();
    public Product productoSelected = new Product();
    static final int PICK_CONTACT_REQUEST = 1;  // The request code

    public User userLogged = new User();
    public FloatingActionButton fab;

    private FragmentManager fm;
    private UserService service;
    private TextView emailUsuarioLogged, nombreUsuario;


    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        setTitle("Waneat");

        this.service = new UserService(MainActivity.this);

        /*
        TODO:Cambiar al metodo de api
         */
        this.userLogged = this.service.getUserByEmail();


        fab = (FloatingActionButton) findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCart();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Poner el email en el navigationdrawer
        View headerView = navigationView.getHeaderView(0);
        emailUsuarioLogged = (TextView) headerView.findViewById(R.id.current_user);
        nombreUsuario = (TextView) headerView.findViewById(R.id.nombreUsuarioHeader);


        //metemos la info en el header
        nombreUsuario.setText(userLogged.getNombre()+ " "+userLogged.getApellidos());
        emailUsuarioLogged.setText(userLogged.getEmail());

        loadFragment();
        cargarProductosIniciales();

        fm = getSupportFragmentManager();
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if(getSupportFragmentManager().getBackStackEntryCount() == 0) finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void openCart(){
        fm = getSupportFragmentManager();
        DialogFragment dialog = new DialogFragment();
        dialog.show(fm, "Cart");
    }

    @Override
    public void removeProduct(int position) {
        if(this.productosCesta.get(position).getCantidad()<=1){
            this.productosCesta.remove(position);
        }else{
            int cantidadActual = this.productosCesta.get(position).getCantidad()-1;
            this.productosCesta.get(position).setCantidad(cantidadActual);
        }
    }

    @Override
    public void addProduct(int position) {
        int cantidadActual = this.productosCesta.get(position).getCantidad()+1;
        this.productosCesta.get(position).setCantidad(cantidadActual);
    }

    @Override
    public void addToCart(Product prod) {
        if(productosCesta.isEmpty()){
            this.productosCesta.add(prod);
        }else{
            for(int i=0;i<this.productosCesta.size();i++){
                int can = 0;
                if(productosCesta.get(i).getId() == prod.getId()) {
                    can = productosCesta.get(i).getCantidad();
                    Product newPro = productosCesta.get(i);
                    newPro.setCantidad(can + 1);
                    productosCesta.remove(productosCesta.get(i));
                    productosCesta.add(newPro);
                }else{
                    this.productosCesta.add(prod);
                }
            }
        }
    }

    private void cargarProductosIniciales(){
        //    public Product(int id, String nombre, String descripcion, float precio, ArrayList<Integer> imagen, String comentariosAdicionales, String categoria, int cantidad) {
        imagen.add(R.drawable.plato1);
        imagen.add(R.drawable.plato2);
        Product producto = new Product(0, "Spaguettis", "boloñesa, algo más", 2.0, imagen, "Sin salsa", "pasta", 0);
        //this.productosCesta.add(producto);
        this.productosLista.add(producto);
        Product producto2 = new Product(1, "Macarrones", "boloñesa, algo más", 3.0, imagen, "Con salsa", "pasta", 0);
        //this.productosCesta.add(producto2);
        this.productosLista.add(producto2);
        Product producto3 = new Product(2, "Lubina", "boloñesa, algo más", 5.0, imagen, "Con salsa", "pescado", 0);
        //this.productosCesta.add(producto3);
        this.productosLista.add(producto3);
        Product producto4 = new Product(3, "Tenera", "boloñesa, algo más", 5.0, imagen, "Con salsa", "carne", 0);
        //this.productosCesta.add(producto4);
        this.productosLista.add(producto4);
        Product producto5 = new Product(4, "Cereales", "boloñesa, algo más", 1.0, imagen, "Sin salsa", "Desayuno", 0);
        //this.productosCesta.add(producto5);
        this.productosLista.add(producto5);
    }

    @Override
    public ArrayList<Product> getProductosCesta() {
        return this.productosCesta;
    }

    @Override
    public double getCestaPrice() {
        double precioTotal = 0.0;
        for (Product pro: this.productosCesta) {
            precioTotal += pro.getPrecio()*pro.getCantidad();
        }
        return precioTotal;
    }

    @Override
    public User getUser() {
      return  this.userLogged;
    }

    private void loadFragment(){
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, new ListProductFragment());
        ft.addToBackStack("MY_FRAGMENT");
        ft.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public ArrayList<Product> getProductos() {
        return this.productosLista;
    }

    @Override
    public Product getProductoSelected() {
        return this.productoSelected;
    }

    @Override
    public void verProducto(int position) {
        this.productoSelected = this.productosLista.get(position);
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, new ProductFragment());
        ft.addToBackStack("MY_FRAGMENT");
        ft.commit();
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_miperfil) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, UsuarioFragment.newInstance()).addToBackStack("MY_FRAGMENT");
            ft.commit();
        }else if(id == R.id.inicio){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, ListProductFragment.newInstance()).addToBackStack("MY_FRAGMENT");
            ft.commit();
        }else if (id == R.id.nav_mispedidos) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, OrderList.newInstance(null,null)).addToBackStack("MY_FRAGMENT");
            ft.commit();
        } else if (id == R.id.nav_qr) {
            startActivityForResult(new Intent(MainActivity.this, QRActivity.class), PICK_CONTACT_REQUEST);

        } else if (id == R.id.nav_mistarjetas) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, TarjetasFragment.newInstance()).addToBackStack("MY_FRAGMENT");
            ft.commit();
        } else if (id == R.id.nav_logout) {
            this.service.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showFloatingActionButton() {
        fab.show();
    };

    public void hideFloatingActionButton() {
        fab.hide();
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_CONTACT_REQUEST) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = new Bundle();
                String result=data.getStringExtra("read_qr");
                bundle.putString("qr",result);
                ListProductFragment main = ListProductFragment.newInstance();
                main.setArguments(bundle);

                fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment, main, "MY_FRAGMENT");
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void interfaceOrder(Order order) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, ShowOrder.newInstance(order)).addToBackStack("MY_FRAGMENT");
        ft.commit();
    }
}
