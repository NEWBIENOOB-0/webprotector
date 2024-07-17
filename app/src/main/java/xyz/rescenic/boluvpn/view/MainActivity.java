package xyz.rescenic.boluvpn.view;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import xyz.rescenic.boluvpn.R;
import xyz.rescenic.boluvpn.adapter.ServerListRVAdapter;
import xyz.rescenic.boluvpn.interfaces.ChangeServer;
import xyz.rescenic.boluvpn.interfaces.NavItemClickListener;
import xyz.rescenic.boluvpn.model.Server;

import java.util.ArrayList;

import xyz.rescenic.boluvpn.Utils;


public class MainActivity extends AppCompatActivity implements NavItemClickListener {
    private FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    private Fragment fragment;
    private RecyclerView serverListRv;
    private ArrayList<Server> serverLists;
    private ServerListRVAdapter serverListRVAdapter;
    private DrawerLayout drawer;
    private ChangeServer changeServer;

    public static final String TAG = "CakeVPN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize all variable
        initializeAll();

        ImageButton menuRight = findViewById(R.id.navbar_right);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        menuRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDrawer();
            }
        });

        transaction.add(R.id.container, fragment);
        transaction.commit();

        // Server List recycler view initialize
        if (serverLists != null) {
            serverListRVAdapter = new ServerListRVAdapter(serverLists, this);
            serverListRv.setAdapter(serverListRVAdapter);
        }

    }

    /**
     * Initialize all object, listener etc
     */
    private void initializeAll() {
        drawer = findViewById(R.id.drawer_layout);

        fragment = new MainFragment();
        serverListRv = findViewById(R.id.serverListRv);
        serverListRv.setHasFixedSize(true);

        serverListRv.setLayoutManager(new LinearLayoutManager(this));

        serverLists = getServerList();
        changeServer = (ChangeServer) fragment;

    }

    /**
     * Close navigation drawer
     */
    public void closeDrawer(){
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            drawer.openDrawer(GravityCompat.END);
        }
    }

    /**
     * Generate server array list
     */
    private ArrayList<Server> getServerList() {
        ArrayList<Server> servers = new ArrayList<>();

        // United States
        servers.add(new Server("United States",
                Utils.getImgURL(R.drawable.usa_flag),
                "usa.ovpn",
                "",
                ""
        ));
        servers.add(new Server("United States 2",
                Utils.getImgURL(R.drawable.usa_flag),
                "usa2.ovpn",
                "",
                ""
        ));
        servers.add(new Server("United States 3",
                Utils.getImgURL(R.drawable.usa_flag),
                "usa3.ovpn",
                "",
                ""
        ));

        // Japan
        servers.add(new Server("Japan",
                Utils.getImgURL(R.drawable.japan),
                "japan.ovpn",
                "",
                ""
        ));
        servers.add(new Server("Japan 2",
                Utils.getImgURL(R.drawable.japan),
                "japan2.ovpn",
                "",
                ""
        ));
        servers.add(new Server("Japan 3",
                Utils.getImgURL(R.drawable.japan),
                "japan3.ovpn",
                "",
                ""
        ));
        servers.add(new Server("Japan 4",
                Utils.getImgURL(R.drawable.japan),
                "japan4.ovpn",
                "",
                ""
        ));
        servers.add(new Server("Japan 5",
                Utils.getImgURL(R.drawable.japan),
                "japan5.ovpn",
                "",
                ""
        ));
        servers.add(new Server("Japan 6",
                Utils.getImgURL(R.drawable.japan),
                "japan6.ovpn",
                "",
                ""
        ));

        // Russia
        servers.add(new Server("Russian",
                Utils.getImgURL(R.drawable.russian),
                "russian.ovpn",
                "",
                ""
        ));
        servers.add(new Server("Russian 2",
                Utils.getImgURL(R.drawable.russian),
                "russia2.ovpn",
                "",
                ""
        ));

        // Korea
        servers.add(new Server("Korea",
                Utils.getImgURL(R.drawable.korea),
                "korea.ovpn",
                "",
                ""
        ));
        servers.add(new Server("Korea 2",
                Utils.getImgURL(R.drawable.korea),
                "korea2.ovpn",
                "",
                ""
        ));
        servers.add(new Server("Korea 3",
                Utils.getImgURL(R.drawable.korea),
                "korea3.ovpn",
                "",
                ""
        ));

        return servers;
    }


    /**
     * On navigation item click, close drawer and change server
     * @param index: server index
     */
    @Override
    public void clickedItem(int index) {
        closeDrawer();
        changeServer.newServer(serverLists.get(index));
    }
}
