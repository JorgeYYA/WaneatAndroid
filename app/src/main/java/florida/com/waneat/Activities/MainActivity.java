package florida.com.waneat.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import florida.com.waneat.Fragments.DialogFragment;
import florida.com.waneat.Fragments.InitialFragment;
import florida.com.waneat.Fragments.ListProductFragment;
import florida.com.waneat.Fragments.OrderList;
import florida.com.waneat.Fragments.ProductFragment;
import florida.com.waneat.Fragments.ShowOrder;
import florida.com.waneat.Fragments.TarjetasFragment;
import florida.com.waneat.Fragments.UsuarioFragment;
import florida.com.waneat.Models.Order;
import florida.com.waneat.Models.Product;
import florida.com.waneat.Models.Restaurant;
import florida.com.waneat.Models.User;
import florida.com.waneat.Preferences.Preferences;
import florida.com.waneat.R;
import florida.com.waneat.Services.RestaurantService;
import florida.com.waneat.Services.UserService;


public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, DialogFragment.CestaInterface,
        TarjetasFragment.OnFragmentInteractionListener, UsuarioFragment.UserProfileListener,
        ListProductFragment.OnFragmentInteractionListener, ProductFragment.OnFragmentInteractionListener,
        OrderList.InterfaceOrder, ShowOrder.OnFragmentInteractionListener, InitialFragment.OnFragmentInteractionListener{



    public ArrayList<Product> productosCesta = new ArrayList<>();
    public ArrayList<Product> productosLista = new ArrayList<>();

    public Product productoSelected = new Product();
    public Restaurant restauranteSelected = new Restaurant();

    static final int PICK_CONTACT_REQUEST = 1;

    public User userLogged = new User();
    public FloatingActionButton fab, fab_cat, fab_carne, fab_pescado, fab_pasta, fab_bebida;

    private FragmentManager fm;
    private UserService service;
    private TextView emailUsuarioLogged, nombreUsuario;
    Animation fabOpen, fabClose, rotateForward, rotateBackward;
    boolean isOpen = false;

    private Toolbar toolbar;

    RecyclerView mRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Waneat");

        this.service = new UserService(MainActivity.this);
        this.userLogged = Preferences.gsonToUser(MainActivity.this);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab_cat = (FloatingActionButton) findViewById(R.id.fab_cat);
        fab_carne = (FloatingActionButton) findViewById(R.id.fab_carne);
        fab_pescado = (FloatingActionButton) findViewById(R.id.fab_pescado);
        fab_pasta = (FloatingActionButton) findViewById(R.id.fab_pasta);
        fab_bebida = (FloatingActionButton) findViewById(R.id.fab_bebida);


        fabOpen = AnimationUtils.loadAnimation(this,R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this,R.anim.fab_close);

        rotateForward = AnimationUtils.loadAnimation(this,R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(this,R.anim.rotate_backward);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCart();

            }
        });

        fab_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();

            }
        });

        fab_carne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();

            }
        });

        fab_pescado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();

            }
        });

        fab_pasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();

            }
        });

        fab_bebida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();

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
        nombreUsuario.setText(userLogged.getNombre());
        emailUsuarioLogged.setText(userLogged.getEmail());

        loadFragment();

        fm = getSupportFragmentManager();
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if(getSupportFragmentManager().getBackStackEntryCount() == 0) finish();
            }
        });

    }

    private void animateFab()
    {
        if(isOpen)
        {
            fab_cat.startAnimation(rotateForward);
            fab_carne.startAnimation(fabClose);
            fab_pescado.startAnimation(fabClose);
            fab_pasta.startAnimation(fabClose);
            fab_bebida.startAnimation(fabClose);
            fab_carne.setClickable(false);
            fab_pescado.setClickable(false);
            fab_pasta.setClickable(false);
            fab_bebida.setClickable(false);
            isOpen = false;
        }
        else
        {
            fab_cat.startAnimation(rotateBackward);
            fab_carne.startAnimation(fabOpen);
            fab_pescado.startAnimation(fabOpen);
            fab_pasta.startAnimation(fabOpen);
            fab_bebida.startAnimation(fabOpen);
            fab_carne.setClickable(true);
            fab_pescado.setClickable(true);
            fab_pasta.setClickable(true);
            fab_bebida.setClickable(true);
            isOpen = true;
        }

    }

    @Override
    public void callQRActivity() {
        startActivityForResult(new Intent(MainActivity.this, QRActivity.class), PICK_CONTACT_REQUEST);
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
        boolean added = false;
        int can = 0;
        int id = 0;
        int pos = 0;
        if(productosCesta.isEmpty()){
            this.productosCesta.add(prod);
        }else{
            for(int i=0;i<this.productosCesta.size();i++){
                if(productosCesta.get(i).getId() == prod.getId()) {
                    added = true;
                    if(added){
                        id = productosCesta.get(i).getId();
                        pos = i;
                    }
                }
            }

            if(!added) {
                this.productosCesta.add(prod);
            }else{
                Product newPro = productosCesta.get(pos);
                newPro.setCantidad(newPro.getCantidad() + 1);
                productosCesta.remove(pos);
                productosCesta.add(newPro);
            }
            added = false;
        }
    }

    @Override
    public Restaurant getRestauranteSelected() {
        return this.restauranteSelected;
    }

    public static boolean verificaConexion(Context ctx) {
        boolean bConectado = false;
        ConnectivityManager connec = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // No sólo wifi, también GPRS
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        // este bucle debería no ser tan ñapa
        for (int i = 0; i < 2; i++) {
            // ¿Tenemos conexión? ponemos a true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                bConectado = true;
            }
        }
        return bConectado;
    }


    @Override
    public ArrayList<Product> getProductosCesta() {
        return this.productosCesta;
    }

    @Override
    public double getCestaPrice() {
        double precioTotal = 0.0;
        for (Product pro: this.productosCesta) {
            precioTotal += pro.getPriceProduct()*pro.getCantidad();
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
        ft.replace(R.id.fragment, new InitialFragment());
        ft.addToBackStack("MY_FRAGMENT");
        ft.commit();
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorSecondaryDarkWaneat));
    }

    @Override
    public List<Product> getProductos() {
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
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorSecondaryDarkWaneat));
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (id == R.id.nav_miperfil) {
            ft.replace(R.id.fragment, UsuarioFragment.newInstance()).addToBackStack("MY_FRAGMENT");
            toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorSecondaryDarkWaneat));
        }else if(id == R.id.inicio){
            ft.replace(R.id.fragment, ListProductFragment.newInstance()).addToBackStack("MY_FRAGMENT");
            toolbar.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));

        }else if (id == R.id.nav_mispedidos) {
            ft.replace(R.id.fragment, OrderList.newInstance(null,null)).addToBackStack("order_list");
            toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorSecondaryDarkWaneat));

        } else if (id == R.id.nav_qr) {
            startActivityForResult(new Intent(MainActivity.this, QRActivity.class), PICK_CONTACT_REQUEST);

        } else if (id == R.id.nav_mistarjetas) {
            ft.replace(R.id.fragment, TarjetasFragment.newInstance()).addToBackStack("MY_FRAGMENT");
            toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorSecondaryDarkWaneat));

        } else if (id == R.id.nav_logout) {
            this.service.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        } else if (id == R.id.nav_about) {

        }

        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showFloatingActionButton() {
        fab.show();
        fab_cat.show();
    };

    @Override
    public void hideFloatingActionButton() {
        fab.hide();
        fab_cat.hide();
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_CONTACT_REQUEST) {
            if (resultCode == RESULT_OK) {

                //Le pasamos si ha entrado en activity qr o no.
                Bundle bundle = new Bundle();
                boolean result=data.getBooleanExtra("read_qr", false);
                bundle.putBoolean("qr",result);
                ListProductFragment main = ListProductFragment.newInstance();
                main.setArguments(bundle);

                //CARGAMOS LOS RESTAURANTES
                this.restauranteSelected = Preferences.gsonToRestaurant(MainActivity.this);
                this.productosLista = this.restauranteSelected.getProducts();

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
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorSecondaryDarkWaneat));
        ft.commit();
    }
}
