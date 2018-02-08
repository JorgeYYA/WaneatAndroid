package florida.com.waneat.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import florida.com.waneat.Fragments.DialogFragment;
import florida.com.waneat.Fragments.ProductFragment;
import florida.com.waneat.Fragments.UsuarioFragment;
import florida.com.waneat.Models.Product;
import florida.com.waneat.Models.User;
import florida.com.waneat.R;
import florida.com.waneat.Services.UserService;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DialogFragment.CestaInterface, UsuarioFragment.UserProfileListener {

    public ArrayList<Product> productosCesta = new ArrayList<Product>();
    public User userLogged = new User();


    private android.app.FragmentManager fm;
    private FragmentTransaction ft;
    private UserService service;
    private TextView emailUsuarioLogged, nombreUsuario;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        this.service = new UserService(MainActivity.this);

        /*
        TODO:Cambiar al metodo de api
         */
        this.userLogged = this.service.getUserByEmail();

        Log.d("JEJEJ", "onCreate: "+this.userLogged.getEmail());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        //Debug para poder probar ProductFragment
        //\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, ProductFragment.newInstance()).addToBackStack(null);
        ft.commit();
        ///\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
        //Debug para poder probar ProductFragment


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


        cargarProductosIniciales();
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
        FragmentManager fm = getSupportFragmentManager();
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

    private void cargarProductosIniciales(){
        //    public Product(int id, String nombre, String descripcion, float precio, ArrayList<Integer> imagen, String comentariosAdicionales, String categoria, int cantidad) {
        Product producto = new Product(0, "spaguettis", "boloñesa, algo más", 2.0, null, "Sin salsa", "pasta", 3);
        this.productosCesta.add(producto);
        Product producto2 = new Product(1, "macarrones", "boloñesa, algo más", 3.0, null, "Con salsa", "pasta", 2);
        this.productosCesta.add(producto2);
        Log.d("prueba", "cargarProductosIniciales: "+this.productosCesta.get(0).getNombre());
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_miperfil) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, UsuarioFragment.newInstance()).addToBackStack(null);
            ft.commit();
        } else if (id == R.id.nav_mispedidos) {

        } else if (id == R.id.nav_qr) {

        } else if (id == R.id.nav_mistarjetas) {


        } else if (id == R.id.nav_logout) {
            this.service.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
